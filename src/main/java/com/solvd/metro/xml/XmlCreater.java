package com.solvd.metro.xml;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.solvd.metro.Metro;
import com.solvd.metro.profession.Employee;
import com.solvd.metro.station.Station;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlCreater {

    public static void xmlWork(Metro metro) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element rootElement =
                    doc.createElementNS("https://solvd.metro.com", "Metros");
            doc.appendChild(rootElement);

            Element employeeElement = doc.createElement("Employees");
            rootElement.appendChild(employeeElement);
            int i = 0;
            for(Map.Entry<Integer, Employee> entry : metro.getEmployees().entrySet()){
                if (entry.getKey() != null) {
                    employeeElement.appendChild(getEmployee(doc,
                            i,
                            entry.getValue().getFirstName(),
                            entry.getValue().getLastName(),
                            entry.getValue().getProfession(),
                            entry.getKey()));
                }
                i++;
            }

            Element employeeStation = doc.createElement("Stations");
            rootElement.appendChild(employeeStation);

            i = 0;
            for(Station station : metro.getStations()){
                if (station.getName() != null) {
                    employeeStation.appendChild(getStation(doc, i, station.getName(), station.getDateBasis()));
                }
                i++;
            }

            rootElement.appendChild(getTimetable(doc, "Timetable",
                    metro.getTimeTable().getStartWorking(),
                    metro.getTimeTable().getEndWorking()));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("D:/Java/Courses/metro-maven/metro.xml"));

            transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("Создание XML файла закончено");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private static Node getEmployee(Document doc, Integer id, String firstName, String lastName, String profession, Integer passport) {
        Element metro = doc.createElement("Employee");

        metro.setAttribute("id", String.valueOf(id));

        metro.appendChild(getElements(doc, "firstName", firstName));

        metro.appendChild(getElements(doc, "lastName", lastName));
        metro.appendChild(getElements(doc, "profession", profession));
        metro.appendChild(getElements(doc, "passport", String.valueOf(passport)));
        return metro;
    }

    private static Node getStation(Document doc, Integer id, String name, LocalDate dateBasis) {
        Element metro = doc.createElement("Station");

        metro.setAttribute("id", String.valueOf(id));

        metro.appendChild(getElements(doc, "Name", name));

        metro.appendChild(getElements(doc, "dateBasis", String.valueOf(dateBasis)));
        return metro;
    }

    private static Node getTimetable(Document doc, String nameTag, LocalTime startWorking, LocalTime endWorking) {
        Element metro = doc.createElement(nameTag);

        metro.appendChild(getElements(doc, "StartWorking", String.valueOf(startWorking)));

        metro.appendChild(getElements(doc, "EndWorking", String.valueOf(endWorking)));
        return metro;
    }
}
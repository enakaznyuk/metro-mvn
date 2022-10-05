package com.solvd.metro.xml;

import com.solvd.metro.Metro;
import com.solvd.metro.impl.IPars;
import com.solvd.metro.profession.Cleaner;
import com.solvd.metro.profession.Employee;
import com.solvd.metro.profession.Engineer;
import com.solvd.metro.profession.Machinist;
import com.solvd.metro.station.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParser implements IPars {

    private static final Logger LOGGER = LogManager.getLogger(XmlParser.class);

    @Override
    public Metro parse(File file) {
        Metro result = new Metro();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            System.out.println("Root element: " + document.getDocumentElement().getNodeName());
            NodeList nodeListStation = document.getElementsByTagName("Station");
            NodeList nodeListEmployee = document.getElementsByTagName("Employee");

            List<Station> stationList = new ArrayList<Station>();
            Map<Integer, Employee> employeeMap = new HashMap<>();

            for (int i = 0; i < nodeListStation.getLength(); i++) {
                stationList.add(getStation(nodeListStation.item(i)));
            }

            for (Station station : stationList) {
                System.out.println(station.toString());
            }

            for (int i = 0; i < nodeListEmployee.getLength(); i++) {
                employeeMap.put(getKey(nodeListEmployee.item(i)), getEmployee(nodeListEmployee.item(i)));
            }

            for (Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {
                System.out.println(entry.toString());
            }

            result.setStations(stationList);
        }catch (ParserConfigurationException | SAXException | IOException e){
            LOGGER.error(e);
        }
        return result;
    }

    private static Station getStation(Node node) {
        Station station = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
                    station = new Station(
                    getTagValue("Name", element),
                    LocalDate.parse(getTagValue("dateBasis", element)));
        }
        return station;
    }

    private static Employee getEmployee(Node node) {
        Employee employee = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            if("Machinist".equals(getProfession(node))){
                Machinist machinist = new Machinist(
                        getTagValue("firstName", element),
                        getTagValue("lastName", element),
                        getTagValue("profession", element));
                employee = machinist;
            }else if("Engineer".equals(getProfession(node))){
                Engineer engineer = new Engineer(
                        getTagValue("firstName", element),
                        getTagValue("lastName", element),
                        getTagValue("profession", element));
                employee = engineer;
            }
            Cleaner cleaner = new Cleaner(
                    getTagValue("firstName", element),
                    getTagValue("lastName", element),
                    getTagValue("profession", element));
            employee = cleaner;
        }
        return employee;
    }

    private static Integer getKey(Node node) {
        Integer key = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            key = Integer.valueOf(getTagValue("passport", element));
        }
        return key;
    }

    private static String getProfession(Node node) {
        String prof = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            prof = getTagValue("passport", element);
        }
        return prof;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}

package com.solvd.metro.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorkWithFile {

    private static final Logger LOGGER = LogManager.getLogger(WorkWithFile.class);

    public static void readFile(String string){

        String fileForRead;
        try {
            File file = new File(string);
            fileForRead = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            String[] strings = fileForRead.toLowerCase().split("\\W");
            Set<String> fileData = new HashSet<>();

            for(String str : strings){
                fileData.add(str);
            }

            Map<String, Integer> map = new HashMap<>();

            for(String str : fileData){
                int i = StringUtils.countMatches(fileForRead.toLowerCase(), str);
                map.put(str, i);
            }

            Map.Entry[] a = map.entrySet().toArray(Map.Entry[]::new);
            File newFile = new File("D:/Java/Courses/metro-maven/src/main/resources/newFile.txt");
            Arrays.sort(a, (Comparator) (o1, o2) -> ((Map.Entry<String, Integer>) o2).getValue()
                    .compareTo(((Map.Entry<String, Integer>) o1).getValue()));
            for(Map.Entry<String, Integer> e : a){
                LOGGER.info(e.getKey() + " ; " + e.getValue());
                FileUtils.write(newFile, e.getKey() + ": " + e.getValue() + "\n", StandardCharsets.UTF_8, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

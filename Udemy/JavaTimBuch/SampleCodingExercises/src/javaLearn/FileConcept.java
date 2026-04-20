package javaLearn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileConcept {
    static String total;

    public static void main(String[] args) throws IOException {
String total = "";
        FileWriter csvWriter = new FileWriter("/Users//harish-10327/Downloads/returnfile.txt");
        BufferedReader csvReader = new BufferedReader(new FileReader("/Users//harish-10327/Downloads/workfile.txt"));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            System.out.println(data[0]);
           createCsvDataSimple(data[0], csvWriter);
        }
        csvReader.close();
        csvWriter.flush();
        csvWriter.close();



    }

    private static void createCsvDataSimple(String columnName, FileWriter csvWriter) throws IOException {


        if(!columnName.equalsIgnoreCase("harish")) {
            csvWriter.append("\"");
            csvWriter.append(columnName);
            csvWriter.append("\",");
            csvWriter.append("\n");
        }



            total = total + "," + "Temp.\"" +columnName+"\"";
            if(columnName.equalsIgnoreCase("harish")){
                csvWriter.append(total);
                total = "";
                csvWriter.append("\n");
                csvWriter.append("\n");
            }

        }

}

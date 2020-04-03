package app;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class App{
    static Scanner reader;
    static File newFile;
    public static void main(String[] args)throws Exception{
        reader = new Scanner(new File("file.txt"));
        newFile = new File("newfile.txt");
        FileWriter writer = new FileWriter(newFile, true);

        String copy = "";
        while(reader.hasNextLine()){
            String curr = reader.nextLine();
            String sub = "";

            if(curr.length() > 42)
                sub = curr.substring(39, 42);
            
                
            if(sub.matches("[0-9][0-9][0-9]")){

                String lineToCopy = curr.substring(0, 36);

                if(lineToCopy.trim().isEmpty()){
                    curr = copy + curr.substring(36);
                }else{
                   copy = lineToCopy; 
                }
            }

            writer.write(curr + System.lineSeparator());
        }
    }


}
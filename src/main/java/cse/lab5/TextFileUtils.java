package cse.lab5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;


public final class TextFileUtils
{
    public static String getText_FromInputFile(String fileLocation, String fileName)
    {
        String filePath = fileLocation + "/" + fileName;
        
        StringWriter writer = new StringWriter();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            
            while (reader.ready())
                writer.write(reader.readLine() + "\n");
            
            return writer.toString();
        }
        
        catch (IOException exception) {
            throw new IllegalStateException("I/O exception occured: " + exception.getMessage());
        }
    }
    
    public static void printTextToScreen_AndWriteToOutputFile(String text,
        String fileLocation, String fileName)
    {
        System.out.println(text);
        
        String filePath = fileLocation + "/" + fileName;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(text);
        }
        
        catch (IOException exception) {
            throw new IllegalStateException("I/O exception occured: " + exception.getMessage());
        }
    }
    
    // convert the encrypted byte[] format into a Base64-format String
    public static String bytesToBase64String(byte[] bytes)
    {
        return Base64.getEncoder().encodeToString(bytes);
    }
}

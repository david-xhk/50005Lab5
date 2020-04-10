package cse.lab5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import javax.crypto.*;
import java.util.Base64;


public class DesSolution extends AbstractCryptoSolution
{
    public static final String RESOURCES = "src/main/resources";
    public static final String RESULTS = "src/main/results/part1";
    public static final String[] FILE_NAMES = {"shorttext.txt", "longtext.txt"};
    
    public static void main(String[] args)
        throws IOException
    {
        //question1_GetText_FromInputFiles();
        //question2_EncryptText_FromInputFiles_ThenConvert_EncryptedBytes_ToString();
        //question3_EncryptText_FromInputFiles_ThenConvert_EncryptedBytes_ToBase64Format();
        //question5_EncryptText_FromInputFiles_ThenDecrypt_AndConvert_DecryptedBytes_ToString();
        //question6_EncryptText_FromInputFiles_ThenGetLengthsOf_EncryptedBytes();
    }
    
    // print the content of the input file
    public static void question1_GetText_FromInputFiles()
        throws IOException
    {
        for (String fileName : FILE_NAMES) {
            StringWriter writer = new StringWriter();
            
            String text = getText_FromInputFile(RESOURCES, fileName);
            
            writer.write("Original text: " + "\n\n");
            
            writer.write(text + "\n");
            
            String result = writer.toString();
            
            printTextToScreen_AndWriteToOutputFile(result,
                RESULTS, "Question1_OriginalText_" + fileName);
        }
    }
    
    // print the encrypted bytes of the input file as String
    public static void question2_EncryptText_FromInputFiles_ThenConvert_EncryptedBytes_ToString()
        throws IOException
    {
        for (String fileName : FILE_NAMES) {
            StringWriter writer = new StringWriter();
            
            byte[] encryptedBytes = getText_FromInputFile_AndEncrypt(
                RESOURCES, fileName,
                DES, ECB_PKCS5_PADDING);
            
            writer.write("Encrypted bytes as String: " + "\n\n");
            
            writer.write(new String(encryptedBytes) + "\n");
            
            String result = writer.toString();
            
            printTextToScreen_AndWriteToOutputFile(result,
                RESULTS, "Question2_EncryptedBytes_AsString_" + fileName);
        }
    }
    
    // print the encrypted bytes of the input file as Base64-format String
    public static void question3_EncryptText_FromInputFiles_ThenConvert_EncryptedBytes_ToBase64Format()
        throws IOException
    {
        for (String fileName : FILE_NAMES) {
            StringWriter writer = new StringWriter();
            
            byte[] encryptedBytes = getText_FromInputFile_AndEncrypt(
                RESOURCES, fileName,
                DES, ECB_PKCS5_PADDING);
            
            writer.write("Encrypted bytes in Base64-format: " + "\n\n");
            
            writer.write(bytesToBase64String(encryptedBytes) + "\n");
            
            String result = writer.toString();
            
            printTextToScreen_AndWriteToOutputFile(result,
                RESULTS, "Question3_EncryptedBytes_InBase64Format_" + fileName);
        }
    }
    
    // print the decrypted bytes of the input file and compare it with original text
    public static void question5_EncryptText_FromInputFiles_ThenDecrypt_AndConvert_DecryptedBytes_ToString()
        throws IOException
    {
        for (String fileName : FILE_NAMES) {
            StringWriter writer = new StringWriter();
            
            byte[] decryptedBytes = getText_FromInputFile_AndEncrypt_ThenDecrypt(
                RESOURCES, fileName,
                DES, ECB_PKCS5_PADDING);
            
            writer.write("Decrypted bytes as String: " + "\n\n");
            
            writer.write(new String(decryptedBytes) + "\n");
            
            String result = writer.toString();
            
            printTextToScreen_AndWriteToOutputFile(result,
                RESULTS, "Question5_DecryptedBytes_AsString_" + fileName);
        }
    }
    
    // print the length of the encrypted bytes for each of the input files
    public static void question6_EncryptText_FromInputFiles_ThenGetLengthsOf_EncryptedBytes()
    {
        for (String fileName : FILE_NAMES) {
            StringWriter writer = new StringWriter();
            
            String text = getText_FromInputFile(RESOURCES, fileName);
            
            writer.write(fileName + " text length: " + text.length() + "\n");
            
            byte[] textBytes = text.getBytes();
            
            writer.write(fileName + " number of bytes: " + textBytes.length + "\n");
            
            byte[] encryptedBytes = getText_FromInputFile_AndEncrypt(
                RESOURCES, fileName,
                DES, ECB_PKCS5_PADDING);
            
            writer.write(fileName + " number of encrypted bytes: " + encryptedBytes.length + "\n");
            
            String result = writer.toString();
            
            printTextToScreen_AndWriteToOutputFile(result,
                RESULTS, "Question6_NumberOfBytes_AndEncryptedBytes_" + fileName);
        }
    }
    
    // get and encrypt the content of the input file
    public static byte[] getText_FromInputFile_AndEncrypt(
        String fileLocation, String fileName,
        String algorithm, String config)
    {
        String text = getText_FromInputFile(fileLocation, fileName);
        
        byte[] textBytes = text.getBytes();
            
        SecretKey key = generateKey(algorithm);
        
        return encryptBytes(textBytes, algorithm, config, key);
    }
    
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
    
    // get, encrypt, and then decrypt the content of the input file
    public static byte[] getText_FromInputFile_AndEncrypt_ThenDecrypt(
        String fileLocation, String fileName,
        String algorithm, String config)
    {
        String text = getText_FromInputFile(fileLocation, fileName);
        
        byte[] textBytes = text.getBytes();
            
        SecretKey key = generateKey(algorithm);
        
        byte[] encryptedBytes = encryptBytes(textBytes, algorithm, config, key);
        
        return decryptBytes(encryptedBytes, algorithm, config, key);
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
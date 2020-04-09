package cse.lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.crypto.*;
import java.util.Base64;


public class DesSolution extends AbstractDesSolution
{
    public static final String[] FILE_NAMES = {"shorttext.txt", "longtext.txt"};
    public static final String RESOURCES_LOCATION = "src/main/resources";
    
    public static void main(String[] args)
        throws IOException
    {
        question1_PrintContent_OfInputFile_ToScreen();
        //question2_EncryptContent_OfInputFile_AndPrintCipherBytes_ToScreen();
        //question3_EncryptContent_OfInputFile_ThenBase64Encode_AndPrintCipherBytes_ToScreen();
        //question5_EncryptContent_OfInputFile_ThenDecrypt_AndPrintCipherBytes_ToScreen();
        //question6_EncryptContent_OfInputFiles_AndPrint_LengthsOfCipherBytes_ToScreen();
    }
    
    // print the content of the input file
    public static void question1_PrintContent_OfInputFile_ToScreen()
        throws IOException
    {
        String fileName = FILE_NAMES[0];
        
        String content = (String) getContent_OfInputFile(fileName);
        
        System.out.println("Original content: ");
        System.out.println(content);
    }
    
    // print the encrypted bytes of the input file as String
    public static void question2_EncryptContent_OfInputFile_AndPrintCipherBytes_ToScreen()
        throws IOException
    {
        String fileName = FILE_NAMES[0];
        
        byte[] cipherBytes = getAnd_EncryptContent_OfInputFile(fileName, ECB_CONFIG);
        
        System.out.println(new String(cipherBytes));
    }
    
    // print the encrypted bytes of the input file as Base64-format String
    public static void question3_EncryptContent_OfInputFile_ThenBase64Encode_AndPrintCipherBytes_ToScreen()
        throws IOException
    {
        String fileName = FILE_NAMES[0];
        
        byte[] cipherBytes = getAnd_EncryptContent_OfInputFile(fileName, ECB_CONFIG);
        
        System.out.println(bytesToBase64String(cipherBytes));
    }
    
    // print the decrypted bytes of the input file and compare it with original text
    public static void question5_EncryptContent_OfInputFile_ThenDecrypt_AndPrintCipherBytes_ToScreen()
        throws IOException
    {
        String fileName = FILE_NAMES[0];
        
        byte[] decryptedBytes = getAnd_EncryptAnd_DecryptContent_OfInputFile(fileName, ECB_CONFIG);
        
        System.out.println(new String(decryptedBytes));
    }
    
    // print the length of the encrypted bytes for each of the input files
    public static void question6_EncryptContent_OfInputFiles_AndPrint_LengthsOfCipherBytes_ToScreen()
    {
        for (String fileName : FILE_NAMES) {
            String content = getContent_OfInputFile(fileName);
            
            System.out.println(fileName + " content size: " + content.length());
            
            byte[] inputBytes = content.getBytes();
            
            System.out.println(fileName + " input length: " + inputBytes.length);
            
            byte[] cipherBytes = getAnd_EncryptContent_OfInputFile(fileName, ECB_CONFIG);
        
            System.out.println(fileName + " cipher length: " + cipherBytes.length);
        }
    }
    
    // get the content of the input file
    public static String getContent_OfInputFile(String fileName)
    {
        String filePath = RESOURCES_LOCATION + "/" + fileName;
        StringBuilder data = new StringBuilder();
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            
            while (bufferedReader.ready())
                data.append(bufferedReader.readLine() + "\n");
            
            return data.toString();
        }
        
        catch (IOException exception) {
            throw new IllegalStateException("I/O exception occured");
        }
    }
    
    // get and encrypt the content of the input file
    public static byte[] getAnd_EncryptContent_OfInputFile(String fileName, String config)
    {
        String content = getContent_OfInputFile(fileName);
        
        byte[] inputBytes = content.getBytes();
            
        SecretKey key = generateKey();
        
        return encryptBytes(inputBytes, key, config);
    }
    
    // get, encrypt, and then decrypt the content of the input file
    public static byte[] getAnd_EncryptAnd_DecryptContent_OfInputFile(String fileName, String config)
    {
        String content = getContent_OfInputFile(fileName);
        
        byte[] inputBytes = content.getBytes();
            
        SecretKey key = generateKey();
        
        byte[] cipherBytes = encryptBytes(inputBytes, key, config);
        
        return decryptBytes(cipherBytes, key, config);
    }
    
    // convert the encrypted byte[] format into a Base64-format String
    public static String bytesToBase64String(byte[] bytes)
    {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
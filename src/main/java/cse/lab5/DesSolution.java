package cse.lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import java.util.Base64;


public class DesSolution
{
    private static final String ALGORITHM = "DES";
    private static final String CONFIG = "/ECB/PKCS5Padding";
    private static final String RESOURCES_LOCATION = "src/main/resources";
    
    public static void main(String[] args)
        throws IOException
    {
        //question1_PrintContent_OfInputFile_ToScreen();
        //question2_EncryptContent_OfInputFile_AndPrintCipherBytes_ToScreen();
        //question3_EncryptContent_OfInputFile_ThenBase64Encode_AndPrintCipherBytes_ToScreen();
        //question5_EncryptContent_OfInputFile_ThenDecrypt_AndPrintCipherBytes_ToScreen();
        //question6_EncryptContent_OfInputFiles_AndPrint_LengthsOfCipherBytes_ToScreen();
    }
    
    // print the content of the input file
    public static void question1_PrintContent_OfInputFile_ToScreen()
        throws IOException
    {
        String fileName = "shorttext.txt";
        //String fileName = "longtext.txt";
        
        String content = getContent_OfInputFile(fileName);
        
        System.out.println("Original content: ");
        System.out.println(content);
    }
    
    // print the encrypted bytes of the input file as String
    public static void question2_EncryptContent_OfInputFile_AndPrintCipherBytes_ToScreen()
        throws IOException
    {
        String fileName = "shorttext.txt";
        //String fileName = "longtext.txt";
        
        byte[] cipherBytes = getAnd_EncryptContent_OfInputFile(fileName);
        
        System.out.println(new String(cipherBytes));
    }
    
    // print the encrypted bytes of the input file as Base64-format String
    public static void question3_EncryptContent_OfInputFile_ThenBase64Encode_AndPrintCipherBytes_ToScreen()
        throws IOException
    {
        String fileName = "shorttext.txt";
        //String fileName = "longtext.txt";
        
        byte[] cipherBytes = getAnd_EncryptContent_OfInputFile(fileName);
        
        System.out.println(bytesToBase64String(cipherBytes));
    }
    
    // print the decrypted bytes of the input file and compare it with original text
    public static void question5_EncryptContent_OfInputFile_ThenDecrypt_AndPrintCipherBytes_ToScreen()
        throws IOException
    {
        String fileName = "shorttext.txt";
        //String fileName = "longtext.txt";
        
        byte[] decryptedBytes = getAnd_EncryptAnd_DecryptContent_OfInputFile(fileName);
        
        System.out.println(new String(decryptedBytes));
    }
    
    // print the length of the encrypted bytes for each of the input files
    public static void question6_EncryptContent_OfInputFiles_AndPrint_LengthsOfCipherBytes_ToScreen()
    {
        String[] fileNames = {"shorttext.txt", "longtext.txt"};
        
        for (String fileName : fileNames) {
            String content = getContent_OfInputFile(fileName);
            
            System.out.println(fileName + " content size: " + content.length());
            
            byte[] inputBytes = content.getBytes();
            
            System.out.println(fileName + " input length: " + inputBytes.length);
            
            byte[] cipherBytes = getAnd_EncryptContent_OfInputFile(fileName);
        
            System.out.println(fileName + " cipher length: " + cipherBytes.length);
        }
    }
    
    // get the content of the input file as String
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
    public static byte[] getAnd_EncryptContent_OfInputFile(String fileName)
    {
        String content = getContent_OfInputFile(fileName);
        
        byte[] inputBytes = content.getBytes();
            
        SecretKey key = generateKey();
        
        return encryptBytes(inputBytes, key);
    }
    
    // get, encrypt, and then decrypt the content of the input file
    public static byte[] getAnd_EncryptAnd_DecryptContent_OfInputFile(String fileName)
    {
        String content = getContent_OfInputFile(fileName);
        
        byte[] inputBytes = content.getBytes();
            
        SecretKey key = generateKey();
        
        byte[] cipherBytes = encryptBytes(inputBytes, key);
        
        return decryptBytes(cipherBytes, key);
    }
    
    // encrypt bytes using given key
    public static byte[] encryptBytes(byte[] bytes, SecretKey key)
    {
        Cipher cipher = getCipher();
        
        initializeCipher(cipher, key, Cipher.ENCRYPT_MODE);
        
        return doFinal(cipher, bytes);
    }
    
    // decrypt bytes using given key
    public static byte[] decryptBytes(byte[] bytes, SecretKey key)
    {
        Cipher cipher = getCipher();
        
        initializeCipher(cipher, key, Cipher.DECRYPT_MODE);
        
        return doFinal(cipher, bytes);
    }
    
    // generate secret key for DES algorithm
    public static SecretKey generateKey()
    {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            
            return keyGenerator.generateKey();
        }
        
        catch (NoSuchAlgorithmException exception) {
            throw new IllegalArgumentException("algorithm invalid");
        }
    }
    
    // create cipher object
    public static Cipher getCipher()
    {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM + CONFIG);
            
            return cipher;
        }
        
        catch (NoSuchAlgorithmException exception) {
            throw new IllegalArgumentException("algorithm invalid");
        }
        
        catch (NoSuchPaddingException exception) {
            throw new IllegalArgumentException("padding invalid");
        }
    }
    
    // initialize the cipher with the given key and chosen encryption mode
    public static void initializeCipher(Cipher cipher, SecretKey key, int mode)
    {
        try {
            cipher.init(mode, key);
        }
        
        catch (InvalidKeyException exception) {
            throw new IllegalArgumentException("key invalid");
        }
    }
    
    // do the actual encryption
    public static byte[] doFinal(Cipher cipher, byte[] bytes)
    {
        try {
            return cipher.doFinal(bytes);
        }
        
        catch (IllegalBlockSizeException exception) {
            throw new IllegalArgumentException("block size invalid");
        }
        
        catch (BadPaddingException exception) {
            throw new IllegalArgumentException("padding invalid");
        }
    }
    
    // convert the encrypted byte[] format into a Base64-format String
    public static String bytesToBase64String(byte[] bytes)
    {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
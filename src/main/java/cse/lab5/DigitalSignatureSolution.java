package cse.lab5;

import static cse.lab5.Constants.*;
import static cse.lab5.TextFileUtils.*;
import java.io.IOException;
import java.io.StringWriter;
import java.security.Key;
import java.security.KeyPair;
import javax.crypto.Cipher;


public class DigitalSignatureSolution extends AbstractSignedCryptoSolution
{
    public static void main(String[] args)
        throws IOException
    {
        //question1_DigestText_FromInputFiles_ThenGetLengthsOf_MessageDigests();
        //question2_DigestText_FromInputFiles_AndEncrypt_ThenGetLengthsOf_MessageDigests();
    }
    
    // print message digest for each of the input files in Base64 format
    public static void question1_DigestText_FromInputFiles_ThenGetLengthsOf_MessageDigests()
        throws IOException
    {
        for (String fileName : TEXT_FILE_NAMES) {
            StringWriter writer = new StringWriter();
            
            String text = getText_FromInputFile(RESOURCES, fileName);
            
            writer.write("number of characters in " + fileName + ": " + text.length() + "\n");
            
            byte[] textBytes = text.getBytes();
            
            writer.write("number of bytes in " + fileName + ": " + textBytes.length + "\n");
            
            byte[] digestBytes = digestMessage(textBytes, MD5);
            
            writer.write("number of bytes in " + fileName + " digest: " + digestBytes.length + "\n");
            
            writer.write(fileName + " digest in Base64 format: " + bytesToBase64String(digestBytes) + "\n");
            
            String result = writer.toString();
            
            printTextToScreen_AndWriteToOutputFile(result,
                PART3_RESULTS, "Question1_LengthOf_MessageDigest_" + fileName);
        }
    }
    
    // print message digest for each of the input files in Base64 format after encryption and decryption
    public static void question2_DigestText_FromInputFiles_AndEncrypt_ThenGetLengthsOf_MessageDigests()
        throws IOException
    {
        for (String fileName : TEXT_FILE_NAMES) {
            StringWriter writer = new StringWriter();
            
            String text = getText_FromInputFile(RESOURCES, fileName);
            
            writer.write("number of characters in " + fileName + ": " + text.length() + "\n");
            
            byte[] textBytes = text.getBytes();
            
            writer.write("number of bytes in " + fileName + ": " + textBytes.length + "\n");
            
            byte[] digestBytes = digestMessage(textBytes, MD5);
            
            writer.write(fileName + " digest in Base64 format: " + bytesToBase64String(digestBytes) + "\n");
            
            byte[] encryptedBytes = encryptMessageDigest(
                digestBytes,
                RSA, ECB_PKCS1_PADDING, 1024);
            
            writer.write("number of bytes in encrypted " + fileName + " digest: " + encryptedBytes.length + "\n");
            
            writer.write("encrypted " + fileName + " digest in Base64 format: " + bytesToBase64String(encryptedBytes) + "\n");
            
            byte[] decryptedBytes = encryptMessageDigest_ThenDecrypt(
                digestBytes,
                RSA, ECB_PKCS1_PADDING, 1024);
            
            writer.write("decrypted " + fileName + " digest in Base64 format: " + bytesToBase64String(decryptedBytes) + "\n");
            
            String result = writer.toString();
            
            printTextToScreen_AndWriteToOutputFile(result,
                PART3_RESULTS, "Question2_LengthOf_EncryptedMessageDigest_" + fileName);
        }
    }
    
    // encrypt the message digest
    public static byte[] encryptMessageDigest(
        byte[] digestBytes,
        String cipherAlgorithm, String config, int keySize)
    {
        KeyPair keyPair = generateKeyPair(cipherAlgorithm, keySize);
        
        Key privateKey = keyPair.getPrivate();
        
        return encryptBytes(digestBytes, cipherAlgorithm, config, privateKey);
    }
    
    // encrypt and then decrypt the message digest
    public static byte[] encryptMessageDigest_ThenDecrypt(
        byte[] digestBytes,
        String cipherAlgorithm, String config, int keySize)
    {
        KeyPair keyPair = generateKeyPair(cipherAlgorithm, keySize);
        
        Key privateKey = keyPair.getPrivate();
        
        Key publicKey = keyPair.getPublic();
        
        byte[] encryptedBytes = encryptBytes(digestBytes, cipherAlgorithm, config, privateKey);
        
        initializeCipher(Cipher.DECRYPT_MODE, publicKey);
        
        return decryptBytes(encryptedBytes);
    }
}

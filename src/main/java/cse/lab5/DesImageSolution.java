package cse.lab5;

import static cse.lab5.ImageFileUtils.*;
import static cse.lab5.Constants.*;
import java.nio.ByteBuffer;
import java.security.Key;
import javax.crypto.Cipher;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class DesImageSolution extends AbstractCryptoSolution
{
    public static void main(String[] args)
        throws IOException
    {
        //question1_EncryptImage_FromInputFiles_UsingDES_WithECB();
        //question3_EncryptImage_FromInputFiles_UsingDES_WithCBC();
        //question4_EncryptImage_FromInputFiles_BottomUp_UsingDES_WithCBC();
    }
    
    public static void question1_EncryptImage_FromInputFiles_UsingDES_WithECB()
    {
        for (String fileName : IMAGE_FILE_NAMES)
            getImage_FromInputFile_AndEncrypt_ThenWriteImage_ToOutputFile(
                RESOURCES, fileName,
                PART2_RESULTS, "Question1_ECB_" + fileName,
                DES, ECB_PKCS5_PADDING);
    }
    
    public static void question3_EncryptImage_FromInputFiles_UsingDES_WithCBC()
    {
        for (String fileName : IMAGE_FILE_NAMES)
            getImage_FromInputFile_AndEncrypt_ThenWriteImage_ToOutputFile(
                RESOURCES, fileName,
                PART2_RESULTS, "Question3_CBC_" + fileName,
                DES, CBC_PKCS5_PADDING);
    }
    
    public static void question4_EncryptImage_FromInputFiles_BottomUp_UsingDES_WithCBC()
    {
        for (String fileName : IMAGE_FILE_NAMES)
            getImage_FromInputFile_AndEncrypt_BottomUp_ThenWriteImage_ToOutputFile(
                RESOURCES, fileName,
                PART2_RESULTS, "Question4_CBC_BottomUp_" + fileName,
                DES, CBC_PKCS5_PADDING);
    }
    
    public static void getImage_FromInputFile_AndEncrypt_ThenWriteImage_ToOutputFile(
        String fileLocation, String fileName, 
        String outFileLocation, String outFileName,
        String algorithm, String config)
    {
        BufferedImage image = getImage_FromInputFile(fileLocation, fileName);
        
        int[][] intArray = imageToIntArray(image);
        
        int[][] encryptedIntArray = encryptIntArray(intArray, algorithm, config);
        
        BufferedImage encryptedImage = intArrayToImage(encryptedIntArray);
        
        writeImage_ToOutputFile(encryptedImage, outFileLocation, outFileName);
    }
    
    public static void getImage_FromInputFile_AndEncrypt_BottomUp_ThenWriteImage_ToOutputFile(
        String fileLocation, String fileName, 
        String outFileLocation, String outFileName,
        String algorithm, String config)
    {
        BufferedImage image = getImage_FromInputFile(fileLocation, fileName);
        
        int[][] intArray = imageToIntArray(image);
        
        int[][] encryptedIntArray = encryptIntArray_BottomUp(intArray, algorithm, config);
        
        BufferedImage encryptedImage = intArrayToImage(encryptedIntArray);
        
        writeImage_ToOutputFile(encryptedImage, outFileLocation, outFileName);
    }
    
    public static int[][] encryptIntArray(int[][] intArray, String algorithm, String config)
    {
        Key key = generateKey(algorithm);
        
        createCipher(algorithm, config);
        
        initializeCipher(Cipher.ENCRYPT_MODE, key);
        
        int rows = intArray.length;
        
        int cols = intArray[0].length;
        
        int[][] encryptedIntArray = new int[rows][cols];
        
        for (int idx = 0; idx < rows; idx++) {
            
            ByteBuffer bytes = ByteBuffer.allocate(4 * cols);
            
            for (int idy = 0; idy < cols; idy++)
                bytes.putInt(4 * idy, intArray[idx][idy]);
            
            ByteBuffer encryptedBytes = ByteBuffer.wrap(encryptBytes(bytes.array()));
            
            for (int idy = 0; idy < cols; idy++)
                encryptedIntArray[idx][idy] = encryptedBytes.getInt(4 * idy);
        }
        
        return encryptedIntArray;
    }
    
    public static int[][] encryptIntArray_BottomUp(int[][] intArray, String algorithm, String config)
    {
        Key key = generateKey(algorithm);
        
        createCipher(algorithm, config);
        
        initializeCipher(Cipher.ENCRYPT_MODE, key);
        
        int rows = intArray.length;
        
        int cols = intArray[0].length;
        
        int[][] encryptedIntArray = new int[rows][cols];
        
        for (int idx = 0; idx < rows; idx++) {
            
            ByteBuffer bytes = ByteBuffer.allocate(4 * cols);
            
            for (int idy = 0; idy < cols; idy++)
                bytes.putInt(4 * idy, intArray[idx][cols-1-idy]);
            
            ByteBuffer encryptedBytes = ByteBuffer.wrap(encryptBytes(bytes.array()));
            
            for (int idy = 0; idy < cols; idy++)
                encryptedIntArray[idx][idy] = encryptedBytes.getInt(4 * (cols-1-idy));
        }
        
        return encryptedIntArray;
    }
}
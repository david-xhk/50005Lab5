package cse.lab5;

import javax.imageio.ImageIO;
import java.io.*;
import java.nio.ByteBuffer;
import java.awt.image.BufferedImage;
import javax.crypto.*;


public class DesImageSolution extends AbstractCryptoSolution
{
    public static final String RESOURCES = "src/main/resources";
    public static final String RESULTS = "src/main/results/part2";
    public static final String[] FILE_NAMES = {"SUTD.bmp", "triangle.bmp"};
    
    public static void main(String[] args)
        throws IOException
    {
        //question1_EncryptImage_FromInputFiles_UsingDES_WithECB();
        //question3_EncryptImage_FromInputFiles_UsingDES_WithCBC();
        //question4_EncryptImage_FromInputFiles_BottomUp_UsingDES_WithCBC();
    }
    
    public static void question1_EncryptImage_FromInputFiles_UsingDES_WithECB()
    {
        for (String fileName : FILE_NAMES)
            getImage_FromInputFile_AndEncrypt_ThenWriteImage_ToOutputFile(
                RESOURCES, fileName,
                RESULTS, "Question1_ECB_" + fileName,
                DES, ECB_CONFIG);
    }
    
    public static void question3_EncryptImage_FromInputFiles_UsingDES_WithCBC()
    {
        for (String fileName : FILE_NAMES)
            getImage_FromInputFile_AndEncrypt_ThenWriteImage_ToOutputFile(
                RESOURCES, fileName,
                RESULTS, "Question3_CBC_" + fileName,
                DES, CBC_CONFIG);
    }
    
    public static void question4_EncryptImage_FromInputFiles_BottomUp_UsingDES_WithCBC()
    {
        for (String fileName : FILE_NAMES)
            getImage_FromInputFile_AndEncrypt_BottomUp_ThenWriteImage_ToOutputFile(
                RESOURCES, fileName,
                RESULTS, "Question4_CBC_BottomUp_" + fileName,
                DES, CBC_CONFIG);
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
        SecretKey key = generateKey(algorithm);
        
        int rows = intArray.length;
        
        int cols = intArray[0].length;
        
        int[][] encryptedIntArray = new int[rows][cols];
        
        for (int idx = 0; idx < rows; idx++) {
            
            ByteBuffer bytes = ByteBuffer.allocate(4 * cols);
            
            for (int idy = 0; idy < cols; idy++)
                bytes.putInt(4 * idy, intArray[idx][idy]);
            
            ByteBuffer encryptedBytes = ByteBuffer.wrap(encryptBytes(bytes.array(), algorithm, config, key));
            
            for (int idy = 0; idy < cols; idy++)
                encryptedIntArray[idx][idy] = encryptedBytes.getInt(4 * idy);
        }
        
        return encryptedIntArray;
    }
    
    public static int[][] encryptIntArray_BottomUp(int[][] intArray, String algorithm, String config)
    {
        int rows = intArray.length;
        
        int cols = intArray[0].length;
        
        int[][] encryptedIntArray = new int[rows][cols];
        
        SecretKey key = generateKey(algorithm);
        
        for (int idx = 0; idx < rows; idx++) {
            
            ByteBuffer bytes = ByteBuffer.allocate(4 * cols);
            
            for (int idy = 0; idy < cols; idy++)
                bytes.putInt(4 * idy, intArray[idx][cols-1-idy]);
            
            ByteBuffer encryptedBytes = ByteBuffer.wrap(encryptBytes(bytes.array(), algorithm, config, key));
            
            for (int idy = 0; idy < cols; idy++)
                encryptedIntArray[idx][idy] = encryptedBytes.getInt(4 * (cols-1-idy));
        }
        
        return encryptedIntArray;
    }
    
    public static int[][] imageToIntArray(BufferedImage image)
    {
        int image_width = image.getWidth();
        int image_length = image.getHeight();
        
        int[][] intArray = new int[image_width][image_length];
        
        for (int idx = 0; idx < image_width; idx++) {
            
            for(int idy = 0; idy < image_length; idy++)
                intArray[idx][idy] = image.getRGB(idx, idy);
        }
        
        return intArray;
    }
    
    public static BufferedImage intArrayToImage(int[][] intArray)
    {
        int image_width = intArray.length;
        int image_length = intArray[0].length;
        
        BufferedImage image = new BufferedImage(image_width, image_length, BufferedImage.TYPE_3BYTE_BGR);
        
        for (int idx = 0; idx < image_width; idx++) {
            
            for (int idy = 0; idy < image_length; idy++)
                image.setRGB(idx, idy, intArray[idx][idy]);
        }
        
        return image;
    }
    
    public static BufferedImage getImage_FromInputFile(String fileLocation, String fileName)
    {
        String filePath = fileLocation + "/" + fileName;
        
        try {
            return ImageIO.read(new File(filePath));
        }
        
        catch (IOException exception) {
            throw new IllegalStateException("I/O exception occured");
        }
    }
    
    public static void writeImage_ToOutputFile(BufferedImage image, String outFileLocation, String outFileName)
    {
        String filePath = outFileLocation + "/" + outFileName;
        
        try {
            ImageIO.write(image, "BMP", new File(filePath));
        }
        
        catch (IOException exception) {
            throw new IllegalStateException("I/O exception occured");
        }
    }
}
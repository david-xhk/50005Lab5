package cse.lab5;

import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.crypto.*;


public class DesImageSolution extends AbstractDesSolution
{
    public static final String[] FILE_NAMES = {"SUTD.bmp", "triangle.bmp"};
    public static final String RESOURCES_LOCATION = "src/main/resources";
    
    public static void main(String[] args)
        throws IOException
    {
        for (String fileName : FILE_NAMES) {
            getAnd_EncryptContent_OfInputFile_ThenWriteImage_ToOutputFile(fileName, "ECB_" + fileName, ECB_CONFIG);
            
            getAnd_EncryptContent_OfInputFile_ThenWriteImage_ToOutputFile(fileName, "CBC_" + fileName, CBC_CONFIG);
        }
        
    }
    
    public static void getAnd_EncryptContent_OfInputFile_ThenWriteImage_ToOutputFile(
        String fileName, String outFileName, String config)
    {
        byte[][] byteArray = getAnd_EncryptContent_OfInputFile(fileName, config);
        
        BufferedImage image = byteArrayToImage(byteArray);
        
        writeImage_ToOutputFile(image, outFileName);
    }
    
    public static byte[][] getAnd_EncryptContent_OfInputFile(String fileName, String config)
    {
        SecretKey key = generateKey();
        
        BufferedImage image = getContent_OfInputFile(fileName);
        
        int image_width = image.getWidth();
        
        int image_length = image.getHeight();
        
        byte[][] byteArray = imageToByteArray(image);
        
        byte[][] encryptedArray = new byte[image_width][image_length*4];
        
        for (int idx = 0; idx < image_width; idx++) {
            
            // encrypt each column or row bytes
            byte[] encryptedBytes = encryptBytes(byteArray[idx], key, config);
            
            for (int idy = 0; idy < image_length*4; idy++)
                encryptedArray[idx][idy] = encryptedBytes[idy];
        }
        
        return encryptedArray;
    }
    
    public static BufferedImage getContent_OfInputFile(String fileName)
    {
        String filePath = RESOURCES_LOCATION + "/" + fileName;
        
        try {
            return ImageIO.read(new File(filePath));
        }
        
        catch (IOException exception) {
            throw new IllegalStateException("I/O exception occured");
        }
    }
    
    public static void writeImage_ToOutputFile(BufferedImage image, String outFileName)
    {
        String filePath = RESOURCES_LOCATION + "/" + outFileName;
        
        try {
            ImageIO.write(image, "BMP", new File(filePath));
        }
        
        catch (IOException exception) {
            throw new IllegalStateException("I/O exception occured");
        }
    }
    
    public static byte[][] imageToByteArray(BufferedImage image)
    {
        int image_width = image.getWidth();
        int image_length = image.getHeight();
        
        byte[][] byteArray = new byte[image_width][image_length*4];
        
        for (int idx = 0; idx < image_width; idx++) {
            
            for(int idy = 0; idy < image_length; idy++) {
                
                int rgb = image.getRGB(idx, idy);
                
                byte[] bytes = intToBytes(rgb);
                
                for (int idz = 0; idz < 4; idz++)
                    byteArray[idx][(4*idy+idz)] = bytes[idz];
            }
        }
        
        return byteArray;
    }
    
    public static BufferedImage byteArrayToImage(byte[][] byteArray)
    {
        int image_width = byteArray.length;
        int image_length = byteArray[0].length / 4;
        
        BufferedImage image = new BufferedImage(image_width, image_length, BufferedImage.TYPE_3BYTE_BGR);
        
        for (int idx = 0; idx < image_width; idx++) {
            
            for (int idy = 0; idy < image_length; idy++) {
                
                byte[] bytes = new byte[4];
                
                for (int idz = 0; idz < 4; idz++)
                    bytes[idz] = byteArray[idx][4*idy+idz];
                
                int rgb = bytesToInt(bytes);
                
                image.setRGB(idx, idy, rgb);
            }
        }
        
        return image;
    }
    
    private static byte[] intToBytes(int value)
    {
        return new byte[] {
            (byte) (value >> 24),
            (byte) (value >> 16),
            (byte) (value >> 8),
            (byte) (value)};
    }
    
    private static int bytesToInt(byte[] bytes)
    {
        return (
            ((bytes[0] & 0xFF) << 24) | 
            ((bytes[1] & 0xFF) << 16) | 
            ((bytes[2] & 0xFF) << 8 ) | 
            ((bytes[3] & 0xFF) << 0 ));
    }
}
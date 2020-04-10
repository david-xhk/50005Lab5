package cse.lab5;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public final class ImageFileUtils
{
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

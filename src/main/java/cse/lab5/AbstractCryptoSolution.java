package cse.lab5;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public abstract class AbstractCryptoSolution
{
    public static final String DES = "DES";
    public static final String AES = "AES";
    public static final String ECB_CONFIG = "ECB/PKCS5Padding";
    public static final String CBC_CONFIG = "CBC/PKCS5Padding";
    
    private static Cipher currentCipher;
    private static String currentAlgorithm;
    private static String currentConfig;
    private static int currentMode;
    private static SecretKey currentKey;
    
    // generate secret key for DES algorithm
    public static SecretKey generateKey(String algorithm)
    {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            
            return keyGenerator.generateKey();
        }
        
        catch (NoSuchAlgorithmException exception) {
            throw new IllegalArgumentException("algorithm invalid");
        }
    }
    
    // encrypt bytes using given key
    public static byte[] encryptBytes(byte[] bytes, String algorithm, String config, SecretKey key)
    {
        if (!algorithm.equals(currentAlgorithm) || !config.equals(currentConfig))
            createCipher(algorithm, config);
        
        if (Cipher.ENCRYPT_MODE != currentMode || !key.equals(currentKey))
            initializeCipher(Cipher.ENCRYPT_MODE, key);
        
        return doFinal(currentCipher, bytes);
    }
    
    // decrypt bytes using given key
    public static byte[] decryptBytes(byte[] bytes, String algorithm, String config, SecretKey key)
    {
        if (!algorithm.equals(currentAlgorithm) || !config.equals(currentConfig))
            createCipher(algorithm, config);
        
        if (Cipher.DECRYPT_MODE != currentMode || !key.equals(currentKey))
            initializeCipher(Cipher.DECRYPT_MODE, key);
        
        return doFinal(currentCipher, bytes);
    }
    
    public static void createCipher(String algorithm, String config)
    {
        // create cipher object with given and mode
        try {
            currentCipher = Cipher.getInstance(algorithm + "/" + config);
            
            currentAlgorithm = algorithm;
            
            currentConfig = config;
        }
        
        catch (NoSuchAlgorithmException exception) {
            throw new IllegalArgumentException("algorithm invalid");
        }
        
        catch (NoSuchPaddingException exception) {
            throw new IllegalArgumentException("padding invalid");
        }
    }
    
    public static void initializeCipher(int mode, SecretKey key)
    {
        // initialize the cipher with the given key and chosen encryption mode
        try {
            currentCipher.init(mode, key);
            
            currentMode = mode;
            
            currentKey = key;
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
}

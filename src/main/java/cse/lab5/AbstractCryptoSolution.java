package cse.lab5;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;


public abstract class AbstractCryptoSolution
{
    protected static Cipher currentCipher = null;
    protected static String currentAlgorithm = null;
    protected static String currentConfig = null;
    protected static Integer currentMode = -1;
    protected static Key currentKey = null;
    
    // generate secret key for DES algorithm
    public static Key generateKey(String algorithm)
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
    public static byte[] encryptBytes(byte[] bytes, String algorithm, String config, Key key)
    {
        if (!algorithm.equals(currentAlgorithm) || !config.equals(currentConfig))
            createCipher(algorithm, config);
        
        if (currentMode.equals(Cipher.ENCRYPT_MODE) || !key.equals(currentKey))
            initializeCipher(Cipher.ENCRYPT_MODE, key);
        
        return doFinal(bytes);
    }
    
    // encrypt bytes (with preconfigured cipher)
    public static byte[] encryptBytes(byte[] bytes)
    {
        ensureCipherInitialised();
        
        if (currentMode == Cipher.DECRYPT_MODE)
            throw new IllegalStateException("cipher in decrypt mode");
        
        return doFinal(bytes);
    }
    
    // decrypt bytes using given key
    public static byte[] decryptBytes(byte[] bytes, String algorithm, String config, Key key)
    {
        if (!algorithm.equals(currentAlgorithm) || !config.equals(currentConfig))
            createCipher(algorithm, config);
        
        if (Cipher.DECRYPT_MODE != currentMode || !key.equals(currentKey))
            initializeCipher(Cipher.DECRYPT_MODE, key);
        
        return doFinal(bytes);
    }
    
    // encrypt bytes (with preconfigured cipher)
    public static byte[] decryptBytes(byte[] bytes)
    {
        ensureCipherInitialised();
        
        if (currentMode == Cipher.ENCRYPT_MODE)
            throw new IllegalStateException("cipher in encrypt mode");
        
        return doFinal(bytes);
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
    
    public static void initializeCipher(int mode, Key key)
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
    private static byte[] doFinal(byte[] bytes)
    {
        try {
            return currentCipher.doFinal(bytes);
        }
        
        catch (IllegalBlockSizeException exception) {
            throw new IllegalArgumentException("block size invalid");
        }
        
        catch (BadPaddingException exception) {
            throw new IllegalArgumentException("padding invalid");
        }
    }
    
    private static void ensureCipherInitialised()
    {
        if (currentAlgorithm == null)
            throw new IllegalStateException("algorithm missing");
        
        if (currentConfig == null)
            throw new IllegalStateException("config missing");
        
        if (currentCipher == null)
            throw new IllegalStateException("cipher missing");
        
        if (currentMode.equals(-1))
            throw new IllegalStateException("mode missing");
        
        if (currentKey == null)
            throw new IllegalStateException("key missing");
    }
}

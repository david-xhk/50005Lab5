package cse.lab5;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public abstract class AbstractDesSolution
{
    public static final String ALGORITHM = "DES";
    public static final String ECB_CONFIG = "ECB/PKCS5Padding";
    public static final String CBC_CONFIG = "CBC/PKCS5Padding";
    
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
    
    // encrypt bytes using given key
    public static byte[] encryptBytes(byte[] bytes, SecretKey key, String config)
    {
        Cipher cipher = getCipher(config);
        
        initializeCipher(cipher, key, Cipher.ENCRYPT_MODE);
        
        return doFinal(cipher, bytes);
    }
    
    // decrypt bytes using given key
    public static byte[] decryptBytes(byte[] bytes, SecretKey key, String config)
    {
        Cipher cipher = getCipher(config);
        
        initializeCipher(cipher, key, Cipher.DECRYPT_MODE);
        
        return doFinal(cipher, bytes);
    }
    
    // create cipher object with chosen configuration
    public static Cipher getCipher(String config)
    {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM + "/" + config);
            
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
}

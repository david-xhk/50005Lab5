package cse.lab5;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class AbstractSignedCryptoSolution extends AbstractCryptoSolution
{
    // generate key pair with the given key size
    public static KeyPair generateKeyPair(String algorithm, int keySize)
    {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
            
            keyPairGenerator.initialize(keySize);
            
            return keyPairGenerator.generateKeyPair();
        }
        
        catch (NoSuchAlgorithmException exception) {
            throw new IllegalArgumentException("algorithm invalid");
        }
    }
    
    // calculate a message digest using chosen algorithm
    public static byte[] digestMessage(byte[] input, String algorithm)
    {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            
            messageDigest.update(input);
            
            return messageDigest.digest();
        }
        
        catch (NoSuchAlgorithmException exception) {
            throw new IllegalArgumentException("algorithm invalid");
        }
    }
}

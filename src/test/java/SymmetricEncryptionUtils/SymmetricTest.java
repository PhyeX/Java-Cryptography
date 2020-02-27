package SymmetricEncryptionUtils;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import static org.junit.jupiter.api.Assertions.*;

class SymmetricTest {

    @Test
    void createAESKey() throws Exception{
        SecretKey key = Symmetric.createAESKey();
        assertNotNull(key);
        System.out.println("Secret Key: "+DatatypeConverter.printHexBinary(key.getEncoded()));
    }

    @Test
    void testAESCyrptoRoutine() throws Exception{
        SecretKey key = Symmetric.createAESKey();
        byte [] initializationVector = Symmetric.createInitialiaztionVector();
        String plainText = "This is the text we are going to hide in plain sight";
        byte[] cipherText = Symmetric.performAESEncryption(plainText, key, initializationVector);
        assertNotNull(cipherText);
        System.out.println("Cipher Text: "+ DatatypeConverter.printHexBinary(cipherText));
        String decryptedText = Symmetric.performAESDecryption(cipherText, key, initializationVector);
        System.out.println("DecryptedText: "+ decryptedText);
        assertEquals(plainText,decryptedText);
    }

}
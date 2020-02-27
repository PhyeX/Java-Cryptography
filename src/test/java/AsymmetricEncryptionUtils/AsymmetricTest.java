package AsymmetricEncryptionUtils;

import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class AsymmetricTest {

    @Test
    void generateRSAKeyPair() throws Exception{
        KeyPair keyPair  = Asymmetric.generateRSAKeyPair();
        assertNotNull(keyPair);
        System.out.println("Private Key: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
        System.out.println("Public Key: " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
    }

    @Test
    void testRSACryptoRoutine() throws  Exception{
        KeyPair keyPair = Asymmetric.generateRSAKeyPair();
        String plainText = "This is the text we are going to hide in plain sight";
        byte[] cipherText = Asymmetric.performRSAEncryption(plainText,keyPair.getPrivate());
        System.out.println("Cipher Text: " + DatatypeConverter.printHexBinary(cipherText));
        String decrpytedText = Asymmetric.performRSADecryption(cipherText, keyPair.getPublic());
        assertEquals(plainText, decrpytedText);
    }
}
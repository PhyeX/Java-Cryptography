package DigitalSignatureUtils;

import AsymmetricEncryptionUtils.Asymmetric;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.*;

class DigitalSignatureTest {

    @Test
    void createDSAKey() throws Exception {
        KeyPair keyPair  = DigitalSignature.createDSAKey();
        assertNotNull(keyPair);
        System.out.println("Private Key: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
        System.out.println("Public Key: " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
    }
    @Test
    void testDSACrypto() throws  Exception{
        KeyPair keyPair = DigitalSignature.createDSAKey();
        String plainText = "This is the text we are going to hide in plain sight";
        byte[] cipherText = DigitalSignature.performDSASignature(plainText,keyPair.getPrivate());
        System.out.println("Digital signature for given text : " + DatatypeConverter.printHexBinary(cipherText));
        System.out.println("DSA Decryption: " +DigitalSignature.DSADecryption(cipherText,keyPair.getPublic()));
        assertEquals(plainText,new String(cipherText));
    }
}

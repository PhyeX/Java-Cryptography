package DigitalSignatureUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.*;
import java.util.Arrays;

public class DigitalSignature {

    private static final String DSA = "DSA";
    private static final String SHA256withDSA = "SHA256withDSA";

    public static KeyPair createDSAKey() throws Exception{
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(DSA);
        keyPairGen.initialize(2048,secureRandom);
        return keyPairGen.generateKeyPair();
    }

    public static byte[] performDSASignature(String plainText, PrivateKey privateKey) throws Exception{
        Signature sign = Signature.getInstance(SHA256withDSA);
        sign.initSign(privateKey);
        byte[] bytes = plainText.getBytes();
        sign.update(bytes);
        sign.sign();
        return bytes;
    }

    public static String DSADecryption (byte[] plainText,PublicKey publicKey)throws Exception{
    Signature sign = Signature.getInstance(SHA256withDSA);
    sign.initVerify(publicKey);
    sign.update(plainText);
    return new String(plainText);
    }
}

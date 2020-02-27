package KeyStoreUtils;

import AsymmetricEncryptionUtils.Asymmetric;
import HashUtils.Hash;
import SymmetricEncryptionUtils.Symmetric;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

import static org.junit.jupiter.api.Assertions.*;

class KeyStorageTest {

    @Test
    void generateKeyStoreWithPassword() throws Exception{
        KeyStore keyStore = KeyStorage.generateKeyStore("password");
        assertNotNull(keyStore);
    }

    @Test
    void saveSymmetricKey() throws  Exception{

        KeyStore keyStore = KeyStorage.generateKeyStore("password");
        SecretKey secretKey = Symmetric.createAESKey();
        System.out.println("Secretkey has been created: "+secretKey.getEncoded());
        KeyStore.SecretKeyEntry secret = new KeyStore.SecretKeyEntry(secretKey);
        KeyStore.ProtectionParameter password = new KeyStore.PasswordProtection("password".toCharArray());
        keyStore.setEntry("db-encryption-secret", secret, password);
        System.out.println("KeyStore size: "+ keyStore.size());

        SecretKey secretKey1 = Symmetric.createAESKey();
        System.out.println("Secretkey2 has been created: "+secretKey.getEncoded());
        KeyStore.SecretKeyEntry secret2 = new KeyStore.SecretKeyEntry(secretKey);
        keyStore.setEntry("db-tayyip-password",secret2,password);
        System.out.println("KeyStore size: "+ keyStore.size());

        Key ssoSigningKey = keyStore.getKey("db-encryption-secret","password".toCharArray());
        Key ssoSigningKey2 = keyStore.getKey("db-tayyip-password","password".toCharArray());

        assertNotNull(ssoSigningKey);
        assertNotNull(ssoSigningKey2);
    }





}
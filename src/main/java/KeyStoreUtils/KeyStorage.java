package KeyStoreUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.security.KeyStore;

public class KeyStorage {

     public static final String PCKS12 = "pkcs12";

     public static final String filePath = "newKeyStoreFileName.jks";

     public static KeyStore generateKeyStore (String password) throws Exception{
          KeyStore keyStore = KeyStore.getInstance(PCKS12);
          char[] pwdArray =  password.toCharArray();
          keyStore.load(new FileInputStream(filePath),pwdArray);
          return keyStore;
     }


}

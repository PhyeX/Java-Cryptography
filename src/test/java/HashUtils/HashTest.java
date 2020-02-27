package HashUtils;

import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.sql.SQLOutput;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HashTest {

    @Test
    void generateRandomSalt() {
        byte[] salt = Hash.generateRandomSalt();
        assertNotNull(salt);
        System.out.println("Salt: "+DatatypeConverter.printHexBinary(salt));
    }

    @Test
    void createSHA2Hash() throws Exception{
        byte[] salt = Hash.generateRandomSalt();
        String valueToHash = UUID.randomUUID().toString();
        System.out.println("valueToHash: "+valueToHash);
        byte[] hash = Hash.createSHA2Hash(valueToHash,salt);
        System.out.println("HASH: "+valueToHash);
        assertNotNull(hash);
        byte[] hash2 = Hash.createSHA2Hash(valueToHash,salt);
        System.out.println("HASH: "+valueToHash);
        assertEquals(DatatypeConverter.printHexBinary(hash),DatatypeConverter.printHexBinary(hash2));
    }

    @Test
    void testPasswordRoutine(){
        String secretPhrase = "correct horse battery staple";
        String passwordHash = Hash.hashPassword(secretPhrase);
        System.out.println("PasswordHash: "+passwordHash);
        assertTrue(Hash.verifyPassword(secretPhrase,passwordHash));

    }
}
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class BankingAppEncryption {
    public static String encryptTransactionDetails(String details) throws Exception {
        // Weak algorithm and insecure mode of operation
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(details.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static void main(String[] args) throws Exception {
        String sensitiveDetails = "AccountNumber=123456789&Amount=1000";
        String encryptedDetails = encryptTransactionDetails(sensitiveDetails);
        System.out.println("Encrypted transaction details: " + encryptedDetails);
    }
}


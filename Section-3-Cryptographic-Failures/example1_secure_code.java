public class BankingAppEncryption {
    private static final int AES_KEY_SIZE = 256;
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 16 * 8; // 16 bytes in bits

    public static String encryptTransactionDetails(String details, SecretKey secretKey, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);
        byte[] encrypted = cipher.doFinal(details.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static void main(String[] args) throws Exception {
        String sensitiveDetails = "AccountNumber=123456789&Amount=1000";

        // Generate a strong key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE); // Use 256-bit AES
        SecretKey secretKey = keyGen.generateKey();

        // Generate a secure IV
        byte[] iv = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        String encryptedDetails = encryptTransactionDetails(sensitiveDetails, secretKey, iv);
        System.out.println("Encrypted transaction details: " + encryptedDetails);
    }
}


using System;
using System.Security.Cryptography;
using System.Text;

public class SocialMediaAppEncryption
{
    public static string EncryptUserMessage(string message, byte[] key, byte[] iv)
    {
        using (Aes aes = Aes.Create())
        {
            aes.Key = key;
            aes.IV = iv;
            ICryptoTransform encryptor = aes.CreateEncryptor(aes.Key, aes.IV);
            byte[] encrypted = encryptor.TransformFinalBlock(Encoding.UTF8.GetBytes(message), 0, message.Length);
            return Convert.ToBase64String(encrypted);
        }
    }

    public static void Main()
    {
        string userMessage = "Hello, this is a private message!";

        // Generate a strong key
        using (Aes aes = Aes.Create())
        {
            aes.KeySize = 256; // Use 256-bit key
            aes.GenerateKey();
            aes.GenerateIV();

            byte[] key = aes.Key;
            byte[] iv = aes.IV;

            string encryptedMessage = EncryptUserMessage(userMessage, key, iv);
            Console.WriteLine("Encrypted user message: " + encryptedMessage);
        }
    }
}


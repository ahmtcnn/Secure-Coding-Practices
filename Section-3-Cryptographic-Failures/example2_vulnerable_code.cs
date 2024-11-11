using System;
using System.Security.Cryptography;
using System.Text;

public class SocialMediaAppEncryption
{
    public static string EncryptUserMessage(string message)
    {
        string key = "hardcodedkey123"; // Hard-coded key
        byte[] keyBytes = Encoding.UTF8.GetBytes(key);
        using (Aes aes = Aes.Create())
        {
            aes.Key = keyBytes;
            aes.IV = new byte[16]; // Zero initialization vector (IV)
            ICryptoTransform encryptor = aes.CreateEncryptor(aes.Key, aes.IV);
            byte[] encrypted = encryptor.TransformFinalBlock(Encoding.UTF8.GetBytes(message), 0, message.Length);
            return Convert.ToBase64String(encrypted);
        }
    }

    public static void Main()
    {
        string userMessage = "Hello, this is a private message!";
        string encryptedMessage = EncryptUserMessage(userMessage);
        Console.WriteLine("Encrypted user message: " + encryptedMessage);
    }
}


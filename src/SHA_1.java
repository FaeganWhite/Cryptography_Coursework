/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.lang.Math;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author faegan
 */
public class SHA_1 {
    
    // Main function
    public static void main(String[] args) {
        // Create a HammingCode object
        SHA_1 self = new SHA_1();
        //
        System.out.println(self.HashPassword("simple"));
    }
    
    // Encode a 6 digit number in hamming 10 6
    public String HashPassword(String inputString) {
        // Try - required to call "getInstance"
        try {
            // Get the SHA-1 algorithm
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            // Digest the input into bytes
            byte[] inputBytes = sha1.digest(inputString.getBytes());
            // Converts the bytes to a signed integer
            BigInteger inputNumber = new BigInteger(1, inputBytes);
            // Convert the integer into a hexidecimal value
            String hexResult = inputNumber.toString(16);
            // While the hexidecimal is less than 32 bits
            while (hexResult.length() < 40) {
                // Add a zero to the start
                hexResult = "0" + hexResult;
            }
            // Return the result
            return hexResult;
        // If the SHA-1 algorithm cannot be found
        } catch (NoSuchAlgorithmException e) {
            // Throw a No Such Algorithm exception
            throw new RuntimeException(e);
        }
    }
}

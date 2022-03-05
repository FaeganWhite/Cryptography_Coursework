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
public class BruteForce {
    
    // Establish the password dictionaries
    String[] alphaNumericAlphabet = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] bchAlphabet = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    
    // Crack an alphanumeric password of a specified length
    public String AlphanumericCrack(String inputHash, int passwordLength) {
        // Return the password
        return BreakPasswordHash(inputHash, passwordLength, alphaNumericAlphabet);
    }
    
    // Crack a bch hash
    public String BCHCrack(String inputHash) {
        // Return the bch code
        return BreakPasswordHash(inputHash, 10, bchAlphabet);
    }
    
    // Crack an alphanumeric password with a given length
    public String BreakPasswordHash(String inputHash, int passwordLength, String[] alphabet) {
        // Get the start time
        long start_time = System.currentTimeMillis();
        // Create a new SHA-1
        SHA_1 sha1 = new SHA_1();
        // Get the numerical range of the dictionary
        int range = alphabet.length - 1;
        // Create a code array to store the iterations
        int[] code = new int[passwordLength+1];
        // For every place in the code
        for (int a = 0; a < passwordLength; a++) {
            // Set the value to zero
            code[a] = 0;
        }
        // Get the total number of loops required to try every combination
        double loopNumber = Math.pow(range, passwordLength);
        // Create a counter
        int counter = 0;
        // While the counter is smaller than the possible length of combinations
        while (counter <= loopNumber) {
            // Convert the integer code to a string
            String codeString = CodeToString(code, alphabet);
            // Print the code string for debugging
            //System.out.println(codeString);
            // Convert the string to a hash
            String codeHash = sha1.HashPassword(codeString);
            // If the hash equals the given hash
            if (codeHash.equals(inputHash)) {
                // Get the end time
                long end_time = System.currentTimeMillis();
                // Return the string as the cracked password
                return "Password found: "+codeString+"\nTime elapsed: "+Long.toString((end_time-start_time))+" milliseconds";
            }
            // Otherwise, increment the code by 1
            code = Increment(code, range, code.length-1);
            // Increment the counter
            counter++;
        }
        // Get the end time
        long end_time = System.currentTimeMillis();
        // If no solution is found, return a message
        return "No solution found"+"\nTime elapsed: "+Long.toString((end_time-start_time))+" milliseconds";
    }
    
    // Convert a numerical code to a string usen a given alphabet
    private String CodeToString(int[] code, String[] dictionary) {
        // Create a blank output string to hold the output
        String outputString = "";
        // For every place in the code
        for (int a = 0; a < code.length; a++) {
            // Add the value to the output string according to the dictionary
            outputString += dictionary[code[a]];
        }
        // Return the output string
        return outputString;
    }
    
    // Increment an array of integers
    private int[] Increment(int[] code, int range, int position) {
        // Increment the value at the given position of the code
        code[position] = code[position]+1;
        // If the value is beyond the range
        if (code[position] > range) {
            // If the position isn't the first value
            if (position != 0) {
                // Return the value to 0
                code[position] = 0;
                // Increment the value in the position before
                code = Increment(code, range, position-1);
            // Otherwise
            } else {
                // Return the value to zero
                code[position] = 0;
            }
        }
        // return the incemented code
        return code;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author faegan
 */
public class RainbowTable {
    
    // Establish varaibles for the RainbowTable
    int width;
    int height;
    int max_password_length;
    long prime;
    public String[][] table;
    
    // Initiate a rainbow table
    public RainbowTable(int inWidth, int inHeight, int password_length) {
        // Set the width of the table
        width = inWidth;
        // Set the height of the table
        height = inHeight;
        // Set the maximum password length
        max_password_length = password_length;
        // Establish the table array
        table = new String[height][2];
    }
    
    // Generate a table using the alphabet
    public String GenerateTable(String[] alphabet) {
        // Get the password space
        long password_space = GetPasswordSpace(alphabet.length);
        // Get the prime
        prime = FindNextPrime(password_space);
        // Create a list of end chain passwords
        ArrayList<String> end_passwords = new ArrayList<String>();
        // For every row
        for (int a = 0; a < height; a++) {
            // Get the percentage through the chains 
            double position = (double) a / (double) height;
            // Generate a number reperesenting the percentage throught the password space
            long first_num = (long) Math.round((long) password_space * position);
            // Generate an initial chain value from the percentage through the password space
            table[a][0] = IntToString(first_num, alphabet);
        }
        // Create a SHA-1 object
        SHA_1 sha1 = new SHA_1();
        // For every row
        for (int a = 0; a < height; a++) {
            //
            System.out.println("Generating "+Integer.toString(a));
            // Initiate reduced as the initial password
            String reduced = (String) table[a][0];
            // Initiate a boolean to check if the final password in the chain already exists in the table (aka. collision)
            boolean present = true;
            // Create a counter
            int counter = 0;
            // While the final password already exists in the table
            while (present == true) {
                // Create a current hash string
                String current_hash;
                // For every column
                for (int pos = 0; pos < width*2; pos+=2) {
                    // Get the hash from the previous password
                    current_hash = sha1.HashPassword(reduced);
                    // Reduce the hash
                    reduced = Reduce(current_hash, pos/2, alphabet);
                }
                // If the password doesn't exist in the table end
                if (end_passwords.indexOf(reduced) == -1) {
                    // Set present to false
                    present = false;
                // Otherwise
                } else {
                    // Set the new start of the chain to the reduced hash of the last password
                    reduced = Reduce(sha1.HashPassword(reduced), counter+a, alphabet);
                    // Set the first password of the chain to the reduced value
                    table[a][0] = reduced;
                }
                // Increment the counter
                counter ++;
            }
            // Add the final password to the table
            table[a][1] = reduced;
            // Add the password to the list of final passwords
            end_passwords.add(reduced);
        }
        // Create a return string
        String return_string = "";
        // For every row
        for (int a = 0; a < height; a++) {
            // Get the row
            String[] row = table[a];
            // Print the row
            return_string += "["+row[0]+","+row[1] + "]\n";
        }
        // Return the return string
        return return_string;
    }
    
    // Update the prime number used in reduction
    public void UpdatePrime(String[] alphabet) {
        // Get the password space
        long password_space = GetPasswordSpace(alphabet.length);
        // Set the prime
        prime = FindNextPrime(password_space);
    }
    
    // Crack a hash using the rainbow table
    public String Crack(String hash, String[] alphabet) {
        // Get the start time
        long start_time = System.currentTimeMillis();
        // Copy the hash
        String temp_hash = hash;
        // Create a SHA-1 object
        SHA_1 sha1 = new SHA_1();
        // For every row in the table
        for (int c = width-1; c > -1; c--) {
            //
            System.out.println("Checking "+Integer.toString(c));
            // restore the temporary hash
            temp_hash = hash;
            //
            String reduced = "";
            // For every column
            for (int a = 0; a < width-c; a++) {
                // Reduce the hash
                reduced = Reduce(temp_hash, a+c, alphabet);
                //
                temp_hash = sha1.HashPassword(reduced);
            }
            // For every row
            for (int b = 0; b < table.length; b++) {
                // If the reduced value is on the end of the row
                if (reduced.equals(table[b][table[b].length - 1])) {
                    // Get the first item in the row
                    String start = (String) table[b][0];
                    // For every entry
                    for (int d = 0; d < width; d++) {
                        // Hash the password
                        String new_hash = sha1.HashPassword(start);
                        // If the hash matches the old hash
                        if (new_hash.equals(hash)) {
                            // Get the end time
                            long end_time = System.currentTimeMillis();
                            // Return the value
                            return "Password: "+start+"\nTime elapsed: "+Long.toString((end_time-start_time))+" milliseconds";
                        // Otherwise
                        } else {
                            // Set the start as the reduced hash
                            start = Reduce(new_hash, d, alphabet);
                        }
                    }
                }
            }
            // Hash the reduced password
            temp_hash = sha1.HashPassword(reduced);
        }
        // Get the end time
        long end_time = System.currentTimeMillis();
        // Otherwise alert the user the crack was unsuccessful
        return "Crack Unsuccessful"+"\nTime elapsed: "+Long.toString((end_time-start_time))+" milliseconds";
    }
    
    // Reduce a hash to a string with 1:1 relationship from alphabet
    public String Reduce(String hash, int position, String[] alphabet) {
        // Get the position as a string
        String additive = Integer.toString(position);
        // Add it to the hash
        hash += additive;
        // Generate a 1 to 1 int from the hash
        long hash_code = Math.abs(HashToLong(hash) % prime);
        // Return the integer as a string using the alphabet
        return IntToString(hash_code, alphabet);
    }
    
    // Convert hash to a long
    public long HashToLong(String input_hash) {
        // Get the current prime to use as the base
        long long_val = prime;
        // Get the length of the input hash
        int length = input_hash.length();
        // For every character in the hash
        for (int a = 0; a < length; a++) {
            // Multiply by 31 and add the value of the character
            long_val = 31*long_val + input_hash.charAt(a);
        } 
        // Return the long value
        return long_val;
    }
    
    // Convert an integer to string
    private String IntToString(long number, String[] alphabet) {
        // Create a modular arethmetic unit
        ModArithmetic mod = new ModArithmetic();
        // If the number is negative
        if (number < 0) {
            // Return an empty string
            return "";
        // Otherwise
        } else {
            // Create an int to store the remainder
            long remainder;
            // Get the alphabet base
            long base = alphabet.length;
            // Create an output string
            String out_string = "";
            // While there is a number left
            while (number >= 0) {
                // Get the remainder from the base
                remainder = number % base;
                // Divide the number by the base
                number = number/base;
                // Add the character to the output string
                out_string = alphabet[(int) remainder] + out_string;
                // Decrement the number to handle different lengths
                number --;
            }
            // Return the output string
            return out_string;
        }  
    }
    
    // Find the next prime number
    public long FindNextPrime(long number){
        // Create a counter
        long counter;
        // Incement the input
        number++;
        // Forever
        while(true){
            // Get the square root of the number
            long root = (long) Math.sqrt(number);
            // Set the counter to 0
            counter = 0;
            // For between 2 and the sqrt of the input
            for (long i = 2; i <= root; i++){
                // If the input is divisible by i
                if (number % i == 0)
                    // Increment the counter
                    counter++;
            }
            // If there were no divisors
            if (counter == 0)
                // Return the current number
                return number;
            // Otherwise
            else{
                // Increment the number
                number++;
            }
        }
    }
    
    // Calculate the size of the password space
    public long GetPasswordSpace(int alphabet_size) {
        // Cretae an int to track the password space
        long password_space = 0;
        // For every value up to and including the password length
        for (int a = 1; a < max_password_length+1; a++) {
            // Add the alphabet size to the power of a to the password space
            password_space += (long) Math.pow(alphabet_size, a);
        }
        // Return the password space
        return password_space;
    }
}

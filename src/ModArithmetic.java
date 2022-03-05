/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author faegan
 */
public class ModArithmetic {
    
    // Main function
    public static void main(String[] args) {
        // Create a Arithmetic function
        ModArithmetic self = new ModArithmetic();
        // Print out a test value
        System.out.println(self.ModSqrt(9, 11));
    }
    
    // Function to get the absolute mod of a number
    public int AbsMod(int x, int m) {
        // Get the mod of the number
        int tempMod = x % m;
        // If it's positive
        if (tempMod >= 0) {
            // Return it
            return tempMod;
        // Otherwise if it is negative
        } else {
            // Return the absolute version of the result
            return m + tempMod; 
        }
    }
    
    // Method to add modulus
    public int ModAdd(int a, int b, int m) {
        // Add the 2 numbers
        int sum = a + b;
        // Return the absolute sum of these numbers
        return AbsMod(sum, m);
    }
    
    // Method to multiply modulus
    public int ModMultiply(int a, int b, int m) {
        // Add the 2 numbers
        int result = a * b;
        // Return the absolute sum of these numbers
        return AbsMod(result, m);
    }
    
    // Get the mod inverse of the number
    public int ModInverse(int a, int m) {
        // Divide the mod by the given number
        double divided = (double)m / (double)a;
        // If the result is an integer
        if (divided == (int)divided) {
            // Return -1 as there is no answer
            return -1;
         // Otherwise
        } else {
            // Add 1 to the mod value
            int currentVal = m+1;
            // Divide the current value by the given number
            double divideProduct = (double)currentVal/(double)a;
            // While the division of the current value and the a is greater than the mod
            while (divideProduct <= m) {
                // If the divide product is an integer
                if (divideProduct == (int)divideProduct) {
                    // Return the answer
                    return (int)divideProduct;
                // Otherwise
                } else {
                    // Increment the currentVal by the mod amount
                    currentVal += m;
                    // Divide the current value by the given number
                    divideProduct = (double)currentVal/(double)a;
                }
            }
            // Return -1 if no inverse was found
            return -1;
        }
    }
    
    // Get the mod inverse of the number
    public int ModInverse11(int a) {
        // Generate an inverse table
        int[] inverseTable = {1, 6, 4, 3, 9, 2, 8, 7, 5, 10};
        // Return the specified value
        return inverseTable[AbsMod(a, 11)-1];
    }
    
    // Method to divide modulus
    public int ModDivide(int a, int b, int m) {
        // Create an inverse value
        int inverse;
        // If the divisor isn't 0
        if (b != 0) {
            // Get the inverse of the divisor
            inverse = ModInverse11(b);
        // Otherwise
        } else {
            // Return error
            return -1;
        }
        // If the result isn't -1
        if (inverse != -1) {
            // Return the multiplication of a and inverse b
            return ModMultiply(a, inverse,  m);
        // Otherwise
        } else {
            // Return -1
            return -1;
        }
    }
    
    // Method to divide modulus
    public int ModSqrt(int a, int m) {
        // Add the value and the modulus value together
        int current_value = a + m;
        // While the value is less than the modulus value squared
        while (current_value < m*m) {
            // If the result of a square root is a whole number
            if (Math.sqrt(current_value) == (int)Math.sqrt(current_value)) {
                // Find the absolute mod of the square root
                int result = AbsMod(-(int)Math.sqrt(current_value), m);
                // If the result is larger than the input
                if (result > a) {
                    // Take the result away from the modulus
                    result = m - result;
                }
                // Return the result
                return result;
            }
            // Otherwise increment the value
            current_value += m;
        }
        // Otherwise return -1
        return -1;
    }
}
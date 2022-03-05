/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.lang.Math;
/**
 *
 * @author faegan
 */
public class BCH {
    
    // Main function
    public static void main(String[] args) {
        // Create a HammingCode object
        BCH self = new BCH();
        // Run the tests
        self.TestEncode();
        self.TestDecode();
    }
    
    // Test encoding
    public void TestEncode() {
        System.out.println("Testing the encode:");
        // Print out some test values
        System.out.println(Encode10_6_with_matrix("000001"));
        System.out.println(Encode10_6_with_matrix("000002"));
        System.out.println(Encode10_6_with_matrix("000010"));
        System.out.println(Encode10_6_with_matrix("000011"));
        System.out.println(Encode10_6_with_matrix("000003"));
        System.out.println(Encode10_6_with_matrix("000009"));
        
    }
    
    // Test decoding
    public void TestDecode() {
        System.out.println("\nTesting the decode:");
        // Print out some test values
        System.out.println(Decode10_6("3745195876"));
        System.out.println(Decode10_6("3945195876"));
        System.out.println(Decode10_6("3745995876"));
        System.out.println(Decode10_6("3715195076"));
        System.out.println(Decode10_6("0743195876"));
        System.out.println(Decode10_6("3745195840"));
        System.out.println(Decode10_6("2745795878"));
        System.out.println(Decode10_6("8745105876"));
        System.out.println(Decode10_6("3745102876"));
        System.out.println(Decode10_6("3742102896"));
        System.out.println(Decode10_6("1145195876"));
        System.out.println(Decode10_6("3745191976"));
        System.out.println(Decode10_6("3745190872"));
        System.out.println(Decode10_6("1115195876"));
        System.out.println(Decode10_6("3121195876"));
        System.out.println(Decode10_6("1135694766"));
        System.out.println(Decode10_6("0888888074"));
        System.out.println(Decode10_6("5614216009"));
        System.out.println(Decode10_6("9990909923"));
        System.out.println(Decode10_6("1836703776"));
        System.out.println(Decode10_6("9885980731"));
        
    }
    
    // Encode a 6 digit number in hamming 10 6
    public String Encode10_6_basic(String inputString) {
        // If the string's length is 6
        if (inputString.length() == 6) {
            // Create a modulus arethmetic unit
            ModArithmetic modArth = new ModArithmetic();
            // Create an array to hold the output
            int[] n = new int[10];
            // For every number in the input
            for (int x = 0; x < 6; x++) {
                // Add the number to the array
                n[x] = Integer.parseInt(String.valueOf(inputString.charAt(x)));
            }
            // Calculate the additional 4 numbers
            n[6] = modArth.AbsMod(4*n[0]+10*n[1]+9*n[2]+2*n[3]+n[4]+7*n[5], 11);
            n[7] = modArth.AbsMod(7*n[0]+8*n[1]+7*n[2]+n[3]+9*n[4]+6*n[5], 11);
            n[8] = modArth.AbsMod(9*n[0]+n[1]+7*n[2]+8*n[3]+7*n[4]+7*n[5], 11);
            n[9] = modArth.AbsMod(n[0]+2*n[1]+9*n[2]+10*n[3]+4*n[4]+n[5], 11);
            // If any of the additional numbers are 10
            if (n[6] == 10 || n[7] == 10 || n[8] == 10 || n[9] == 10) {
                // Print an error message
                return "Unusable number";
            // Otherwise
            } else {
                // Create a string to hold the return value
                String returnString = "";
                // For every value of n
                for (int x = 0; x < 10; x++) {
                    // Add the value to the return string
                    returnString += String.valueOf(n[x]);
                }
                // Return the return string
                return returnString;
            }
        // Otherwise
        } else {
            // Print an error message
            return "Input must be 6 characters";
        }
    }
    
    // Encode a 6 digit number in hamming 10 6
    public String Encode10_6_with_matrix(String inputString) {
        // If the string's length is 6
        if (inputString.length() == 6) {
            // Create a modulus arethmetic unit
            ModArithmetic modArth = new ModArithmetic();
            // Create an array to hold matrixA
            int[] matrixA = new int[6];
            // For every number in the input
            for (int x = 0; x < 6; x++) {
                // Add the number to the array
                matrixA[x] = Integer.parseInt(String.valueOf(inputString.charAt(x)));
            }
            // Create a maxtrix unit
            MatrixUnit matrixUnit = new MatrixUnit();
            // Get the generator matrix
            int[][] generatorMatrix = matrixUnit.GetGeneratorMatrix();
            // Multiply matrixA with the generator matrix
            int[] n = matrixUnit.MatrixMultiply(matrixA, generatorMatrix);
            // For every value in the multiplication result
            for (int a = 0; a < n.length; a++) {
                // Set it to mod 11 of the result
                n[a] = modArth.AbsMod(n[a], 11);
            }
            // If any of the additional numbers are 0
            if (n[6] == 10 || n[7] == 10 || n[8] == 10 || n[9] == 10) {
                // Print an error message
                return "Unusable number";
            // Otherwise
            } else {
                // Create a string to hold the return value
                String returnString = "";
                // For every value of n
                for (int x = 0; x < 10; x++) {
                    // Add the value to the return string
                    returnString += String.valueOf(n[x]);
                }
                // Return the return string
                return returnString;
            }
        // Otherwise
        } else {
            // Print an error message
            return "Input must be 6 characters";
        }
    }
    
    // Decode a 10 digit number in hamming 10 6
    public String Decode10_6(String inputString) {
        // If the string's length is 10
        if (inputString.length() == 10) {
            // Create a modulus arethmetic unit
            ModArithmetic modArth = new ModArithmetic();
            // Create an array to hold the input
            int[] n = new int[10];
            // For every number in the input
            for (int x = 0; x < 10; x++) {
                // Add the number to the array
                n[x] = Integer.parseInt(String.valueOf(inputString.charAt(x)));
            }
            // Calculate the 4 syndromes
            int s1 = modArth.AbsMod(n[0]+n[1]+n[2]+n[3]+n[4]+n[5]+n[6]+n[7]+n[8]+n[9], 11);
            int s2 = modArth.AbsMod(n[0]+2*n[1]+3*n[2]+4*n[3]+5*n[4]+6*n[5]+7*n[6]+8*n[7]+9*n[8]+10*n[9], 11);
            int s3 = modArth.AbsMod(n[0]+4*n[1]+9*n[2]+5*n[3]+3*n[4]+3*n[5]+5*n[6]+9*n[7]+4*n[8]+n[9], 11);
            int s4 = modArth.AbsMod(n[0]+8*n[1]+5*n[2]+9*n[3]+4*n[4]+7*n[5]+2*n[6]+6*n[7]+3*n[8]+10*n[9], 11);
            System.out.println("syn("+String.valueOf(s1)+","+String.valueOf(s2)+","+String.valueOf(s3)+","+String.valueOf(s4)+")");
            // Calculate P
            int P = modArth.AbsMod((s2*s2) - (s1*s3), 11);
            // Calculate Q
            int Q = modArth.AbsMod((s1*s4) - (s2*s3), 11);
            // Calculate R
            int R = modArth.AbsMod((s3*s3) - (s2*s4), 11);
            System.out.println("pqr("+String.valueOf(P)+","+String.valueOf(Q)+","+String.valueOf(R)+")");
            // If all syndromes are 0
            if (s1 == 0 && s2 == 0 && s3 == 0 && s4 ==0) {
                // Print out the error information
                return "Output: "+inputString+"\nNo errors found\n";
            // If all 3 values are 0 (a single error)
            } else if (P == 0 && Q == 0 && R == 0) {
                // If any of the syndomes are 0
                if (s1 != 0 && s2 != 0 && s3 != 0 && s4 != 0) {
                    // Correct the error
                    n[modArth.ModDivide(s2, s1, 11)-1] = modArth.AbsMod(n[modArth.ModDivide(s2, s1, 11)-1]-s1, 11);
                    // For every value of n
                    for (int x = 0; x < 10; x++) {
                        // If the value is 10
                        if (n[x] == 10) {
                            // Return more than 2 errors detected
                            return "More than 2 errors detected!\n";
                        }
                    }
                    // Create a string to hold the return value
                    String returnString = "";
                    // For every value of n
                    for (int x = 0; x < 10; x++) {
                        // Add the value to the return string
                        returnString += String.valueOf(n[x]);
                    }
                    // Print out the error information
                    return "Single error detected\nPosition: "+String.valueOf(modArth.ModDivide(s2, s1, 11))+"\nMagnitude: "+String.valueOf(s1)+"\nOutput: "+returnString+"\n";
                // Otherwise
                } else {
                    // Return more than 2 errors detected
                    return "More than 2 errors detected!\n";
                }
            // Otherwise if there are 2 errors
            } else {    
                // Calculate position 1
                int i = modArth.AbsMod(modArth.ModDivide((-Q+modArth.ModSqrt(((Q*Q)-(4*P*R)), 11)), (2*P), 11), 11);
                // Calculate position 2
                int j = modArth.AbsMod(modArth.ModDivide((-Q-modArth.ModSqrt(((Q*Q)-(4*P*R)), 11)), (2*P), 11), 11);
                // If sqrt has an answer (doesn't return -1)
                if (modArth.ModSqrt(((Q*Q)-(4*P*R)), 11) != -1 && i != 0 && j != 0 && modArth.ModDivide(((i*s1)-s2), (i-j), 11) != -1) {
                    // Calculate magnitude 2
                    int b = modArth.AbsMod(modArth.ModDivide(((i*s1)-s2), (i-j), 11), 11);
                    // Calculate magnitude 1
                    int a = modArth.AbsMod(s1 - b, 11);
                    // Correct the error
                    n[i-1] = modArth.AbsMod(n[i-1]-a, 11);
                    n[j-1] = modArth.AbsMod(n[j-1]-b, 11);
                    // Create a string to hold the return value
                    String returnString = "";
                    // For every value of n
                    for (int x = 0; x < 10; x++) {
                        // If the value is 10
                        if (n[x] == 10) {
                            // Return more than 2 errors detected
                            return "More than 2 errors detected!\n";
                        }
                        // Add the value to the return string
                        returnString += String.valueOf(n[x]);
                    }
                    // Print out the error information
                    return "Double error detected\nPosition 1: "+String.valueOf(i)+"\nMagnitude 1: "+String.valueOf(a)+"\nPosition 2: "+String.valueOf(j)+"\nMagnitude 2: "+String.valueOf(b)+"\nOutput: "+returnString + "\n";
                // Otherwise
                } else{
                    // Return more than 2 errors detected
                    return "More than 2 errors detected!\n";
                }
            }
        // Otherwise
        } else {
            // Print an error message
            return "Input must be 10 characters";
        } 
    }
}

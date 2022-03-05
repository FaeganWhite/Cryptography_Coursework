/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author faegan
 */
public class MatrixUnit {
    
    // Main function{
    public static void main(String[] args) {
        // Create a HammingCode object
        MatrixUnit self = new MatrixUnit();
    }
    
    // Get a generator matrix
    public int[][] GetGeneratorMatrix() {
        // Create a generator matrix{{
        int[][] generatorMatrix = {
                                    {1,0,0,0,0,0},
                                    {0,1,0,0,0,0},
                                    {0,0,1,0,0,0},
                                    {0,0,0,1,0,0},
                                    {0,0,0,0,1,0},
                                    {0,0,0,0,0,1},
                                    {4,10,9,2,1,7},
                                    {7,8,7,1,9,6},
                                    {9,1,7,8,7,7},
                                    {1,2,9,10,4,1}
                                };
        // Return the generator matrix
        return generatorMatrix;
    }
    
    // Multiply 2 matricies
    public int[] MatrixMultiply(int[] matrixA, int[][] matrixB) {
        // If matrixA length is the same as the height of matrixB
        if (matrixA.length == matrixB[0].length) {
            // Create a variable to store if matrixB is the same width through
            boolean valid = true;
            // For every column of matrixB
            for (int a = 0; a < matrixB.length; a++) {
                // If the row at this height doesn't match the first row
                if (matrixB[a].length != matrixB[0].length) {
                    // Set valid to false
                    valid = false;
                    // Break the loop
                    break;
                }
            }
            // If the matricies are valid
            if (valid == true) {
                // Create a return matrix the same dimensions as matrixA
                int[] returnMatrix = new int[matrixB.length];
                // For each column in matixB
                for (int a = 0; a < matrixB.length; a++) {
                    // Create a sum variable
                    int sum = 0;
                    // For every value in matrixA
                    for (int b = 0; b < matrixA.length; b++) {
                        // Multiply the values from matrixA and B and add it to the sum
                        sum += matrixA[b] * matrixB[a][b];
                    }
                    // Set the value in the return matrix to the sum
                    returnMatrix[a] = sum;
                }
                // Return the return matrix
                return returnMatrix;
            // Otherwise
            } else {
                // Print error message
                System.out.println("Matrix B isn't an even size");
                // Return -1
                return new int[-1];
            }
        // Otherwise
        } else {
            // Print an error message
            System.out.println("Matrix sizes don't match");
            // Return -1
            return new int[-1];
        }
    }
}

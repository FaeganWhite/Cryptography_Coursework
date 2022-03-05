/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 *
 * @author faegan
 */
public class FileSystem {
    
    // Function to save a file
    public void Save(String save_string, String save_name) {
        // Try
        try {
            // Create a file writer using the save name
            FileWriter file_writer = new FileWriter(save_name);
            // Write the text to save to the file
            file_writer.write(save_string);
            // Close the file writer
            file_writer.close();
        // Otherwise, if there is an exception
        } catch (IOException exception) {
            // Print the error stack
            exception.printStackTrace();
        }
    }
    
    // Function to load a file
    public String Load(String load_name) {
        //
        String output = "";
        // Try
        try {
            // Create a file object using the load name
            File file = new File(load_name);
            // Create a file reader for the file
            Scanner reader = new Scanner(file);
            // While there are more lines in the file
            while (reader.hasNextLine()) {
                // If this is the first line
                if (output.equals("")) {
                    // Set the output to the line
                    output = reader.nextLine()+"\n";
                // Otherwise
                } else {
                    // Add the line to the output string
                    output += reader.nextLine()+"\n";
                }
            }
            // Close the reader object
            reader.close();
        // Otherwise, if there is an exception
        } catch (FileNotFoundException exception) {
            // Print the error stack
            exception.printStackTrace();
            // Return an error as an output
            output = "ERROR";
        }
        // Output the string
        return output;
    }
}

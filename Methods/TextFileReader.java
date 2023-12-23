/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - TextFileReader.java
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {
    private String filePath; // File path to be set
    
    public TextFileReader(String filePath) {
        this.filePath = filePath; // Setting the file path
    }

    public void readTextFileWithDelay() {
        double delayInSeconds = 0.01; // Change this to set the delay in seconds

        try {
            File file = new File(filePath); // Creating a File object using the provided file path
            FileReader fileReader = new FileReader(file); // Initializing FileReader to read the file
            BufferedReader bufferedReader = new BufferedReader(fileReader); // Initializing BufferedReader for efficient reading
            String line;

            while ((line = bufferedReader.readLine()) != null) { // Reading the file line by line
                processAndPrintWithDelay(line, delayInSeconds); // Processing and printing each line with a delay
            }

            bufferedReader.close(); // Closing the BufferedReader
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Handling IOException or InterruptedException
        }
    }

    // Processing and printing text with specified delays
    private void processAndPrintWithDelay(String text, double delayInSeconds) throws InterruptedException {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '[') {
                if (text.regionMatches(i, "[FIGURE_START]", 0, 13)) {
                    i += 12;
                    applyFigureStyle(); // Applying style for figure start
                    continue;
                } else if (text.regionMatches(i, "[FIGURE_END]", 0, 11) || text.regionMatches(i, "[MY_END]", 0, 7)) {
                    i += 10; // Adjusting the index for [FIGURE_END] and [MY_END]
                    resetStyle(); // Resetting the style
                    continue;
                } else if (text.regionMatches(i, "[MY_START]", 0, 9)){
                    i += 8;
                    applyMyStyle(); // Applying style for "my" text
                    continue;
                }
            }
    
            if (c == '.' || c == ',' || c == ';') {
                Thread.sleep((long) (delayInSeconds * 300)); // Adding a longer pause after punctuation
            } else {
                Thread.sleep((long) (delayInSeconds * 15)); // Normal delay for characters
            }
    
            System.out.print(c); // Printing the character
        }
    
        System.out.println(); // Moving to the next line after processing each line
        Thread.sleep((long) (delayInSeconds * 800)); // Adding a longer pause at the end of each line for suspense
    }    

    // Applying style for figures
    private void applyFigureStyle() {
        // Applying ANSI escape codes for red color and italics
        System.out.print("\u001B[3m"); // Italic style
        System.out.print("\u001B[31m"); // Red color
    }

    // Applying style for "my" text
    private void applyMyStyle() {
        // Applying ANSI escape codes for bold and white color
        System.out.print("\u001B[1m"); // Bold style
        System.out.print("\u001B[37m"); // White color
    }

    // Resetting the text style
    private void resetStyle() {
        // Resetting ANSI escape codes to default
        System.out.print("\u001B[0m"); // Reset to default style
    }
}
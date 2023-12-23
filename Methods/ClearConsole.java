/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - ClearConsole.java
*/
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ClearConsole {

    // Method 1: Clears console when called
    public static void clearConsole() {
        try {
            // Checking the operating system to execute the appropriate command for clearing console
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Clears console in Windows
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor(); // Clears console in other operating systems
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Handling any exceptions that occur during the process
        }
    }

    // Method 2: Clears console when Enter is pressed
    public static void clearOnEnter() {
        System.out.println("Press ENTER to continue."); // Prompting the user to press Enter
        try {
            System.in.read(); // Waits for user input
            clearConsole(); // Clears console after Enter is pressed
        } catch (IOException e) {
            e.printStackTrace(); // Handling any input/output exceptions
        }
    }

    // Method 3: Clears console after a specified duration (in milliseconds) and waits before continuing
    public static void clearConsoleAfterDelay(long delayMillis) {
        Timer timer = new Timer(); // Creating a timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clearConsole(); // Clears console after the specified delay
            }
        }, delayMillis); // Scheduling the task

        try {
            Thread.sleep(delayMillis); // Pauses execution for the specified duration
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handling interruptions to the thread's sleep
        }
    }
}
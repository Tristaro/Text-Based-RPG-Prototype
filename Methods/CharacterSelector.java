/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - CharacterSelector.java
*/
import java.util.Scanner;

public class CharacterSelector {
    private Scanner scanner; // Scanner to get user input
    private Dice dice; // Dice for random selection
    private Character mainCharacter; // Field to store the chosen main character

    public CharacterSelector() {
        scanner = new Scanner(System.in); // Initializing the scanner
        dice = new Dice(); // Initializing the dice
    }

    public void selectCharacter(Character[] availableCharacters) {
        // Prompting the user to choose a character
        System.out.println("Choose your character:");
        System.out.println("1. Barbarian");
        System.out.println("2. Paladin");
        System.out.println("3. Bard");
        System.out.println("4. Astrologer");
        System.out.println("5. Let Fate Decide");

        int choice = getUserChoice(); // Getting user input for character choice

        switch (choice) {
            case 1:
                mainCharacter = availableCharacters[0]; // Assigning Barbarian as the main character
                break;
            case 2:
                mainCharacter = availableCharacters[1]; // Assigning Paladin as the main character
                break;
            case 3:
                mainCharacter = availableCharacters[2]; // Assigning Bard as the main character
                break;
            case 4:
                mainCharacter = availableCharacters[3]; // Assigning Astrologer as the main character
                break;
            case 5:
                int diceResult = dice.rollD4(); // Rolling a D4 dice
                mainCharacter = availableCharacters[diceResult - 1]; // Randomly selecting a character based on dice roll
                break;
            default:
                System.out.println("Invalid choice. Choosing the first character by default.");
                mainCharacter = availableCharacters[0]; // Choosing the first character as default
                break;
        }

        Character[] characters = new Character[availableCharacters.length - 1]; // Array to store remaining characters
        int index = 0;
        for (Character character : availableCharacters) {
            if (character != mainCharacter) {
                characters[index] = character; // Storing remaining characters
                index++;
            }
        }

        // Printing remaining characters
        System.out.println("Should fate permit, you may come across others who share your destiny: ");
        for (Character character : availableCharacters) {
            if (character != mainCharacter) {
                System.out.println(character.getName()); // Printing the names of remaining characters
            }
        }
    }

    public Character getMainCharacter() {
        return mainCharacter; // Returning the selected main character
    }

    private int getUserChoice() {
        int choice = -1;
        while (choice < 1 || choice > 5) {
            System.out.print("Enter your choice (1-5): ");
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Getting user input for choice
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number."); // Handling invalid input
            }
        }
        return choice; // Returning the user's valid choice
    }
}
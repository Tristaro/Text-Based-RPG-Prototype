/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - GameOutcomeChecker.java
*/
public class GameOutcomeChecker {
    // Checking the game outcome based on character and enemy statuses
    public static void checkGameOutcome(Character[] availableCharacters, Character[] enemies) {
        if (!anyCharacterAlive(availableCharacters)) {
            System.out.println("Game Over!"); // Display "Game Over" if no characters are alive
        } else if (!anyEnemyAlive(enemies)) {
            System.out.println("Victory!"); // Display "Victory" if no enemies are alive
        }
    }

    // Method to check if any character is alive
    public static boolean anyCharacterAlive(Character[] characters) {
        for (Character character : characters) {
            if (character.isAlive()) {
                return true; // Returns true if any character is alive
            }
        }
        return false; // Returns false if no characters are alive
    }

    // Method to check if any enemy is alive
    public static boolean anyEnemyAlive(Character[] enemies) {
        for (Character enemy : enemies) {
            if (enemy.isAlive()) {
                return true; // Returns true if any enemy is alive
            }
        }
        return false; // Returns false if no enemies are alive
    }
}
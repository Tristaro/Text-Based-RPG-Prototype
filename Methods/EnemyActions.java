/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - EnemyActions.java
*/

import java.util.Arrays;
import java.util.Random;

public class EnemyActions {
    public static void performEnemyActions(Character[] availableCharacters, Character mainCharacter, Character[] enemies, int damageDealt, int healingDone) {
        Dice dice = new Dice(); // Initializing a dice object
        Random random = new Random(); // Initializing a random number generator

        // Loop for targeting a random character from availableCharacters
        for (Character enemy : enemies) {
            if (enemy.isAlive()) { // Checking if the enemy is alive
                int randomCharacterIndex = random.nextInt(availableCharacters.length); // Generating a random index
                Character character = availableCharacters[randomCharacterIndex]; // Getting a random character from the array

                if (character != mainCharacter && character.isAlive()) { // Checking if the character is not the main character and is alive
                    int rollResult = dice.rollD8(); // Rolling a D8
                    performEnemyAction(enemy, character, rollResult, damageDealt, healingDone);
                }
            }
        }

        // Loop specifically targeting the main character
        for (Character enemy : enemies) {
            if (enemy.isAlive()) { // Checking if the enemy is alive
                if (!Arrays.asList(availableCharacters).contains(enemy)) { // Ensure enemy is not in availableCharacters
                    int rollResult = dice.rollD8(); // Rolling a D8
                    performEnemyAction(enemy, mainCharacter, rollResult, damageDealt, healingDone);
                }
            }
        }
    }
    
    // Method to perform enemy action based on rollResult
    private static void performEnemyAction(Character enemy, Character target, int rollResult, int damageDealt, int healingDone) {
        // Checking if the enemy is still alive before performing actions
        if (enemy.isAlive()) {
            // Performing actions based on the dice roll
            switch (rollResult) {
                case 1:
                    enemy.primaryAttack(target, damageDealt);
                    break;
                case 2:
                    enemy.secondaryAttack(target, damageDealt);
                    break;
                case 3:
                    enemy.specialAttack(target, damageDealt);
                    break;
                case 4:
                    enemy.primaryMagic(target, damageDealt);
                    break;
                case 5:
                    enemy.secondaryMagic(target, damageDealt);
                    break;
                case 6:
                    enemy.tertiaryMagic(target, damageDealt);
                    break;
                case 7:
                    enemy.block();
                    break;
                case 8:
                    enemy.heal(enemy, healingDone);
                    break;
                default:
                    // Handle default case if needed
                    break;
            }

            // Checking for character status after the action
            if (!target.isAlive()) {
                target.CharDeath(); // Handling character death
            }
        }
    }
}
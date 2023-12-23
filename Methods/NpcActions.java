/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - NpcActions.java
*/
import java.util.Random;

public class NpcActions {
    public static void performActions(Character[] availableCharacters, Character mainCharacter, Character[] enemies, int damageDealt, int healingDone) {
        Dice dice = new Dice(); // Initializing a dice object
        Random random = new Random(); // Initializing a random number generator

        for (Character character : availableCharacters) {
            if (character != mainCharacter && character.isAlive()) { // Checking if the character is not the main character and is alive
                int randomEnemyIndex = random.nextInt(enemies.length); // Generating a random index for enemy selection
                Character enemy = enemies[randomEnemyIndex]; // Getting a random enemy from the array

                // Checking if the enemy is alive before performing actions
                if (enemy.isAlive()) {
                    int rollResult = dice.rollD8(); // Rolling a D8

                    // Performing actions based on the dice roll
                    switch (rollResult) {
                        case 1:
                            character.primaryAttack(enemy, damageDealt);
                            break;
                        case 2:
                            character.secondaryAttack(enemy, damageDealt);
                            break;
                        case 3:
                            character.specialAttack(enemy, damageDealt);
                            break;
                        case 4:
                            character.primaryMagic(enemy, damageDealt);
                            break;
                        case 5:
                            character.secondaryMagic(enemy, damageDealt);
                            break;
                        case 6:
                            character.tertiaryMagic(enemy, damageDealt);
                            break;
                        case 7:
                            character.block();
                            break;
                        case 8:
                            character.heal(character, healingDone);
                            break;
                        default:
                            // Handle default case if needed
                            break;
                    }
                    
                    // Checking for enemy status after the action
                    if (!enemy.isAlive()) {
                        enemy.CharDeath(); // Handling enemy death
                    }
                }
            }
        }
    }
}
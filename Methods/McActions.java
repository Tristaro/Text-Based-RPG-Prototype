/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - McActions.java
*/
import java.util.Scanner;

public class McActions {
    public static void performActions(Character mainCharacter, Character[] enemies, int damageDealt, int healingDone) {
        Scanner scanner = new Scanner(System.in); // Initializing a scanner for user input
        
        System.out.println("Choose an action for the main character:");
        // Displaying available actions for the main character
        System.out.println("1. " + mainCharacter.getPrimaryAttackName());
        System.out.println("2. " + mainCharacter.getSecondaryAttackName());
        System.out.println("3. " + mainCharacter.getSpecialAttackName());
        System.out.println("4. " + mainCharacter.getPrimaryMagicName());
        System.out.println("5. " + mainCharacter.getSecondaryMagicName());
        System.out.println("6. " + mainCharacter.getTertiaryMagicName());
        System.out.println("7. " + mainCharacter.getBlockName());
        System.out.println("8. " + mainCharacter.getHealName());

        int choice = scanner.nextInt(); // Getting user choice
        scanner.nextLine(); // Consume the newline character
        
        switch (choice) {
            // Performing the chosen action based on user input
            case 1:
                mainCharacter.primaryAttack(enemies[0], damageDealt);
                break;
            case 2:
                mainCharacter.secondaryAttack(enemies[0], damageDealt);
                break;
            case 3:
                mainCharacter.specialAttack(enemies[0], damageDealt);
                break;
            case 4:
                mainCharacter.primaryMagic(enemies[0], damageDealt);
                break;
            case 5:
                mainCharacter.secondaryMagic(enemies[0], damageDealt);
                break;
            case 6:
                mainCharacter.tertiaryMagic(enemies[0], damageDealt);
                break;
            case 7:
                mainCharacter.block();
                break;
            case 8:
                mainCharacter.heal(mainCharacter, healingDone);
                break;
            default:
                System.out.println("Invalid choice!"); // Display message for an invalid choice
                break;
        }

        // Checking for enemy status after the action
        if (!enemies[0].isAlive()) {
            enemies[0].CharDeath(); // Handling enemy death
        }
    }
}
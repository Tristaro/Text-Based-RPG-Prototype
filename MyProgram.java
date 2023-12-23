/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - MyProgram.java
*/
import java.util.Scanner; // Importing the Scanner class from java.util package

public class MyProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object named 'scanner'

        // Creates Ally Character Objects
        Barbarian barbarian = new Barbarian("Barbarian"); // Creating the Barbarian
        Paladin paladin = new Paladin("Paladin"); // Creating the Paladin
        Bard bard = new Bard("Bard"); // Creating the Bard
        Astrologer astrologer = new Astrologer("Astrologer"); // Creating the Astrologer

        // Creates Test Enemy Character Objects 
        TestMob testMob = new TestMob("TestMob", 10000, 1000, 1000, 1000); // Creating a TestMob monster
        TestMob testMob2 = new TestMob("TestMob2", 100, 1000, 1000, 1000); // Creating a TestMob2 monster
        TestMob testMob3 = new TestMob("TestMob3", 100, 1000, 1000, 1000); // Creating a TestMob3 monster

        // Creates Enemy Character Objects 
        Emperor emperor = new Emperor("Emperor", 500, 200, 200, 200); // Creating an Emperor
        Commander commander = new Commander("Commander", 350, 100, 100, 100); // Creating a Commander
        EliteSoldier eliteSoldier = new EliteSoldier("Elite Soldier", 200, 100, 100, 100); // Creating an Elite Soldier
        Soldier soldier1 = new Soldier("Soldier A", 100, 100, 100, 100); // Creating a Soldier
        Soldier soldier2 = new Soldier("Soldier B", 100, 100, 100, 100); // Creating a Soldier
        Soldier soldier3 = new Soldier("Soldier C", 100, 100, 100, 100); // Creating a Soldier
        Soldier soldier4 = new Soldier("Soldier D", 100, 100, 100, 100); // Creating a Soldier

        // Creating Arrays to Store Character Objects 
        Character[] availableCharacters = {barbarian, paladin, bard, astrologer}; // Ally Objects
        Character[] test = {testMob}; // Enemy Objects
        Character[] act1 = {soldier1};
        Character[] act2 = {eliteSoldier};
        Character[] act3 = {testMob2, testMob3};
        Character[] act4 = {testMob2, testMob3};

        // Initializing Default Values 
        int damageDealt = 0; // Stores Damage Dealt by Characters
        int healingDone = 0; // Stores Healing Done by Characters
        boolean isAlive = true;

        ClearConsole.clearConsole(); // Clears the console screen

        // Path to Intro text file
        String filePathIntro = "Story/Intro.txt";
        TextFileReader readerIntro = new TextFileReader(filePathIntro);
        readerIntro.readTextFileWithDelay(); // Reads and displays the intro text with a delay

        ClearConsole.clearOnEnter();
        ClearConsole.clearOnEnter();

        // Path to Character Selection text file
        String filePathCharSelection = "Story/CharSelection.txt";
        TextFileReader readerCharSelection = new TextFileReader(filePathCharSelection);
        readerCharSelection.readTextFileWithDelay(); // Reads and displays character selection text with a delay

        // Handle file path Variables for different story parts
        String folderPath = "Story/";
        String folderOrigin = "Origins/";
        String folderTutorial = "Tutorial/";
        String folderAct1 = "Act1/";
        String folderAct2 = "Act2/";
        String folderAct3 = "Act3/";
        String folderAct4 = "Act4/";
        String fileName = "";

        // Create an instance of CharacterSelector
        CharacterSelector selector = new CharacterSelector();

        // Call selectCharacter method to choose a character
        selector.selectCharacter(availableCharacters);

        Character mainCharacter = selector.getMainCharacter();
        Character[] mainCharacter2 = {mainCharacter};

        // Determine character file based on selected character type
        if (mainCharacter instanceof Barbarian) {
            fileName = "Barbarian.txt";
        } else if (mainCharacter instanceof Paladin) {
            fileName = "Paladin.txt";
        } else if (mainCharacter instanceof Bard) {
            fileName = "Bard.txt";
        } else if (mainCharacter instanceof Astrologer) {
            fileName = "Astrologer.txt";
        }

        ClearConsole.clearConsoleAfterDelay(3000); // Clears console after a delay

        // Read character origin story
        String filePathOrigin = folderPath + folderOrigin + fileName;
        TextFileReader readerOrigin = new TextFileReader(filePathOrigin);
        readerOrigin.readTextFileWithDelay(); // Reads and displays character origin story with a delay

        ClearConsole.clearOnEnter();
        ClearConsole.clearOnEnter();

        // Read tutorial related to the character
        String filePathTutorial = folderPath + folderTutorial + fileName;
        TextFileReader readerTutorial = new TextFileReader(filePathTutorial);
        readerTutorial.readTextFileWithDelay(); // Reads and displays character tutorial with a delay

        ClearConsole.clearOnEnter();
        ClearConsole.clearOnEnter();

        // Read Act 1 story related to the character
        String filePathAct1 = folderPath + folderAct1 + fileName;
        TextFileReader readerAct1 = new TextFileReader(filePathAct1);
        readerAct1.readTextFileWithDelay(); // Reads and displays Act 1 story with a delay

        ClearConsole.clearOnEnter();
        ClearConsole.clearOnEnter();

        // Act 1 gameplay loop
        while (GameOutcomeChecker.anyEnemyAlive(act1) && GameOutcomeChecker.anyCharacterAlive(availableCharacters)) {
            // Display main character's status
            System.out.println("Main Character's Health: " + mainCharacter.getHealth());
            System.out.println("Main Character's Stamina: " + mainCharacter.getStamina());
            System.out.println("Main Character's Mana: " + mainCharacter.getMana());
            System.out.println("Main Character's Divinity: " + mainCharacter.getDivinity());

            // Perform actions for main character, NPCs, and enemies in Act 1
            McActions.performActions(mainCharacter, act1, damageDealt, healingDone);
            EnemyActions.performEnemyActions(mainCharacter2, mainCharacter, act1, damageDealt, healingDone);

            ClearConsole.clearOnEnter();
            ClearConsole.clearOnEnter();
        }

        // Check game outcome for Act 1
        GameOutcomeChecker.checkGameOutcome(availableCharacters, act1);

        System.out.println("Place Holder For Act2 StoryLine");

        ClearConsole.clearOnEnter();
        ClearConsole.clearOnEnter();

        // Act 2 gameplay loop
        while (GameOutcomeChecker.anyEnemyAlive(act2) && GameOutcomeChecker.anyCharacterAlive(availableCharacters)) {
            // Display main character's status
            System.out.println("Main Character's Health: " + mainCharacter.getHealth());
            System.out.println("Main Character's Stamina: " + mainCharacter.getStamina());
            System.out.println("Main Character's Mana: " + mainCharacter.getMana());
            System.out.println("Main Character's Divinity: " + mainCharacter.getDivinity());

            // Perform actions for main character, NPCs, and enemies in Act 2
            McActions.performActions(mainCharacter, act2, damageDealt, healingDone);
            NpcActions.performActions(mainCharacter2, mainCharacter, act2, damageDealt, healingDone);
            EnemyActions.performEnemyActions(availableCharacters, mainCharacter, act2, damageDealt, healingDone);

            ClearConsole.clearOnEnter();
            ClearConsole.clearOnEnter();
        }

        // Check game outcome for Act 2
        GameOutcomeChecker.checkGameOutcome(availableCharacters, act2);

        System.out.println("Congrats, you have reached the End of the Demo, More story is on its way, as well as more enemies to fight");

        // Close scanner
        scanner.close();
    }
}
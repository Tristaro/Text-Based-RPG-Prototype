/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - Bard.java
*/
public class Bard implements Character {

    // Attributes for the Bard Character
    private String name; // Name of the Bard
    private int health; // Health of the Bard
    private int mana; // Mana of the Bard
    private int stamina; // Stamina of the Bard
    private int divinity; // Divinity of the Bard

    // Additional attributes 
    private int damageReductionBonus = 0; // Stores the damage reduction from 'Block'
    private Dice dice; // Handles dice rolling for the Bard's actions

    // Initialize Bard Attributes with default values
    public Bard(String name) {
        this.name = name; // Set the provided name for the Bard
        this.health = 100; // Set default health to 100 for the Bard
        this.mana = 200; // Set default mana to 200 for the Bard
        this.stamina = 200; // Set default stamina to 200 for the Bard
        this.divinity = 0; // Set default divinity to 0 for the Bard
        this.dice = new Dice(); // Create a new Dice object for handling random actions
        this.damageReductionBonus = 0; // Initialize damage reduction bonus to 0 for the Bard
}

    // Implementing methods from Character interface

    // Set the name of the Bard
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Get the name of the Bard
    @Override
    public String getName() {
        return name;
    }

    // Get the health of the Bard
    @Override
    public int getHealth() {
        return health;
    }

    // Maximum health of the Bard
    @Override
    public int maxHealth() {
        return this.health; 
    }

    // Set the health of the Bard
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    // Get the mana of the Bard
    @Override
    public int getMana() {
        return mana;
    }

    // Maximum mana of the Bard
    @Override
    public int maxMana() {
        return this.mana; 
    }

    // Set the mana of the Bard
    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    // Get the stamina of the Bard
    @Override
    public int getStamina() {
        return stamina;
    }

    // Maximum stamina of the Bard
    @Override
    public int maxStamina() {
        return this.stamina; 
    }

    // Set the stamina of the Bard
    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    // Get the divinity of the Bard
    @Override
    public int getDivinity() {
        return divinity;
    }

    // Maximum divinity of the Bard
    @Override
    public int maxDivinity() {
        return this.divinity; 
    }

    // Set the divinity of the Bard
    @Override
    public void setDivinity(int divinity) {
        this.divinity = divinity;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

// Methods related to Attacks and Actions

    // Calculate Damage taken by Character
    @Override
    public void takeDamage(int damage) {
        // Check if there's a damage reduction bonus
        if (damageReductionBonus > 0) {
            // Reduce damage based on the damage reduction bonus
            if (damage <= damageReductionBonus) {
                damageReductionBonus -= damage;
                System.out.println(this.name + " takes " + damage + " damage! Damage Reduction Bonus: " + damageReductionBonus);
                return;
            } else {
                damage -= damageReductionBonus;
                damageReductionBonus = 0;
            }
        }
        // Inflict the remaining damage to the character's health
        this.health -= damage;
        System.out.println(this.name + " takes " + damage + " damage!");
        sayCatchphrase("Damage Taken");
    }

    // Method to handle character death for Bard
    @Override
    public void CharDeath() {
        // Check if the character's health is zero or below
        if (this.health <= 0) {
            sayCatchphrase("Death");
            System.out.println(this.name + " has been defeated!");
        }
    }

    // Character's Damage Negating Move Name
    @Override
    public String getBlockName() {
        return ""; // There's no specific name for the damage negating move
    }

    // Character's Damage Negating Move 
    @Override
    public void block() {
        sayCatchphrase("Block");
        int damageReduction = dice.rollD10(); // Roll a D10 for damage reduction
        System.out.println(this.name + " uses block and reduces incoming damage by " + damageReduction + "!");
        damageReductionBonus += damageReduction; // Store the damage reduction bonus
    }
        
    // Restores Mana & Stamina & Divinity
    @Override
    public void restore() {
        this.mana += 40; // Restore 40 mana
        this.stamina += 30; // Restore 30 stamina
    }

// Melee Attacks

    // Character's Primary Attack Name
    @Override
    public String getPrimaryAttackName() {
        return "Slashing Chord"; // Returns the name of the primary attack
    }

    // Character's Primary Attack
    @Override
    public void primaryAttack(Character target, int damage) {
        sayCatchphrase("Slashing Chord"); // Display character's action for primary attack
        int staminaCost = 20; // Define stamina cost for primary attack
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(2, 6); // Calculate damage for Slashing Chord
            System.out.println(this.name + " performs a Slashing Chord and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage); // Inflict calculated damage on the target
            stamina -= staminaCost; // Reduce stamina after performing the attack
        } else {
            System.out.println("Not enough stamina to perform a primary attack!"); // Display message if insufficient stamina
        }
        restore(); // Restore character's attributes after the attack
    }

    // Character's Secondary Attack Name
    @Override
    public String getSecondaryAttackName() {
        return "Mesmerizing Melody"; // Returns the name of the secondary attack
    }

    // Character's Secondary Attack
    @Override
    public void secondaryAttack(Character target, int damage) {
        sayCatchphrase("Mesmerizing Melody"); // Display character's action for secondary attack
        int staminaCost = 30; // Define stamina cost for secondary attack
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(2, 10); // Calculate damage for Mesmerizing Melody
            System.out.println(this.name + " performs a Mesmerizing Melody and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage); // Inflict calculated damage on the target
            stamina -= staminaCost; // Reduce stamina after performing the attack
        } else {
            System.out.println("Not enough stamina to perform a secondary attack!"); // Display message if insufficient stamina
        }
        restore(); // Restore character's attributes after the attack
    }

    // Character's Special Attack Name
    @Override
    public  String getSpecialAttackName() {
        return "Crescendo Strike"; // Returns the name of the special attack
    }

    // Character's Special Attack
    @Override
    public void specialAttack(Character target, int damage) {
        sayCatchphrase("Crescendo Strike"); // Display character's action for special attack
        int staminaCost = 40; // Define stamina cost for special attack
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(3, 12); // Calculate damage for Crescendo Strike
            System.out.println(this.name + " performs a Crescendo Strike and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage); // Inflict calculated damage on the target
            stamina -= staminaCost; // Reduce stamina after performing the attack
        } else {
            System.out.println("Not enough stamina to perform a special attack!"); // Display message if insufficient stamina
        }
        restore(); // Restore character's attributes after the attack
    }

// Magic Abilities

    // Character's Primary Magic Name
    @Override
    public  String getPrimaryMagicName() {
        return "Dissonant Echo"; // Returns the name of the primary magic
    }

    // Character's Primary Magic
    @Override
    public void primaryMagic(Character target, int damage) {
        sayCatchphrase("Dissonant Echo"); // Display character's action for primary magic
        int manaCost = 50; // Define mana cost for primary magic
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(4, 6); // Calculate damage for Dissonant Echo
            System.out.println(this.name + " casts Dissonant Echo and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage); // Inflict calculated damage on the target
            mana -= manaCost; // Reduce mana after performing the magic attack
        } else {
            System.out.println("Not enough mana to perform a primary magic attack!"); // Display message if insufficient mana
        }
        restore(); // Restore character's attributes after the attack
    }

    // Character's Secondary Magic Name
    @Override
    public  String getSecondaryMagicName() {
        return "Harmonic Resonance"; // Returns the name of the secondary magic
    }

    // Character's Secondary Magic
    @Override
    public void secondaryMagic(Character target, int damage) {
        sayCatchphrase("Harmonic Resonance"); // Display character's action for secondary magic
        int manaCost = 60; // Define mana cost for secondary magic
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(3, 10); // Calculate damage for Harmonic Resonance
            System.out.println(this.name + " casts Harmonic Resonance and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage); // Inflict calculated damage on the target
            mana -= manaCost; // Reduce mana after performing the magic attack
        } else {
            System.out.println("Not enough mana to perform a secondary magic attack!"); // Display message if insufficient mana
        }
        restore(); // Restore character's attributes after the attack
    }

    // Character's Tertiary Magic Name
    @Override
    public  String getTertiaryMagicName() {
        return "Resounding Echo"; // Returns the name of the tertiary magic
    }

    // Character's Tertiary Magic
    @Override
    public void tertiaryMagic(Character target, int damage) {
        sayCatchphrase("Resounding Echo"); // Display character's action for tertiary magic
        int manaCost = 40; // Define mana cost for tertiary magic
        int staminaCost = 30; // Define stamina cost for tertiary magic
        if (mana >= manaCost && stamina >= staminaCost) {
            int damageDealt = dice.rollDice(4, 10); // Calculate damage for Resounding Echo
            System.out.println(this.name + " unleashes a Resounding Echo and deals " + (damageDealt - damage) + " damage to enemies!");
            // Apply damage or additional effects to enemies
            mana -= manaCost; // Reduce mana after performing the magic attack
            stamina -= staminaCost; // Reduce stamina after performing the magic attack
        } else {
            System.out.println("Not enough mana or stamina to perform the attack!"); // Display message if insufficient resources
        }
        restore(); // Restore character's attributes after the attack
    }

    // Character's 'Heal' Name
    @Override
    public  String getHealName() {
        return "Healing Harmony"; // Returns the name of the healing ability
    }

    // Character's 'Heal'
    @Override
    public void heal(Character target, int damage) {
        sayCatchphrase("Healing"); // Display character's action for healing
        int manaCost = 50; // Define mana cost for healing
        if (mana >= manaCost) {
            int healingDone = dice.rollDice(2, 8); // Calculate healing for Healing Harmony
            if (target instanceof Bard) {
                ((Bard) target).setHealth(target.getHealth() + healingDone); // Heal allied Bard
                System.out.println(this.name + " uses Healing Harmony on " + target.getName() + " and heals for " + healingDone + "!");
            } else {
                System.out.println("Healing Harmony can only target allied Bards!"); // Display message for invalid target
                return;
            }
            mana -= manaCost; // Reduce mana after performing the healing
        } else {
            System.out.println("Not enough mana to perform a healing action!"); // Display message if insufficient mana
        }
        restore(); // Restore character's attributes after the action
    }
 
    // Method to display catchphrases based on actions
    @Override
    public void sayCatchphrase(String action) {
        // Switch statement to determine the appropriate catchphrase for the given action
        switch (action) {
            case "Slashing Chord":
                System.out.println(this.name + ": 'Strumming the song of battle!'"); // Display catchphrase for Slashing Chord
                break;
            case "Mesmerizing Melody":
                System.out.println(this.name + ": 'Entangled in the web of my music!'"); // Display catchphrase for Mesmerizing Melody
                break;
            case "Crescendo Strike":
                System.out.println(this.name + ": 'The finale approaches with a flourish!'"); // Display catchphrase for Crescendo Strike
                break;
            case "Dissonant Echo":
                System.out.println(this.name + ": 'Discord echoes through the air!'"); // Display catchphrase for Dissonant Echo
                break;
            case "Harmonic Resonance":
                System.out.println(this.name + ": 'Harmony guides my every note!'"); // Display catchphrase for Harmonic Resonance
                break;
            case "Resounding Echo":
                System.out.println(this.name + ": 'Echoes reverberate in symphony!'"); // Display catchphrase for Resounding Echo
                break;
            case "Heal":
                System.out.println(this.name + ": 'Melodies of mending soothe our wounds!'"); // Display catchphrase for Heal
                break;
            case "Block":
                System.out.println(this.name + ": 'Warding off the impending strike!'"); // Display catchphrase for Block
                break;
            case "Damage Taken":
                System.out.println(this.name + ": 'Yet the song continues, undeterred!'"); // Display catchphrase for Damage Taken
                break;
            case "Death":
                System.out.println(this.name + ": 'My song ends here...'"); // Display catchphrase for Death
                break;
            default:
                System.out.println(this.name + ": 'The music of adventure never ends!'"); // Default catchphrase for unknown actions
                break;
        }
    }    
}
/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - Paldin.java
*/
public class Paladin implements Character {

    // Attributes for the Paladin Character
    private String name; // Name of the Paladin
    private int health; // Health of the Paladin
    private int mana; // Mana of the Paladin
    private int stamina; // Stamina of the Paladin
    private int divinity; // Divinity of the Paladin
    
    // Additional attributes 
    private int damageReductionBonus = 0; // Stores the additional damage reduction from the 'Block' action
    private Dice dice; // Handles dice rolling for the Paladin's actions

    // Initialize Paladin Attributes with default values
    public Paladin(String name) {
        this.name = name; // Set the provided name for the Paladin
        this.health = 100; // Set default health to 100
        this.mana = 0; // Set default mana to 0
        this.stamina = 200; // Set default stamina to 200  
        this.divinity = 200; // Set default divinity to 200      
        this.dice = new Dice(); // Initialize a Dice object for random actions
        this.damageReductionBonus = 0; // Initialize damage reduction bonus to 0
    }

    // Implementing methods from Character interface

    // Set the name of the Paldin
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Get the name of the Paldin
    @Override
    public String getName() {
        return name;
    }

    // Get the health of the Paldin
    @Override
    public int getHealth() {
        return health;
    }

    // Maximum health of the Paldin
    @Override
    public int maxHealth() {
        return this.health; 
    }

    // Set the health of the Paldin
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    // Get the mana of the Paldin
    @Override
    public int getMana() {
        return mana;
    }

    // Maximum mana of the Paldin
    @Override
    public int maxMana() {
        return this.mana; 
    }

    // Set the mana of the Paldin
    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    // Get the stamina of the Paldin
    @Override
    public int getStamina() {
        return stamina;
    }

    // Maximum stamina of the Paldin
    @Override
    public int maxStamina() {
        return this.stamina; 
    }

    // Set the stamina of the Paldin
    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    // Get the divinity of the Paldin
    @Override
    public int getDivinity() {
        return divinity;
    }

    // Maximum divinity of the Paldin
    @Override
    public int maxDivinity() {
        return this.divinity; 
    }

    // Set the divinity of the Paldin
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
        // Check if there's damage reduction bonus
        if (damageReductionBonus > 0) {
            // Reduce damage based on damage reduction bonus
            if (damage <= damageReductionBonus) {
                damageReductionBonus -= damage;
                System.out.println(this.name + " takes " + damage + " damage! Damage Reduction Bonus: " + damageReductionBonus);
                return;
            } else {
                damage -= damageReductionBonus;
                damageReductionBonus = 0;
            }
        }
        // If there's no damage reduction bonus or remaining damage after reduction, deduct from health
        this.health -= damage;
        System.out.println(this.name + " takes " + damage + " damage!");
        sayCatchphrase("Damage Taken"); // Display catchphrase for damage taken
    }

    // Method to handle character death for Paladin
    @Override
    public void CharDeath() {
        // Check if the character's health reaches zero or less
        if (this.health <= 0) {
            sayCatchphrase("Death"); // Display catchphrase for death
            System.out.println(this.name + " has been defeated!"); // Inform character defeat
        }
    }

    // Character's Damage Negating Move Name
    @Override
    public String getBlockName() {
        return ""; // Placeholder for the character's damage negating move name
    }

    // Character's Damage Negating Move 
    @Override
    public void block() {
        sayCatchphrase("Block"); // Display catchphrase for blocking
        int damageReduction = dice.rollD10(); // Roll a D10 for damage reduction
        System.out.println(this.name + " uses block and reduces incoming damage by " + damageReduction + "!");
        damageReductionBonus += damageReduction; // Store the damage reduction bonus
    }
        
    // Restores Mana & Stamina & Divinity
    @Override
    public void restore() {
        this.stamina += 30; // Restore 30 stamina
        this.divinity += 30; // Restore 30 Divinity
    }

// Melee Attacks

    // Character's Primary Attack Name
    @Override
    public String getPrimaryAttackName() {
        return "Smiting Strike"; // Name of the primary attack
    }

    // Character's Primary Attack
    @Override
    public void primaryAttack(Character target, int damage) {
        sayCatchphrase("Smiting Strike");
        int staminaCost = 20;
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(2, 8); // Roll 2D8 for damage with Smiting Strike
            System.out.println(this.name + " performs a Smiting Strike and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform a primary attack!");
        }
        restore(); // Restore after the attack
    }

    // Character's Secondary Attack Name
    @Override
    public String getSecondaryAttackName() {
        return "Righteous Blow"; // Name of the secondary attack
    }

    // Character's Secondary Attack
    @Override
    public void secondaryAttack(Character target, int damage) {
        sayCatchphrase("Righteous Blow");
        int staminaCost = 30;
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(2, 10); // Roll 2D10 for damage with Righteous Blow
            System.out.println(this.name + " performs a Righteous Blow and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform a secondary attack!");
        }
        restore(); // Restore after the attack
    }

    // Character's Special Attack Name
    @Override
    public  String getSpecialAttackName() {
        return "Divine Retribution"; // Name of the special attack
    }

    // Character's Special Attack
    @Override
    public void specialAttack(Character target, int damage) {
        sayCatchphrase("Divine Retribution");
        int staminaCost = 40;
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(3, 10); // Roll 3D10 for damage with Divine Retribution
            System.out.println(this.name + " performs a Divine Retribution and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform a special attack!");
        }
        restore(); // Restore after the attack
    }

// Divine Abilities

    // Character's Primary Magic Name
    @Override
    public String getPrimaryMagicName() {
        return "Holy Smite"; // Name of the primary magic attack
    }

    // Character's Primary Magic
    @Override
    public void primaryMagic(Character target, int damage) {
        sayCatchphrase("Holy Smite");
        int divinityCost = 50;
        if (divinity >= divinityCost) {
            int damageDealt = dice.rollDice(4, 8); // Roll 4D8 for damage with Holy Smite
            System.out.println(this.name + " casts Holy Smite and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough divinity to perform a primary magic attack!");
        }
        restore(); // Restore after the attack
    }

    // Character's Secondary Magic Name
    @Override
    public String getSecondaryMagicName() {
        return "Radiant Burst"; // Name of the secondary magic attack
    }

    // Character's Secondary Magic
    @Override
    public void secondaryMagic(Character target, int damage) {
        sayCatchphrase("Radiant Burst");
        int divinityCost = 60;
        if (divinity >= divinityCost) {
            int damageDealt = dice.rollDice(4, 10); // Roll 4D10 for damage with Radiant Burst
            System.out.println(this.name + " casts Radiant Burst and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough divinity to perform a secondary magic attack!");
        }
        restore(); // Restore after the attack
    }

    // Character's Tertiary Magic Name
    @Override
    public String getTertiaryMagicName() {
        return "Sanctified Judgment"; // Name of the tertiary magic attack
    }

    // Character's Tertiary Magic
    @Override
    public void tertiaryMagic(Character target, int damage) {
        sayCatchphrase("Sanctified Judgment");
        int divinityCost = 70;
        if (divinity >= divinityCost) {
            int damageDealt = dice.rollDice(5, 10); // Roll 5D10 for damage with Sanctified Judgment
            System.out.println(this.name + " casts Sanctified Judgment and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough divinity to perform a tertiary magic attack!");
        }
        restore(); // Restore after the attack
    }

    // Character's 'Heal' Name
    @Override
    public String getHealName() {
        return "Divine Heal"; // Name of the healing action
    }

    // Character's 'Heal'
    @Override
    public void heal(Character target, int damage) {
        sayCatchphrase("Healing");
        int divinityCost = 40;
        if (divinity >= divinityCost) {
            int healingDone = dice.rollDice(3, 8); // Roll 3D8 for healing with Divine Heal

            if (target == this) {
                this.health += healingDone;
                System.out.println(this.name + " uses Divine Heal on themselves and heals for " + healingDone + "!");
            } else {
                target.setHealth(target.getHealth() + healingDone);
                System.out.println(this.name + " uses Divine Heal on " + target.getName() + " and heals for " + healingDone + "!");
            }
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough divinity to perform a healing action!");
        }
        restore(); // Restore after the action
    }

    // Method to display catchphrases for character actions
    @Override
    public void sayCatchphrase(String action) {
        // Switch statement to determine the appropriate catchphrase for the given action
        switch (action) {
            case "Smiting Strike":
                System.out.println(this.name + ": 'May righteousness guide my blade!'"); // Display catchphrase for Smiting Strike
                break;
            case "Righteous Blow":
                System.out.println(this.name + ": 'In the name of justice, fall!'"); // Display catchphrase for Righteous Blow
                break;
            case "Divine Retribution":
                System.out.println(this.name + ": 'Divine retribution shall be swift!'"); // Display catchphrase for Divine Retribution
                break;
            case "Holy Smite":
                System.out.println(this.name + ": 'By the light, be cleansed!'"); // Display catchphrase for Holy Smite
                break;
            case "Radiant Burst":
                System.out.println(this.name + ": 'Radiance, illuminate our path!'"); // Display catchphrase for Radiant Burst
                break;
            case "Sanctified Judgment":
                System.out.println(this.name + ": 'Let judgment be cast upon the unworthy!'"); // Display catchphrase for Sanctified Judgment
                break;
            case "Healing":
                System.out.println(this.name + ": 'The divine's grace heals our wounds!'"); // Display catchphrase for Healing
                break;
            case "Block":
                System.out.println(this.name + ": 'I shall defend with unwavering resolve!'"); // Display catchphrase for Block
                break;
            case "Damage Taken":
                System.out.println(this.name + ": 'Adversity strengthens our cause!'"); // Display catchphrase for Damage Taken
                break;
            case "Death":
                System.out.println(this.name + ": 'My duty... fulfilled.'"); // Display catchphrase for Death
                break;
            default:
                System.out.println(this.name + ": 'For honor and valor!'"); // Default catchphrase for unknown actions
                break;
        }
    }
}
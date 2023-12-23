/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - Barbarian.java
*/
public class Barbarian implements Character {

    // Attributes for the Barbarian Character
    private String name; // Name of the Barbarian
    private int health; // Health of the Barbarian
    private int mana; // Mana of the Barbarian
    private int stamina; // Stamina of the Barbarian
    private int divinity; // Divinity of the Barbarian
    
    // Additional attributes 
    private int damageReductionBonus = 0; // Stores the damage reduction from 'Block'
    private Dice dice; // Handles dice rolling for the Barbarian's actions

    // Initialize Barbarian Attributes with default values
    public Barbarian(String name) {
        this.name = name; // Set the provided name for the Barbarian
        this.health = 200; // Set default health to 200 for the Barbarian
        this.mana = 100; // Set default mana to 100 for the Barbarian
        this.stamina = 200; // Set default stamina to 200 for the Barbarian
        this.divinity = 0; // Set default divinity to 0 for the Barbarian
        this.dice = new Dice(); // Create a new Dice object for handling random actions
        this.damageReductionBonus = 0; // Initialize damage reduction bonus to 0 for the Barbarian
    }

    // Implementing methods from Character interface

    // Set the name of the Barbarian
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Get the name of the Barbarian
    @Override
    public String getName() {
        return name;
    }

    // Get the health of the Barbarian
    @Override
    public int getHealth() {
        return health;
    }

    // Maximum health of the Barbarian
    @Override
    public int maxHealth() {
        return this.health; 
    }

    // Set the health of the Barbarian
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    // Get the mana of the Barbarian
    @Override
    public int getMana() {
        return mana;
    }

    // Maximum mana of the Barbarian
    @Override
    public int maxMana() {
        return this.mana; 
    }

    // Set the mana of the Barbarian
    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    // Get the stamina of the Barbarian
    @Override
    public int getStamina() {
        return stamina;
    }

    // Maximum stamina of the Barbarian
    @Override
    public int maxStamina() {
        return this.stamina; 
    }

    // Set the stamina of the Barbarian
    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    // Get the divinity of the Barbarian
    @Override
    public int getDivinity() {
        return divinity;
    }

    // Maximum divinity of the Barbarian
    @Override
    public int maxDivinity() {
        return this.divinity; 
    }

    // Set the divinity of the Barbarian
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
            // If the damage is less than or equal to the bonus, reduce the bonus
            if (damage <= damageReductionBonus) {
                damageReductionBonus -= damage;
                System.out.println(this.name + " takes " + damage + " damage! Damage Reduction Bonus: " + damageReductionBonus);
                return;
            } else {
                // Reduce incoming damage by the bonus and reset the bonus
                damage -= damageReductionBonus;
                damageReductionBonus = 0;
            }
        }
        // Reduce character's health by remaining damage after bonus
        this.health -= damage;
        System.out.println(this.name + " takes " + damage + " damage!");
        sayCatchphrase("Damage Taken");
    }

    // Method to handle character death for TestMob
    @Override
    public void CharDeath() {
        // Check if the character's health is zero or less
        if (this.health <= 0) {
            sayCatchphrase("Death");
            System.out.println(this.name + " has been defeated!");
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
        sayCatchphrase("Block");
        // Roll a D10 for damage reduction
        int damageReduction = dice.rollD10();
        System.out.println(this.name + " uses block and reduces incoming damage by " + damageReduction + "!");
        // Store the damage reduction bonus
        damageReductionBonus += damageReduction;
    }
        
    // Restores Mana & Stamina & Divinity
    @Override
    public void restore() {
        this.mana += 20; // Restore 20 mana
        this.stamina += 30; // Restore 30 stamina
    }

// Melee Attacks

    // Character's Primary Attack Name
    @Override
    public String getPrimaryAttackName() {
        return "Crushing Blow"; // Name of the primary attack
    }

    // Character's Primary Attack
    @Override
    public void primaryAttack(Character target, int damage) {
        sayCatchphrase("Crushing Blow");
        int staminaCost = 20;
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(1, 8); // Roll 1D8 for damage with Crushing Blow
            System.out.println(this.name + " performs a Crushing Blow and deals " + (damageDealt - damage) + " damage!");
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
        return "Whirling Stike"; // Name of the secondary attack
    }

    // Character's Secondary Attack
    @Override
    public void secondaryAttack(Character target, int damage) {
        sayCatchphrase("Whirling Stike");
        int staminaCost = 30;
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollD12() + dice.rollD6(); // Roll 1D12 + 1D6 for damage with Whirling Strike
            System.out.println(this.name + " performs a Whirling Strike and deals " + (damageDealt - damage) + " damage!");
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
        return "Savage Cleave"; // Name of the special attack
    }

    // Character's Special Attack
    @Override
    public void specialAttack(Character target, int damage) {
        sayCatchphrase("Savage Cleave");
        int staminaCost = 40;
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(3, 12); // Roll 3D12 for damage with Savage Cleave
            System.out.println(this.name + " performs a Savage Cleave and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform a special attack!");
        }
        restore(); // Restore after the attack
    }
        
// Magic 

    // Character's Primary Magic Name
    @Override
    public String getPrimaryMagicName() {
        return "Thundering Shockwave"; // Name of the primary magic attack
    }

    // Character's Primary Magic
    @Override
    public void primaryMagic(Character target, int damage) {
        sayCatchphrase("Thundering Shockwave");
        int manaCost = 50;
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(4, 6); // Thundering Shockwave: Roll 4D6 for damage
            System.out.println(this.name + " casts Thundering Shockwave and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform a primary magic attack!");
        }
        restore(); // Restore after the attack
    }

    // Character's Secondary Magic Name
    @Override
    public String getSecondaryMagicName() {
        return "Firey Rampaget"; // Name of the secondary magic attack
    }

    // Character's Secondary Magic
    @Override
    public void secondaryMagic(Character target, int damage) {
        sayCatchphrase("Firey Rampage");
        int manaCost = 60;
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(3, 8); // Fiery Rampage: Roll 3D8 for damage
            System.out.println(this.name + " casts Fiery Rampage and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform a secondary magic attack!");
        }
        restore(); // Restore after the attack
    }

    // Character's Tertiary Magic Name
    @Override
    public String getTertiaryMagicName() {
        return "Frostbite Fury"; // Name of the tertiary magic attack
    }

    // Character's Tertiary Magic
    @Override
    public void tertiaryMagic(Character target, int damage) {
        sayCatchphrase("Frostbite Fury");
        int manaCost = 70;
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(2, 10); // Frostbite Fury: Roll 2D10 for damage
            System.out.println(this.name + " casts Frostbite Fury and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform a tertiary magic attack!");
        }
        restore(); // Restore after the attack
    }

    // Character's 'Heal' Name
    @Override
    public String getHealName() {
        return "Vitality Surge"; // Name of the healing action
    }

    // Character's 'Heal'
    @Override
    public void heal(Character target, int damage) {
        sayCatchphrase("Healing");
        int manaCost = 40;
        if (mana >= manaCost) {
            int healingDone = dice.rollDice(3, 6); // Vitality Surge: Roll 3D6 for healing

            if (target == this) {
                this.health += healingDone;
                System.out.println(this.name + " uses Vitality Surge on themselves and heals for " + healingDone + "!");
            } else {
                target.setHealth(target.getHealth() + healingDone);
                System.out.println(this.name + " uses Vitality Surge on " + target.getName() + " and heals for " + healingDone + "!");
            }
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform a healing action!");
        }
        restore(); // Restore after the action
    }

    // Method to display catchphrases for character actions
    @Override
    public void sayCatchphrase(String action) {
        // Switch statement to determine the appropriate catchphrase for the given action
        switch (action) {
            case "Crushing Blow":
                System.out.println(this.name + ": 'Feel the might of the mountains!'"); // Display catchphrase for Crushing Blow
                break;
            case "Whirling Strike":
                System.out.println(this.name + ": 'None can withstand my fury!'"); // Display catchphrase for Whirling Strike
                break;
            case "Savage Cleave":
                System.out.println(this.name + ": 'Witness the wrath of nature!'"); // Display catchphrase for Savage Cleave
                break;
            case "Thundering Shockwave":
                System.out.println(this.name + ": 'Thunder roars at my command!'"); // Display catchphrase for Thundering Shockwave
                break;
            case "Fiery Rampage":
                System.out.println(this.name + ": 'Burn in the flames of my rage!'"); // Display catchphrase for Fiery Rampage
                break;
            case "Frostbite Fury":
                System.out.println(this.name + ": 'Feel the chill of winter's embrace!'"); // Display catchphrase for Frostbite Fury
                break;
            case "Healing":
                System.out.println(this.name + ": 'Life flows through us once more!'"); // Display catchphrase for Healing
                break;
            case "Block":
                System.out.println(this.name + ": 'I shall weather this storm!'"); // Display catchphrase for Block
                break;
            case "Damage Taken":
                System.out.println(this.name + ": 'Is that all you've got?'"); // Display catchphrase for Damage Taken
                break;
            case "Death":
                System.out.println(this.name + ": 'My spirit remains unbroken, even in death!'"); // Display catchphrase for Death
                break;
            default:
                System.out.println(this.name + ": 'For honor and glory!'"); // Default catchphrase for unknown actions
                break;
        }
    }
}
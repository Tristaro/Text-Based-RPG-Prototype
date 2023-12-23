/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - EliteSoldier.java
*/
public class EliteSoldier implements Character {

    // Attributes for the Elite Soldier Character
    private String name; // Name of the Elite Soldier
    private int health; // Health of the Elite Soldier
    private int mana; // Mana of the Elite Soldier
    private int stamina; // Stamina of the Elite Soldier
    private int divinity; // Divinity of the Elite Soldier

    // Additional attributes
    private int damageReductionBonus = 0; // Stores the damage reduction from 'Block'
    private Dice dice; // Handles dice rolling for the Elite Soldier's actions

    // Initialize Elite Soldier Attributes with default values
    public EliteSoldier(String name, int health, int mana, int stamina, int divinity) {
        this.name = name; // Set the provided name for the Elite Soldier
        this.health = health; // Set default health for the Elite Soldier
        this.mana = mana; // Set default mana for the Elite Soldier
        this.stamina = stamina; // Set default stamina for the Elite Soldier
        this.divinity = divinity; // Set default divinity for the Elite Soldier
        this.dice = new Dice(); // Create a new Dice object for handling random actions
        this.damageReductionBonus = 0; // Initialize damage reduction bonus to 0 for the Elite Soldier
    }

    // Implementing methods from Character interface

    // Set the name of the Elite Soldier
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Get the name of the Elite Soldier
    @Override
    public String getName() {
        return name;
    }

    // Get the health of the Elite Soldier
    @Override
    public int getHealth() {
        return health;
    }

    // Maximum health of the Elite Soldier
    @Override
    public int maxHealth() {
        return this.health;
    }

    // Set the health of the Elite Soldier
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    // Get the mana of the Elite Soldier
    @Override
    public int getMana() {
        return mana;
    }

    // Maximum mana of the Elite Soldier
    @Override
    public int maxMana() {
        return this.mana;
    }

    // Set the mana of the Elite Soldier
    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    // Get the stamina of the Elite Soldier
    @Override
    public int getStamina() {
        return stamina;
    }

    // Maximum stamina of the Elite Soldier
    @Override
    public int maxStamina() {
        return this.stamina;
    }

    // Set the stamina of the Elite Soldier
    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    // Get the divinity of the Elite Soldier
    @Override
    public int getDivinity() {
        return divinity;
    }

    // Maximum divinity of the Elite Soldier
    @Override
    public int maxDivinity() {
        return this.divinity;
    }

    // Set the divinity of the Elite Soldier
    @Override
    public void setDivinity(int divinity) {
        this.divinity = divinity;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

// Methods related to Attacks and Actions

        // Calculate damage taken by the Elite Soldier
    @Override
    public void takeDamage(int damage) {
        // Check for damage reduction bonus
        if (damageReductionBonus > 0) {
            // If damage is less than or equal to damage reduction bonus
            if (damage <= damageReductionBonus) {
                damageReductionBonus -= damage;
                System.out.println(this.name + " takes " + damage + " damage! Damage Reduction Bonus: " + damageReductionBonus);
                return;
            } else {
                damage -= damageReductionBonus;
                damageReductionBonus = 0;
            }
        }
        // Deduct damage from the Elite Soldier's health
        this.health -= damage;
        System.out.println(this.name + " takes " + damage + " damage!");
        sayCatchphrase("Damage Taken");
    }

    // Method to handle character death for EliteSoldier
    @Override
    public void CharDeath() {
        if (this.health <= 0) {
            sayCatchphrase("Death");
            System.out.println(this.name + " has been defeated!");
        }
    }

    // Adjustments to the Elite Soldier's block move
    @Override
    public String getBlockName() {
        return "Fortified Stance"; // Name of the Elite Soldier's block move
    }

    @Override
    public void block() {
        sayCatchphrase("Fortified Stance");
        int damageReduction = dice.rollD12(); // Roll a D12 for damage reduction
        System.out.println(this.name + " assumes a Fortified Stance, reducing incoming damage by " + damageReduction + "!");
        damageReductionBonus += damageReduction; // Store the damage reduction bonus
    }

    // Restores Mana, Stamina, and Divinity for the Elite Soldier
    @Override
    public void restore() {
        this.mana += 20; // Restore 20 mana
        this.stamina += 30; // Restore 30 stamina
        this.divinity += 25; // Restore 25 divinity (adjust the value as needed)
    }

// Melee Attacks

    // Character's Primary Attack Name
    @Override
    public String getPrimaryAttackName() {
        return "Blade Fury"; // Name of the primary attack
    }

    // Character's Primary Attack
    @Override
    public void primaryAttack(Character target, int damage) {
        sayCatchphrase("Blade Fury"); // Display catchphrase for Blade Fury
        int staminaCost = 12; // Define stamina cost for Blade Fury
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(3, 8); // Blade Fury: Roll 3D8 for damage
            System.out.println(this.name + " unleashes a Blade Fury, dealing " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform Blade Fury!"); // Display insufficient stamina message
        }
        restore(); // Restore resources after the attack
    }

    // Character's Secondary Attack Name
    @Override
    public String getSecondaryAttackName() {
        return "Powerful Assault"; // Name of the secondary attack
    }

    // Character's Secondary Attack
    @Override
    public void secondaryAttack(Character target, int damage) {
        sayCatchphrase("Powerful Assault"); // Display catchphrase for Powerful Assault
        int staminaCost = 20; // Define stamina cost for Powerful Assault
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(4, 6); // Powerful Assault: Roll 4D6 for damage
            System.out.println(this.name + " executes a Powerful Assault and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform Powerful Assault!"); // Display insufficient stamina message
        }
        restore(); // Restore resources after the attack
    }

    // Character's Special Attack Name
    @Override
    public String getSpecialAttackName() {
        return "Wrath of Empire"; // Name of the special attack
    }

    // Character's Special Attack
    @Override
    public void specialAttack(Character target, int damage) {
        sayCatchphrase("Wrath of Empire"); // Display catchphrase for Wrath of Empire
        int staminaCost = 30; // Define stamina cost for Wrath of Empire
        int divinityCost = 25; // Define divinity cost for Wrath of Empire
        if (stamina >= staminaCost && divinity >= divinityCost) {
            int damageDealt = dice.rollDice(5, 8); // Wrath of Empire: Roll 5D8 for damage
            System.out.println(this.name + " invokes the Wrath of Empire, dealing " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough stamina or divinity to perform Wrath of Empire!"); // Display insufficient resources message
        }
        restore(); // Restore resources after the attack
    }

// Magic 

    // Character's Primary Magic Name
    @Override
    public String getPrimaryMagicName() {
        return "Battlecry"; // Name of the primary magic attack
    }

    // Character's Primary Magic
    @Override
    public void primaryMagic(Character target, int damage) {
        sayCatchphrase("Battlecry"); // Display catchphrase for Battlecry
        int manaCost = 40; // Define mana cost for Battlecry
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(4, 8); // Battlecry: Roll 4D8 for damage
            System.out.println(this.name + " lets out a Battlecry and deals " + (damageDealt - damage) + " magical damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform Battlecry!"); // Display insufficient mana message
        }
        restore(); // Restore resources after the attack
    }

    // Character's Secondary Magic Name
    @Override
    public String getSecondaryMagicName() {
        return "Shadow Strike"; // Name of the secondary magic attack
    }

    // Character's Secondary Magic
    @Override
    public void secondaryMagic(Character target, int damage) {
        sayCatchphrase("Shadow Strike"); // Display catchphrase for Shadow Strike
        int manaCost = 50; // Define mana cost for Shadow Strike
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(3, 10); // Shadow Strike: Roll 3D10 for damage
            System.out.println(this.name + " casts Shadow Strike and deals " + (damageDealt - damage) + " mystical damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform Shadow Strike!"); // Display insufficient mana message
        }
        restore(); // Restore resources after the attack
    }

    // Character's Tertiary Magic Name
    @Override
    public String getTertiaryMagicName() {
        return "Divine Strike"; // Name of the tertiary magic attack
    }

    // Character's Tertiary Magic
    @Override
    public void tertiaryMagic(Character target, int damage) {
        sayCatchphrase("Divine Strike"); // Display catchphrase for Divine Strike
        int divinityCost = 35; // Define divinity cost for Divine Strike
        if (divinity >= divinityCost) {
            int damageDealt = dice.rollDice(4, 8); // Divine Strike: Roll 4D8 for damage
            System.out.println(this.name + " channels divine power into a devastating strike, dealing " + (damageDealt - damage) + " divine damage!");
            target.takeDamage(damageDealt - damage);
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough divinity to perform Divine Strike!"); // Display insufficient divinity message
        }
        restore(); // Restore resources after the attack
    }

    // Character's 'Heal' Name
    @Override
    public String getHealName() {
        return "Combat Medic"; // Name of the healing action
    }

    // Character's 'Heal'
    @Override
    public void heal(Character target, int damage) {
        sayCatchphrase("Combat Medic"); // Display catchphrase for Combat Medic
        int staminaCost = 25; // Define stamina cost for Combat Medic
        if (stamina >= staminaCost) {
            int healingDone = dice.rollDice(3, 8); // Combat Medic: Roll 3D8 for healing

            if (target == this) {
                this.health += healingDone;
                System.out.println(this.name + " acts as a Combat Medic on themselves and heals for " + healingDone + "!");
            } else {
                target.setHealth(target.getHealth() + healingDone);
                System.out.println(this.name + " acts as a Combat Medic on " + target.getName() + " and heals for " + healingDone + "!");
            }
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform Combat Medic!"); // Display insufficient stamina message
        }
        restore(); // Restore resources after the action
    }

    // Adjustments to the EliteSoldier's catchphrase
    @Override
    public void sayCatchphrase(String action) {
        switch (action) {
            case "Blade Fury":
                System.out.println(this.name + ": 'Feel the fury of our blades!'"); // Display catchphrase for Blade Fury
                break;
            case "Fortified Stance":
                System.out.println(this.name + ": 'Stand firm against the tide!'"); // Display catchphrase for Fortified Stance
                break;
            case "Powerful Assault":
                System.out.println(this.name + ": 'No retreat, no surrender!'"); // Display catchphrase for Powerful Assault
                break;
            case "Wrath of Empire":
                System.out.println(this.name + ": 'Witness the wrath of our Empire!'"); // Display catchphrase for Wrath of Empire
                break;
            case "Death":
                System.out.println(this.name + ": 'For the fallen comrades!'"); // Display catchphrase for Death
                break;
            case "Battlecry":
                System.out.println(this.name + ": 'Rally under the banner of the Empire!'"); // Display catchphrase for Battlecry
                break;
            case "Shadow Strike":
                System.out.println(this.name + ": 'Embrace the shadows of our cause!'"); // Display catchphrase for Shadow Strike
                break;
            case "Divine Strike":
                System.out.println(this.name + ": 'May divine power strike our enemies!'"); // Display catchphrase for Divine Strike
                break;
            case "Combat Medic":
                System.out.println(this.name + ": 'Healing hands, ready to aid!'"); // Display catchphrase for Combat Medic
                break;
            default:
                System.out.println(this.name + ": 'Empire above all!'"); // Default catchphrase for unknown actions
                break;
        }
    }
}
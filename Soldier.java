/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - Soldier.java
*/
public class Soldier implements Character {

    // Attributes for the Soldier Character
    private String name; // Name of the Soldier
    private int health; // Health of the Soldier
    private int mana; // Mana of the Soldier
    private int stamina; // Stamina of the Soldier
    private int divinity; // Divinity of the Soldier

    // Additional attributes
    private int damageReductionBonus = 0; // Stores the damage reduction from 'Block'
    private Dice dice; // Handles dice rolling for the Soldier's actions

    // Initialize Soldier Attributes with default values
    public Soldier(String name, int health, int mana, int stamina, int divinity) {
        this.name = name; // Set the provided name for the Soldier
        this.health = health; // Set default health for the Soldier
        this.mana = mana; // Set default mana for the Soldier
        this.stamina = stamina; // Set default stamina for the Soldier
        this.divinity = divinity; // Set default divinity for the Soldier
        this.dice = new Dice(); // Create a new Dice object for handling random actions
        this.damageReductionBonus = 0; // Initialize damage reduction bonus to 0 for the Soldier
    }

    // Implementing methods from Character interface

    // Set the name of the Soldier
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Get the name of the Soldier
    @Override
    public String getName() {
        return name;
    }

    // Get the health of the Soldier
    @Override
    public int getHealth() {
        return health;
    }

    // Maximum health of the Soldier
    @Override
    public int maxHealth() {
        return this.health;
    }

    // Set the health of the Soldier
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    // Get the mana of the Soldier
    @Override
    public int getMana() {
        return mana;
    }

    // Maximum mana of the Soldier
    @Override
    public int maxMana() {
        return this.mana;
    }

    // Set the mana of the Soldier
    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    // Get the stamina of the Soldier
    @Override
    public int getStamina() {
        return stamina;
    }

    // Maximum stamina of the Soldier
    @Override
    public int maxStamina() {
        return this.stamina;
    }

    // Set the stamina of the Soldier
    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    // Get the divinity of the Soldier
    @Override
    public int getDivinity() {
        return divinity;
    }

    // Maximum divinity of the Soldier
    @Override
    public int maxDivinity() {
        return this.divinity;
    }

    // Set the divinity of the Soldier
    @Override
    public void setDivinity(int divinity) {
        this.divinity = divinity;
    }

    // Check if the Soldier is alive
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

    // Method to handle character death for Soldier
    @Override
    public void CharDeath() {
        // Check if the character's health is zero or less
        if (this.health <= 0) {
            sayCatchphrase("Death");
            System.out.println(this.name + " has been defeated!");
        }
    }

    // Soldier's Damage Negating Move Name
    @Override
    public String getBlockName() {
        return "Guard"; // Name of the Soldier's damage negating move
    }

    // Soldier's Damage Negating Move
    @Override
    public void block() {
        sayCatchphrase("Guard"); // Display catchphrase for Guard
        // Roll a D10 for damage reduction
        int damageReduction = dice.rollD10();
        System.out.println(this.name + " uses Guard and reduces incoming damage by " + damageReduction + "!");
        // Store the damage reduction bonus
        damageReductionBonus += damageReduction;
    }

    // Restores Mana & Stamina & Divinity
    @Override
    public void restore() {
        this.mana += 20; // Restore 20 mana
        this.stamina += 30; // Restore 30 stamina
        this.divinity += 15; // Restore 15 divinity
    }

// Melee Attacks

    // Character's Primary Attack Name
    @Override
    public String getPrimaryAttackName() {
        return "Sword Slash"; // Name of the primary attack
    }

    // Character's Primary Attack
    @Override
    public void primaryAttack(Character target, int damage) {
        sayCatchphrase("Sword Slash"); // Display catchphrase for Sword Slash
        int staminaCost = 10;
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(2, 6); // Roll 2D6 for damage with Sword Slash
            System.out.println(this.name + " performs a Sword Slash and deals " + (damageDealt - damage) + " damage!");
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
        return "Shield Bash"; // Name of the secondary attack
    }

    // Character's Secondary Attack
    @Override
    public void secondaryAttack(Character target, int damage) {
        sayCatchphrase("Shield Bash"); // Display catchphrase for Shield Bash
        int staminaCost = 15;
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(3, 4); // Roll 3D4 for damage with Shield Bash
            System.out.println(this.name + " performs a Shield Bash and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform a secondary attack!");
        }
        restore(); // Restore after the attack
    }

    // Character's Special Attack Name
    @Override
    public String getSpecialAttackName() {
        return "Empire's Wrath"; // Name of the special attack
    }

    // Character's Special Attack
    @Override
    public void specialAttack(Character target, int damage) {
        sayCatchphrase("Empire's Wrath"); // Display catchphrase for Empire's Wrath
        int staminaCost = 25;
        int divinityCost = 20;
        if (stamina >= staminaCost && divinity >= divinityCost) {
            int damageDealt = dice.rollDice(4, 6); // Roll 4D6 for damage with Empire's Wrath
            System.out.println(this.name + " unleashes Empire's Wrath and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough stamina or divinity to perform Empire's Wrath!");
        }
        restore(); // Restore after the attack
    }

// Magic 

    // Soldier's Primary Magic Name
    @Override
    public String getPrimaryMagicName() {
        return "Rallying Cry"; // Name of the primary magic attack
    }

    // Soldier's Primary Magic
    @Override
    public void primaryMagic(Character target, int damage) {
        sayCatchphrase("Rallying Cry"); // Display catchphrase for Rallying Cry
        int manaCost = 30;
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(3, 4); // Rallying Cry: Roll 3D4 for damage
            System.out.println(this.name + " shouts a Rallying Cry and deals " + (damageDealt - damage) + " magical damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform Rallying Cry!");
        }
        restore(); // Restore after the attack
    }

    // Soldier's Secondary Magic Name
    @Override
    public String getSecondaryMagicName() {
        return "Dark Enchantment"; // Name of the secondary magic attack
    }

    // Soldier's Secondary Magic
    @Override
    public void secondaryMagic(Character target, int damage) {
        sayCatchphrase("Dark Enchantment"); // Display catchphrase for Dark Enchantment
        int manaCost = 40;
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(2, 8); // Dark Enchantment: Roll 2D8 for damage
            System.out.println(this.name + " casts Dark Enchantment and deals " + (damageDealt - damage) + " mystical damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform Dark Enchantment!");
        }
        restore(); // Restore after the attack
    }

    // Soldier's Tertiary Magic Name
    @Override
    public String getTertiaryMagicName() {
        return "Empowerment"; // Name of the tertiary magic attack
    }

    // Soldier's Tertiary Magic
    @Override
    public void tertiaryMagic(Character target, int damage) {
        sayCatchphrase("Empowerment"); // Display catchphrase for Empowerment
        int divinityCost = 30;
        if (divinity >= divinityCost) {
            int damageDealt = dice.rollDice(4, 6); // Empowerment: Roll 4D6 for damage
            System.out.println(this.name + " empowers and deals " + (damageDealt - damage) + " divine damage!");
            target.takeDamage(damageDealt - damage);
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough divinity to perform Empowerment!");
        }
        restore(); // Restore after the attack
    }

    // Soldier's 'Heal' Name
    @Override
    public String getHealName() {
        return "Field Medic"; // Name of the healing action
    }

    // Soldier's 'Heal'
    @Override
    public void heal(Character target, int damage) {
        sayCatchphrase("Field Medic"); // Display catchphrase for Field Medic
        int staminaCost = 20;
        if (stamina >= staminaCost) {
            int healingDone = dice.rollDice(2, 8); // Field Medic: Roll 2D8 for healing

            if (target == this) {
                this.health += healingDone;
                System.out.println(this.name + " uses Field Medic on themselves and heals for " + healingDone + "!");
            } else {
                target.setHealth(target.getHealth() + healingDone);
                System.out.println(this.name + " uses Field Medic on " + target.getName() + " and heals for " + healingDone + "!");
            }
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform Field Medic!");
        }
        restore(); // Restore after the action
    }
    
    // Method to display catchphrases for character actions
    @Override
    public void sayCatchphrase(String action) {
        // Switch statement to determine the appropriate catchphrase for the given action
        switch (action) {
            case "Sword Slash":
                System.out.println(this.name + ": 'For the Empire!'"); // Display catchphrase for Sword Slash
                break;
            case "Shield Bash":
                System.out.println(this.name + ": 'You won't escape!'"); // Display catchphrase for Shield Bash
                break;
            case "Guard":
                System.out.println(this.name + ": 'Defend the Empire!'"); // Display catchphrase for Guard
                break;
            case "Damage Taken":
                System.out.println(this.name + ": 'I'll endure!'"); // Display catchphrase for Damage Taken
                break;
            case "Death":
                System.out.println(this.name + ": 'For the glory... of the Empire...'"); // Display catchphrase for Death
                break;
            case "Field Medic":
                System.out.println(this.name + ": 'Stay in the fight!'"); // Display catchphrase for Field Medic
                break;
            case "Empire's Wrath":
                System.out.println(this.name + ": 'The Empire's fury strikes!'"); // Display catchphrase for Empire's Wrath
                break;
            case "Rallying Cry":
                System.out.println(this.name + ": 'Unite under the Empire's banner!'"); // Display catchphrase for Rallying Cry
                break;
            case "Dark Enchantment":
                System.out.println(this.name + ": 'Embrace the shadows of the Empire!'"); // Display catchphrase for Dark Enchantment
                break;
            case "Empowerment":
                System.out.println(this.name + ": 'The Empire's strength flows through me!'"); // Display catchphrase for Empowerment
                break;
            default:
                System.out.println(this.name + ": 'Empire above all!'"); // Default catchphrase for unknown actions
                break;
        }
    }
}
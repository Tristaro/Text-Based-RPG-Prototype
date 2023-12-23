/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - Commander.java
*/
public class Commander implements Character {

    // Attributes for the Commander Character
    private String name; // Name of the Commander
    private int health; // Health of the Commander
    private int mana; // Mana of the Commander
    private int stamina; // Stamina of the Commander
    private int divinity; // Divinity of the Commander

    // Additional attributes 
    private int damageReductionBonus = 0; // Stores the damage reduction from 'Block'
    private Dice dice; // Handles dice rolling for the Commander's actions

    // Initialize Commander Attributes with default values
    public Commander(String name, int health, int mana, int stamina, int divinity) {
        this.name = name; // Set the provided name for the Commander
        this.health = health; // Set default health to 200 for the Commander
        this.mana = mana; // Set default mana to 100 for the Commander
        this.stamina = stamina; // Set default stamina to 200 for the Commander
        this.divinity = divinity; // Set default divinity to 0 for the Commander
        this.dice = new Dice(); // Create a new Dice object for handling random actions
        this.damageReductionBonus = 0; // Initialize damage reduction bonus to 0 for the Commander
    }

    // Implementing methods from Character interface

    // Set the name of the Commander
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Get the name of the Commander
    @Override
    public String getName() {
        return name;
    }

    // Get the health of the Commander
    @Override
    public int getHealth() {
        return health;
    }

    // Maximum health of the Commander
    @Override
    public int maxHealth() {
        return this.health; 
    }

    // Set the health of the Commander
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    // Get the mana of the Commander
    @Override
    public int getMana() {
        return mana;
    }

    // Maximum mana of the Commander
    @Override
    public int maxMana() {
        return this.mana; 
    }

    // Set the mana of the Commander
    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    // Get the stamina of the Commander
    @Override
    public int getStamina() {
        return stamina;
    }

    // Maximum stamina of the Commander
    @Override
    public int maxStamina() {
        return this.stamina; 
    }

    // Set the stamina of the Commander
    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    // Get the divinity of the Commander
    @Override
    public int getDivinity() {
        return divinity;
    }

    // Maximum divinity of the Commander
    @Override
    public int maxDivinity() {
        return this.divinity; 
    }

    // Set the divinity of the Commander
    @Override
    public void setDivinity(int divinity) {
        this.divinity = divinity;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0; // Check if the Elite Soldier is alive
    }

// Methods related to Attacks and Actions

    // Calculate Damage taken by Character
    @Override
    public void takeDamage(int damage) {
        // Logic to handle damage reduction bonus
        if (damageReductionBonus > 0) {
            if (damage <= damageReductionBonus) {
                // Apply damage reduction bonus
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

    // Method to handle character death for Commander
    @Override
    public void CharDeath() {
        // Check if the character's health is zero or less
        if (this.health <= 0) {
            sayCatchphrase("Death");
            System.out.println(this.name + " has been defeated!");
        }
    }

    // Commander's Damage Negating Move Name
    @Override
    public String getBlockName() {
        return "Defensive Formation"; // Name of the damage negating move
    }

    // Commander's Damage Negating Move
    @Override
    public void block() {
        sayCatchphrase("Defensive Formation");
        int damageReduction = dice.rollD12(); // Roll a D12 for damage reduction
        System.out.println(this.name + " uses Defensive Formation, reducing incoming damage by " + damageReduction + "!");
        damageReductionBonus += damageReduction; // Store the damage reduction bonus
    }

    // Restores Mana & Stamina & Divinity
    @Override
    public void restore() {
        this.mana += 20; // Restore 20 mana
        this.stamina += 20; // Restore 20 stamina
        this.divinity += 25; // Restore 25 divinity
    }

// Melee Attacks

        // Character's Primary Attack Name
    @Override
    public String getPrimaryAttackName() {
        return "Spear Thrust";
    }

    // Character's Primary Attack
    @Override
    public void primaryAttack(Character target, int damage) {
        sayCatchphrase("Spear Thrust");
        int staminaCost = 12;
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(3, 8); // Spear Thrust: Roll 3D8 for damage
            System.out.println(this.name + " performs a Spear Thrust and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform Spear Thrust!");
        }
        restore();
    }

    // Character's Secondary Attack Name
    @Override
    public String getSecondaryAttackName() {
        return "Tactical Strike";
    }

    // Character's Secondary Attack
    @Override
    public void secondaryAttack(Character target, int damage) {
        sayCatchphrase("Tactical Strike");
        int staminaCost = 18;
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(4, 6); // Tactical Strike: Roll 4D6 for damage
            System.out.println(this.name + " executes a Tactical Strike and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform Tactical Strike!");
        }
        restore();
    }

    // Character's Special Attack Name
    @Override
    public String getSpecialAttackName() {
        return "Commander's Authority";
    }

    // Character's Special Attack
    @Override
    public void specialAttack(Character target, int damage) {
        sayCatchphrase("Commander's Authority");
        int staminaCost = 30;
        int divinityCost = 25;
        if (stamina >= staminaCost && divinity >= divinityCost) {
            int damageDealt = dice.rollDice(5, 8); // Commander's Authority: Roll 5D8 for damage
            System.out.println(this.name + " uses Commander's Authority, dealing " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            stamina -= staminaCost;
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough stamina or divinity to perform Commander's Authority!");
        }
        restore();
    }

// Magic 

    // Character's Primary Magic Name
    @Override
    public String getPrimaryMagicName() {
        return "Inspiring Aura";
    }

    // Character's Primary Magic
    @Override
    public void primaryMagic(Character target, int damage) {
        sayCatchphrase("Inspiring Aura");
        int manaCost = 35;
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(4, 8); // Inspiring Aura: Roll 4D8 for damage
            System.out.println(this.name + " emits an Inspiring Aura, dealing " + (damageDealt - damage) + " magical damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform Inspiring Aura!");
        }
        restore();
    }

    // Character's Secondary Magic Name
    @Override
    public String getSecondaryMagicName() {
        return "Strategic Strike";
    }

    // Character's Secondary Magic
    @Override
    public void secondaryMagic(Character target, int damage) {
        sayCatchphrase("Strategic Strike");
        int manaCost = 45;
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(3, 10); // Strategic Strike: Roll 3D10 for damage
            System.out.println(this.name + " uses Strategic Strike and deals " + (damageDealt - damage) + " mystical damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform Strategic Strike!");
        }
        restore();
    }

    // Character's Tertiary Magic Name
    @Override
    public String getTertiaryMagicName() {
        return "Battlefield Blessing";
    }

    // Character's Tertiary Magic
    @Override
    public void tertiaryMagic(Character target, int damage) {
        sayCatchphrase("Battlefield Blessing");
        int divinityCost = 35;
        if (divinity >= divinityCost) {
            int damageDealt = dice.rollDice(3, 10); // Battlefield Blessing: Roll 3D10 for damage
            System.out.println(this.name + " invokes Battlefield Blessing and deals " + damageDealt + " divine damage!");
            target.takeDamage(damageDealt);
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough divinity to perform Battlefield Blessing!");
        }
        restore();
    }

    // Character's Healing Ability Name
    @Override
    public String getHealName() {
        return "Field Medic";
    }

    // Character's Healing Ability
    @Override
    public void heal(Character target, int damage) {
        sayCatchphrase("Field Medic");
        int staminaCost = 25;
        if (stamina >= staminaCost) {
            int healingDone = dice.rollDice(3, 8); // Field Medic: Roll 3D8 for healing

            if (target == this) {
                this.health += healingDone;
                System.out.println(this.name + " acts as a Field Medic on themselves and heals for " + healingDone + "!");
            } else {
                target.setHealth(target.getHealth() + healingDone);
                System.out.println(this.name + " acts as a Field Medic on " + target.getName() + " and heals for " + healingDone + "!");
            }
            stamina -= staminaCost;
        } else {
            System.out.println("Not enough stamina to perform Field Medic!");
        }
        restore();
    }

    // Method to display catchphrases for character actions
    @Override
    public void sayCatchphrase(String action) {
        // Switch statement to determine the appropriate catchphrase for the given action
        switch (action) {
            case "Spear Thrust":
                System.out.println(this.name + ": 'For the Empire!'"); // Display catchphrase for Spear Thrust
                break;
            case "Tactical Strike":
                System.out.println(this.name + ": 'You won't escape!'"); // Display catchphrase for Tactical Strike
                break;
            case "Defensive Formation":
                System.out.println(this.name + ": 'Defend the Empire!'"); // Display catchphrase for Defensive Formation
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
            case "Commander's Authority":
                System.out.println(this.name + ": 'The Empire's authority commands!'"); // Display catchphrase for Commander's Authority
                break;
            case "Inspiring Aura":
                System.out.println(this.name + ": 'Rise and fight for the Empire!'"); // Display catchphrase for Inspiring Aura
                break;
            case "Strategic Strike":
                System.out.println(this.name + ": 'Execute with precision!'"); // Display catchphrase for Strategic Strike
                break;
            case "Battlefield Blessing":
                System.out.println(this.name + ": 'The Empire's blessing heals!'"); // Display catchphrase for Battlefield Blessing
                break;
            default:
                System.out.println(this.name + ": 'Empire above all!'"); // Default catchphrase for unknown actions
                break;
        }
    }
}
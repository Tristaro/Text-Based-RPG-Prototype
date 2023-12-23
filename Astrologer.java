/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - Astrologer.java
*/
public class Astrologer implements Character {

    // Attributes for the Astrologer Character
    private String name; // Name of the Astrologer
    private int health; // Health of the Astrologer
    private int mana; // Mana of the Astrologer
    private int stamina; // Stamina of the Astrologer
    private int divinity; // Divinity of the Astrologer
    
    // Additional attributes 
    private int damageReductionBonus = 0; // Stores the damage reduction from 'Block'
    private Dice dice; // Handles dice rolling for the Astrologer's actions

    // Initialize Astrologer Attributes with default values
    public Astrologer(String name) {
        this.name = name; // Set the provided name for the Astrologer
        this.health = 100; // Set default health to 100 for the Astrologer
        this.mana = 200; // Set default mana to 200 for the Astrologer
        this.stamina = 0; // Set default stamina to 0 for the Astrologer
        this.divinity = 200; // Set default divinity to 200 for the Astrologer
        this.dice = new Dice(); // Create a new Dice object for handling random actions
        this.damageReductionBonus = 0; // Initialize damage reduction bonus to 0 for the Astrologer
}

    // Implementing methods from Character interface

    // Set the name of the Astrologer
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Get the name of the Astrologer
    @Override
    public String getName() {
        return name;
    }

    // Get the health of the Astrologer
    @Override
    public int getHealth() {
        return health;
    }

    // Maximum health of the Astrologer
    @Override
    public int maxHealth() {
        return this.health; 
    }

    // Set the health of the Astrologer
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    // Get the mana of the Astrologer
    @Override
    public int getMana() {
        return mana;
    }

    // Maximum mana of the Astrologer
    @Override
    public int maxMana() {
        return this.mana; 
    }

    // Set the mana of the Astrologer
    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    // Get the stamina of the Astrologer
    @Override
    public int getStamina() {
        return stamina;
    }

    // Maximum stamina of the Astrologer
    @Override
    public int maxStamina() {
        return this.stamina; 
    }

    // Set the stamina of the Astrologer
    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    // Get the divinity of the Astrologer
    @Override
    public int getDivinity() {
        return divinity;
    }

    // Maximum divinity of the Astrologer
    @Override
    public int maxDivinity() {
        return this.divinity; 
    }

    // Set the divinity of the Astrologer
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
        if (damageReductionBonus > 0) {
            if (damage <= damageReductionBonus) {
                damageReductionBonus -= damage;
                System.out.println(this.name + " takes " + damage + " damage! Damage Reduction Bonus: " + damageReductionBonus);
                return;
            } else {
                damage -= damageReductionBonus;
                damageReductionBonus = 0;
            }
        }
        this.health -= damage;
        System.out.println(this.name + " takes " + damage + " damage!");
        sayCatchphrase("Damage Taken");
    }

    // Method to handle character death for Barbarian
    @Override
    public void CharDeath() {
        if (this.health <= 0) {
            sayCatchphrase("Death");
            System.out.println(this.name + " has been defeated!");
        }
    }

    // Character's Damage Negating Move Name
    @Override
    public String getBlockName() {
        return "";
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
        this.mana += 50; // Restore 50 mana
        this.divinity += 40; // Restore 40 divinity
    }

// Magic Attacks

    // Character's Primary Attack Name
    @Override
    public String getPrimaryAttackName() {
        return "Cosmic Bolt";
    }

    // Character's Primary Attack
    @Override
    public void primaryAttack(Character target, int damage) {
        sayCatchphrase("Cosmic Bolt");
        int manaCost = 50;
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(4, 6); // Cosmic Bolt: Roll 4D6 for damage
            System.out.println(this.name + " casts Cosmic Bolt and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform Cosmic Bolt!");
        }
        restore();
    }

    // Character's Secondary Attack Name
    @Override
    public String getSecondaryAttackName() {
        return "Astral Flare";
    }

    // Character's Secondary Attack
    @Override
    public void secondaryAttack(Character target, int damage) {
        sayCatchphrase("Astral Flare");
        int manaCost = 60;
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(3, 8); // Astral Flare: Roll 3D8 for damage
            System.out.println(this.name + " casts Astral Flare and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
        } else {
            System.out.println("Not enough mana to perform Astral Flare!");
        }
        restore();
    }

    // Character's Special Attack Name
    @Override
    public String getSpecialAttackName() {
        return "Ethereal Burst";
    }

    // Character's Special Attack
    @Override
    public void specialAttack(Character target, int damage) {
        sayCatchphrase("Ethereal Burst");
        int manaCost = 60;
        int divinityCost = 40;
        if (mana >= manaCost && divinity >= divinityCost) {
            int damageDealt = dice.rollDice(5, 6); // Ethereal Burst: Roll 5D6 for damage
            System.out.println(this.name + " casts Ethereal Burst and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough mana or divinity to perform Ethereal Burst!");
        }
        restore();
    }

// Divine Attacks

    // Character's Primary Magic Name
    @Override
    public String getPrimaryMagicName() {
        return "Celestial Wrath";
    }

    // Character's Primary Magic
    @Override
    public void primaryMagic(Character target, int damage) {
        sayCatchphrase("Celestial Wrath");
        int divinityCost = 70;
        if (divinity >= divinityCost) {
            int damageDealt = dice.rollDice(2, 10); // Celestial Wrath: Roll 2D10 for damage
            System.out.println(this.name + " calls upon Celestial Wrath and deals " + (damageDealt - damage) + " divine damage!");
            target.takeDamage(damageDealt - damage);
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough divinity to perform Celestial Wrath!");
        }
        restore();
    }

    // Character's Secondary Magic Name
    @Override
    public String getSecondaryMagicName() {
        return "Starfall Blast";
    }

    // Character's Secondary Magic
    @Override
    public void secondaryMagic(Character target, int damage) {
        sayCatchphrase("Starfall Blast");
        int divinityCost = 80;
        if (divinity >= divinityCost) {
            int damageDealt = dice.rollDice(3, 10); // Starfall Blast: Roll 3D10 for damage
            System.out.println(this.name + " summons Starfall Blast and deals " + (damageDealt - damage) + " celestial damage!");
            target.takeDamage(damageDealt - damage);
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough divinity to perform Starfall Blast!");
        }
        restore();
    }

    // Character's Tertiary Magic Name
    @Override
    public String getTertiaryMagicName() {
        return "Divine Eclipse";
    }

    // Character's Tertiary Magic
    @Override
    public void tertiaryMagic(Character target, int damage) {
        sayCatchphrase("Divine Eclipse");
        int manaCost = 70;
        int divinityCost = 50;
        if (mana >= manaCost && divinity >= divinityCost) {
            int damageDealt = dice.rollDice(4, 8); // Divine Eclipse: Roll 4D8 for damage
            System.out.println(this.name + " casts Divine Eclipse and deals " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage);
            mana -= manaCost;
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough mana or divinity to perform Divine Eclipse!");
        }
        restore();
    }

    // Character's 'Heal' Name
    @Override
    public String getHealName() {
        return "Celestial Dew";
    }

    // Character's 'Heal'
    @Override
    public void heal(Character target, int damage) {
        sayCatchphrase("Healing");
        int manaCost = 60;
        int divinityCost = 50;
        if (mana >= manaCost && divinity >= divinityCost) {
            int healingDone = dice.rollDice(2, 10); // Celestial Dew: Roll 2D10 for healing

            if (target == this) {
                this.health += healingDone;
                System.out.println(this.name + " uses Celestial Dew on themselves and heals for " + healingDone + "!");
            } else {
                target.setHealth(target.getHealth() + healingDone);
                System.out.println(this.name + " uses Celestial Dew on " + target.getName() + " and heals for " + healingDone + "!");
            }
            mana -= manaCost;
            divinity -= divinityCost;
        } else {
            System.out.println("Not enough mana or divinity to perform Celestial Dew!");
        }
        restore();
    }

    // Method to display catchphrases for celestial actions
    @Override
    public void sayCatchphrase(String action) {
        // Switch statement to determine the appropriate catchphrase for the given action
        switch (action) {
            case "Cosmic Bolt":
                System.out.println(this.name + ": 'A bolt from the cosmic tapestry!'"); // Display catchphrase for Cosmic Bolt
                break;
            case "Astral Flare":
                System.out.println(this.name + ": 'Witness the brilliance of the stars!'"); // Display catchphrase for Astral Flare
                break;
            case "Celestial Wrath":
                System.out.println(this.name + ": 'The heavens pass judgment!'"); // Display catchphrase for Celestial Wrath
                break;
            case "Starfall Blast":
                System.out.println(this.name + ": 'A shower of celestial power!'"); // Display catchphrase for Starfall Blast
                break;
            case "Ethereal Burst":
                System.out.println(this.name + ": 'A blend of cosmic energies unleashed!'"); // Display catchphrase for Ethereal Burst
                break;
            case "Divine Eclipse":
                System.out.println(this.name + ": 'The divine darkness eclipses all!'"); // Display catchphrase for Divine Eclipse
                break;
            case "Healing":
                System.out.println(this.name + ": 'Stars guide the healing light!'"); // Display catchphrase for Healing
                break;
            case "Block":
                System.out.println(this.name + ": 'The cosmic shield holds firm!'"); // Display catchphrase for Block
                break;
            case "Damage Taken":
                System.out.println(this.name + ": 'A mere ripple in the astral flow!'"); // Display catchphrase for Damage Taken
                break;
            case "Death":
                System.out.println(this.name + ": 'Stars... guide me...'"); // Display catchphrase for Death
                break;
            default:
                System.out.println(this.name + ": 'Gazing into the stars, the unknown beckons!'"); // Default catchphrase for unknown actions
                break;
        }
    }    
}
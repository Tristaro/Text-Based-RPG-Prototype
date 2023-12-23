/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - TestMob.java
*/public class TestMob implements Character {

    // Attributes for the TestMob character
    private String name; // Name of the TestMob
    private int health; // Health of the TestMob
    private int mana; // Mana of the TestMob
    private int stamina; // Stamina of the TestMob
    private int divinity; // Divinity of the TestMob
    
    // Additional attributes 
    private int damageReductionBonus = 0; // Stores the damage reduction from 'Block'
    private Dice dice; // Handles dice rolling for the TestMob's actions

    // Constructor to initialize TestMob attributes
    public TestMob(String name, int health, int mana, int stamina, int divinity) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.stamina = stamina;
        this.divinity = divinity;
        this.dice = new Dice();
        this.damageReductionBonus = 0;
    }

    // Implementing methods from Character interface

    // Set the name of the TestMob
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Get the name of the TestMob
    @Override
    public String getName() {
        return name;
    }

    // Get the health of the TestMob
    @Override
    public int getHealth() {
        return health;
    }

    // Maximum health of the TestMob
    @Override
    public int maxHealth() {
        return this.health; 
    }

    // Set the health of the TestMob
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    // Get the mana of the TestMob
    @Override
    public int getMana() {
        return mana;
    }

    // Maximum mana of the TestMob
    @Override
    public int maxMana() {
        return this.mana; 
    }

    // Set the mana of the TestMob
    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    // Get the stamina of the TestMob
    @Override
    public int getStamina() {
        return stamina;
    }

    // Maximum stamina of the TestMob
    @Override
    public int maxStamina() {
        return this.stamina; 
    }

    // Set the stamina of the TestMob
    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    // Get the divinity of the TestMob
    @Override
    public int getDivinity() {
        return divinity;
    }

    // Maximum divinity of the TestMob
    @Override
    public int maxDivinity() {
        return this.divinity; 
    }

    // Set the divinity of the TestMob
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
    }

    // Method to handle character death for TestMob
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
        int damageReduction = dice.rollD10(); // Roll a D10 for damage reduction
        System.out.println(this.name + " uses block and reduces incoming damage by " + damageReduction + "!");
        damageReductionBonus += damageReduction; // Store the damage reduction bonus
    }
        
    // Restores Mana & Stamina
    @Override
    public void restore() {
        this.mana += 10; // Restore 10 mana
        this.stamina += 20; // Restore 20 stamina
    }

// Melee Attacks

    // Character's Primary Attack Name
    @Override
    public String getPrimaryAttackName() {
        return "Slashing Claw";
    }

    // Character's Primary Attack
    @Override
    public void primaryAttack(Character target, int damage) {
        int damageDealt = dice.rollD8(); // Roll 1D8 for damage with Slashing Claw
        System.out.println(this.name + " performs a Slashing Claw and deals " + (damageDealt - damage) + " damage!");
        target.takeDamage(damageDealt - damage);
        restore();
    }

    // Character's Secondary Attack Name
    @Override
    public String getSecondaryAttackName() {
        return "Battering Ram";
    }

        // Character's Secondary Attack
    @Override
    public void secondaryAttack(Character target, int damage) {
        int damageDealt = dice.rollDice(2, 6); // Roll 2D6 for damage with Battering Ram
        System.out.println(this.name + " performs a Battering Ram and deals " + (damageDealt - damage) + " damage!");
        target.takeDamage(damageDealt - damage);
        restore();
    }

    // Character's Special Attack Name
    @Override
    public  String getSpecialAttackName() {
        return "Pummeling Smash";
    }

        // Character's Special Attack
    @Override
    public void specialAttack(Character target, int damage) {
        int damageDealt = dice.rollD20(); // Roll 1D20 for damage with Pummeling Smash
        System.out.println(this.name + " performs a Pummeling Smash and deals " + (damageDealt - damage) + " damage!");
        target.takeDamage(damageDealt - damage);
        restore();
    }

// Magic 

    // Character's Primary Magic Name
    @Override
    public  String getPrimaryMagicName() {
        return "Ethereal Blast";
    }

    // Character's Primary Magic
    @Override
    public void primaryMagic(Character target, int damage) {
        int damageDealt = dice.rollDice(3, 4); // Ethereal Blast: Roll 3D4 for damage
        System.out.println(this.name + " casts Ethereal Blast and deals " + (damageDealt - damage) + " damage!");
        target.takeDamage(damageDealt - damage);
        restore();
    }

    // Character's Secondary Magic Name
    @Override
    public  String getSecondaryMagicName() {
        return "Toxic Spit";
    }

    // Character's Secondary Magic
    @Override
    public void secondaryMagic(Character target, int damage) {
        int damageDealt = dice.rollDice(3, 6); // Toxic Spit: Roll 3D6 for damage
        System.out.println(this.name + " casts Toxic Spit and deals " + (damageDealt - damage) + " damage!");
        target.takeDamage(damageDealt - damage);
        restore();
    }

    // Character's Tertiary Magic Name
    @Override
    public  String getTertiaryMagicName() {
        return "Arcane Bolt";
    }

    // Character's Tertiary Magic
    @Override
    public void tertiaryMagic(Character target, int damage) {
        int damageDealt = dice.rollDice(4, 8); // Arcane Bolt: Roll 4D8 for damage
        System.out.println(this.name + " casts Arcane Bolt and deals " + (damageDealt - damage) + " damage!");
        target.takeDamage(damageDealt - damage);
        restore();
    }

    // Character's 'Heal' Name
    @Override
    public  String getHealName() {
        return "Regenerative Aura";
    }

    // Character's 'Heal'
    @Override
    public void heal(Character target, int damage) {
        int healingDone = dice.rollDice(2, 6); // Regenerative Aura: Roll 2D6 for healing
        if (target == this) {
            this.health += healingDone; // Heal the TestMob itself
            System.out.println(this.name + " uses Regenerative Aura on themselves and heals for " + healingDone + "!");
        } else {
            System.out.println(this.name + " uses Regenerative Aura on " + target.getName() + " and heals for " + healingDone + "!");
            target.setHealth(target.getHealth() + healingDone); // Heal the ally
        }
        restore();
    }

    // Method to display catchphrases
    @Override
    public void sayCatchphrase(String action) {
        switch (action) {
            case "Crushing Blow":
                System.out.println(this.name + ": 'Feel the might of the mountains!'");
                break;
            case "Whirling Strike":
                System.out.println(this.name + ": 'None can withstand my fury!'");
                break;
            case "Savage Cleave":
                System.out.println(this.name + ": 'Witness the wrath of nature!'");
                break;
            case "Thundering Shockwave":
                System.out.println(this.name + ": 'Thunder roars at my command!'");
                break;
            case "Fiery Rampage":
                System.out.println(this.name + ": 'Burn in the flames of my rage!'");
                break;
            case "Frostbite Fury":
                System.out.println(this.name + ": 'Feel the chill of winter's embrace!'");
                break;
            case "Healing":
                System.out.println(this.name + ": 'Life flows through us once more!'");
                break;
            case "Block":
                System.out.println(this.name + ": 'I shall weather this storm!'");
                break;
            case "Damage Taken":
                System.out.println(this.name + ": 'Is that all you've got?'");
                break;
            case "Death":
                System.out.println(this.name + ": 'My journey ends here...'");
                break;
            default:
                System.out.println(this.name + ": 'For honor and glory!'");
                break;
        }
    }
}
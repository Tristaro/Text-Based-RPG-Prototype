/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - Emperor.java
*/
public class Emperor implements Character {

    // Attributes for the Emperor Character
    private String name; // Name of the Emperor
    private int health; // Health of the Emperor
    private int mana; // Mana of the Emperor
    private int stamina; // Stamina of the Emperor
    private int divinity; // Divinity of the Emperor

    // Additional attributes
    private int damageReductionBonus = 0; // Stores the damage reduction from 'Block'
    private Dice dice; // Handles dice rolling for the Emperor's actions

    // Initialize Emperor Attributes with default values
    public Emperor(String name, int health, int mana, int stamina, int divinity) {
        this.name = name; // Set the provided name for the Emperor
        this.health = health; // Set default health for the Emperor
        this.mana = mana; // Set default mana for the Emperor
        this.stamina = stamina; // Set default stamina for the Emperor
        this.divinity = divinity; // Set default divinity for the Emperor
        this.dice = new Dice(); // Create a new Dice object for handling random actions
        this.damageReductionBonus = 0; // Initialize damage reduction bonus to 0 for the Emperor
    }
    
// Implementing methods from Character interface
    
    // Set the name of the Emperor
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Get the name of the Emperor
    @Override
    public String getName() {
        return name;
    }

    // Get the health of the Emperor
    @Override
    public int getHealth() {
        return health;
    }

    // Maximum health of the Emperor
    @Override
    public int maxHealth() {
        return this.health;
    }

    // Set the health of the Emperor
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    // Get the mana of the Emperor
    @Override
    public int getMana() {
        return mana;
    }

    // Maximum mana of the Emperor
    @Override
    public int maxMana() {
        return this.mana;
    }

    // Set the mana of the Emperor
    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    // Get the stamina of the Emperor
    @Override
    public int getStamina() {
        return stamina;
    }

    // Maximum stamina of the Emperor
    @Override
    public int maxStamina() {
        return this.stamina;
    }

    // Set the stamina of the Emperor
    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    // Get the divinity of the Emperor
    @Override
    public int getDivinity() {
        return divinity;
    }

    // Maximum divinity of the Emperor
    @Override
    public int maxDivinity() {
        return this.divinity;
    }

    // Set the divinity of the Emperor
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
        this.mana += 30; // Restore 30 mana
        this.stamina += 40; // Restore 40 stamina
        this.divinity += 20; // Restore 20 divinity
    }

// Melee Attacks

    // Character's Primary Attack Name
    @Override
    public String getPrimaryAttackName() {
        return "Imperial Strike"; // Name of the primary attack
    }

    // Character's Primary Attack
    @Override
    public void primaryAttack(Character target, int damage) {
        sayCatchphrase("Imperial Strike"); // Vocalizes the attack
        int staminaCost = 12; // Stamina cost for using this attack
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(3, 8); // Imperial Strike: Roll 3D8 for damage
            System.out.println(this.name + " executes an Imperial Strike dealing " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage); // Deals damage to the target
            stamina -= staminaCost; // Reduces stamina after the attack
        } else {
            System.out.println("Not enough stamina to perform Imperial Strike!"); // Insufficient stamina message
        }
        restore(); // Restore after the attack
    }

    // Character's Secondary Attack Name
    @Override
    public String getSecondaryAttackName() {
        return "Crown Slam"; // Name of the secondary attack
    }

    // Character's Secondary Attack
    @Override
    public void secondaryAttack(Character target, int damage) {
        sayCatchphrase("Crown Slam"); // Vocalizes the attack
        int staminaCost = 18; // Stamina cost for using this attack
        if (stamina >= staminaCost) {
            int damageDealt = dice.rollDice(4, 6); // Crown Slam: Roll 4D6 for damage
            System.out.println(this.name + " delivers a Crown Slam dealing " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage); // Deals damage to the target
            stamina -= staminaCost; // Reduces stamina after the attack
        } else {
            System.out.println("Not enough stamina to perform Crown Slam!"); // Insufficient stamina message
        }
        restore(); // Restore after the attack
    }

    // Character's Special Attack Name
    @Override
    public String getSpecialAttackName() {
        return "Emperor's Wrath"; // Name of the special attack
    }

    // Character's Special Attack
    @Override
    public void specialAttack(Character target, int damage) {
        sayCatchphrase("Emperor's Wrath"); // Vocalizes the attack
        int staminaCost = 30; // Stamina cost for using this attack
        int divinityCost = 25; // Divinity cost for using this attack
        if (stamina >= staminaCost && divinity >= divinityCost) {
            int damageDealt = dice.rollDice(5, 8); // Emperor's Wrath: Roll 5D8 for damage
            System.out.println(this.name + " invokes the Emperor's Wrath dealing " + (damageDealt - damage) + " damage!");
            target.takeDamage(damageDealt - damage); // Deals damage to the target
            stamina -= staminaCost; // Reduces stamina after the attack
            divinity -= divinityCost; // Reduces divinity after the attack
        } else {
            System.out.println("Not enough stamina or divinity to perform Emperor's Wrath!"); // Insufficient resources message
        }
        restore(); // Restore after the attack
    }

// Magic 

    // Character's Primary Magic Name
    @Override
    public String getPrimaryMagicName() {
        return "Royal Decree"; // Name of the primary magic attack
    }

    // Character's Primary Magic
    @Override
    public void primaryMagic(Character target, int damage) {
        sayCatchphrase("Royal Decree"); // Vocalizes the attack
        int manaCost = 45; // Mana cost for using this attack
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(3, 10); // Royal Decree: Roll 3D10 for damage
            System.out.println(this.name + " proclaims a Royal Decree dealing " + (damageDealt - damage) + " magical damage!");
            target.takeDamage(damageDealt - damage); // Deals damage to the target
            mana -= manaCost; // Reduces mana after the attack
        } else {
            System.out.println("Not enough mana to perform Royal Decree!"); // Insufficient mana message
        }
        restore(); // Restore after the attack
    }

    // Character's Secondary Magic Name
    @Override
    public String getSecondaryMagicName() {
        return "Imperial Judgment"; // Name of the secondary magic attack
    }

    // Character's Secondary Magic
    @Override
    public void secondaryMagic(Character target, int damage) {
        sayCatchphrase("Imperial Judgment"); // Vocalizes the attack
        int manaCost = 55; // Mana cost for using this attack
        if (mana >= manaCost) {
            int damageDealt = dice.rollDice(4, 8); // Imperial Judgment: Roll 4D8 for damage
            System.out.println(this.name + " pronounces Imperial Judgment dealing " + (damageDealt - damage) + " mystical damage!");
            target.takeDamage(damageDealt - damage); // Deals damage to the target
            mana -= manaCost; // Reduces mana after the attack
        } else {
            System.out.println("Not enough mana to perform Imperial Judgment!"); // Insufficient mana message
        }
        restore(); // Restore after the attack
    }

    // Character's Tertiary Magic Name
    @Override
    public String getTertiaryMagicName() {
        return "Divine Emperor"; // Name of the tertiary magic attack
    }

    // Character's Tertiary Magic
    @Override
    public void tertiaryMagic(Character target, int damage) {
        sayCatchphrase("Divine Emperor"); // Vocalizes the attack
        int divinityCost = 35; // Divinity cost for using this attack
        if (divinity >= divinityCost) {
            int healingDone = dice.rollDice(3, 10); // Divine Emperor: Roll 3D10 for healing

            if (target == this) {
                this.health += healingDone;
                System.out.println(this.name + " channels the Divine Emperor and heals for " + healingDone + "!");
            } else {
                target.setHealth(target.getHealth() + healingDone);
                System.out.println(this.name + " channels the Divine Emperor and heals " + target.getName() + " for " + healingDone + "!");
            }
            divinity -= divinityCost; // Reduces divinity after the attack
        } else {
            System.out.println("Not enough divinity to perform Divine Emperor!"); // Insufficient divinity message
        }
        restore(); // Restore after the attack
    }

    // Character's 'Heal' Name
    @Override
    public String getHealName() {
        return "Regal Blessing"; // Name of the healing action
    }

    // Character's 'Heal'
    @Override
    public void heal(Character target, int damage) {
        sayCatchphrase("Regal Blessing"); // Vocalizes the action
        int staminaCost = 25; // Stamina cost for using this action
        if (stamina >= staminaCost) {
            int healingDone = dice.rollDice(4, 8); // Regal Blessing: Roll 4D8 for healing

            if (target == this) {
                this.health += healingDone;
                System.out.println(this.name + " bestows Regal Blessing upon themselves and heals for " + healingDone + "!");
            } else {
                target.setHealth(target.getHealth() + healingDone);
                System.out.println(this.name + " bestows Regal Blessing upon " + target.getName() + " and heals for " + healingDone + "!");
            }
            stamina -= staminaCost; // Reduces stamina after the action
        } else {
            System.out.println("Not enough stamina to perform Regal Blessing!"); // Insufficient stamina message
        }
        restore(); // Restore after the action
    }

    @Override
public void sayCatchphrase(String action) {
    // Switch statement to determine the appropriate catchphrase for the given action
    switch (action) {
        case "Imperial Strike":
            System.out.println(this.name + ": 'For the Empire!'"); // Display catchphrase for Imperial Strike
            break;
        case "Crown Slam":
            System.out.println(this.name + ": 'None shall oppose!'"); // Display catchphrase for Crown Slam
            break;
        case "Royal Guard":
            System.out.println(this.name + ": 'Protect the Empire!'"); // Display catchphrase for Royal Guard
            break;
        case "Damage Taken":
            System.out.println(this.name + ": 'The throne endures!'"); // Display catchphrase for Damage Taken
            break;
        case "Death":
            System.out.println(this.name + ": 'The Empire will remember!'"); // Display catchphrase for Death
            break;
        case "Regal Blessing":
            System.out.println(this.name + ": 'The Emperor's grace be upon you!'"); // Display catchphrase for Regal Blessing
            break;
        case "Emperor's Wrath":
            System.out.println(this.name + ": 'Witness true power!'"); // Display catchphrase for Emperor's Wrath
            break;
        case "Royal Decree":
            System.out.println(this.name + ": 'Obey the Emperor's command!'"); // Display catchphrase for Royal Decree
            break;
        case "Imperial Judgment":
            System.out.println(this.name + ": 'Feel the Empire's judgment!'"); // Display catchphrase for Imperial Judgment
            break;
        case "Divine Emperor":
            System.out.println(this.name + ": 'Embrace the Emperor's divine touch!'"); // Display catchphrase for Divine Emperor
            break;
        default:
            System.out.println(this.name + ": 'For the glory of the Empire!'"); // Default catchphrase for unknown actions
            break;
    }
}

}
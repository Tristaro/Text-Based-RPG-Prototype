// This is the Character interface that you will have to implement in your assignment.
public interface Character {
    
    // Methods For Names
    void setName(String name); // Sets the Name of a Character
    String getName(); // Gets the Name of a Character
    
    // Methods For Health
    int getHealth(); // Gets the Health of a Character
    int maxHealth(); // Max Health of a Character
    void setHealth(int health); // Sets the Health of a Character
    
    // Methods For Mana
    int getMana(); // Gets the Mana of a Character
    int maxMana(); // Max Mana of a Character
    void setMana(int mana); // Sets the Mana of a Character

    // Methods For Stamina
    int getStamina(); // Gets the Stamina of a Character
    int maxStamina(); // Max Stamina of a Character
    void setStamina(int stamina); // Sets the Stamina of a Character
    
    // Methods For Divinity
    int getDivinity(); // Gets the Divinity of a Character
    int maxDivinity(); // Max Divinity of a Character
    void setDivinity(int Divinity); // Sets the Divinity of a Character

    // Method to handle character Actions
    void takeDamage(int damage); // Calculate Damage taken by Character
    void CharDeath(); // Handle character death
    boolean isAlive(); // Handles Alive State
    void block(); // Character's Damage Negating Move 
    String getBlockName();
    void restore(); // Restores Mana & Stamina & Divinity
    // Melee Attacks
    String getPrimaryAttackName();
    void primaryAttack(Character target, int damage); // Character's Primary Attack
    String getSecondaryAttackName();
    void secondaryAttack(Character target, int damage); // Character's Secondary Attack
    String getSpecialAttackName();
    void specialAttack(Character target, int damage);  // Character's Special Attack
    // Magic Attacks
    String getPrimaryMagicName();
    void primaryMagic(Character target, int damage); // Character's Primary Magic
    String getSecondaryMagicName();
    void secondaryMagic(Character target, int damage); // Character's Secondary Magic
    String getTertiaryMagicName();
    void tertiaryMagic(Character target, int damage);  // Character's Tertiary Magic
    String getHealName();
    void heal(Character target, int damage); // Character's 'Heal' 

    //Methods for Buffs
    // To Be Added For Cpt

    //Method For Catchpharse 
    void sayCatchphrase(String action);

}
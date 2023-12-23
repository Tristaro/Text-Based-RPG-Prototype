/*
Tristan Braganza
12/08/2023
Unit 3 - Assignment - Dice.java
*/
import java.util.Random;

public class Dice {
    private Random random; // Random number generator for dice rolls

    public Dice() {
        random = new Random(); // Initializing the random number generator
    }

    // Roll a 4-sided die and return the result
    public int rollD4() {
        return random.nextInt(4) + 1; // Rolling a D4 die
    }

    // Roll a 6-sided die and return the result
    public int rollD6() {
        return random.nextInt(6) + 1; // Rolling a D6 die
    }

    // Roll an 8-sided die and return the result
    public int rollD8() {
        return random.nextInt(8) + 1; // Rolling a D8 die
    }

    // Roll a 10-sided die and return the result
    public int rollD10() {
        return random.nextInt(10) + 1; // Rolling a D10 die
    }

    // Roll a 12-sided die and return the result
    public int rollD12() {
        return random.nextInt(12) + 1; // Rolling a D12 die
    }

    // Roll a 20-sided die and return the result
    public int rollD20() {
        return random.nextInt(20) + 1; // Rolling a D20 die
    }

    // Roll a specified number of dice with a specified number of sides each and return the sum
    public int rollDice(int numDice, int diceSides) {
        int sum = 0;
        for (int i = 0; i < numDice; i++) {
            sum += random.nextInt(diceSides) + 1; // Rolling multiple dice and summing the results
        }
        return sum; // Returning the total sum of the dice rolls
    }
}
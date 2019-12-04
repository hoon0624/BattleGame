import java.util.Random;
import java.util.ArrayList;
public class Character {
	
	// attributes
	private String name;
	private double attackValue;
	private double maxHealth;
	private double currentHealth;
	private int numOfWins;
	private static ArrayList<Spell> spells;
	
	// constructor
	public Character(String name, double attackValue, double maxHealth, int numOfWins) {
		this.name = name; 
		this.attackValue = attackValue;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		this.numOfWins = numOfWins;
	}
	
	// other methods
	// get methods
	public String getName() {
		return this.name;
	}
	
	public double getAttackValue() {
		return this.attackValue;
	}
	
	public double getMaxHealth() {
		return this.maxHealth;
	}
	
	public double getCurrHealth() {
		return this.currentHealth;
	}
	
	public int getNumWins() {
		return this.numOfWins;
	}
	
	// set method
	public static void setSpells(ArrayList<Spell> s) {
		Character.spells = s;
	}
	
	// displaySpells() that prints out one spell per line
	public static void displaySpells() {
		for(int i=0; i<spells.size(); i++) {
			System.out.println(spells.get(i));
		}
	}
	
	// castSpell() method that takes name of a spell to cast and an int and returns a double indicating damage
	public double castSpell(String spell, int x) {
		// temporary value to keep double
		double counter=0;
		// check if ArrayList spells has the String spell
		for(int i=0; i<spells.size(); i++) {
			if(spells.get(i).getName().equalsIgnoreCase(spell)) {
				counter = spells.get(i).getMagicDamage(x);
				return counter;
			}
		}
		return -1;
	}
	
	// toString() method
	public String toString() {
		String s = name + " current health is " + currentHealth;
		return s;
	}
	
	// method that calculates and returns how much damage one character does when they attack 
	public double getAttackDamage(int a) {
		Random numberGenerator = new Random(a);
		double attackDamage = this.attackValue * ((numberGenerator.nextDouble()*0.3) + 0.7);
		return attackDamage;
	}
	
	// method that returns character's current health after taking damage
	public double takeDamage(double damageDone) {
		this.currentHealth = currentHealth - damageDone;
		return currentHealth;
	}
	
	// method increases numOfWins everytime a character has won
	public void increaseWins() {
		this.numOfWins = numOfWins + 1;
	}
	
}

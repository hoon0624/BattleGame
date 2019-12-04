import java.util.Random;
public class Spell {

	// attributes
	private String name;
	private double minDamage;
	private double maxDamage;
	private double chanceOfSuccess;
	
	// constructor
	public Spell(String name, double minDamage, double maxDamage, double chanceOfSuccess) {
		this.name = name;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.chanceOfSuccess = chanceOfSuccess;
		
		// throw IllegalArgumentException if minDamage is less than 0 or greater than maxDamage
		if(minDamage < 0 || minDamage > maxDamage) {
			throw new IllegalArgumentException("minimum damage is less than 0 or greater than the maximum damage");
		}
		// throw IllegalArgumentException if chance of success is less than 0 or greater than 1
		if(chanceOfSuccess < 0 || chanceOfSuccess > 1) {
			throw new IllegalArgumentException("chance of success is less than zero or greater than one");
		}
	}
	
	// other methods
	public String getName() {
		return this.name;
	}

	// getMagicDamage() method that takes input for the seed to generate random object and returns double for damage
	public double getMagicDamage(int x) {
		Random randomGenerator = new Random(x);
		double randomNumber = randomGenerator.nextDouble();
		if(randomNumber > chanceOfSuccess) {
			return 0;
		}
		else {
			double randomDouble = randomGenerator.nextDouble()*(maxDamage - minDamage) + minDamage; 
			return randomDouble;
		}
	}
	
	// toString() method
	public String toString() {
		String s = "Name: " + name + " Damage: " + minDamage + "-" + maxDamage + " Chance: " + (chanceOfSuccess*100)+"%"; 
		return s;
	}
}

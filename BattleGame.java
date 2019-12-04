import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
public class BattleGame {
		
	// attribute
	private static Random numberGenerator = new Random(789);
	
	// private helper method that displays the information of characters
	private static void displayInfo(Character a) {
		System.out.println("Name: " + a.getName() + "\nHealth: " + a.getMaxHealth());
		System.out.println("Attack: " + a.getAttackValue() + "\nNumberOfWins: " + a.getNumWins() + "\n");
	}
	
	// private helper method that displays one character's attack damage and the other's health
	private static void displayAttack(Character a, Character b) {
		// display character's name and how much damage they do 
		double characterDamage = a.getAttackDamage(numberGenerator.nextInt());
		System.out.println(a.getName() + " attacks for " + characterDamage + " damage!");
		
		// character b takes damage
		b.takeDamage(characterDamage);
	}
	
	// private helper method that displays when the player has lost
	private static void displayLost(Character monster, String filename) {
		System.out.println();
		System.out.println("Oh no! You lost!");
		System.out.println("Successfully wrote to file: " + filename);
		System.out.println(monster.getName() + " has won: " + monster.getNumWins() + " times");
		FileIO.writeCharacter(monster, filename);
	}
	// private helper method that displays when the player has won
	private static void displayWon(Character player, String filename) {
		System.out.println();
		System.out.println("Fantastic! You killed the monster");
		System.out.println("Successfully wrote to file: " + filename);
		System.out.println(player.getName() + " has won: " + player.getNumWins() + " times");
		FileIO.writeCharacter(player, filename);
	}
	
	// playGame() method
	public static void playGame(String p, String m, String spellName) {
		// call readSpells() method 
		ArrayList<Spell> arrOfSpells = FileIO.readSpells(spellName);
		
		// if readSpells() method returns null print a message
		if(arrOfSpells == null) {
			System.out.println("Game will be played without spells");
		}
			
		// create the player and the monster using readCharacter() method
		Character player = FileIO.readCharacter(p);
		Character monster = FileIO.readCharacter(m);
		
		// if method does not return two valid reference print message saying game cannot be played
		if(player == null || monster == null) {
			System.out.println("game cannot be played");
			return;
		}
		
		// use setSpells() method to initialize attributes
		Character.setSpells(arrOfSpells);
			
		// display info of two characters
		displayInfo(player);
		displayInfo(monster);
		
		// display available spells 
		System.out.println("Here are the available spells:");
		player.displaySpells();
		
		// create Scanner object to take input
		Scanner read = new Scanner(System.in);
			
		// loop for the game
		while(monster.getCurrHealth() > 0 || player.getCurrHealth() > 0) {
			// ask user for command
			System.out.println("Enter a command: ");
			String command = read.nextLine(); 
			
			// when player commands to attack
			if(command.toLowerCase().equals("attack")) {
				displayAttack(player, monster);
				if(monster.getCurrHealth() > 0) {
					// use toString() method to display name and current health
					System.out.println(monster);
					System.out.println();
				} else {
					System.out.println(monster.getName() + " was knocked out!");	
					player.increaseWins();
					// use private helper method to display the message that the player won
					displayWon(player, p);
					break;
				}
				
				// swapping the roles
				displayAttack(monster, player);
				if(player.getCurrHealth() > 0) {
					// use toString() method to display name and current health
					System.out.println(player);
					System.out.println();
				} else {
					System.out.println(player.getName() + " was knocked out!");	
					monster.increaseWins();
					// use private helper method to display the message that the player lost
					displayLost(monster, m);
					break;
				}
			}
			
			// if user put quit as command terminate the method
			else if(command.toLowerCase().equals("quit")) {
				System.out.println("Goodbye!");
				break;
			}
			
			// if user put command that is not attack or quit, player is casting spell
			else {
				int randNum = numberGenerator.nextInt();
				double spellDamageDone = player.castSpell(command, randNum);
				
				if(spellDamageDone == -1) {
					System.out.println(player.getName() + " tried to cast " + command + " ,but they don't know that spell.");
					System.out.println();
					// monster attacks the player
					displayAttack(monster, player);
					if(player.getCurrHealth() > 0) {
						System.out.println(player);
						System.out.println();
					} else {
						System.out.println(player.getName() + " was knocked out!");	
						monster.increaseWins();
						displayLost(monster, m);
						break;
					}
				}
				
				// if spellDamageDone is 0, it means the character has failed to cast
				else if(spellDamageDone <= 0) {
					System.out.println(player.getName() + " tried to cast " + command + ", but they failed.");
					System.out.println();
					// monster attacks the player
					displayAttack(monster, player);
					if(player.getCurrHealth() > 0) {
						System.out.println(player);
						System.out.println();
					} else {
						System.out.println(player.getName() + " was knocked out!");	
						monster.increaseWins();
						displayLost(monster, m);
						break;
					}
				} 
				
				// when player has successfully casted a sepll
				else {
					System.out.println(player.getName() + " casts " + command + " dealing " + spellDamageDone + " damage!" );
					monster.takeDamage(spellDamageDone);
					if(monster.getCurrHealth() > 0) {
						System.out.println(monster);
						System.out.println();
					} else {
						System.out.println(monster.getName() + " was knocked out!");	
						player.increaseWins();
						displayWon(player, p);
						break;
					}
					
					displayAttack(monster, player);
					if(player.getCurrHealth() > 0) {
						// use toString() method to display name and current health
						System.out.println(player);
						System.out.println();
					} else {
						System.out.println(player.getName() + " was knocked out!");	
						monster.increaseWins();
						displayLost(monster, m);
						break;
					}
				}
			}	
		}
	}
}

import java.io.*;
import java.util.ArrayList;
public class FileIO {
	
	// a method that takes filename as a String parameter and returns a new Character
	public static Character readCharacter(String filename) {
		// make new Character so it can be returned after try&catch blocks
		Character s = null;
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String name = br.readLine();
			double attackValue = Double.parseDouble(br.readLine());
			double maxHealth = Double.parseDouble(br.readLine());
			int numOfWins = Integer.parseInt(br.readLine());
			
			s = new Character(name, attackValue, maxHealth, numOfWins);
			
			br.close();
			fr.close();
		} catch(FileNotFoundException e) {
			System.out.println("file was not thre");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("something went wrong");
			e.printStackTrace();
		}
		
		return s;
	}
	
	public static ArrayList<Spell> readSpells(String filename){
		// make new ArrayList so it can be returned after try&catch blocks
		ArrayList<Spell> spellArray = new ArrayList<Spell>();
		
		try {
			FileReader fr = new FileReader(filename);
			// second filer reader 
			FileReader fr2 = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			// second buffered reader 
			BufferedReader br2 = new BufferedReader(fr2);
			String currentLine = br.readLine();
			// counting how many lines are in a file
			int counter=0;
			while(currentLine != null) { 
				counter++;
				currentLine = br.readLine();
			}
			
			// loop for putting each line of spells in ArrayList of Spells
			String spellLine = br2.readLine();
			for(int i=0; i<counter; i++) {
				// split a line of String into 4 of individual values
				String[] arrOfSpell = spellLine.split("\t");
				// make a Spell object with these value and store them in ArrayList of Spells
				Spell temp = new Spell(arrOfSpell[0], Double.parseDouble(arrOfSpell[1]), Double.parseDouble(arrOfSpell[2]), Double.parseDouble(arrOfSpell[3]));
				spellArray.add(temp);
				spellLine = br2.readLine();
			}
			br.close();
			br2.close();
			fr.close();
			fr2.close();
			
			// catch blocks
		} catch(FileNotFoundException e) {
			System.out.println("file was not there");
			e.printStackTrace();
			return null;
		} catch(IOException e) {
			System.out.println("something went wrong");
			e.printStackTrace();
			return null;
		}
		return spellArray;
	}
	
	// method that write the characters back to a file 
	public static void writeCharacter(Character c, String filename) {
		try {
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(c.getName());
			bw.newLine();
			bw.write(Double.toString(c.getAttackValue()));
			bw.newLine();
			bw.write(Double.toString(c.getMaxHealth()));
			bw.newLine();
			bw.write(Integer.toString(c.getNumWins()));
			
			bw.close();
			fw.close();
		} catch(IOException e) {
			System.out.println("something went wrong");
			e.printStackTrace();
		}
	}
}

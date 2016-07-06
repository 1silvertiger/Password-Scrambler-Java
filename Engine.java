import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Engine {

	private static final int HIGHEST_NON_META = 126;

	private int lowestUsableAscii;
	private int highestUsableAscii;

	private Random rand;
	private CharacterGuideline guideline;

	// the seed can be taken from several sources, so we let a different class
	// handle it
	public Engine(Seed seed, CharacterGuideline guideline) {
		rand = new Random(seed.getSeed());
		this.guideline = guideline;
		lowestUsableAscii = this.guideline.getLowestUsableAscii();
		highestUsableAscii = this.guideline.getHighestUsableAscii();
	}

	// this finds the scrambled password for a string input
	public String generateScramble(String input) {
		// this will hold the substitutes of all the characters we get
		ArrayList<char[]> substitutes = new ArrayList<>();

		// we generate a substitute for each character
		for (int i = 0; i < input.length(); i++) {
			substitutes.add(generateSubstitutes(input.charAt(i)));
			// add salt
			if (i == rand.nextInt(i + 1)) {
				substitutes.add(generateSubstitutes((char) randomAscii()));
			}
		}

		// we randomize the quads and return
		String temp1 = randomizeSubstitutes(substitutes);
		return temp1;
	}

	private char[] generateSubstitutes(char character) {
		// this holds the substitute we're generating
		char[] thisGroup = new char[randomSize()];
		for (int i = 0; i < thisGroup.length; i++) {
			thisGroup[i] = (char) generateRandomAscii(character);
		}
		return thisGroup;
	}

	// we only have the substitutes. we have no idea what our original
	// characters were
	private String randomizeSubstitutes(ArrayList<char[]> quads) {
		String output = null;

		// the ints in this array will be the new order of the chars
		// in our quads.
		int[] newOrder;

		// this will hold all the chars from all the quads
		ArrayList<Character> allCharsFromQuads = new ArrayList<>();

		// this will hold all the chars from the quads after they've been
		// scrambled
		ArrayList<Character> scrambledChars = new ArrayList<>();

		// \\//\\//\\//\\//\\//\\Combine all quads//\\//\\//\\//\\//\\//\\

		// this iterates through all the chars from the quads
		// we are putting them all in one array
		for (int i = 0; i < quads.size(); i++) {
			// we get the array of chars from this group
			char[] temp1 = quads.get(i);
			// we go through the array and add each individual element to
			// allCharsFromQuads
			for (int j = 0; j < temp1.length; j++) {
				allCharsFromQuads.add(temp1[j]);
			}
		}

		// now we have all the chars from the quads in one array list,
		// allCharsFromQuads

		// \\//\\//\\//\\//\\//\\Scramble all characters//\\//\\//\\//\\//\\//\\

		// this scrambles the chars. we go sequentially through the positions in
		// scrambled chars and newOrder. the sequential position in
		// scrambledChars is assigned the newOrder position of allCharsFromQuads
		newOrder = permute(allCharsFromQuads.size());
		for (int i = 0; i < allCharsFromQuads.size(); i++) {
			scrambledChars.add(allCharsFromQuads.get(newOrder[i]));
		}

		// we turn our array of chars into an string
		char[] finalPassword = new char[scrambledChars.size() + 1];
		for (int i = 0; i < scrambledChars.size(); i++) {
			finalPassword[i] = scrambledChars.get(i);
		}
		output = String.valueOf(finalPassword);

		return output;
	}

	// this finds a new ascii for a character
	private int generateRandomAscii(int ascii) {
		// this holds our ascii
		int temp1 = 0;
		// this iterates an ascii code number of times
		for (int i = 0; i <= ascii; i++) {
			// we throw away most of the generated ascii codes
			temp1 = randomAscii();
		}
		return temp1;
	}

	// this returns a random usable ascii
	private int randomAscii() {
		int temp1;
		boolean keepTrying = false;

		do {
			do {
				temp1 = rand.nextInt(highestUsableAscii);
				if (temp1 > HIGHEST_NON_META) {
					if (rand.nextInt(11) % 3 != 0) {
						temp1 = lowestUsableAscii - 1;
					}
				}
			} while ((temp1 < lowestUsableAscii || temp1 > highestUsableAscii) );
			keepTrying = !guideline.checkCharacter(temp1);
		} while (keepTrying);

		return temp1;
	}

	private int randomSize() {
		int temp1 = 0;
		while (temp1 < 3 || temp1 > 5) {
			temp1 = rand.nextInt(5);
		}
		return temp1;
	}

	private int[] permute(int number) {

		// this will hold the permutation
		int[] ints = new int[number];
		// if intsUsed[i] = true, i is already assigned to a position in ints
		boolean[] intsUsed = new boolean[number];

		// we make sure all of intsUsed is false
		for (int j = 0; j < intsUsed.length; j++) {
			intsUsed[j] = false;
		}

		boolean tryAgain;
		for (int i = 0; i < ints.length; i++) {
			do {
				tryAgain = false;
				int num = rand.nextInt(number);
				ints[i] = num;
				if (intsUsed[num]) {
					tryAgain = true;
				}
				intsUsed[num] = true;
			} while (tryAgain);
		}

		return ints;
	}
}

//add password length
//add minimum character requirements
//lock symbol field so no other characters can be typed in
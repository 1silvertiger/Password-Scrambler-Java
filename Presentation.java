import java.util.Scanner;

public class Presentation {

	public static void main(String[] args) {
		Seed seed = new Seed();
		Scanner keyboard = new Scanner(System.in);
		TextTransfer textTransfer = new TextTransfer();
		CharacterGuideline guideline = new CharacterGuideline();
		
		System.out.println("Uppercase = 0");
		System.out.println("Lowercase = 1");
		System.out.println("Numbers = 2");
		System.out.println("Symbols = 3");
		System.out.println("Metacharacters = 4");

		int choice = keyboard.nextInt();
		switch (choice) {
		case 0:
			guideline.setAllowable(CharacterGuidelinePresets.UPPERCASE);
			break;
		case 1:
			guideline.setAllowable(CharacterGuidelinePresets.LOWERCASE);
			break;
		case 2:
			guideline.setAllowable(CharacterGuidelinePresets.NUMBERS);
			break;
		case 3:
			guideline.setAllowable(CharacterGuidelinePresets.SYMBOLS);
			break;
		case 4:
			guideline.setAllowable(CharacterGuidelinePresets.METACHARACTERS);
			break;
		}

		Engine engine = new Engine(seed, guideline);
		String input;
		
		do {
			System.out.print("Type: ");
			input = keyboard.nextLine();
			String output = engine.generateScramble(input);
			System.out.println("Generated password: " + output);
			textTransfer.setClipboardContents(output);
			System.out.println("Clipboard contents: " + textTransfer.getClipboardContents());

		} while (input != "0");

		keyboard.close();
	}

}

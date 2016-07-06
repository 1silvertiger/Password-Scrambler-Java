
public class CharacterGuideline {

	private static final int LOWEST_SYMBOL1 = 32;
	private static final int HIGHEST_SYMBOL1 = 47;
	private static final int LOWEST_SYMBOL2 = 58;
	private static final int HIGHEST_SYMBOL2 = 64;
	private static final int LOWEST_SYMBOL3 = 91;
	private static final int HIGHEST_SYMBOL3 = 96;
	private static final int LOWEST_SYMBOL4 = 123;
	private static final int HIGHEST_SYMBOL4 = 126;
	private static final int LOWEST_NUMBER = 48;
	private static final int HIGHEST_NUMBER = 57;
	private static final int LOWEST_LOWERCASE_LETTER = 97;
	private static final int HIGHEST_LOWERCASE_LETTER = 122;
	private static final int HIGHEST_UPPERCASE_LETTER = 90;
	private static final int LOWEST_UPPERCASE_LETTER = 65;
	private static final int LOWEST_META = 128;
	private static final int HIGHEST_META = 255;

	private int[] printableAsciis = { 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
			52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78,
			79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104,
			105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125,
			126, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181,
			182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202,
			203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223,
			224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244,
			245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 };

	private Ascii[] asciis = new Ascii[printableAsciis.length];

	public CharacterGuideline() {
		for (int i = 0; i < asciis.length; i++) {
			asciis[i] = new Ascii(printableAsciis[i], (char) printableAsciis[i]);
			asciis[i].setUsable(false);
		}
	}

	public void setAllowable(CharacterGuidelinePresets guideline) {
		for (int i = 0; i < asciis.length; i++) {
			Ascii ascii = asciis[i];
			switch (guideline) {
			case LOWERCASE:
				if (ascii.getAsciiCode() >= LOWEST_LOWERCASE_LETTER
						&& ascii.getAsciiCode() <= HIGHEST_LOWERCASE_LETTER) {
					ascii.setUsable(true);
				}
				break;
			case NUMBERS:
				if (ascii.getAsciiCode() >= LOWEST_NUMBER && ascii.getAsciiCode() <= HIGHEST_NUMBER) {
					ascii.setUsable(true);
				}
				break;
			case UPPERCASE:
				if (ascii.getAsciiCode() >= LOWEST_UPPERCASE_LETTER
						&& ascii.getAsciiCode() <= HIGHEST_UPPERCASE_LETTER) {
					ascii.setUsable(true);
				}
				break;
			case SYMBOLS:
				if (ascii.getAsciiCode() >= LOWEST_SYMBOL1 && ascii.getAsciiCode() <= HIGHEST_SYMBOL1) {
					ascii.setUsable(true);
				} else if (ascii.getAsciiCode() >= LOWEST_SYMBOL2 && ascii.getAsciiCode() <= HIGHEST_SYMBOL2) {
					ascii.setUsable(true);
				} else if (ascii.getAsciiCode() >= LOWEST_SYMBOL3 && ascii.getAsciiCode() <= HIGHEST_SYMBOL3) {
					ascii.setUsable(true);
				} else if (ascii.getAsciiCode() >= LOWEST_SYMBOL4 && ascii.getAsciiCode() <= HIGHEST_SYMBOL4) {
					ascii.setUsable(true);
				}
				break;
			case METACHARACTERS:
				if (ascii.getAsciiCode() >= LOWEST_META && ascii.getAsciiCode() <= HIGHEST_META) {
					ascii.setUsable(true);
				}
				break;
			default:
				ascii.setUsable(true);
				break;
			}
		}
	}

	public void setString(String string) {
		char[] chars = string.toCharArray();
		chars.toString();
		for (Character temp1 : chars) {
			for (Ascii ascii : asciis) {
				if (ascii.getCharacter() == temp1) {
					ascii.setUsable(true);
				}
			}
		}
	}

	public void setCharacter(int asciiCode, boolean condition) {
		for (Ascii ascii : asciis) {
			if (ascii.getAsciiCode() == asciiCode) {
				ascii.setUsable(condition);
			}
		}
	}

	public void setCharacter(char character, boolean condition) {
		for (Ascii ascii : asciis) {
			if (ascii.getCharacter() == character) {
				ascii.setUsable(condition);
			}
		}
	}

	public boolean checkCharacter(int asciiCode) {
		boolean isUsable = false;
		for (Ascii ascii : asciis) {
			if (ascii.getAsciiCode() == asciiCode) {
				isUsable = ascii.isUsable();
			}
		}
		return isUsable;
	}

	public boolean checkCharacter(char character) {
		boolean isUsable = false;
		for (Ascii ascii : asciis) {
			if (ascii.getCharacter() == character) {
				isUsable = ascii.isUsable();
			}
		}
		return isUsable;
	}

	public int getHighestUsableAscii() {
		int highest = 0;
		for (Ascii thisAscii : asciis) {
			if (thisAscii.getAsciiCode() > highest) {
				highest = thisAscii.getAsciiCode();
			}
		}
		return highest;
	}

	public int getLowestUsableAscii() {
		int lowest = 0;
		for (Ascii thisAscii : asciis) {
			if (thisAscii.getAsciiCode() < lowest) {
				lowest = thisAscii.getAsciiCode();
			}
		}
		return lowest;
	}

	public String toString() {
		String string = null;
		for (Ascii ascii : asciis) {
			ascii.toString();
		}
		return string;
	}
}

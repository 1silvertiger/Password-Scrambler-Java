
public class Ascii {

    private int asciiCode;
    private char character;
    private boolean usable;

    public Ascii() {
        asciiCode = 0;
        character = '0';
        usable = false;
    }

    public Ascii(int asciiCode, char character) {
        this.asciiCode = asciiCode;
        this.character = character;
        usable = false;
    }
    //adding a test change from InteliJ IDE to test VCS

    public int getAsciiCode() {
        return asciiCode;
    }

    public void setAsciiCode(int asciiCode) {
        this.asciiCode = asciiCode;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public String toString() {
        String temp1 = " ";
//		System.out.println(getAsciiCode());
        System.out.print(getCharacter() + " ");
        System.out.println(isUsable());
        return temp1;
    }

}

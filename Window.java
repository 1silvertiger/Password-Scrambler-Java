import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class Window {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 510, 327);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblTypePassphraseHere = new JLabel("Type key word or phrase here:");
		lblTypePassphraseHere.setBounds(30, 195, 199, 14);
		frame.getContentPane().add(lblTypePassphraseHere);

		JTextArea textArea = new JTextArea();
		textArea.setBackground(UIManager.getColor("ColorChooser.background"));
		textArea.setEditable(false);
		textArea.setBounds(30, 264, 371, 14);
		frame.getContentPane().add(textArea);

		JCheckBox chckbxLowerCaseLetters = new JCheckBox("lower case");
		chckbxLowerCaseLetters.setSelected(true);
		chckbxLowerCaseLetters.setBounds(259, 38, 89, 23);
		frame.getContentPane().add(chckbxLowerCaseLetters);

		JCheckBox chckbxNumbers = new JCheckBox("numbers");
		chckbxNumbers.setSelected(true);
		chckbxNumbers.setBounds(259, 88, 79, 23);
		frame.getContentPane().add(chckbxNumbers);

		JCheckBox chckbxUpperCaseLetters = new JCheckBox("upper case");
		chckbxUpperCaseLetters.setSelected(true);
		chckbxUpperCaseLetters.setBounds(259, 62, 89, 23);
		frame.getContentPane().add(chckbxUpperCaseLetters);

		JCheckBox chckbxSymbols = new JCheckBox("symbols");
		chckbxSymbols.setSelected(true);
		chckbxSymbols.setBounds(259, 115, 79, 23);
		frame.getContentPane().add(chckbxSymbols);

		JCheckBox chckbxMetacharacters = new JCheckBox("metacharacters");
		chckbxMetacharacters.setBounds(259, 168, 124, 23);
		frame.getContentPane().add(chckbxMetacharacters);

		textField = new JTextField();
		textField.setText("!@#$%^&*()-_=+`~[]{}\\|;:' \",.<>/?");
		textField.setBounds(259, 141, 190, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JCheckBox chckbxPrintResult = new JCheckBox("Print result");
		chckbxPrintResult.setBounds(304, 219, 97, 23);
		frame.getContentPane().add(chckbxPrintResult);

		JButton btnScramble = new JButton("Scramble");
		btnScramble.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Seed seed = new Seed();
				CharacterGuideline guideline = new CharacterGuideline();

				if (chckbxLowerCaseLetters.isSelected()) {
					guideline.setAllowable(CharacterGuidelinePresets.LOWERCASE);
				}
				if (chckbxUpperCaseLetters.isSelected()) {
					guideline.setAllowable(CharacterGuidelinePresets.UPPERCASE);
				}
				if (chckbxNumbers.isSelected()) {
					guideline.setAllowable(CharacterGuidelinePresets.NUMBERS);
				}
				if (chckbxSymbols.isSelected()) {
					guideline.setString(textField.getText());
				}
				if (chckbxMetacharacters.isSelected()) {
					guideline.setAllowable(CharacterGuidelinePresets.METACHARACTERS);
				}

				Engine PasswordScrambler = new Engine(seed, guideline);
				TextTransfer textTransfer = new TextTransfer();
				String newPassword = PasswordScrambler.generateScramble(String.valueOf(passwordField.getPassword()));
				textTransfer.setClipboardContents(newPassword);
				textArea.setText("Password loaded in clipboard");
				if (chckbxPrintResult.isSelected()) {
					System.out.println(newPassword);
				}
				passwordField.selectAll();
			}
		});
		btnScramble.setBounds(208, 219, 89, 23);
		frame.getContentPane().add(btnScramble);

		JTextArea txtrMessage = new JTextArea();
		txtrMessage.setEditable(false);
		txtrMessage.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
		txtrMessage.setBackground(UIManager.getColor("ColorChooser.background"));
		txtrMessage.setText(
				"Type an easy to remember key \r\nword or phrase into the field \r\nbelow.  PasswordScrambler will \r\ngenerate a longer, stronger \r\npassword based on the key word \r\nor phrase you provide.  This \r\npassword is loaded into the \r\nclipboard.");
		txtrMessage.setLineWrap(true);
		txtrMessage.setBounds(30, 39, 209, 145);
		frame.getContentPane().add(txtrMessage);

		JLabel lblPasswordscrambler = new JLabel("PasswordScrambler");
		lblPasswordscrambler.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		lblPasswordscrambler.setBounds(30, 11, 168, 30);
		frame.getContentPane().add(lblPasswordscrambler);

		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textArea.setText(null);
				passwordField.setText(null);
			}
		});
		passwordField.grabFocus();
		passwordField.setBounds(30, 220, 168, 20);
		frame.getContentPane().add(passwordField);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 251, 414, 2);
		frame.getContentPane().add(separator);

		JLabel lblInclude = new JLabel("Include:");
		lblInclude.setBounds(262, 20, 64, 14);
		frame.getContentPane().add(lblInclude);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(249, 27, 16, 182);
		frame.getContentPane().add(separator_1);

	}
}

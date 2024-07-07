import java.util.Map;
import java.util.Iterator;

public class decryptor {
    // Map to hold the Vigenere cipher table
    private Map<Character, Map<Character, Character>> map;

    // Key, keystream, plain text, and cipher text strings
    private String key;
    private String keystream = "";
    private String plain_text = "";
    private String cipher_text;

    // Constructor: Initializes the decryptor with a Vigenere cipher map, key, and cipher text
    public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
        this.map = _map;

        // Preprocess both key and text
        preprocessor prepKey = new preprocessor(_key);
        prepKey.preprocess();
        this.key = prepKey.get_preprocessed_string();

        preprocessor prepText = new preprocessor(text);
        prepText.preprocess();
        this.cipher_text = prepText.get_preprocessed_string();

        // Check if the cipher text is empty (invalid input)
        if (cipher_text.isEmpty()) {
            System.out.println("Given input not proper. Please try again.");
        }
    }

    // Decrypt the cipher text to plain text
	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}

    // Generates the keystream based on the key and cipher text length
    private void generate_keystream() {
        StringBuilder keystreamBuilder = new StringBuilder();
        int textLen = cipher_text.length(); // Length of the cipher text
        int keyLen = key.length(); // Length of the key

        // Keystream generation logic:
        if (textLen <= keyLen) {
            keystreamBuilder.append(key, 0, textLen);
        } else {
            for (int i = 0; i < textLen; i++) {
                keystreamBuilder.append(key.charAt(i % keyLen));
            }
        }

        keystream = keystreamBuilder.toString(); // Set the generated keystream
    }

    // Decrypts the cipher text to plain text using the Vigenere cipher table
    private void generate_plain_text() {
        StringBuilder plainTextBuilder = new StringBuilder();

        for (int i = 0; cipher_text.length() > i; i++) {
            char cipherChar = cipher_text.charAt(i); // Character from the cipher text
            char keyChar = keystream.charAt(i); // Corresponding keystream character

            // Use map.get(x).keySet() with an iterator
            Map<Character, Character> row = map.get(keyChar);
            if (row != null) {
                Iterator<Character> iterator = row.keySet().iterator();
                while (iterator.hasNext()) {
                    Character plainChar = iterator.next();
                    if (row.get(plainChar) == cipherChar) {
                        plainTextBuilder.append(plainChar); // Append the decrypted character
                        break;
                    }
                }
            } else {
                throw new IllegalArgumentException("Invalid character in keystream: " + keyChar);
            }
        }

        plain_text = plainTextBuilder.toString(); // Set the decrypted plain text
    }

    // Returns the generated keystream
    public String get_keystream() {
        return keystream;
    }

    // Returns the decrypted plain text
    public String get_plain_text() {
        return plain_text;
    }
}

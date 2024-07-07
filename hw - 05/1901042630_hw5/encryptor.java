import java.util.Map;

public class encryptor {
    // Map to hold the Vigenere cipher table
    private Map<Character, Map<Character, Character>> map;

    // Key, keystream, plain text, and cipher text strings
    private String key;
    private String keystream = "";
    private String plain_text;
    private String cipher_text = "";

    // Constructor: Initializes the encryptor with a Vigenere cipher map, key, and plain text
    public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
        this.map = _map;

        // Preprocess both key and text
        preprocessor prepKey = new preprocessor(_key);
        prepKey.preprocess();
        this.key = prepKey.get_preprocessed_string();

        preprocessor prepText = new preprocessor(text);
        prepText.preprocess();
        this.plain_text = prepText.get_preprocessed_string();

        // Check if the plain text is empty (invalid input)
        if (plain_text.isEmpty()) {
            System.out.println("Given input not proper. Please try again.");
        }
    }

    // Encrypt the plain text to cipher text
	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}

    // Generates the keystream based on the key and plain text length
    private void generate_keystream() {
        StringBuilder keystreamBuilder = new StringBuilder();
        int textLen = plain_text.length(); // Length of the plain text
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

    // Encrypts the plain text to cipher text using the Vigenere cipher table
    private void generate_cipher_text() {
        StringBuilder cipherTextBuilder = new StringBuilder();

        for (int i = 0; plain_text.length() > i; i++) {
            char plainChar = plain_text.charAt(i); // Character from the plain text
            char keyChar = keystream.charAt(i); // Corresponding keystream character

            Map<Character, Character> row = map.get(plainChar);
            if (row != null) {
                char cipherChar = row.get(keyChar);
                cipherTextBuilder.append(cipherChar); // Append the encrypted character
            } else {
                throw new IllegalArgumentException("Invalid character in plaintext: " + plainChar);
            }
        }

        cipher_text = cipherTextBuilder.toString(); // Set the encrypted cipher text
    }

    // Returns the generated keystream
    public String get_keystream() {
        return keystream;
    }

    // Returns the encrypted cipher text
    public String get_cipher_text() {
        return cipher_text;
    }
}

public class preprocessor {
    // Initial input string and preprocessed string
    private String initial_string;
    private String preprocessed_string;

    // Constructor: Initializes the preprocessor with an initial string
    public preprocessor(String str) {
        this.initial_string = str != null ? str : ""; // Set the initial string or an empty string if null
        this.preprocessed_string = ""; // Initialize the preprocessed string
    }

    /**
     * Preprocess the string: capitalize and clean up non-alphabetic characters.
     */
    public void preprocess() {
        capitalize(); // Convert to uppercase and replace specific Turkish characters
        clean(); // Remove non-alphabetic characters
    }

    /**
     * Converts the initial string to uppercase and replaces specific Turkish characters.
     */
    private void capitalize() {
        preprocessed_string = initial_string.toUpperCase()
            .replace('\u0130', 'I'); // Unicode for Turkish uppercase İ (with dot)
    }

    /**
     * Removes all non-alphabetic characters from the string.
     */
    private void clean() {
        StringBuilder cleanString = new StringBuilder();
        for (char c : preprocessed_string.toCharArray()) {
            if (Character.isLetter(c) && c >= 'A' && c <= 'Z') {
                cleanString.append(c); // Append only uppercase English letters
            }
        }
        preprocessed_string = cleanString.toString(); // Set the cleaned string
    }

    /**
     * Returns the preprocessed string.
     * @return preprocessed string
     */
    public String get_preprocessed_string() {
        return preprocessed_string;
    }
}

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
    // Set to hold the English alphabet in uppercase letters
    private Set<Character> english_alphabet = new LinkedHashSet<Character>();

    // Map to hold the Vigenere cipher table
    private Map<Character, Map<Character, Character>> map = new HashMap<Character, Map<Character, Character>>();

    // Constructor: Initializes the English alphabet and Vigenere cipher map
    public alphabet() {
        // do not edit this method
        fill_english_alphabet(); // Fill the English alphabet
        fill_map(); // Fill the Vigenere cipher map
    }

    // Fills the english_alphabet set with uppercase English letters
    private void fill_english_alphabet() {
        // do not edit this method
        for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            english_alphabet.add(c);
        }
    }

    // Fills the map with the Vigenere cipher table using iterators
    private void fill_map() {
        Iterator<Character> rowIterator = english_alphabet.iterator(); // Iterator for rows
        int shift = 0; // Shift index for the cipher table

        while (rowIterator.hasNext()) {
            char rowStart = rowIterator.next(); // Start character of the row

            // Initialize inner map to store the shifted column values
            Map<Character, Character> innerMap = new HashMap<Character, Character>();
            Iterator<Character> colIterator = english_alphabet.iterator(); // Iterator for columns

            // Skip the initial characters according to the shift value
            for (int i = 0; i < shift; i++) {
                if (colIterator.hasNext()) {
                    colIterator.next();
                }
            }

            // Fill the inner map with the shifted column characters
            for (Character colChar : english_alphabet) {
                if (!colIterator.hasNext()) {
                    colIterator = english_alphabet.iterator(); // Reset column iterator
                }
                innerMap.put(colChar, colIterator.next());
            }

            map.put(rowStart, innerMap); // Add the row to the map
            shift++; // Increment the shift value for the next row
        }
    }

    // Prints the Vigenere cipher table
    public void print_map() {
        System.out.println("*** Vigenere Cipher ***\n\n");
        System.out.println("    " + english_alphabet);
        System.out.print("    ------------------------------------------------------------------------------");
        for (Character k : map.keySet()) {
            System.out.print("\n" + k + " | ");
            System.out.print(map.get(k).values());
        }
        System.out.println("\n");
    }

    // Returns the Vigenere cipher map
    public Map get_map() {
        return map;
    }
}

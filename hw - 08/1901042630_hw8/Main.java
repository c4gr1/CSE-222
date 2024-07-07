import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Main class is the entry point of the social network analysis application.
 * It provides a command-line interface for interacting with the social network.
 */
public class Main {
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Main loop for the command-line interface
        while (true) {
            // Display menu options
            System.out.println("===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (choice) {
                    case 1:
                        // Add a new person to the social network
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        System.out.print("Enter hobbies (comma-separated): ");
                        String hobbiesInput = scanner.nextLine();
                        network.addPerson(name, age, Arrays.asList(hobbiesInput.split(",")));
                        break;

                    case 2:
                        // Remove a person from the social network
                        System.out.print("Enter name: ");
                        name = scanner.nextLine();
                        System.out.print("Enter timestamp (yyyy-MM-dd HH:mm:ss): ");
                        String timestampStr = scanner.nextLine();
                        LocalDateTime timestamp = parseTimestamp(timestampStr, formatter);
                        if (timestamp != null) {
                            network.removePerson(name, timestamp);
                        }
                        break;

                    case 3:
                        // Add a friendship between two people
                        System.out.print("Enter first person's name: ");
                        String name1 = scanner.nextLine();
                        System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        String timestampStr1 = scanner.nextLine();
                        LocalDateTime timestamp1 = parseTimestamp(timestampStr1, formatter);
                        System.out.print("Enter second person's name: ");
                        String name2 = scanner.nextLine();
                        System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        String timestampStr2 = scanner.nextLine();
                        LocalDateTime timestamp2 = parseTimestamp(timestampStr2, formatter);
                        if (timestamp1 != null && timestamp2 != null) {
                            network.addFriendship(name1, timestamp1, name2, timestamp2);
                        }
                        break;

                    case 4:
                        // Remove a friendship between two people
                        System.out.print("Enter first person's name: ");
                        name1 = scanner.nextLine();
                        System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        timestampStr1 = scanner.nextLine();
                        timestamp1 = parseTimestamp(timestampStr1, formatter);
                        System.out.print("Enter second person's name: ");
                        name2 = scanner.nextLine();
                        System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        timestampStr2 = scanner.nextLine();
                        timestamp2 = parseTimestamp(timestampStr2, formatter);
                        if (timestamp1 != null && timestamp2 != null) {
                            network.removeFriendship(name1, timestamp1, name2, timestamp2);
                        }
                        break;

                    case 5:
                        // Find the shortest path between two people
                        System.out.print("Enter first person's name: ");
                        name1 = scanner.nextLine();
                        System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        timestampStr1 = scanner.nextLine();
                        timestamp1 = parseTimestamp(timestampStr1, formatter);
                        System.out.print("Enter second person's name: ");
                        name2 = scanner.nextLine();
                        System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        timestampStr2 = scanner.nextLine();
                        timestamp2 = parseTimestamp(timestampStr2, formatter);
                        if (timestamp1 != null && timestamp2 != null) {
                            network.findShortestPath(name1, timestamp1, name2, timestamp2);
                        }
                        break;

                    case 6:
                        // Suggest friends for a person
                        System.out.print("Enter person's name: ");
                        name = scanner.nextLine();
                        System.out.print("Enter timestamp (yyyy-MM-dd HH:mm:ss): ");
                        timestampStr = scanner.nextLine();
                        timestamp = parseTimestamp(timestampStr, formatter);
                        if (timestamp != null) {
                            System.out.print("Enter maximum number of friends to suggest: ");
                            int maxSuggestions = scanner.nextInt();
                            scanner.nextLine();  // Consume newline
                            network.suggestFriends(name, timestamp, maxSuggestions);
                        }
                        break;

                    case 7:
                        // Count clusters in the social network
                        network.countClusters();
                        break;

                    case 8:
                        // Exit the program
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        // Handle invalid menu options
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                // Handle any unexpected errors
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Parses a timestamp string into a LocalDateTime object.
     * 
     * @param timestampStr The timestamp string to parse
     * @param formatter The DateTimeFormatter to use for parsing
     * @return A LocalDateTime object, or null if the input string is invalid
     */
    private static LocalDateTime parseTimestamp(String timestampStr, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(timestampStr, formatter);
        } catch (Exception e) {
            // Handle invalid timestamp format
            System.out.println("Invalid timestamp format. Please use 'yyyy-MM-dd HH:mm:ss'.");
            return null;
        }
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Adding persons with fixed timestamps for consistency
        LocalDateTime johnTimestamp = LocalDateTime.parse("2024-05-29 10:30:00", formatter);
        network.addPerson("John Doe", 25, Arrays.asList("reading", "hiking", "cooking"), johnTimestamp);

        LocalDateTime janeTimestamp = LocalDateTime.parse("2024-05-28 14:45:00", formatter);
        network.addPerson("Jane Smith", 30, Arrays.asList("running", "reading"), janeTimestamp);

        LocalDateTime aliceTimestamp = LocalDateTime.parse("2024-05-27 09:15:00", formatter);
        network.addPerson("Alice Johnson", 28, Arrays.asList("painting", "cooking"), aliceTimestamp);

        LocalDateTime bobTimestamp = LocalDateTime.parse("2024-05-29 11:00:00", formatter);
        network.addPerson("Bob Brown", 35, Arrays.asList("hiking", "painting"), bobTimestamp);

        LocalDateTime charlieTimestamp = LocalDateTime.parse("2024-05-29 09:00:00", formatter);
        network.addPerson("Charlie Lee", 22, Arrays.asList("reading", "running", "gaming"), charlieTimestamp);

        LocalDateTime davidTimestamp = LocalDateTime.parse("2024-05-29 13:00:00", formatter);
        network.addPerson("David Kim", 27, Arrays.asList("gaming", "cooking"), davidTimestamp);

        LocalDateTime emilyTimestamp = LocalDateTime.parse("2024-05-29 08:00:00", formatter);
        network.addPerson("Emily Davis", 23, Arrays.asList("hiking", "running"), emilyTimestamp);

        LocalDateTime frankTimestamp = LocalDateTime.parse("2024-05-29 07:30:00", formatter);
        network.addPerson("Frank Wilson", 40, Arrays.asList("painting", "gaming"), frankTimestamp);

        // Adding friendships
        network.addFriendship("John Doe", johnTimestamp, "Jane Smith", janeTimestamp);
        network.addFriendship("John Doe", johnTimestamp, "Bob Brown", bobTimestamp);
        network.addFriendship("Jane Smith", janeTimestamp, "Alice Johnson", aliceTimestamp);
        network.addFriendship("Alice Johnson", aliceTimestamp, "Bob Brown", bobTimestamp);
        network.addFriendship("Charlie Lee", charlieTimestamp, "David Kim", davidTimestamp);
        network.addFriendship("Emily Davis", emilyTimestamp, "Frank Wilson", frankTimestamp);

        // Finding shortest path
        System.out.println("\nFinding shortest path between John Doe and Alice Johnson:");
        network.findShortestPath("John Doe", johnTimestamp, "Alice Johnson", aliceTimestamp);

        System.out.println("\nFinding shortest path between John Doe and Frank Wilson:");
        network.findShortestPath("John Doe", johnTimestamp, "Frank Wilson", frankTimestamp);

        // Suggesting friends
        System.out.println("\nSuggesting friends for John Doe:");
        network.suggestFriends("John Doe", johnTimestamp, 3);

        System.out.println("\nSuggesting friends for Emily Davis:");
        network.suggestFriends("Emily Davis", emilyTimestamp, 3);

        // Counting clusters
        System.out.println("\nCounting clusters in the network:");
        network.countClusters();

        // Removing friendships
        System.out.println("\nRemoving friendship between John Doe and Bob Brown:");
        network.removeFriendship("John Doe", johnTimestamp, "Bob Brown", bobTimestamp);

        System.out.println("\nRemoving friendship between Emily Davis and Frank Wilson:");
        network.removeFriendship("Emily Davis", emilyTimestamp, "Frank Wilson", frankTimestamp);

        // Counting clusters after removal
        System.out.println("\nCounting clusters after removal:");
        network.countClusters();

        // Removing persons
        System.out.println("\nRemoving person Jane Smith:");
        network.removePerson("Jane Smith", janeTimestamp);

        System.out.println("\nRemoving person Bob Brown:");
        network.removePerson("Bob Brown", bobTimestamp);

        // Counting clusters after person removal
        System.out.println("\nCounting clusters after person removal:");
        network.countClusters();
    }
}

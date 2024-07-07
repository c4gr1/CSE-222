import java.time.LocalDateTime;
import java.util.*;

/**
 * The SocialNetwork class represents a social network using a graph data structure.
 * It includes methods to add/remove people, add/remove friendships, find shortest paths,
 * suggest friends, and count clusters within the network.
 */
public class SocialNetwork {
    private Map<String, Person> people; // Map to store people with their unique identifier (name + timestamp)
    private Map<Person, List<Person>> graph; // Adjacency list representation of the social network graph

    /**
     * Constructs an empty SocialNetwork.
     */
    public SocialNetwork() {
        people = new HashMap<>();
        graph = new HashMap<>();
    }

    /**
     * Adds a person to the social network with the current timestamp.
     *
     * @param name    The name of the person.
     * @param age     The age of the person.
     * @param hobbies The hobbies of the person.
     */
    public void addPerson(String name, int age, List<String> hobbies) {
        LocalDateTime timestamp = LocalDateTime.now().withNano(0); // Remove nanoseconds for simplicity
        addPerson(name, age, hobbies, timestamp);
    }

    /**
     * Adds a person to the social network with a specified timestamp.
     *
     * @param name      The name of the person.
     * @param age       The age of the person.
     * @param hobbies   The hobbies of the person.
     * @param timestamp The timestamp of when the person joined the network.
     */
    public void addPerson(String name, int age, List<String> hobbies, LocalDateTime timestamp) {
        Person person = new Person(name, age, hobbies, timestamp);
        people.put(name + timestamp.toString(), person);
        graph.put(person, new ArrayList<>());
        System.out.println("Person added: " + person);
    }

    /**
     * Removes a person from the social network based on their name and timestamp.
     *
     * @param name      The name of the person.
     * @param timestamp The timestamp of when the person joined the network.
     */
    public void removePerson(String name, LocalDateTime timestamp) {
        Person person = people.remove(name + timestamp.toString());
        if (person != null) {
            graph.remove(person);
            for (List<Person> friends : graph.values()) {
                friends.remove(person);
            }
            System.out.println("Person removed: " + person);
        } else {
            System.out.println("Person not found: " + name + " with timestamp: " + timestamp);
        }
    }

    /**
     * Adds a friendship (edge) between two people in the social network.
     *
     * @param name1      The name of the first person.
     * @param timestamp1 The timestamp of when the first person joined the network.
     * @param name2      The name of the second person.
     * @param timestamp2 The timestamp of when the second person joined the network.
     */
    public void addFriendship(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        Person person1 = people.get(name1 + timestamp1.toString());
        Person person2 = people.get(name2 + timestamp2.toString());

        if (person1 != null && person2 != null) {
            graph.get(person1).add(person2);
            graph.get(person2).add(person1);
            System.out.println("Friendship added between " + person1 + " and " + person2);
        } else {
            System.out.println("One or both persons not found.");
        }
    }

    /**
     * Removes a friendship (edge) between two people in the social network.
     *
     * @param name1      The name of the first person.
     * @param timestamp1 The timestamp of when the first person joined the network.
     * @param name2      The name of the second person.
     * @param timestamp2 The timestamp of when the second person joined the network.
     */
    public void removeFriendship(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        Person person1 = people.get(name1 + timestamp1.toString());
        Person person2 = people.get(name2 + timestamp2.toString());

        if (person1 != null && person2 != null) {
            graph.get(person1).remove(person2);
            graph.get(person2).remove(person1);
            System.out.println("Friendship removed between " + person1 + " and " + person2);
        } else {
            System.out.println("One or both persons not found.");
        }
    }

    /**
     * Finds the shortest path between two people in the social network using Breadth-First Search (BFS).
     *
     * @param name1      The name of the starting person.
     * @param timestamp1 The timestamp of when the starting person joined the network.
     * @param name2      The name of the ending person.
     * @param timestamp2 The timestamp of when the ending person joined the network.
     */
    public void findShortestPath(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        Person start = people.get(name1 + timestamp1.toString());
        Person end = people.get(name2 + timestamp2.toString());

        if (start == null || end == null) {
            System.out.println("One or both persons not found.");
            return;
        }

        Queue<Person> queue = new LinkedList<>();
        Map<Person, Person> previous = new HashMap<>();
        Set<Person> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();

            if (current.equals(end)) {
                List<Person> path = new ArrayList<>();
                for (Person at = end; at != null; at = previous.get(at)) {
                    path.add(at);
                }
                Collections.reverse(path);
                System.out.println("Shortest path: " + path);
                return;
            }

            for (Person neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println("No path found between " + start + " and " + end);
    }

    /**
     * Suggests friends for a person based on mutual friends and common hobbies.
     *
     * @param name          The name of the person.
     * @param timestamp     The timestamp of when the person joined the network.
     * @param maxSuggestions The maximum number of friends to suggest.
     */
    public void suggestFriends(String name, LocalDateTime timestamp, int maxSuggestions) {
        Person person = people.get(name + timestamp.toString());
        if (person == null) {
            System.out.println("Person not found.");
            return;
        }

        Map<Person, Double> scores = new HashMap<>();

        for (Person p : graph.keySet()) {
            if (!p.equals(person) && !graph.get(person).contains(p)) {
                int mutualFriends = 0;
                int commonHobbies = 0;

                for (Person friend : graph.get(person)) {
                    if (graph.get(p).contains(friend)) {
                        mutualFriends++;
                    }
                }

                for (String hobby : person.getHobbies()) {
                    if (p.getHobbies().contains(hobby)) {
                        commonHobbies++;
                    }
                }

                double score = mutualFriends + 0.5 * commonHobbies;
                if (score > 0) {
                    scores.put(p, score);
                }
            }
        }

        List<Map.Entry<Person, Double>> suggestions = new ArrayList<>(scores.entrySet());
        suggestions.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("Suggested friends for " + person.getName() + ":");
        for (int i = 0; i < Math.min(maxSuggestions, suggestions.size()); i++) {
            Person suggestedPerson = suggestions.get(i).getKey();
            double score = suggestions.get(i).getValue();
            System.out.println(suggestedPerson + " (Score: " + score + ")");
        }
    }

    /**
     * Counts and displays the number of clusters (connected components) in the social network.
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>();
        int clusterCount = 0;

        for (Person person : graph.keySet()) {
            if (!visited.contains(person)) {
                clusterCount++;
                System.out.println("Cluster " + clusterCount + ":");
                bfsCluster(person, visited);
                System.out.println();
            }
        }

        System.out.println("Number of clusters found: " + clusterCount);
    }

    /**
     * Performs a Breadth-First Search (BFS) to explore a cluster starting from a given person.
     *
     * @param start   The starting person for the BFS.
     * @param visited A set to keep track of visited people.
     */
    private void bfsCluster(Person start, Set<Person> visited) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            System.out.println(current);

            for (Person neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Person class represents an individual in the social network.
 * It includes personal information such as name, age, hobbies, and the timestamp
 * of when the person joined the network.
 */
public class Person {
    private String name; // The person's name
    private int age; // The person's age
    private List<String> hobbies; // The person's hobbies
    private LocalDateTime timestamp; // The timestamp when the person joined the network

    /**
     * Constructs a new Person with the specified name, age, hobbies, and timestamp.
     * 
     * @param name The person's name
     * @param age The person's age
     * @param hobbies A list of the person's hobbies
     * @param timestamp The date and time when the person joined the network
     */
    public Person(String name, int age, List<String> hobbies, LocalDateTime timestamp) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.timestamp = timestamp;
    }

    /**
     * Returns the name of the person.
     * 
     * @return The person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the person.
     * 
     * @return The person's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the list of hobbies of the person.
     * 
     * @return The person's hobbies
     */
    public List<String> getHobbies() {
        return hobbies;
    }

    /**
     * Returns the timestamp of when the person joined the network.
     * 
     * @return The person's timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns a string representation of the person, including their name, age, and
     * timestamp.
     * 
     * @return A string representation of the person
     */
    @Override
    public String toString() {
        return name + " (Age: " + age + ", Joined: " + timestamp.toString() + ")";
    }
}

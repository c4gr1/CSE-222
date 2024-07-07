public class Person {
    
    // The first name of the person
    String name;
    
    // The last name of the person
    String surname;
    
    // The address of the person
    String address;
    
    // The phone number of the person
    String phone;
    
    // A unique identifier for the person
    int ID;
    
    // Constructor for the Person class
    public Person(String name, String surname, String address, String phone, int ID) {
        this.name = name; // Initialize the first name
        this.surname = surname; // Initialize the last name
        this.address = address; // Initialize the address
        this.phone = phone; // Initialize the phone number
        this.ID = ID; // Initialize the unique identifier
    }

    // Getter for the first name
    public String getName() {
        return name;
    }

    // Setter for the first name
    public void setName(String name) {
        this.name = name; // Set the first name
    }

    // Getter for the last name
    public String getSurname() {
        return surname;
    }

    // Setter for the last name
    public void setSurname(String surname) {
        this.surname = surname; // Set the last name
    }

    // Getter for the address
    public String getAddress() {
        return address;
    }

    // Setter for the address
    public void setAddress(String address) {
        this.address = address; // Set the address
    }

    // Getter for the phone number
    public String getPhone() {
        return phone;
    }

    // Setter for the phone number
    public void setPhone(String phone) {
        this.phone = phone; // Set the phone number
    }

    // Getter for the unique identifier
    public int getID() {
        return ID;
    }

    // Setter for the unique identifier
    public void setID(int ID) {
        this.ID = ID; // Set the unique identifier
    }
}

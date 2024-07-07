public class Operator extends Person {
    
    int wage; // The wage of the operator
    Customer[] customers; // Array to store customers associated with this operator

    // Constructor for the Operator class
    public Operator(String name, String surname, String address, String phone, int ID, int wage) {
        super(name, surname, address, phone, ID); // Call the constructor of the Person superclass
        
        this.wage = wage; // Initialize the wage specific to the Operator
        this.customers = new Customer[100]; // Initialize the array for storing customers
    }

    // Method to print operator details
    public void print_operator() {
        System.out.println("Name & Surname: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID);
        System.out.println("Wage: " + wage);
        System.out.println("----------------------------");
    }

    // Method to print details of all customers associated with this operator
    public void print_customers() {
        int index = 1; // Index to number customers for display

        for (Customer customer : customers) {
            if (customer != null) { // Ensure the customer object is not null
                System.out.print("Customer #" + index + " ");
                if (customer instanceof RetailCustomer) {
                    System.out.println("(a retail customer):");
                } else {
                    System.out.println("(a corporate customer):");
                }
                customer.print_customer(); // Print customer details
                customer.print_orders(); // Print orders of the customer
                System.out.println("----------------------------");
                index++; // Increment index for the next customer
            }
        }
    }
     
    // Method to define (set) customers for this operator
    public void define_customers(Customer[] customers) {
        this.customers = customers; // Set the customers array
    }

    // Getter for wage
    public int getWage() {
        return wage;
    }

    // Setter for wage
    public void setWage(int wage) {
        this.wage = wage; // Set the wage of the operator
    }
}

public abstract class Customer extends Person {

    Order[] orders; // Array to store orders associated with this customer
    int operator_ID; // ID of the operator associated with this customer

    // Constructor for the Customer class
    public Customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
        super(name, surname, address, phone, ID); // Call the constructor of the Person superclass
        
        this.operator_ID = operator_ID; // Initialize the operator ID specific to the Customer
        this.orders = new Order[100]; // Initialize the array for storing orders
    }

    // Method to print customer details
    public void print_customer() {
        System.out.println("Name & Surname: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID);
        System.out.println("Operator ID: " + operator_ID);
    }

    // Method to print all orders associated with this customer
    public void print_orders() {
        int index = 1; // Index to number orders for display

        for (Order order : orders) {
            if (order != null && order.count != 0) { // Ensure the order object is not null and count is not zero
                // Print order details in the desired format
                System.out.print("Order #" + index + " => ");
                order.print_order(); // Print details of the order
                index++; // Increment index for the next order
            }
        }
    }

    // Method to define (set) orders for this customer
    public void define_orders(Order[] orders) {
        this.orders = orders; // Set the orders array
    }

    // Getter for operator_ID
    public int getOperator_ID() {
        return operator_ID;
    }

    // Setter for operator_ID
    public void setOperator_ID(int operator_ID) {
        this.operator_ID = operator_ID; // Set the operator ID of the customer
    }
}

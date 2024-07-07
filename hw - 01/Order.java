public class Order {
    
    // The product name of the order
    String product_name;
    // The quantity of the product ordered
    int count;
    // The total price of the order
    int total_price;
    // The status of the order
    int status;
    // The customer ID associated with the order
    int customer_ID;

    // Constructor for the Order class
    public Order(String product_name, int count, int total_price, int status, int customer_ID) {
        this.product_name = product_name; // Initialize the product name
        this.count = count; // Initialize the count
        this.total_price = total_price; // Initialize the total price
        this.status = status; // Initialize the status
        this.customer_ID = customer_ID; // Initialize the customer ID
    }

    // Method to print order details in the desired format
    public void print_order() {
        String statusText = convertStatusToText(status); // Convert status code to text
        
        // Print order details
        System.out.println("Product name: " + product_name + 
                           " - Count: " + count + 
                           " - Total price: " + total_price + 
                           " - Status: " + statusText + ".");
    }

    // Helper method to convert status code to meaningful text
    private String convertStatusToText(int status) {
        switch (status) {
            case 0: return "Initialized";
            case 1: return "Processing";
            case 2: return "Completed";
            case 3: return "Cancelled";
            default: return "Unknown";
        }
    }

    // Getters and Setters for all fields

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPrice() {
        return total_price;
    }

    public void setTotalPrice(int total_price) {
        this.total_price = total_price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCustomerID() {
        return customer_ID;
    }

    public void setCustomerID(int customer_ID) {
        this.customer_ID = customer_ID;
    }
}

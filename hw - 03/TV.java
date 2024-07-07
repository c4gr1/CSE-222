/**
 * Represents a Television (TV) as a specific type of Device within the inventory system.
 * <p>
 * This class encapsulates details specific to televisions, including their category, name, price, and the quantity available in stock.
 */
public class TV implements Device {
    private String category; // The category of the device, always "TV"
    private String name; // The name of the television
    private double price; // The price of the television
    private int quantity; // The quantity of the television in stock

    /**
     * Constructs a new TV with the specified name, price, and quantity.
     * <p>
     * Time complexity: O(1) - Direct assignment of parameters to the class fields.
     *
     * @param name     The name of the television.
     * @param price    The price of the television.
     * @param quantity The quantity of the television in stock.
     */
    public TV(String name, double price, int quantity) {
        this.category = "TV"; // Assign the fixed category
        this.name = name; // Assign the specified name
        this.price = price; // Assign the specified price
        this.quantity = quantity; // Assign the specified quantity
    }

    /**
     * Returns the category of this device.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The category of the device, which is "TV".
     */
    @Override
    public String getCategory() {
        return category; // Return the device's category
    }

    /**
     * Returns the name of this TV.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The name of the television.
     */
    @Override
    public String getName() {
        return name; // Return the television's name
    }

    /**
     * Returns the price of this TV.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The price of the television.
     */
    @Override
    public double getPrice() {
        return price; // Return the television's price
    }

    /**
     * Returns the quantity of this TV in stock.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The quantity of the television.
     */
    @Override
    public int getQuantity() {
        return quantity; // Return the current stock quantity
    }

    /**
     * Sets the name of this TV.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param name The new name for the television.
     */
    @Override
    public void setName(String name) {
        this.name = name; // Update the television's name
    }

    /**
     * Sets the price of this TV.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param price The new price for the television.
     */
    @Override
    public void setPrice(double price) {
        this.price = price; // Update the television's price
    }

    /**
     * Sets the quantity of this TV in stock.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param quantity The new quantity for the television.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity; // Update the stock quantity
    }
}

/**
 * Represents a Smartphone as a specific type of Device within the inventory system.
 * <p>
 * This class encapsulates details specific to smartphones, including their category,
 * name, price, and quantity available in stock. It provides concrete implementations
 * for the Device interface methods.
 */
public class Smartphone implements Device {
    private String category; // The category of the device, always "Smartphone"
    private String name; // The name of the smartphone
    private double price; // The price of the smartphone
    private int quantity; // The quantity of the smartphone in stock

    /**
     * Constructs a new Smartphone with the specified name, price, and quantity.
     * <p>
     * Time complexity: O(1) - Direct assignment of parameters to the class fields.
     *
     * @param name     The name of the smartphone.
     * @param price    The price of the smartphone.
     * @param quantity The quantity of the smartphone in stock.
     */
    public Smartphone(String name, double price, int quantity) {
        this.category = "Smartphone"; // Assign the fixed category
        this.name = name; // Assign the specified name
        this.price = price; // Assign the specified price
        this.quantity = quantity; // Assign the specified quantity
    }

    /**
     * Returns the category of this device.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The category of the device, "Smartphone".
     */
    @Override
    public String getCategory() {
        return category; // Return the device's category
    }

    /**
     * Returns the name of this smartphone.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The name of the smartphone.
     */
    @Override
    public String getName() {
        return name; // Return the smartphone's name
    }

    /**
     * Returns the price of this smartphone.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The price of the smartphone.
     */
    @Override
    public double getPrice() {
        return price; // Return the smartphone's price
    }

    /**
     * Returns the quantity of this smartphone in stock.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The quantity of the smartphone.
     */
    @Override
    public int getQuantity() {
        return quantity; // Return the current stock quantity
    }

    /**
     * Sets the name of this smartphone.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param name The new name for the smartphone.
     */
    @Override
    public void setName(String name) {
        this.name = name; // Update the smartphone's name
    }

    /**
     * Sets the price of this smartphone.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param price The new price for the smartphone.
     */
    @Override
    public void setPrice(double price) {
        this.price = price; // Update the smartphone's price
    }

    /**
     * Sets the quantity of this smartphone in stock.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param quantity The new quantity for the smartphone.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity; // Update the stock quantity
    }
}

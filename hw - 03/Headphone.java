/**
 * Represents a Headphone as a specific type of Device in the inventory system.
 * <p>
 * This class encapsulates details specific to headphones, including their category, name, price, and quantity available in stock.
 */
public class Headphone implements Device {
    private String category; // Category of the device, always "Headphone"
    private String name; // Name of the headphone
    private double price; // Price of the headphone
    private int quantity; // Quantity of the headphone in stock

    /**
     * Constructs a new Headphone with the specified name, price, and quantity.
     * <p>
     * Time complexity: O(1) - Direct assignment of parameters to the class fields.
     *
     * @param name     The name of the headphone.
     * @param price    The price of the headphone.
     * @param quantity The quantity of the headphone in stock.
     */
    public Headphone(String name, double price, int quantity) {
        this.category = "Headphone"; // Assign the fixed category
        this.name = name; // Assign the specified name
        this.price = price; // Assign the specified price
        this.quantity = quantity; // Assign the specified quantity
    }

    /**
     * Returns the category of this device.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The category of the device, which is "Headphone".
     */
    @Override
    public String getCategory() {
        return category; // Return the device's category
    }

    /**
     * Returns the name of this headphone.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The name of the headphone.
     */
    @Override
    public String getName() {
        return name; // Return the headphone's name
    }

    /**
     * Returns the price of this headphone.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The price of the headphone.
     */
    @Override
    public double getPrice() {
        return price; // Return the headphone's price
    }

    /**
     * Returns the quantity of this headphone in stock.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The quantity of the headphone.
     */
    @Override
    public int getQuantity() {
        return quantity; // Return the current stock quantity
    }

    /**
     * Sets the name of this headphone.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param name The new name for the headphone.
     */
    @Override
    public void setName(String name) {
        this.name = name; // Update the headphone's name
    }

    /**
     * Sets the price of this headphone.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param price The new price for the headphone.
     */
    @Override
    public void setPrice(double price) {
        this.price = price; // Update the headphone's price
    }

    /**
     * Sets the quantity of this headphone in stock.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param quantity The new quantity for the headphone.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity; // Update the stock quantity
    }
}

/**
 * Represents a Keyboard as a specific type of Device in the inventory system.
 * <p>
 * This class provides details specific to keyboards, including category, name, price, and quantity.
 */
public class Keyboard implements Device {
    private String category; // The category of the device, always "Keyboard" for this class
    private String name; // The name of the keyboard
    private double price; // The price of the keyboard
    private int quantity; // The quantity of the keyboard in stock

    /**
     * Constructs a new Keyboard with the specified name, price, and quantity.
     * <p>
     * Time complexity: O(1) - Direct assignment of parameters to fields.
     *
     * @param name     The name of the keyboard.
     * @param price    The price of the keyboard.
     * @param quantity The quantity of the keyboard in stock.
     */
    public Keyboard(String name, double price, int quantity) {
        this.category = "Keyboard"; // Assigns the fixed category "Keyboard"
        this.name = name; // Assigns the specified name
        this.price = price; // Assigns the specified price
        this.quantity = quantity; // Assigns the specified quantity
    }

    /**
     * Returns the category of this device.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The category of the device, which is "Keyboard".
     */
    @Override
    public String getCategory() {
        return category; // Returns the category of the device
    }

    /**
     * Returns the name of this keyboard.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The name of the keyboard.
     */
    @Override
    public String getName() {
        return name; // Returns the name of the keyboard
    }

    /**
     * Returns the price of this keyboard.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The price of the keyboard.
     */
    @Override
    public double getPrice() {
        return price; // Returns the price of the keyboard
    }

    /**
     * Returns the quantity of this keyboard in stock.
     * <p>
     * Time complexity: O(1) - Returns the value of a field.
     *
     * @return The quantity of the keyboard.
     */
    @Override
    public int getQuantity() {
        return quantity; // Returns the current stock quantity
    }

    /**
     * Sets the name of this keyboard.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param name The new name for the keyboard.
     */
    @Override
    public void setName(String name) {
        this.name = name; // Updates the name of the keyboard
    }

    /**
     * Sets the price of this keyboard.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param price The new price for the keyboard.
     */
    @Override
    public void setPrice(double price) {
        this.price = price; // Updates the price of the keyboard
    }

    /**
     * Sets the quantity of this keyboard in stock.
     * <p>
     * Time complexity: O(1) - Assigns a new value to a field.
     *
     * @param quantity The new quantity for the keyboard.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity; // Updates the stock quantity
    }
}

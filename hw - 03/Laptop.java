/**
 * Represents a Laptop device in the inventory system.
 * <p>
 * This class encapsulates the properties of a laptop, such as its category, name,
 * price, and quantity.
 */
public class Laptop implements Device {
    private String category;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new Laptop instance with the specified details.
     * <p>
     * Time complexity: O(1) - Initializing the fields of a Laptop instance is a constant time operation.
     *
     * @param name     The name of the laptop.
     * @param price    The price of the laptop.
     * @param quantity The quantity of laptops in stock.
     */
    public Laptop(String name, double price, int quantity) {
        this.category = "Laptop";
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Retrieves the category of this device.
     * <p>
     * Time complexity: O(1) - Returning a field's value is a constant time operation.
     *
     * @return The category of the device.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Retrieves the name of the laptop.
     * <p>
     * Time complexity: O(1) - Returning a field's value is a constant time operation.
     *
     * @return The name of the laptop.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price of the laptop.
     * <p>
     * Time complexity: O(1) - Returning a field's value is a constant time operation.
     *
     * @return The price of the laptop.
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Retrieves the quantity of laptops in stock.
     * <p>
     * Time complexity: O(1) - Returning a field's value is a constant time operation.
     *
     * @return The current quantity in stock.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the name of the laptop.
     * <p>
     * Time complexity: O(1) - Setting a field's value is a constant time operation.
     *
     * @param name The new name for the laptop.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Updates the price of the laptop.
     * <p>
     * Time complexity: O(1) - Setting a field's value is a constant time operation.
     *
     * @param price The new price for the laptop.
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Updates the quantity of laptops in stock.
     * <p>
     * Time complexity: O(1) - Setting a field's value is a constant time operation.
     *
     * @param quantity The new quantity of laptops in stock.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

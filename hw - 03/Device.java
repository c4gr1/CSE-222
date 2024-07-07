/**
 * Interface representing a generic device in an inventory system.
 * <p>
 * This interface defines the basic operations and properties that any device should have,
 * such as getting and setting the name, category, price, and quantity of the device.
 */
public interface Device {
    
    /**
     * Gets the category of the device.
     * <p>
     * Time complexity: O(1), as it directly returns a value.
     *
     * @return The category of the device.
     */
    String getCategory();
    
    /**
     * Gets the name of the device.
     * <p>
     * Time complexity: O(1), as it directly returns a value.
     *
     * @return The name of the device.
     */
    String getName();
    
    /**
     * Gets the price of the device.
     * <p>
     * Time complexity: O(1), as it directly returns a value.
     *
     * @return The price of the device.
     */
    double getPrice();
    
    /**
     * Gets the quantity of the device in inventory.
     * <p>
     * Time complexity: O(1), as it directly returns a value.
     *
     * @return The quantity of the device.
     */
    int getQuantity();
    
    /**
     * Sets the name of the device.
     * <p>
     * Time complexity: O(1), as it simply assigns a value.
     *
     * @param name The new name of the device.
     */
    void setName(String name);
    
    /**
     * Sets the price of the device.
     * <p>
     * Time complexity: O(1), as it simply assigns a value.
     *
     * @param price The new price of the device.
     */
    void setPrice(double price);
    
    /**
     * Sets the quantity of the device in inventory.
     * <p>
     * Time complexity: O(1), as it simply assigns a value.
     *
     * @param quantity The new quantity of the device.
     */
    void setQuantity(int quantity);
}

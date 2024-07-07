import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Represents an inventory management system for electronic devices.
 * <p>
 * This class manages a collection of electronic devices, allowing for the addition,
 * removal, and updating of devices. Devices are grouped by category for efficient management.
 */
public class Inventory {
      /**
     * Stores groups of devices, with each group containing devices of the same category.
     */
    private LinkedList<ArrayList<Device>> deviceGroups;

    /**
     * Constructs an empty inventory.
     * <p>
     * Time complexity: O(1) - The initialization of an empty LinkedList is a constant time operation,
     * as it simply allocates memory for the list without adding any elements.
     */
    public Inventory() {
        this.deviceGroups = new LinkedList<>();
    }


    /**
     * Adds a device to the inventory. If the device's category does not already exist,
     * a new category group is created and added to the inventory.
     * <p>
     * This method iterates through the list of device groups to find a matching category. If no matching
     * category is found, a new list for the device's category is created and added. The time complexity
     * of this operation primarily depends on the number of device categories currently in the inventory.
     * <p>
     * Time complexity: O(n) where n is the number of device categories. In the worst case, this method
     * may iterate through all existing categories before adding a new device or creating a new category group.
     *
     * @param device The device to be added to the inventory.
     */
    public void addDevice(Device device) {
        // Initialize a flag to track if the device's category is found
        boolean found = false;
    
        // Iterate through each device group (list of devices by category)
        for (ArrayList<Device> deviceList : deviceGroups) {
            // Check if the current list is not empty and if its category matches the device's category
            if (!deviceList.isEmpty() && deviceList.get(0).getCategory().equals(device.getCategory())) {
                // If a matching category is found, add the device to this list
                deviceList.add(device);
                // Set the flag to true to indicate that the device has been added
                found = true;
                // Exit the loop as we've successfully added the device
                break;
            }
        }
    
        // If no existing category was found for the device
        if (!found) {
            // Create a new list for this category
            ArrayList<Device> newDeviceList = new ArrayList<>();
            // Add the device to the new list
            newDeviceList.add(device);
            // Add the new list to the device groups
            deviceGroups.add(newDeviceList);
        }
    }
    

    /**
     * Removes a device from the inventory by its name. If multiple devices have the same name,
     * the first one found is removed.
     * <p>
     * Time complexity: O(n*m) where n is the number of device categories and m is the average number
     * of devices per category. In the worst case, this method iterates through all devices in all categories.
     *
     * @param name The name of the device to be removed.
     * @return true if the device was found and removed, false otherwise.
     */
    public boolean removeDevice(String name) {
        // Iterate through each device category
        for (ArrayList<Device> deviceList : deviceGroups) {
            // Iterate through each device in the current category
            for (Device device : deviceList) {
                // Check if the current device's name matches the specified name
                if (device.getName().equals(name)) {
                    // Remove the matching device from its category list
                    deviceList.remove(device);
                    // Return true indicating a device was found and removed
                    return true;
                }
            }
        }
        // Return false if no device with the specified name was found in any category
        return false;
    }


    /**
     * Updates the details of a device identified by its name. If a device with the specified
     * name is found, its price and quantity are updated.
     * <p>
     * Time complexity: O(n*m) where n is the number of device categories and m is the average number
     * of devices per category. This is because, in the worst case, the method may need to iterate through 
     * all devices in all categories to find the matching device.
     *
     * @param name     The name of the device to update.
     * @param price    The new price of the device, must be positive to be updated.
     * @param quantity The new quantity of the device, must be non-negative to be updated.
     * @return true if the device was found and updated, false otherwise.
     */
    public boolean updateDeviceDetails(String name, double price, int quantity) {
        // Iterate through each device category
        for (ArrayList<Device> deviceList : deviceGroups) {
            // Iterate through each device in the current category
            for (Device device : deviceList) {
                // Check if the current device's name matches the specified name
                if (device.getName().equals(name)) {
                    // Update price if the new price is positive
                    if (price > 0) device.setPrice(price);
                    // Update quantity if the new quantity is non-negative
                    if (quantity >= 0) device.setQuantity(quantity);
                    // Return true indicating that the device was found and updated
                    return true;
                }
            }
        }
        // Return false if no device with the specified name was found in any category
        return false;
    }


    /**
     * Displays all devices in the inventory to the standard output, grouped by category.
     * <p>
     * This method iterates through each category of devices, displaying each device's details.
     * The output includes the category, name, price, and quantity of each device.
     * <p>
     * Time complexity: O(n) where n is the total number of devices in all categories. The method
     * performs a single iteration over all devices, making it linear in terms of the number of devices.
     */
    public void displayAllDevices() {
        // Iterate through each list of devices (grouped by category)
        for (ArrayList<Device> deviceList : deviceGroups) {
            // Check if the current device list is not empty
            if (!deviceList.isEmpty()) {
                // Optional: Display the category name here if you want each category to be labeled
            }
            // Iterate through each device in the current category
            for (Device device : deviceList) {
                // Print the details of the current device to the standard output
                System.out.println("Category: " + device.getCategory() + " Name: " + device.getName() +
                        " Price: $" + device.getPrice() + " Quantity: " + device.getQuantity());
            }
        }
    }


    /**
     * Finds and returns the device with the lowest price in the inventory. If there are
     * multiple devices with the same lowest price, the first one found is returned.
     * <p>
     * This method iterates through all device categories and devices within those categories
     * to identify the one with the lowest price. 
     * <p>
     * Time complexity: O(n) where n is the total number of devices in the inventory. This is because
     * the method performs a single pass over all devices to find the one with the lowest price.
     *
     * @return The cheapest device in the inventory, or null if the inventory is empty.
     */
    public Device findCheapestDevice() {
        // Initialize with null to handle the case where the inventory is empty
        Device cheapestDevice = null;
        // Set initial min price to maximum possible to ensure any device's price is lower
        double minPrice = Double.MAX_VALUE;

        // Iterate through each category of devices
        for (ArrayList<Device> deviceList : deviceGroups) {
            // Iterate through each device in the current category
            for (Device device : deviceList) {
                // If the current device's price is lower than the current min price
                if (device.getPrice() < minPrice) {
                    // Update min price and reference to the cheapest device found so far
                    minPrice = device.getPrice();
                    cheapestDevice = device;
                }
            }
        }
        // Return the cheapest device found or null if inventory was empty
        return cheapestDevice;
    }


    /**
     * Returns a list of all devices in the inventory, sorted by price in ascending order.
     * <p>
     * This method collects all devices from each category into a single list, then sorts
     * that list by the price of the devices in ascending order. The sorting uses a comparator
     * based on the device price.
     * <p>
     * Time complexity: O(n log n), where n is the total number of devices in the inventory.
     * The collection of devices into a single list is O(n), and the sort operation dominates
     * the time complexity, which is O(n log n) for comparison-based sorting algorithms.
     *
     * @return A sorted list of all devices, from the lowest to the highest price.
     */
    public ArrayList<Device> getSortedDevicesByPrice() {
        // Create a new list to hold all devices from all categories
        ArrayList<Device> allDevices = new ArrayList<>();
        // Iterate through each category of devices and add them to the allDevices list
        for (ArrayList<Device> deviceList : deviceGroups) {
            allDevices.addAll(deviceList);
        }
        // Sort the list of all devices by price in ascending order
        allDevices.sort(Comparator.comparingDouble(Device::getPrice));
        // Return the sorted list of devices
        return allDevices;
    }


    /**
     * Calculates and returns the total value of the inventory based on the price and
     * quantity of each device.
     * <p>
     * This method iterates through each device category and each device within those categories,
     * summing up the products of the price and quantity of each device to compute the total inventory value.
     * <p>
     * Time complexity: O(n), where n is the total number of devices in the inventory. The method performs
     * a single pass over all devices, with the operation within the loop (multiplication and addition)
     * being constant time operations.
     *
     * @return The total value of the inventory, calculated as the sum of the products of price and quantity for each device.
     */
    public double calculateTotalInventoryValue() {
        // Initialize total value to 0
        double totalValue = 0;
        // Iterate through each category of devices
        for (ArrayList<Device> deviceList : deviceGroups) {
            // Iterate through each device in the current category
            for (Device device : deviceList) {
                // Add the product of the device's price and quantity to the total value
                totalValue += device.getPrice() * device.getQuantity();
            }
        }
        // Return the computed total value of the inventory
        return totalValue;
    }


    /**
     * Adjusts the stock of a specified device by its name. Stock can be added or removed
     * based on the addStock flag.
     * <p>
     * This method searches through the inventory to find the device with the specified name.
     * Once found, the stock level is adjusted by either adding or subtracting the specified quantity.
     * Stock levels are not allowed to go below zero.
     * <p>
     * Time complexity: O(n*m) where n is the number of device categories and m is the average number
     * of devices per category. In the worst case, the method may need to iterate through all devices
     * in all categories to find the matching device by name.
     *
     * @param name     The name of the device to restock.
     * @param quantity The quantity to add or remove from the device's stock.
     * @param addStock If true, stock is added; if false, stock is removed, ensuring the stock doesn't fall below zero.
     * @return true if the device was found and its stock adjusted, false otherwise.
     */
    public boolean restockDevice(String name, int quantity, boolean addStock) {
        // Iterate through each category of devices
        for (ArrayList<Device> deviceList : deviceGroups) {
            // Iterate through each device in the current category
            for (Device device : deviceList) {
                // Check if the current device's name matches the specified name, case-insensitively
                if (device.getName().equalsIgnoreCase(name)) {
                    int currentQuantity = device.getQuantity();
                    if (addStock) {
                        // If adding stock, simply increase the quantity
                        device.setQuantity(currentQuantity + quantity);
                    } else {
                        // If removing stock, decrease the quantity but prevent it from going below zero
                        device.setQuantity(Math.max(0, currentQuantity - quantity));
                    }
                    return true; // Device was found and stock was successfully updated
                }
            }
        }
        return false; // No device with the specified name was found
    }


    /**
     * Retrieves a list of all devices in the inventory, regardless of category.
     * <p>
     * This method compiles a comprehensive list of all devices stored in the inventory,
     * effectively flattening the structure of categories into a single list.
     * <p>
     * Time complexity: O(n) where n is the total number of devices in the inventory. 
     * The complexity arises from iterating through each category and adding all devices
     * to the new list, which is a linear operation with respect to the total number of devices.
     *
     * @return A list of all devices, not categorized.
     */
    public ArrayList<Device> getAllDevices() {
        // Initialize a new list to hold all devices
        ArrayList<Device> allDevices = new ArrayList<>();
        // Iterate through each group of devices (each category)
        for (ArrayList<Device> group : deviceGroups) {
            // Add all devices from the current category to the comprehensive list
            allDevices.addAll(group);
        }
        // Return the compiled list of all devices
        return allDevices;
    }

    
}

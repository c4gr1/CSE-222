import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Main class for the Electronics Inventory Management System.
 * <p>
 * This class provides a command-line interface for managing an inventory of electronic devices.
 * Users can add, remove, update, and list devices, as well as perform other inventory operations.
 */
public class Main {

    /**
     * Entry point of the application. Provides a menu for interacting with the inventory system.
     * <p>
     * This application allows users to manage an inventory of electronic devices through a console menu. 
     * Users can add, remove, update details for, list, and perform various other operations on devices.
     * The time complexity of operations depends on the specific action chosen due to the varying complexities 
     * of the operations available (e.g., adding a device is typically O(1), while sorting devices by price 
     * can be O(n log n), where n is the number of devices).
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        // Main loop to display menu and process user input
        while (true) {
            System.out.println("\nWelcome to the Electronics Inventory Management System!");
            System.out.println("Please select an option:");
            System.out.println("1. Add a new device");
            System.out.println("2. Remove a device");
            System.out.println("3. Update device details");
            System.out.println("4. List all devices");
            System.out.println("5. Find the cheapest device");
            System.out.println("6. Sort devices by price");
            System.out.println("7. Calculate total inventory value");
            System.out.println("8. Restock a device");
            System.out.println("9. Export inventory report");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over to handle next line input correctly

            // Process user's choice by invoking the appropriate method
            switch (choice) {
                case 1:
                    addDevice(scanner, inventory); // O(1) adding is to the end of a list
                    break;
                case 2:
                    removeDevice(scanner, inventory); // O(n) in the worst case, if searching through all devices is required
                    break;
                case 3:
                    updateDeviceDetails(scanner, inventory); // O(n) similar to remove, due to potential full list search
                    break;
                case 4:
                    inventory.displayAllDevices(); // O(n), straightforward list traversal
                    break;
                case 5:
                    Device cheapest = inventory.findCheapestDevice(); // O(n), scans all devices for the lowest price
                    if (cheapest != null) {
                        System.out.printf("The cheapest device is:\nCategory: %s, Name: %s, Price: $%.2f, Quantity: %d\n",
                        cheapest.getCategory(), cheapest.getName(), cheapest.getPrice(), cheapest.getQuantity());

                    } else {
                        System.out.println("No devices in inventory.");
                    }
                    break;
                case 6:
                    sortDevicesByPrice(inventory); // O(n log n), assuming a comparison sort is used
                    break;
                case 7:
                    calculateTotalInventoryValue(inventory); // O(n), summing up prices for all devices
                    break;
                case 8:
                    restockDevice(scanner, inventory); // O(n) if a search is needed to find the device by name
                    break;
                case 9:
                    exportInventoryReport(inventory); // O(n), involves iterating over all devices to generate report
                    break;
                case 0:
                    System.out.println("Exiting..."); // Exits the loop and ends the program
                    return;
                default:
                    System.out.println("Invalid option. Please try again."); // Handles unexpected input
                    break;
            }
        }
}


    /**
     * Adds a new device to the inventory based on user input.
     * <p>
     * Time complexity: O(n) where n is the number of device categories, due to the search for the correct category.
     *
     * @param scanner   Scanner for reading user input.
     * @param inventory Inventory to add the device to.
     */
    private static void addDevice(Scanner scanner, Inventory inventory) {
        System.out.print("Enter device category (TV, Smartphone, Laptop, Keyboard, Headphone): ");
        String category = scanner.nextLine();
    
        System.out.print("Enter device name: ");
        String name = scanner.nextLine();
    
        double price;
        int quantity;
        
        do {
            System.out.print("Enter price (must be greater than zero): ");
            price = scanner.nextDouble();
        } while (price <= 0);
        
        do {
            System.out.print("Enter quantity (must be greater than zero): ");
            quantity = scanner.nextInt();
        } while (quantity <= 0);
        
        scanner.nextLine(); // Consume newline left-over
        
    
        // Creating device based on category
        Device device = null;
        switch (category.toLowerCase()) {
            case "tv":
                device = new TV(name, price, quantity);
                break;
            case "smartphone":
                device = new Smartphone(name, price, quantity);
                break;
            case "laptop":
                device = new Laptop(name, price, quantity);
                break;
            case "keyboard":
                device = new Keyboard(name, price, quantity);
                break;
            case "headphone":
                device = new Headphone(name, price, quantity);
                break;
            default:
                System.out.println("Invalid category.");
                return;
        }
    
        inventory.addDevice(device);
        System.out.println(device.getCategory() + ", " + device.getName() + ", $" + device.getPrice() + ", " + device.getQuantity() + " amount added...");
    }
    
    /**
     * Removes a device from the inventory based on its name.
     * <p>
     * Time complexity: O(n*m) where n is the number of device categories and m is the average number of devices per category.
     *
     * @param scanner   Scanner for reading user input.
     * @param inventory Inventory to remove the device from.
     */
    private static void removeDevice(Scanner scanner, Inventory inventory) {
        System.out.print("Enter the name of the device to remove: ");
        String name = scanner.nextLine();
    
        if (inventory.removeDevice(name)) {
            System.out.println(name + " was removed from the inventory.");
        } else {
            System.out.println("Device not found.");
        }
    }
    
    /**
     * Updates the details of a specific device in the inventory.
     * <p>
     * Time complexity: O(n*m) similar to removeDevice, due to searching through devices to find a match by name.
     *
     * @param scanner   Scanner for reading user input.
     * @param inventory Inventory where the device's details will be updated.
     */
    private static void updateDeviceDetails(Scanner scanner, Inventory inventory) {
        System.out.print("Enter the name of the device to update: ");
        String name = scanner.nextLine();
    
        double price;
        int quantity;

        do {
            System.out.print("Enter new price (must be non-negative): ");
            price = scanner.nextDouble();
            if (price <= 0) {
                System.out.println("Error: Price cannot be negative. Please enter a non-negative value.");
            }
        } while (price <= 0); // Repeat until valid non-negative price is entered
    
        do {
            System.out.print("Enter new quantity (must be non-negative): ");
            quantity = scanner.nextInt();
            if (quantity <= 0) {
                System.out.println("Error: Quantity cannot be negative. Please enter a non-negative value.");
            }
            scanner.nextLine(); // Consume newline left-over from int input
        } while (quantity <= 0); // Repeat until valid non-negative quantity is entered
    
        if (inventory.updateDeviceDetails(name, price, quantity)) {
            System.out.printf("%s details updated: Price - $%.2f, Quantity - %d\n", name, price, quantity);
        } else {
            System.out.println("Device not found.");
        }
    }

    /**
     * Sorts and lists all devices in the inventory by price.
     * <p>
     * Time complexity: O(n*log(n)) where n is the total number of devices across all categories, due to sorting.
     *
     * @param inventory Inventory to sort and list devices from.
     */
private static void sortDevicesByPrice(Inventory inventory) {
    ArrayList<Device> sortedDevices = inventory.getSortedDevicesByPrice();
    System.out.println("Devices sorted by price:");
    for (Device device : sortedDevices) {
        System.out.println("Category: " + device.getCategory() + " Name: " + device.getName() +
                " Price: $" + device.getPrice() + " Quantity: " + device.getQuantity());
    }
}

    /**
     * Calculates and displays the total value of the inventory.
     * <p>
     * Time complexity: O(n) where n is the total number of devices, as it sums up the value of all devices.
     *
     * @param inventory Inventory to calculate the total value of.
     */
private static void calculateTotalInventoryValue(Inventory inventory) {
    double totalValue = inventory.calculateTotalInventoryValue();
    System.out.println("Total inventory value: $" + totalValue);
}

    /**
     * Restocks a specific device in the inventory by either adding or removing stock.
     * <p>
     * Time complexity: O(n*m) due to searching for the device by name across all categories.
     *
     * @param scanner   Scanner for reading user input.
     * @param inventory Inventory where the device's stock will be adjusted.
     */
    private static void restockDevice(Scanner scanner, Inventory inventory) {
        System.out.print("Enter the name of the device to restock: ");
        String name = scanner.nextLine();
        
        System.out.print("Do you want to add or remove stock? (Add/Remove): ");
        String action = scanner.nextLine();
        
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        boolean addStock = action.equalsIgnoreCase("Add");
        if (inventory.restockDevice(name, quantity, addStock)) {
            System.out.println("Stock updated for " + name + ".");
        } else {
            System.out.println("Device not found.");
        }
    }

    /**
     * Exports and displays a report of the inventory, listing all devices and summarizing inventory value.
     * <p>
     * This method writes a detailed report to a text file named "InventoryReport.txt". The report includes
     * a list of all devices in the inventory, categorized by device type, and a summary that includes the
     * total number of devices and the total inventory value. The report is generated in a tabular format for
     * easy reading.
     * <p>
     * Time complexity: O(n) for listing all devices, where n is the total number of devices. Writing to the
     * file has a linear time complexity relative to the number of devices, as each device's details are written
     * sequentially.
     *
     * @param inventory Inventory to generate the report from. This inventory is queried for a list of all devices
     *                  and the total value, which are then formatted into a report.
     */
    private static void exportInventoryReport(Inventory inventory) {
        try {
            // Initialize PrintWriter with FileWriter to write to a file in default encoding.
            PrintWriter writer = new PrintWriter(new FileWriter("InventoryReport.txt"));

            // Writing the header of the report
            writer.println("\nElectronics Shop Inventory Report");
            writer.println("Generated on: " + java.time.LocalDate.now());
            writer.println("---------------------------------------");
            writer.println("| No. | Category | Name | Price | Quantity |");
            writer.println("---------------------------------------");

            // Retrieve all devices from inventory and write their details
            ArrayList<Device> allDevices = inventory.getAllDevices();
            int no = 1; // Counter for the list numbering
            for (Device device : allDevices) {
                // Write each device's details formatted in a table row
                writer.printf("| %2d | %-10s | %-20s | $%7.2f | %8d |\n",
                        no++, device.getCategory(), device.getName(), device.getPrice(), device.getQuantity());
            }
            writer.println("---------------------------------------");

            // Calculate and write the summary section of the report
            double totalValue = inventory.calculateTotalInventoryValue();
            writer.println("Summary:");
            writer.println("- Total Number of Devices: " + allDevices.size());
            writer.println("- Total Inventory Value: $" + String.format("%.2f", totalValue));

            writer.println("\nEnd of Report");

            // Close the writer to ensure data is flushed and the file is closed properly
            writer.close();
            
            // Print a message to the console to indicate successful export
            System.out.println("Inventory report successfully exported to InventoryReport.txt");
        } catch (IOException e) {
            // Print an error message to the console if an exception occurs during file writing
            System.out.println("An error occurred while writing the inventory report to file.");
            e.printStackTrace();
        }
    }

}

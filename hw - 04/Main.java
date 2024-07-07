import java.util.Scanner;

/**
 * Main class for File System Management Application.
 * Provides a command-line interface to interact with a simulated file system.
 * Allows the user to perform various file system operations like changing directories,
 * listing contents, creating and deleting files and directories, moving files,
 * searching for items, printing the directory structure, and sorting contents by date.
 */
public class Main {
    /**
     * The main method that initializes the application and handles the user input loop.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Create an instance of FileSystem to manage files and directories.
        FileSystem fs = new FileSystem();
        // Scanner object to read input from the command line.
        Scanner scanner = new Scanner(System.in);
        // Flag to control when to exit the main loop and the application.
        boolean exit = false;

        // Main loop that displays the menu and processes user input.
        while (!exit) {
            // Display the main menu with options.
            System.out.println("===== File System Management Menu =====");
            System.out.println("1. Change directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file/directory");
            System.out.println("4. Delete file/directory");
            System.out.println("5. Move file/directory");
            System.out.println("6. Search file/directory");
            System.out.println("7. Print directory tree");
            System.out.println("8. Sort contents by date created");
            System.out.println("9. Exit");
            System.out.print("Please select an option: ");

            // Read the user's choice from the command line.
            int option = scanner.nextInt();
            // Consume any leftover newline character.
            scanner.nextLine(); 

            // Process the user's choice using a switch statement.
            switch (option) {
                case 1:
                // Display the current directory path before changing it.
                System.out.println("Current directory: " + fs.getCurrentDirectory().getPath());
                System.out.print("Enter new directory path: ");
                String newPath = scanner.nextLine();
                fs.changeDirectory(newPath);
                break;
                case 2:
                    // List all contents in the current directory.
                    fs.listContents();
                    break;
                case 3:
                    // Create a new file or directory based on user input.
                    System.out.print("Create file or directory (f/d): ");
                    String type = scanner.nextLine();
                    if (type.equalsIgnoreCase("f")) {
                        System.out.print("Enter name for new file: ");
                        String fileName = scanner.nextLine();
                        fs.createFile(fileName);
                    } else if (type.equalsIgnoreCase("d")) {
                        System.out.print("Enter name for new directory: ");
                        String dirName = scanner.nextLine();
                        fs.createDirectory(dirName);
                    } else {
                        System.out.println("Invalid type selected. Please choose 'f' for file or 'd' for directory.");
                    }
                    break;
                case 4:
                    // Delete a specified file or directory based on user input.
                    System.out.print("Enter name of file/directory to delete: ");
                    String elementName = scanner.nextLine();
                    fs.deleteElement(elementName);
                    break;
                case 5:
                    // Move a file or directory to a new location based on user input.
                    System.out.print("Enter the name of the file/directory to move: ");
                    String elementName2 = scanner.nextLine();
                    System.out.print("Enter new directory path: ");
                    String newParentPath = scanner.nextLine();
                    fs.moveElement(elementName2, newParentPath);
                    break;
                case 6:
                    // Search for a file or directory based on user input.
                    System.out.print("Search query: ");
                    String query = scanner.nextLine();
                    fs.search(query);
                    break;
                case 7:
                    // Print the directory tree starting from the current directory.
                    fs.printDirectoryTree();
                    break;
                case 8:
                    // Sort the contents of the current directory by the date created.
                    fs.sortContentsByDate();
                    break;
                case 9:
                    // Exit the application.
                    exit = true;
                    break;
                default:
                    // Handle any undefined option.
                    System.out.println("Invalid option");
            }
        }
        // Close the scanner to free up resources.
        scanner.close();
    }
}

import java.util.List;

/**
 * Manages the operations of a simulated file system, providing functionality to manipulate
 * directories and files, such as changing directories, listing contents, creating, deleting, and
 * moving elements, as well as searching and sorting based on creation dates.
 */
public class FileSystem {
    // Root directory of the file system.
    private Directory root;
    // Currently active directory in the file system.
    private Directory currentDirectory;

    /**
     * Constructor for the FileSystem. Initializes the root and sets the current directory to the root.
     */
    public FileSystem() {
        this.root = new Directory("root", null);
        this.currentDirectory = root; // Start at the root directory
    }

    /**
     * Retrieves the current active directory within the file system.
     * This method provides access to the directory object that the user is currently working within,
     * allowing for operations such as listing contents, adding or removing files and directories,
     * or changing to a new directory path.
     *
     * @return The current directory object.
     */
    public Directory getCurrentDirectory() {
        return currentDirectory;
    }

    /**
     * Changes the current directory to a new path specified by the user.
     *
     * @param path The path to change the current directory to, relative to the root.
     */
    public void changeDirectory(String path) {
        if (path.equals("/")) {
            currentDirectory = root;
            return;
        }

        Directory target = root; // Start at the root
        String[] directories = path.split("/");

        for (String dir : directories) {
            if (!dir.isEmpty()) {
                Directory nextDir = target.getSubdirectory(dir);
                if (nextDir != null) {
                    target = nextDir;
                } else {
                    System.out.println("Directory not found: " + dir);
                    return;
                }
            }
        }

        currentDirectory = target;
        System.out.println("Directory changed to: " + currentDirectory.getPath());
    }

    /**
     * Lists the contents of the current directory, showing names and creation dates.
     */
    public void listContents() {
        if (currentDirectory != null) {
            String contents = currentDirectory.listContents();
            if(contents.isEmpty()) {
                System.out.println("The directory is empty.");
            } else {
                System.out.println("Listing contents of " + currentDirectory.getPath() + ":");
                System.out.println(contents);
            }
        } else {
            System.out.println("No directory is currently selected.");
        }
    }

    /**
     * Creates a new file in the current directory.
     *
     * @param fileName The name of the file to create.
     */
    public void createFile(String fileName) {
        File newFile = new File(fileName, currentDirectory);
        currentDirectory.addElement(newFile);
        System.out.println("File created: " + newFile.getName());
    }

    /**
     * Creates a new directory in the current directory.
     *
     * @param dirName The name of the directory to create.
     */
    public void createDirectory(String dirName) {
        Directory newDirectory = new Directory(dirName, currentDirectory);
        currentDirectory.addElement(newDirectory);
        System.out.println("Directory created: " + newDirectory.getName() + "/");
    }

    /**
     * Deletes an element (file or directory) from the current directory.
     *
     * @param elementName The name of the element to delete.
     */
    public void deleteElement(String elementName) {
        boolean success = currentDirectory.removeElement(elementName);
        if (success) {
            System.out.println("Deleted successfully: " + elementName);
        } else {
            System.out.println("Delete failed: Element not found.");
        }
    }

    /**
     * Moves an element from the current directory to another specified directory.
     *
     * @param elementName The name of the element to move.
     * @param newParentPath The target directory path to move the element to.
     */
    public void moveElement(String elementName, String newParentPath) {
        Directory newParent = findDirectory(newParentPath);
        if (newParent == null) {
            System.out.println("Target directory does not exist.");
            return;
        }

        FileSystemElement elementToMove = currentDirectory.removeElementForMove(elementName);
        if (elementToMove == null) {
            System.out.println("Element to move not found.");
            return;
        }

        newParent.addElement(elementToMove);
        System.out.println("Moved " + elementName + " to " + newParent.getPath());
    }

    /**
     * Finds a directory within the file system starting from the root based on a provided path.
     *
     * @param path The path to find the directory.
     * @return The found directory or null if not found.
     */
    private Directory findDirectory(String path) {
        Directory target = root; // Start at the root
        if (path.equals("/")) return target;
        String[] directories = path.split("/");
        for (String dir : directories) {
            if (!dir.isEmpty()) {
                Directory nextDir = target.getSubdirectory(dir);
                if (nextDir != null) {
                    target = nextDir;
                } else {
                    return null; // Directory path does not exist
                }
            }
        }
        return target;
    }

    /**
     * Searches for elements by name starting from the root, regardless of the current directory.
     *
     * @param query The search query.
     */
    public void search(String query) {
        System.out.println("Searching from root...");
        List<String> results = root.search(query);  // Start search from root for comprehensive coverage
        if (results.isEmpty()) {
            System.out.println("No results found for: " + query);
        } else {
            System.out.println("Found:");
            for (String path : results) {
                System.out.println(path);
            }
        }
    }

    /**
     * Prints the directory tree from the current directory, showing the structure and hierarchy.
     */
    public void printDirectoryTree() {
        System.out.println("Path to current directory from root:");
        currentDirectory.printTree("");  // Start printing from the current directory
    }

    /**
     * Sorts the contents of the current directory by their creation dates.
     */
    public void sortContentsByDate() {
        if (currentDirectory != null) {
            currentDirectory.sortContentsByDate();
            // Optionally list the contents after sorting to show the result
            System.out.println(currentDirectory.listContents());
        } else {
            System.out.println("No directory is currently selected to sort.");
        }
    }
}

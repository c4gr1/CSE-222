import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;
import java.text.SimpleDateFormat;

/**
 * Represents a directory in a file system. This class extends {@link FileSystemElement} 
 * and includes functionalities for managing child elements, which can be files or other directories.
 */
public class Directory extends FileSystemElement {
    // List to store child file system elements.
    private LinkedList<FileSystemElement> children;

    /**
     * Constructs a new Directory with a specified name and parent.
     * 
     * @param name The name of the directory.
     * @param parent The parent directory of this directory. Null if this is the root directory.
     */
    public Directory(String name, FileSystemElement parent) {
        super(name, parent);
        children = new LinkedList<>();
    }

    /**
     * Returns the full path of this directory from the root of the filesystem.
     * 
     * @return The full path as a string.
     */
    @Override
    public String getPath() {
        return parent == null ? name : parent.getPath() + "/" + name;
    }

    /**
     * Adds a new file system element (file or directory) as a child of this directory.
     * 
     * @param element The file system element to be added.
     */
    public void addElement(FileSystemElement element) {
        children.add(element);
        element.parent = this;
    }

    /**
     * Removes a file system element from this directory's children.
     * 
     * @param element The element to be removed.
     */
    public void removeElement(FileSystemElement element) {
        children.remove(element);
        element.parent = null;
    }

    /**
     * Searches for a subdirectory within this directory by name.
     * 
     * @param name The name of the subdirectory to find.
     * @return The subdirectory if found, otherwise null.
     */
    public Directory getSubdirectory(String name) {
        for (FileSystemElement element : children) {
            if (element instanceof Directory && element.name.equals(name)) {
                return (Directory) element;
            }
        }
        return null;
    }

    /**
     * Lists all children elements in the directory, displaying their names and creation timestamps.
     * 
     * @return A formatted string listing all children.
     */
    public String listContents() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return children.stream()
            .map(e -> e instanceof Directory ?
                String.format("* %s/ (%s)", e.getName(), sdf.format(e.getDateCreated())) :
                String.format("%s (%s)", e.getName(), sdf.format(e.getDateCreated())))
            .collect(Collectors.joining("\n"));
    }

    /**
     * Removes a child element by name. Includes recursive deletion for directories.
     * 
     * @param elementName The name of the element to remove.
     * @return true if the element was found and removed, false otherwise.
     */
    public boolean removeElement(String elementName) {
        for (FileSystemElement element : children) {
            if (element.getName().equals(elementName)) {
                if (element instanceof Directory) {
                    ((Directory) element).clearDirectory(); // Recursively clear directory
                }
                children.remove(element);
                return true;
            }
        }
        return false;
    }

    /**
     * Recursively clears all contents of this directory, ensuring all child elements are also cleared.
     */
    private void clearDirectory() {
        for (FileSystemElement element : new ArrayList<>(children)) {
            if (element instanceof Directory) {
                ((Directory) element).clearDirectory();
            }
            children.remove(element);
        }
    }

    /**
     * Moves a child element to another directory by removing it from this directory and adding it to the target directory.
     * 
     * @param elementName The name of the element to move.
     * @return The element if it was found and removed, null otherwise.
     */
    public FileSystemElement removeElementForMove(String elementName) {
        FileSystemElement elementToRemove = null;
        for (FileSystemElement element : children) {
            if (element.getName().equals(elementName)) {
                elementToRemove = element;
                break;
            }
        }
        if (elementToRemove != null) {
            children.remove(elementToRemove);
            return elementToRemove;
        }
        return null;
    }

    /**
     * Recursively searches for elements by name, starting from this directory.
     * 
     * @param query The name query string to search for.
     * @return A list of paths to found elements.
     */
    public List<String> search(String query) {
        List<String> found = new ArrayList<>();
        for (FileSystemElement element : children) {
            if (element.getName().equalsIgnoreCase(query)) {
                found.add(element.getPath());
            }
            if (element instanceof Directory) {
                found.addAll(((Directory) element).search(query));
            }
        }
        return found;
    }

    /**
     * Prints the structure of the directory tree starting from this directory, with indentation representing hierarchy.
     * 
     * @param prefix A string prefix used for indentation and hierarchy visualization.
     */
    public void printTree(String prefix) {
        System.out.println(prefix + getName() + "/");
        for (FileSystemElement element : children) {
            if (element instanceof Directory) {
                ((Directory) element).printTree(prefix + "    ");
            } else {
                System.out.println(prefix + "    " + element.getName());
            }
        }
    }

    /**
     * Sorts the contents of this directory by the creation date of each element.
     */
    public void sortContentsByDate() {
        Collections.sort(children, new Comparator<FileSystemElement>() {
            @Override
            public int compare(FileSystemElement f1, FileSystemElement f2) {
                return f1.getDateCreated().compareTo(f2.getDateCreated());
            }
        });
        System.out.println("Contents of " + getName() + " sorted by date created.");
    }
}

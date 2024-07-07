import java.sql.Timestamp;

/**
 * Abstract base class representing an element in a file system, which could be either a file or a directory.
 * This class encapsulates common attributes and functionality for file system elements.
 */
public abstract class FileSystemElement {
    // Name of the file or directory
    protected String name;
    // Timestamp indicating when the file or directory was created
    protected Timestamp dateCreated;
    // Parent directory of the file or directory. This is null if the element is the root.
    protected FileSystemElement parent;

    /**
     * Constructs a new FileSystemElement with a name, a parent, and sets the creation timestamp to the current time.
     *
     * @param name   The name of the file system element.
     * @param parent The parent directory of this element. It can be null if the element is the root.
     */
    public FileSystemElement(String name, FileSystemElement parent) {
        this.name = name;
        this.parent = parent;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Gets the name of the file system element.
     *
     * @return the name of the element.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the timestamp when the file system element was created.
     *
     * @return the creation timestamp of the element.
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Abstract method to be implemented by subclasses to return the full path from the root to this element.
     *
     * @return the full path of this element as a String.
     */
    public abstract String getPath();
}

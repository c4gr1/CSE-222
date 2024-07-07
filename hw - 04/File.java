/**
 * Represents a file in a file system. This class extends {@link FileSystemElement} to provide specific functionalities
 * for file-type elements, including obtaining the file's path within the filesystem hierarchy.
 */
public class File extends FileSystemElement {
    
    /**
     * Constructs a new File instance with a specified name and parent directory.
     * 
     * @param name The name of the file, not including any path information.
     * @param parent The directory that contains this file. This can be null if the file is treated as root.
     */
    public File(String name, FileSystemElement parent) {
        super(name, parent);  // Call to the superclass constructor to initialize common attributes.
    }

    /**
     * Retrieves the full path of this file from the root of the file system to this file,
     * constructed recursively by traversing up to the root.
     * 
     * @return The full path of this file, using '/' as a directory separator.
     */
    @Override
    public String getPath() {
        // If parent is null, it means this file is considered at the root level.
        return parent == null ? name : parent.getPath() + "/" + name;
    }
}

# Compiler
JAVAC = javac
JAVADOC = javadoc
JAVA = java

# Source files
SOURCES = Main.java Person.java SocialNetwork.java

# Output directory for compiled classes
OUT_DIR = bin

# Test file
TEST_FILE = Tester.java

# Default target
all: $(OUT_DIR)/Main.class

# Create output directory if it doesn't exist
$(OUT_DIR):
	mkdir -p $(OUT_DIR)

# Compile source files
$(OUT_DIR)/Main.class: $(SOURCES) | $(OUT_DIR)
	$(JAVAC) -d $(OUT_DIR) $(SOURCES)

# Run the program
run: all
	$(JAVA) -cp $(OUT_DIR) Main

# Generate Javadoc
javadoc: $(SOURCES)
	$(JAVADOC) -d doc $(SOURCES)

# Run the tester
test: $(OUT_DIR)/Tester.class
	$(JAVA) -cp $(OUT_DIR) Tester

$(OUT_DIR)/Tester.class: $(TEST_FILE) $(OUT_DIR)/Main.class
	$(JAVAC) -d $(OUT_DIR) $(TEST_FILE)

# Clean up generated files
clean:
	rm -rf $(OUT_DIR) doc
	rm -f $(OUT_DIR)/*.class

# Define a variable for the Java compiler
JCC = javac

# Define a variable for compilation options
JFLAGS = -g

# List all the Java files
SOURCES = Main.java Stock.java AVLTree.java StockDataManager.java GUIVisualization.java
CLASSES = $(SOURCES:.java=.class)

# Define a variable for the Javadoc tool
JDOC = javadoc

# The default target is to build all classes
all: $(CLASSES)

# Rule for compiling java files
%.class: %.java
	$(JCC) $(JFLAGS) $<

# Target to clean the directory
clean:
	rm -f *.class

# Run the program
run:
	java -Xint Main input_file.txt

# Target to generate Javadoc documentation
javadoc:
	$(JDOC) -d doc $(SOURCES)

# Makefile dependencies to ensure the files are recompiled if any dependencies are updated
Main.class: Main.java StockDataManager.class GUIVisualization.class
StockDataManager.class: StockDataManager.java AVLTree.class Stock.class
AVLTree.class: AVLTree.java Stock.class
GUIVisualization.class: GUIVisualization.java

# Use a phony target to avoid conflict with any files named clean or run
.PHONY: all clean run javadoc

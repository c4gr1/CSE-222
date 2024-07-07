JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -private -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

# List all your Java classes here
CLASSES = \
        Main.java \
        FileSystem.java \
        FileSystemElement.java \
        File.java \
        Directory.java

all : classes

run : 
	$(JR) Main

# Compiles all Java files to class files
classes : $(CLASSES:.java=.class)

# Generic rule for compiling Java files
%.class : %.java
	$(JC) $(JFLAGS) $<

# Generate Javadoc
doc:
	$(JD) $(JDFLAGS) *.java 

# Clean up class files
clean:
	$(RM) *.class 

# Clean up Javadoc
cleandoc:
	$(RM) -r ./javadoc

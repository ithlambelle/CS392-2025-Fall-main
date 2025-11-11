# Makefile for Assignment 7

# Set classpath to include Library
LIBRARY = ../../libraries/Library
CLASSPATH = $(LIBRARY):MySolution

# Compile Library dependencies first
lib:
	cd $(LIBRARY)/MyDeque && javac -cp $(LIBRARY) *.java
	cd $(LIBRARY)/FnList && javac -cp $(LIBRARY) *.java
	cd $(LIBRARY)/LnStrm && javac -cp $(LIBRARY) *.java
	cd $(LIBRARY)/FnGtree && javac -cp $(LIBRARY) *.java

# Compile assignment files
Assign07_01: lib
	javac -cp $(CLASSPATH) MySolution/Assign07_01.java

Assign07_02: lib Assign07_01
	javac -cp $(CLASSPATH) MySolution/Assign07_02.java

# Run tests
test: Assign07_02
	java -cp $(CLASSPATH) Assign07_02

# Clean compiled files
clean:
	rm -f MySolution/*.class
	find $(LIBRARY) -name "*.class" -delete

.PHONY: lib Assign07_01 Assign07_02 test clean


# Makefile for Assignment 4

# Set classpath to include MyLibrary
CLASSPATH = ../../libraries/MyLibrary/FnList:../../libraries/MyLibrary/MyStack:../../libraries/MyLibrary/MyQueue

# Compile all assignment files
all: Assign04_01 Assign04_02 Assign04_03 Assign04_04 Assign04_05

Assign04_01:
	javac -cp $(CLASSPATH) MySolution/Assign04_01.java
	java -cp MySolution:$(CLASSPATH) Assign04_01

Assign04_02:
	javac -cp $(CLASSPATH) MySolution/Assign04_02.java
	java -cp MySolution:$(CLASSPATH) Assign04_02

Assign04_03:
	javac -cp $(CLASSPATH) MySolution/Assign04_03.java
	java -cp MySolution:$(CLASSPATH) Assign04_03

Assign04_04:
	javac -cp $(CLASSPATH) MySolution/Assign04_04.java
	java -cp MySolution:$(CLASSPATH) Assign04_04

Assign04_05:
	javac -cp $(CLASSPATH) MySolution/Assign04_05.java
	java -cp MySolution:$(CLASSPATH) Assign04_05

# Clean compiled files
clean:
	rm -f MySolution/*.class

.PHONY: all Assign04_01 Assign04_02 Assign04_03 Assign04_04 Assign04_05 clean

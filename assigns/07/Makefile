# Makefile for Assignment 7

Library=../../libraries/Library

# Compile Library dependencies first
lib:
	cd $(Library) && make compall

# Compile MyDeque (needed for Assign07_01)
mydeque:
	cd $(Library)/MyRefer && javac -cp $(Library) *.java
	cd $(Library)/MyDeque && javac -cp $(Library) *.java

# Compile assignment files
Assign07_01: mydeque
	cd $(Library)/FnList && javac -cp $(Library) *.java
	cd $(Library)/LnStrm && javac -cp $(Library) *.java
	cd $(Library)/FnGtree && javac -cp $(Library) *.java
	javac -cp MySolution:$(Library) MySolution/Assign07_01.java

Assign07_02: Assign07_01
	javac -cp MySolution:$(Library) MySolution/Assign07_02.java

# Run tests
test: Assign07_02
	java -cp MySolution:$(Library) Assign07_02

# Clean compiled files
clean:
	rm -f MySolution/*.class

cleanall: clean
	cd $(Library) && make cleanall

.PHONY: lib mydeque Assign07_01 Assign07_02 test clean cleanall


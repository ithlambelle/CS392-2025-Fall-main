# Makefile for Assignment 8

Library=../../libraries/Library

# Compile Library dependencies first
lib:
	cd $(Library) && make compall

# Compile assignment files
Assign08_01: lib
	javac -cp MySolution:$(Library) MySolution/Assign08_01.java

Assign08_02: lib
	javac -cp MySolution:$(Library) MySolution/Assign08_02.java

# Compile test
test: Assign08_01 Assign08_02
	javac -cp MySolution:$(Library) MySolution/TestAssign08.java

# Run tests
run: test
	java -cp MySolution:$(Library) TestAssign08

# Clean compiled files
clean:
	rm -f MySolution/*.class

cleanall: clean
	cd $(Library) && make cleanall

.PHONY: lib Assign08_01 Assign08_02 test run clean cleanall


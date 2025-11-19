# Makefile for Assignment 8

LIB_ROOT=../../libraries
LIB_DIR=$(LIB_ROOT)/Library

# Compile Library dependencies first
lib:
	cd $(LIB_DIR) && make compall

# Compile assignment files
Assign08_01: lib
	javac -cp MySolution:$(LIB_ROOT) MySolution/Assign08_01.java

Assign08_02: lib
	javac -cp MySolution:$(LIB_ROOT) MySolution/Assign08_02.java

# Compile test
test: Assign08_01 Assign08_02
	javac -cp MySolution:$(LIB_ROOT) MySolution/TestAssign08.java

# Run tests
run: test
	java -cp MySolution:$(LIB_ROOT) TestAssign08

# Clean compiled files
clean:
	rm -f MySolution/*.class

cleanall: clean
	cd $(LIB_DIR) && make cleanall

.PHONY: lib Assign08_01 Assign08_02 test run clean cleanall

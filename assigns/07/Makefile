# Makefile for Assignment 7

LIB_ROOT=../../libraries
LIB_DIR=$(LIB_ROOT)/Library

# Compile Library dependencies first
lib:
	cd $(LIB_DIR) && make compall

# Compile MyDeque (needed for Assign07_01)
mydeque:
	cd $(LIB_DIR) && make compall

# Compile assignment files
Assign07_01: mydeque
	javac -cp MySolution:$(LIB_ROOT) MySolution/Assign07_01.java

Assign07_02: Assign07_01
	javac -cp MySolution:$(LIB_ROOT) MySolution/Assign07_02.java

# Run tests
test: Assign07_02
	java -cp MySolution:$(LIB_ROOT) Assign07_02

# Clean compiled files
clean:
	rm -f MySolution/*.class

cleanall: clean
	cd $(LIB_DIR) && make cleanall

.PHONY: lib mydeque Assign07_01 Assign07_02 test clean cleanall

LIB_ROOT=../../libraries
LIB_DIR=$(LIB_ROOT)/Library
SRC=MySolution
CLASSPATH=.:$(SRC):$(LIB_ROOT)

lib:
	cd $(LIB_DIR) && make compall

Assign09: lib
	javac -d . -cp $(CLASSPATH) $(SRC)/MyPQueue/*.java $(SRC)/FnGtree/*.java $(SRC)/Assign09_02.java

run: Assign09
	java -cp $(CLASSPATH) Assign09_02

clean:
	rm -f Assign09_02*.class
	rm -f $(SRC)/*.class $(SRC)/MyPQueue/*.class $(SRC)/FnGtree/*.class
	rm -rf Library

cleanall: clean
	cd $(LIB_DIR) && make cleanall

.PHONY: lib Assign09 run clean cleanall

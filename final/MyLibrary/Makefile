# Makefile for MyLibrary - CS392 Functional Programming Library

# Compiler and flags
JAVAC = javac
JAVA = java
JAVAC_FLAGS = -cp .
JAVA_FLAGS = -cp .

# Directories
FNLIST_DIR = FnList
MYSTACK_DIR = MyStack
MYQUEUE_DIR = MyQueue
LNSTRM_DIR = LnStrm
MYDEQUE_DIR = MyDeque

# Source files
FNLIST_SOURCES = $(FNLIST_DIR)/FnList.java $(FNLIST_DIR)/FnListUtil.java
MYSTACK_SOURCES = $(MYSTACK_DIR)/MyStack.java $(MYSTACK_DIR)/MyStackBase.java \
                  $(MYSTACK_DIR)/MyStackArray.java $(MYSTACK_DIR)/MyStackList.java \
                  $(MYSTACK_DIR)/MyStackEmptyExn.java $(MYSTACK_DIR)/MyStackFullExn.java
MYQUEUE_SOURCES = $(MYQUEUE_DIR)/MyQueue.java $(MYQUEUE_DIR)/MyQueueBase.java \
                  $(MYQUEUE_DIR)/MyQueueArray.java $(MYQUEUE_DIR)/MyQueueList.java \
                  $(MYQUEUE_DIR)/MyQueueEmptyExn.java $(MYQUEUE_DIR)/MyQueueFullExn.java
LNSTRM_SOURCES = $(LNSTRM_DIR)/LnStrm.java $(LNSTRM_DIR)/LnStcn.java
MYDEQUE_SOURCES = $(MYDEQUE_DIR)/MyDeque.java $(MYDEQUE_DIR)/MyDequeBase.java \
                  $(MYDEQUE_DIR)/MyDequeList.java $(MYDEQUE_DIR)/MyDequeEmptyExn.java \
                  $(MYDEQUE_DIR)/MyDequeFullExn.java

ALL_SOURCES = $(FNLIST_SOURCES) $(LNSTRM_SOURCES) $(MYSTACK_SOURCES) $(MYQUEUE_SOURCES) $(MYDEQUE_SOURCES)

# Test files
TEST_FILES = FnListTest.java ExamExamples.java

# Default target
all: compile

# Compile all library components
compile: $(ALL_SOURCES)
	@echo "Compiling MyLibrary components..."
	$(JAVAC) $(JAVAC_FLAGS) $(ALL_SOURCES)
	@echo "Compilation complete!"

# Compile and run tests
test: compile $(TEST_FILES)
	@echo "Compiling test files..."
	$(JAVAC) $(JAVAC_FLAGS) $(TEST_FILES)
	@echo "Running FnList tests..."
	$(JAVA) $(JAVA_FLAGS) FnListTest
	@echo "Running exam examples..."
	$(JAVA) $(JAVA_FLAGS) ExamExamples

# Run only FnList tests
test-fnlist: compile
	@echo "Compiling FnList test..."
	$(JAVAC) $(JAVAC_FLAGS) FnListTest.java
	@echo "Running FnList tests..."
	$(JAVA) $(JAVA_FLAGS) FnListTest

# Run only exam examples
test-examples: compile
	@echo "Compiling exam examples..."
	$(JAVAC) $(JAVAC_FLAGS) ExamExamples.java
	@echo "Running exam examples..."
	$(JAVA) $(JAVA_FLAGS) ExamExamples

# Clean compiled files
clean:
	@echo "Cleaning compiled files..."
	find . -name "*.class" -delete
	@echo "Clean complete!"

# Clean everything including this Makefile
cleanall: clean
	@echo "Cleaning all generated files..."

# Help target
help:
	@echo "MyLibrary Makefile - Available targets:"
	@echo "  all          - Compile all library components (default)"
	@echo "  compile      - Compile all library components"
	@echo "  test         - Compile and run all tests"
	@echo "  test-fnlist  - Compile and run FnList tests only"
	@echo "  test-examples- Compile and run exam examples only"
	@echo "  clean        - Remove all compiled .class files"
	@echo "  cleanall     - Remove all generated files"
	@echo "  help         - Show this help message"

# Phony targets
.PHONY: all compile test test-fnlist test-examples clean cleanall help

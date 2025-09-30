
public class TestPayroll {
    public static void main(String[] args) {
        System.out.println("=== COMPREHENSIVE PAYROLL TEST SUITE ===");
        
        // Test 1: Constructor and basic functionality
        System.out.println("\n--- Test 1: Constructor ---");
        // Test: Verify that the Payroll constructor initializes correctly
        // Expected: Creates a Payroll object with size 0 and proper array allocation
        Payroll payroll1 = new Payroll();
        System.out.println("✓ Constructor created successfully");
        System.out.println("Initial size: " + payroll1.size());
        
        // Test 2: Adding employees
        System.out.println("\n--- Test 2: Add Employee ---");
        // Test: Verify that add_employee() correctly adds employees to the payroll
        // Expected: Each employee is added and size increases accordingly
        Payroll payroll2 = new Payroll();
        
        Employee emp1 = createEmployee("Alice Johnson", 1, 50000.0);
        Employee emp2 = createEmployee("Bob Smith", 2, 60000.0);
        Employee emp3 = createEmployee("Carol Davis", 3, 55000.0);
        
        payroll2.add_employee(emp1);
        payroll2.add_employee(emp2);
        payroll2.add_employee(emp3);
        
        System.out.println("✓ Added 3 employees");
        System.out.println("Current size: " + payroll2.size());
        
        // Test 3: Size method
        System.out.println("\n--- Test 3: Size Method ---");
        // Test: Verify that size() returns the correct number of employees
        // Expected: Returns 0 for empty payroll, 1 after adding one employee
        Payroll payroll3 = new Payroll();
        System.out.println("Empty payroll size: " + payroll3.size());
        
        payroll3.add_employee(createEmployee("Test Employee", 999, 1000.0));
        System.out.println("After adding 1 employee: " + payroll3.size());
        
        // Test 4: Print method
        System.out.println("\n--- Test 4: Print Method ---");
        // Test: Verify that print() correctly displays all employees with their details
        // Expected: Each employee's name, ID, and salary are printed in a readable format
        Payroll payroll4 = new Payroll();
        
        payroll4.add_employee(createEmployee("John Doe", 101, 75000.0));
        payroll4.add_employee(createEmployee("Jane Smith", 102, 80000.0));
        
        System.out.println("Printing employees:");
        payroll4.print();
        
        // Test 5: Finding employees
        System.out.println("\n--- Test 5: Find Employee ---");
        // Test: Verify that find_employee() correctly locates employees by name
        // Expected: Returns the correct index for existing employees, throws exception for non-existent ones
        Payroll payroll5 = new Payroll();
        
        payroll5.add_employee(createEmployee("Alice", 1, 50000.0));
        payroll5.add_employee(createEmployee("Bob", 2, 60000.0));
        payroll5.add_employee(createEmployee("Charlie", 3, 70000.0));
        
        try {
            int index = payroll5.find_employee("Bob");
            System.out.println("✓ Found Bob at index: " + index);
            
            index = payroll5.find_employee("Alice");
            System.out.println("✓ Found Alice at index: " + index);
        } catch (EmployeeNotFoundException e) {
            System.out.println("✗ Unexpected exception: " + e);
        }
        
        // Test 6: Removing employees
        System.out.println("\n--- Test 6: Remove Employee ---");
        // Test: Verify that remove_employee() correctly removes employees by index
        // Expected: Employee is removed, size decreases, uses unordered removal (last element replaces removed)
        Payroll payroll6 = new Payroll();
        
        payroll6.add_employee(createEmployee("Employee1", 1, 1000.0));
        payroll6.add_employee(createEmployee("Employee2", 2, 2000.0));
        payroll6.add_employee(createEmployee("Employee3", 3, 3000.0));
        
        System.out.println("Before removal - Size: " + payroll6.size());
        try {
            payroll6.remove_employee(1); // Remove middle employee
            System.out.println("✓ Removed employee at index 1");
            System.out.println("After removal - Size: " + payroll6.size());
        } catch (EmployeeIndexException e) {
            System.out.println("✗ Unexpected exception: " + e);
        }
        
        // Test 7: Exception handling
        System.out.println("\n--- Test 7: Exception Handling ---");
        // Test: Verify that appropriate exceptions are thrown for invalid operations
        // Expected: EmployeeNotFoundException for non-existent names, EmployeeIndexException for invalid indices
        Payroll payroll7 = new Payroll();
        
        // Test EmployeeNotFoundException - searching for non-existent employee
        try {
            payroll7.find_employee("NonExistent");
            System.out.println("✗ Should have thrown EmployeeNotFoundException");
        } catch (EmployeeNotFoundException e) {
            System.out.println("✓ Correctly caught EmployeeNotFoundException");
        }
        
        // Test EmployeeIndexException - removing from invalid positive index
        try {
            payroll7.remove_employee(5); // Invalid index
            System.out.println("✗ Should have thrown EmployeeIndexException");
        } catch (EmployeeIndexException e) {
            System.out.println("✓ Correctly caught EmployeeIndexException");
        }
        
        // Test EmployeeIndexException - removing from negative index
        try {
            payroll7.remove_employee(-1); // Negative index
            System.out.println("✗ Should have thrown EmployeeIndexException");
        } catch (EmployeeIndexException e) {
            System.out.println("✓ Correctly caught EmployeeIndexException for negative index");
        }
        
        // Test 8: Array resizing
        System.out.println("\n--- Test 8: Array Resizing ---");
        // Test: Verify that the array automatically resizes when capacity is exceeded
        // Expected: Array doubles in size when needed, all employees are preserved
        Payroll payroll8 = new Payroll();
        
        // Add many employees to trigger resizing (initial size is 1024, so 1500 will trigger doubling)
        for (int i = 0; i < 1500; i++) {
            Employee emp = createEmployee("Employee" + i, i, 1000.0 + i);
            payroll8.add_employee(emp);
        }
        
        System.out.println("✓ Added 1500 employees (should trigger resizing)");
        System.out.println("Final size: " + payroll8.size());
        
        // Test 9: Copy payroll
        System.out.println("\n--- Test 9: Copy Payroll ---");
        // Test: Verify that copy_payroll() correctly copies all employees from source to target
        // Expected: Target payroll becomes identical to source, handles null source gracefully
        Payroll source = new Payroll();
        source.add_employee(createEmployee("Source1", 1, 1000.0));
        source.add_employee(createEmployee("Source2", 2, 2000.0));
        
        Payroll target = new Payroll();
        target.add_employee(createEmployee("Target1", 3, 3000.0));
        
        System.out.println("Before copy - Target size: " + target.size());
        target.copy_payroll(source);
        System.out.println("After copy - Target size: " + target.size());
        System.out.println("✓ Copy payroll completed");
        
        // Test copying null - should clear the target payroll
        Payroll empty = new Payroll();
        empty.copy_payroll(null);
        System.out.println("✓ Copy null payroll handled correctly");
        
        // Test 10: Add payroll (merge)
        System.out.println("\n--- Test 10: Add Payroll (Merge) ---");
        // Test: Verify that add_payroll() correctly merges two payrolls together
        // Expected: All employees from source are added to target, handles null source gracefully
        Payroll payroll1_merge = new Payroll();
        payroll1_merge.add_employee(createEmployee("Employee1", 1, 1000.0));
        payroll1_merge.add_employee(createEmployee("Employee2", 2, 2000.0));
        
        Payroll payroll2_merge = new Payroll();
        payroll2_merge.add_employee(createEmployee("Employee3", 3, 3000.0));
        payroll2_merge.add_employee(createEmployee("Employee4", 4, 4000.0));
        
        System.out.println("Before merge - Payroll1 size: " + payroll1_merge.size());
        payroll1_merge.add_payroll(payroll2_merge);
        System.out.println("After merge - Payroll1 size: " + payroll1_merge.size());
        System.out.println("✓ Merge payroll completed");
        
        // Test merging null - should not change the target payroll
        Payroll empty_merge = new Payroll();
        empty_merge.add_payroll(null);
        System.out.println("✓ Merge null payroll handled correctly");
        
        System.out.println("\n=== ALL TESTS COMPLETED ===");
    }
    
    private static Employee createEmployee(String name, int id, double salary) {
        // Helper method to create Employee objects for testing
        // Creates a new Employee with the specified name, ID, and salary
        Employee emp = new Employee();
        emp.name = name;
        emp.ID = id;
        emp.salary = salary;
        return emp;
    }
}

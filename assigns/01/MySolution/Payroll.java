
public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;
    
    private Employee people[];
    private int maximum_size;
    private int current_size;

    public Payroll() {
	/* your code */
    people = new Employee[INITIAL_MAXIMUM_SIZE]; // set of  employees in a people variable 
    maximum_size = INITIAL_MAXIMUM_SIZE;
    current_size = 0;

    }
    
    public int size() {
        return current_size;
    }
    
    public void print() {
        for (int i = 0; i < current_size; i++) {
            if (people[i] != null) {
                System.out.println("Employee " + i + ": " + people[i].name + 
                                 " (ID: " + people[i].ID + ", Salary: $" + people[i].salary + ")");
            }
        }
    }
    
    public void add_employee(Employee newbie) {
	/* add a new employee to the payroll, making more room on the payroll if neccessary aka add to the array */
    if (current_size >= maximum_size){
        double_maxsize();
    }
    people[current_size++] = newbie;
    return; 

    }

    public void remove_employee(int i) throws EmployeeIndexException {
	/* remove Employee in people[i], shifting other Employees over to fill its place (or just replacing it with the last Employee: remove is not guaranteed to keep order).  Throw an exception if there is no entry at position i.*/
    if(i < 0 || i >= current_size){
        throw new EmployeeIndexException();
    } //unordered remove, last replace, null slot 
    people[i] = people[current_size - 1];
    people[current_size - 1] = null; // to avoid loitering 
    current_size--;
    return;
    }
    
    public int find_employee(String name) throws EmployeeNotFoundException {
	/* return the first i such that people[i].name == target_name.  Throw an exception if no such i is found. */
        for(int i = 0; i < current_size; i++){
            if(people[i]!= null && name.equals(people[i].name)){
                return i;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public void add_payroll(Payroll source) {
	/* combine two Payrolls into one.  Do not worry about repeated items. */
    if (source == null || source.current_size == 0) return;

    int needed = current_size + source.current_size;
    while(maximum_size < needed){
        double_maxsize();
    }
    
    for (int j=0; j< source.current_size; j++){
        people[current_size + j] = source.people[j];
    }
    current_size = needed;
    return;
    }


    public void copy_payroll(Payroll source) {
	/* assign one payroll to another */
    if (source == null){
        for(int i = 0; i< current_size;i++){
            people[i] = null;
        }
        current_size = 0;
        return;
    }

    while (maximum_size < source.current_size){
        double_maxsize();
    }

    for (int i = 0; i < source.current_size; i++){
        people[i] = source.people[i];
    }

    current_size = source.current_size;
    return;
    }
    
    private void double_maxsize() {
        Employee[] new_people = new Employee[maximum_size * 2];
        for (int i = 0; i < current_size; i++) {
            new_people[i] = people[i];
        }
        people = new_people;
        maximum_size *= 2;
    }
}

public class Assign03_03<T> extends MyQueueBase<T> {

    int nitm = -1;
    FnList<T> frnt = null;
    FnList<T> rear = null;

    public Assign03_03() {
	nitm = 0;
	frnt = new FnList<T>();
	rear = new FnList<T>();
    }

    public int size() {
	return nitm;
    }

    public boolean isFull() {
	return false;
    }

    public T top$raw() {
	// HX: Please implement
        // if rear list is not empty, return its first element
        // otherwise, return first element of front list
        if (!rear.nilq()) {
            return rear.hd();
        } else {
            return frnt.hd();
        }
    }

    public T deque$raw() {
	// HX: Please implement
        // if front list is empty, transfer from rear list
        if (frnt.nilq()) {
            // transfer all elements from rear to front (reversing them)
            frnt = rear.reverse();
            rear = new FnList<T>();
        }
        
        // now dequeue from front list
        T item = frnt.hd();
        frnt = frnt.tl();
        nitm--;
        return item;
    }

    public void enque$raw(T itm) {
	// HX: Please implement
        // add item to rear list
        rear = new FnList<T>(itm, rear);
        nitm++;
    }
    
    public void foritm(java.util.function.Consumer<? super T> action) {
        // HX: Please implement
        // iterate through queue elements in order (front to rear)
        FnList<T> current = frnt;
        FnList<T> reversedRear = rear.reverse();
        
        // iterate through front list
        while (!current.nilq()) {
            action.accept(current.hd());
            current = current.tl();
        }
        
        // iterate through reversed rear list
        while (!reversedRear.nilq()) {
            action.accept(reversedRear.hd());
            reversedRear = reversedRear.tl();
        }
    }
    
    public void iforitm(java.util.function.BiConsumer<Integer, ? super T> action) {
        // HX: Please implement
        // iterate through queue elements in order (front to rear)
        FnList<T> current = frnt;
        FnList<T> reversedRear = rear.reverse();
        int index = 0;
        
        // iterate through front list
        while (!current.nilq()) {
            action.accept(index, current.hd());
            current = current.tl();
            index++;
        }
        
        // iterate through reversed rear list
        while (!reversedRear.nilq()) {
            action.accept(index, reversedRear.hd());
            reversedRear = reversedRear.tl();
            index++;
        }
    }
    
    // test method
    public static void main(String[] argv) {
        System.out.println("testing two-list based queue:");
        
        Assign03_03<Integer> queue = new Assign03_03<Integer>();
        
        // test enqueue
        System.out.println("enqueueing 1, 2, 3, 4, 5");
        queue.enque$raw(1);
        queue.enque$raw(2);
        queue.enque$raw(3);
        queue.enque$raw(4);
        queue.enque$raw(5);
        
        System.out.println("size: " + queue.size());
        System.out.println("top: " + queue.top$raw());
        
        // test dequeue
        System.out.println("dequeueing all elements:");
        while (!queue.isEmpty()) {
            System.out.println("dequeued: " + queue.deque$raw());
        }
        
        // test mixed operations
        System.out.println("\nmixed operations test:");
        queue.enque$raw(10);
        queue.enque$raw(20);
        System.out.println("after enqueue 10, 20 - top: " + queue.top$raw());
        System.out.println("dequeued: " + queue.deque$raw());
        queue.enque$raw(30);
        System.out.println("after enqueue 30 - top: " + queue.top$raw());
        System.out.println("dequeued: " + queue.deque$raw());
        System.out.println("dequeued: " + queue.deque$raw());
        System.out.println("final size: " + queue.size());
    }
}

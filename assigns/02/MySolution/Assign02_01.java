public class Assign02_01 {
    /*
      HX-2025-02-13: 10 points
      The 'int' type in Java is for integers of some fixed precision.
      More precisely, each integer of the type int is represented as a sequence of bits
      of some fixed length. Please write a program that finds this length. Your program
      is expected return the correct answer instantly. Note that you can only use arithmetic
      operations here. In particular, NO BIT-WISE OPERATIONS ARE ALLOWED.
     */
    public static int findIntBitLength() {
        // multiply by 2 until overflow (becomes negative), then add 1 for bit length
        int bitLength = 0;
        int value = 1;
        
        while (value > 0) {
            value = value * 2;
            bitLength++;
        }
        return bitLength + 1;
    }
    
    public static void main(String[] argv) {
        int bitLength = findIntBitLength();
        System.out.println("Java int type has " + bitLength + " bits");
        
        // verification
        System.out.println("Expected: 32 bits, Got: " + bitLength + " bits");
    }
}

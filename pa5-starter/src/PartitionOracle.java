
// These are some imports that the course staff found useful, feel free to use them
// along with other imports from java.util.
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PartitionOracle {

    /**
     * Feel free to use this method to call partition. It catches any exceptions or
     * errors and returns a definitely-invalid pivot (-1) to turn errors into
     * potential failures. For example, in testPartition, you may use
     * 
     * runPartition(p, someArray, someLow, someHigh)
     * 
     * instead of
     * 
     * p.partition(someArray, someLow, someHigh)
     * 
     * @param p
     * @param strs
     * @param low
     * @param high
     * @return
     */
    public static int runPartition(Partitioner p, String[] strs, int low, int high) {
        try {
            return p.partition(strs, low, high);
        } catch (Throwable t) {
            return -1;
        }
    }

    // The three methods below are for you to fill in according to the PA writeup.
    // Feel free to make other helper methods as needed.

    /*
 * Return null if the pivot and after array reflect a correct partitioning of 
 * the before array between low and high.
 *
 * Return a non-null String (your choice) describing why it isn't a valid
 * partition if it is not a valid result. You might choose Strings like these,
 * though there may be more you want to report:
 *
 * - "after array doesn't have same elements as before"
 * - "Item before pivot too large"
 * - "Item after pivot too small"
 */

    public static String isValidPartitionResult(String[] before, int low, int high, int pivot, String[] after) {
        
        if(pivot < 0) pivot += high;

        for(int i = pivot; i < after.length; i++) {
            if(after[i].compareTo(after[pivot]) < 0) {
                return "item after pivot smaller than pivot item.";
            }
        }

        for(int i = 0; i < pivot; i++) {
            if(after[i].compareTo(after[pivot]) > 0) {
                return "item before pivot greater than pivot item.";
            }
        }
        
        if(before.length != after.length) return "arrays not same size after partition.";

        return null;
    }

    public static String[] generateInput(int n) {
        String[] gen = new String[n];

        for(int i = 0; i < n; i++) {
            Random r = new Random();
            int asciiForACapLetter = r.nextInt(26) + 65;  // Generates a random letter from A - Z
            String s = Character.toString((char)(asciiForACapLetter));
            
            gen[i] = s;
        }
        
        return gen;
    }

    public static CounterExample findCounterExample(Partitioner p) {
        //Generate random input array
        String[] input1 = generateInput(100);
        String[] original1 = Arrays.copyOf(input1, input1.length);

        //Partition Array (pivot variable holds pivot index)
        int pivot = p.partition(input1, 0, 3);

        //Compare original array to partitioned array with isValidPartitionResult
        String reason = isValidPartitionResult(original1, 0, 3, pivot, input1);
        if(reason != null) return new CounterExample(original1, 0, 3, pivot, input1, reason);
        
        return null;
    }

    class badPartioner implements Partitioner {
        public int partition(String[] strs, int low, int high) {
            return high-low/2;
        }
    }

    class WPartioner implements Partitioner {
        public int partition(String[] strs, int low, int high) {
            return high-low/2;
        }
    }

}

// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class FirstElePivotPartitioner implements Partitioner{
    public int partition(String[] strs, int low, int high) {
        String pivot = strs[low];
        int p1 = low + 1;
        String temp;

        for(int i = low + 1; i <= high; i++) {
            if(strs[i].compareTo(pivot) < 1) {
                if(i != p1) {
                    temp = strs[p1];
                    strs[p1] = strs[i];
                    strs[i] = temp;
                }
                p1++;
            }

            strs[low] = strs[p1 - 1];
            strs[p1 - 1] = pivot;

        }

        return p1 - 1;
    } 
}

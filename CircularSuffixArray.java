import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {
    char[] vals;
    int[] sortedIndex;
    int length;
 
    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) throw new IllegalArgumentException("string can't be null!");
        length = s.length();
        vals = new char[length];
        sortedIndex = new int[length];

        for (int i = 0; i < s.length(); i++) {
            vals[i] = s.charAt(i); // represents the first letter of each circular array
            sortedIndex[i] = i; // to be updated when sorted
        }
        sort();
    }

    // 3 way string quicksort adapted from lecture StringSorts slide 38
    private void sort() {
        sort(0, length - 1, 0);
    }


    private void sort(int lo, int hi, int d) {
        if (hi <= lo) return;
        char pivot = vals[(sortedIndex[lo] + d) % length];
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        while (i <= gt) {

            char c = vals[(sortedIndex[i] + d) % length];
            if (c < pivot) exch(lt++, i++);
            else if (c > pivot) exch(i, gt--);
            else i++;
        }
        // recursively sort the sub arrays post partitioning
        sort(lo, lt - 1, d);
        if (d < length) {
            sort(lt, gt, d + 1);
        }
        sort(gt + 1, hi, d);

    }

    private void exch(int i, int j) {
        int iVal = sortedIndex[i];
        int jVal = sortedIndex[j];
        sortedIndex[i] = jVal;
        sortedIndex[j] = iVal;
    }

    // length of s
    public int length() {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > length - 1) {
            throw new IllegalArgumentException("i outside of range!");
        }
        return sortedIndex[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String banana = "ABRACADABRA!"; // 12
        CircularSuffixArray tester = new CircularSuffixArray(banana);
        StdOut.println("length: " + tester.length());
        for (int i = 0; i < tester.length(); i++) {
            StdOut.print(tester.index(i) + " ");
        }
        // output expected: 11 10 7 0 3 5 8 1 4 6 9 2
    }
}

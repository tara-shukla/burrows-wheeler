import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        // create CircularSuffixArray from standard input
        String s = BinaryStdIn.readString();
        CircularSuffixArray suffix = new CircularSuffixArray(s);

        int first = 0;
        // iterate through the indices to find value of first
        for (int i = 0; i < suffix.length(); i++) {
            first = suffix.index(i);
            if (first == 0) {
                BinaryStdOut.write(first);
                break;
            }
        }
        // iterate through the suffix array to get the transform
        for (int i = 0; i < suffix.length(); i++) {
            int index = suffix.index(i) - 1;
            if (suffix.index(i) == 0) {
                index = s.length() - 1;
            }
            BinaryStdOut.write(s.charAt(index));
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        final int R = 256; // # of ASCII characters
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();

        int[] count = new int[R + 1];
        char[] sorted = new char[s.length()]; // "first column"
        int[] next = new int[s.length()];

        // count frequencies
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) + 1]++;
        }
        // compute cumulative frequencies
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        // add chars to sorted[] and fill next[]
        for (int i = 0; i < s.length(); i++) {
            sorted[count[s.charAt(i)]++] = s.charAt(i);
            next[count[s.charAt(i)]] = i;
        }

        int currentIndex = first;
        for (int i = 0; i < sorted.length; i++) {
            char current = sorted[currentIndex];
            currentIndex = next[i];
            BinaryStdOut.write(current);
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        }
        else if (args[0].equals("+")) {
            inverseTransform();
        }

    }
}

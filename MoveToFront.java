import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {


    private static StringBuilder init() {
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            letters.append((char) (i));
        }
        return letters;
    }

    // apply move-to-front encoding, reading from stdin and writing to stdout
    public static void encode() {
        StringBuilder letters = init();

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(8);
            int cPos = letters.indexOf(Character.toString(c)); // find index of c in table

            BinaryStdOut.write((char) cPos); // add index to output

            // delete c/ add to front
            letters.deleteCharAt(cPos);
            letters.insert(0, c);
        }

        BinaryStdOut.close();
    }

    // apply move-to-front decoding
    public static void decode() {
        StringBuilder letters = init();
        while (!BinaryStdIn.isEmpty()) {
            char i = BinaryStdIn.readChar(8); // read index of char
            char c = letters.charAt(i);

            BinaryStdOut.write((char) c, 8);
            // delete c/ add to front
            letters.deleteCharAt(i);
            letters.insert(0, c);
        }
        BinaryStdOut.close();
    }


    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {

        if (args[0].equals("-")) encode();
        if (args[0].equals("+")) decode();

    }
}

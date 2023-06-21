import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args){
        RandomizedQueue<String> rdnQueue = new RandomizedQueue<String>();
        //int count = Integer.parseInt(args[0]);
        int count = 8;
        for (int i = 0; i < count; i++) {
            String input = StdIn.readString();
            rdnQueue.enqueue(input);
        }
        for (int i = 0; i < count; i++) {
            System.out.println(rdnQueue.dequeue());
        }
    }
}

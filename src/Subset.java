

import java.lang.*;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by jesse on 4/4/2016.
 */
public class Subset {
    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);
        RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
        while (StdIn.hasNextLine() && !StdIn.isEmpty()) {
            randQueue.enqueue(StdIn.readString());
        }
        Iterator<String> i = randQueue.iterator();
        for (int k = 0; k < number; k++) {
            System.out.println(i.next());
        }
    }
}

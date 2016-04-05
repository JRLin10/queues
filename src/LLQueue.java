import java.lang.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jesse on 3/25/2016.
 */
public class LLQueue<Item> implements Iterable<Item> {
    private Node first, last;
    private int count;

    public LLQueue() {

    }                           // construct an empty deque

    private class Node {
        Item item;
        Node next;
    }

    private class QueueIterator implements Iterator<Item> {
        Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();


        }              // removeFirst and return the item from the front

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public boolean isEmpty() {
        if (count==0) {
            return true;
        }
        return false;

    }                // is the deque empty?

    public int size() {

        return count;


    }                       // return the number of items on the deque

    public void add(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        count++;

    }         // addLast the item to the end

    public Item remove() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;

        }
        count--;
        return item;


    }              // removeFirst and return the item from the front


    public Iterator<Item> iterator() {
        return new QueueIterator();
    }        // return an iterator over items in order from front to end

    public static void main(String[] args) {
        LLQueue<String> list = new LLQueue<String>();
        list.add("Dad");
        list.add("Mom");
        list.add("Alyssa");
        list.add("Jesse");
        LLQueue<String> test = list;
        Iterator<String> i = list.iterator();

        while (i.hasNext()) {
            String s = i.next();
            System.out.println(s);
        }
        System.out.println(list.size());
        test.remove();
        Iterator<String> j = test.iterator();
        while (j.hasNext()) {
            String s = j.next();
            System.out.println(s);
        }
        System.out.println(test.size());


    } // unit testing
}



import java.lang.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jesse on 3/25/2016.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int count;

    public Deque() {
        count = 0;
        first = null;
        last = null;
    }                           // construct an empty deque

    private class Node {
        Item item = null;
        Node next, previous = null;
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
        if (count == 0) {
            return true;
        }
        return false;

    }                // is the deque empty?

    public int size() {

        return count;


    }                       // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.previous = null;
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.previous = first;
        }
        first.next = oldFirst;
        count++;

    }         // add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        last.previous = oldLast;
        count++;

    }         // addLast the item to the end

    public Item removeFirst() {
        if (isEmpty()) {
            throw new UnsupportedOperationException();
        }
        Item item = first.item;
        if (first.next != null) first = first.next;
        first.previous = null;
        count--;
        if (isEmpty()) {
            last = null;
            first = null;
        }
        return item;
    }              // removeFirst and return the item from the front

    public Item removeLast() {
        if (isEmpty()) {
            throw new UnsupportedOperationException();
        }
        Item temp = last.item;
        last = last.previous;
        last.next = null;
        count--;
        if (isEmpty()) {
            last = null;
            first = null;
        }
        return temp;


    }                // remove and return the item from the end


    public Iterator<Item> iterator() {
        return new QueueIterator();
    }        // return an iterator over items in order from front to end

    public static void main(String[] args) {
        Deque<String> list = new Deque<String>();
        list.addLast("Dad");
        list.addFirst("Mom");
        list.addFirst("Alyssa");
        list.addLast("Jesse");
        Deque<String> test = list;
        Iterator<String> i = list.iterator();

        while (i.hasNext()) {
            String s = i.next();
            System.out.println(s);
        }
        System.out.println(list.size());
        test.removeFirst();
        test.removeFirst();
        Iterator<String> j = test.iterator();
        while (j.hasNext()) {
            String s = j.next();
            System.out.println(s);
        }
        System.out.println(test.size());


    } // unit testing
}

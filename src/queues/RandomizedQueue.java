package queues;

import java.lang.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jesse on 3/25/2016.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first, last;
    private Node currentNode = first;
    private int count;

    public RandomizedQueue() {

    }                           // construct an empty deque


    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;

    }                // is the deque empty?

    public int size() {

        return count;


    }                       // return the number of items on the deque

    public void enqueue(Item item) {
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

    public Item dequeue() {
        if (isEmpty()) {
            throw new UnsupportedOperationException();
        }
        int random = (int) (Math.random() * count);
        if (random == 0) {
            Item item = first.item;
            if (count!=1) {
                first = first.next;
            }
            first.previous = null;
            count--;
            if (isEmpty()) {
                last = null;
            }
            return item;
        } else if (random == count - 1) {
            Item temp = last.item;
            last = last.previous;
            last.next = null;
            count--;
            if (isEmpty()) {
                last = null;
            }
            return temp;
        } else {
            Node temp = first;
            for (int i = 0; i < random; i++) {
                temp = temp.next;
            }
            Item ite = temp.item;
            Node pre = temp.previous;
            Node nex = temp.next;
            pre.next = nex;
            nex.previous = pre;
            count--;
            return ite;
        }

    }              // removeFirst and return the item from the front

    public Item sample() {
        int random = (int) (Math.random() * count);
        if (random == 0 || count == 1 || count == 0) {
            return first.item;
        } else if (random == count - 1) {
            return last.item;
        } else {
            Node temp = first;
            for (int i = 0; i < random; i++) {
                temp = temp.next;
            }
            return temp.item;
        }
    }                   // return (but do not remove) a random item

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }        // return an iterator over items in order from front to end

    private class Node {
        Item item = null;
        Node next, previous = null;
    }

    private class QueueIterator implements Iterator<Item> {
        Node current = first;
        Node newLast, newFirst = null;


        public QueueIterator() {
            int oldCount = count;
            newFirst = new Node();
            newLast = new Node();
            for (int i = 0; i < oldCount; i++) {
                Item dq = dequeue();
                Node oldLast = newLast;
                newLast = new Node();
                newLast.next = null;
                newLast.item = dq;
                if (i == 0) {
                    newLast.previous = null;
                    newFirst = newLast;
                } else {
                    newLast.previous = oldLast;
                    oldLast.next = newLast;
                }

            }
            first = newFirst;
            last = newLast;
            count = oldCount;
            current = first;
            currentNode = first;

        }

        public boolean hasNext() {
            return current.next != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();


        }              // removeFirst and return the item from the front

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current = current.next;
            Item item = current.item;
            return item;
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<String> list = new RandomizedQueue<String>();
        list.enqueue("Dad");
        list.enqueue("Mom");
        /*list.enqueue("Alyssa");
        list.enqueue("Jesse");
        list.enqueue("A");
        list.enqueue("B");
        list.enqueue("C");
        list.enqueue("D");
        list.enqueue("E");
        list.enqueue("F");*/
        RandomizedQueue<String> test = list;
        Iterator<String> i = list.iterator();

        while (i.hasNext()) {
            String s = i.next();
            System.out.println(s);
        }

        System.out.println(list.size());
        System.out.println("-----");
        test.dequeue();
        test.dequeue();
        System.out.println("test message");
        for (int j = 0; j< test.size(); j++){
            System.out.println(test.currentNode.item);
            test.currentNode = test.currentNode.next;
        }
        while (i.hasNext()) {
            String s = i.next();
            System.out.println(s);
        }
        System.out.println(test.size());


    } // unit testing
}

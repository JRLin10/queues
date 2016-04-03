package queues;

import java.lang.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jesse on 3/25/2016.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first, last;
    private int count;

    public RandomizedQueue() {

    }                           // construct an empty deque

    private class Node {
        Item item = null;
        Node next, previous = null;
    }

    private class QueueIterator implements Iterator<Item> {
        Node current = first;
        Node newLast, newFirst = null;


        public QueueIterator() {
            Item[] scramble = new Item[count];
            for (int i = 0; i < count; i++){
                scramble[i] = current.item;
                if (current.next != null){
                    current = current.next;
                }
                else {
                    current = first;
                }
            }
            for (int i = 0; i < count; i++){
                int choice = (int)(Math.random()*(count-i))+i;
                Item ite = scramble[i];
                scramble[i] = scramble[choice];
                scramble[choice] = ite;

                Node dad = new Node();
                dad.next=null;
                if (i==0){
                    dad.previous=null;
                    dad.item = ite;
                    newFirst = dad;
                }
                else if (i==count-1){
                    Node pre = current;
                    pre.next = dad;
                    dad.previous = pre;
                    dad.item = ite;
                    newLast = dad;
                    current = first;
                }
                else {
                    Node pre = current;
                    pre.next = dad;
                    dad.previous = pre;
                    dad.item = ite;
                    current = current.next;

                }
            }
            first = newFirst;
            last = newLast;
        }

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


    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
        last.previous = oldLast;
        count++;

    }         // addLast the item to the end

    public Item dequeue() {
        int random = (int)(Math.random()*count);
        if(random==0 || count==1 || count == 0) {
            Item item = first.item;
            first = first.next;
            if (isEmpty()) {
                last = null;
            }
            count--;
            return item;
        }
        else if (random == count-1){
            Item temp = last.item;
            last = last.previous;
            last.next = null;
            if (isEmpty()) {
                last = null;

            }
            count--;
            return temp;
        }
        else {

            Node temp = first;
            for (int i = 0; i<random; i++) {
                temp = temp.next;
            }
            Item ite = temp.item;
            Node pre = temp.previous;
            Node nex = temp.next;
            temp = null;
            pre.next = nex;
            nex.previous = pre;
            count--;
            return ite;
        }


    }              // removeFirst and return the item from the front

    public Item sample()  {
        int random = (int)(Math.random()*count);
        if(random==0 || count==1 || count == 0) {
            return first.item;
        }
        else if (random == count-1){
            return last.item;
        }
        else {
            Node temp = first;
            for (int i = 0; i<random; i++) {
                temp = temp.next;
            }
            return temp.item;
        }
    }                   // return (but do not remove) a random item



    public Iterator<Item> iterator() {
        return new QueueIterator();
    }        // return an iterator over items in order from front to end

    public static void main(String[] args) {
        RandomizedQueue<String> list = new RandomizedQueue<String>();
        list.enqueue("Dad");
        list.enqueue("Mom");
        list.enqueue("Alyssa");
        list.enqueue("Jesse");
        list.enqueue("A");
        list.enqueue("B");
        list.enqueue("C");
        list.enqueue("D");
        list.enqueue("E");
        list.enqueue("F");
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
        test.dequeue();
        test.dequeue();
        Iterator<String> j = test.iterator();
        while (j.hasNext()) {
            String s = j.next();
            System.out.println(s);
        }
        System.out.println(test.size());


    } // unit testing
}
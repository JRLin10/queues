/**
 * Created by Jesse on 3/25/2016.
 */
import java.lang.*;
import java.util.Iterator;

public class normalQueue{

    private String[] array;


    public normalQueue() {
        array = new String[0];

    }                           // construct an empty queue
    public boolean isEmpty() {
        if (array.length==0) {
            return true;
        }
        return false;

    }                // is the deque empty?
    public int size() {
        return array.length;

    }                       // return the number of items on the deque

    public void add(String item)  {
        String[] temp;
        if (array.length == 0) {
            temp = new String[1];
            temp[0] = item;
        }
        else {
            temp = new String[array.length+1];
            for (int i =0; i<array.length; i++){
                temp[i]=array[i];
            }
            temp[array.length]=item;
        }
        array = temp;

    }         // addLast the item to the end
    public String remove()  {
        String item = array[0];
        if (isEmpty()){}
        else if (array.length == 1){
            String[] temp = new String[0];
        }
        else {
            String[] temp = new String[array.length-1];
            for (int i = 1; i< array.length; i++){
                temp[i-1] = array[i];
            }
            array = temp;
        }
        return item;

    }

    public Iterator<String> iterator() {
        return null;

    }        // return an iterator over items in order from front to end
    public static void main(String[] args) {
        normalQueue q = new normalQueue();
        q.add("apple");
        q.add("banana");
        q.add("pear");
        q.add("peach");
        q.add("blueberry");
        for (int i = 0; i < q.size(); i++){
            System.out.print(q.array[i] + " ");
        }
        System.out.println();
        //System.out.println(q.array);
        q.remove();
        //System.out.println(q.array);
        for (int i = 0; i < q.size(); i++){
            System.out.print(q.array[i] + " ");
        }
        System.out.println();

    }  // unit testing
}

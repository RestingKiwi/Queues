import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int capacity;
    // construct an empty randomized queue
    public RandomizedQueue(){
        arr = (Item[]) new Object[1];
        capacity = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return  capacity == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
       return  capacity;
    }
    // add the item
    public void enqueue(Item item){
        if (item == null)
            throw  new IllegalArgumentException("Item is null");
        if (capacity == arr.length)
        {
            resize(arr.length*2);
        }
        arr[capacity] = item;
        capacity++;
    }
    private void resize(int newCap) {
        Item[] newArr = (Item[]) new Object[newCap];

        // Copy element from the old array
        for (int i = 0; i < capacity; i++){
            newArr[i] = arr[i];
        }

        // Update the array
        arr = newArr;
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty!");

        int pos  = StdRandom.uniformInt(0,capacity);

        Item data = arr[pos];
        // Last element of the queue
        if(pos == capacity-1){
            arr[pos] = null;
        }
        else
        {
            // Shift the element to fill
            for (int i =pos; i < capacity-1;i++){
                arr[i] = arr[i+1];
            }

            arr[capacity-1] = null;
        }

        capacity--;

        // Reduce the array length
        if (capacity > 0 && capacity <= arr.length/4)
            resize(arr.length/2);
        return data;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty!");

        int pos  = StdRandom.uniformInt(0,capacity);
        return arr[pos];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return  new RandomQIterator();
    }
    private class RandomQIterator implements Iterator<Item>{

        Item[] shuffledArray = (Item[]) new Object[capacity];
        int pos;
        public RandomQIterator() {
            shuffledArray = (Item[]) new Object[capacity];
            for (int i = 0; i < capacity; i++) {
                shuffledArray[i] = arr[i];
            }
            StdRandom.shuffle(shuffledArray);
            pos = 0;
        }


        @Override
        public boolean hasNext(){
            return capacity != 0 && pos < capacity;
        }

        @Override
        public Item next() {
            if (pos == capacity)
                throw new NoSuchElementException("Deque is empty!");

            return shuffledArray[pos++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() is unsupported!");
        }

    }





    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        // Testing isEmpty() on an empty queue
        System.out.println("Is queue empty? " + queue.isEmpty());  // Expected: true

        // Testing enqueue()
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        // Testing size()
        System.out.println("Queue size: " + queue.size());  // Expected: 3

        // Testing sample()
        System.out.println("Sample item: " + queue.sample());  // Expected: Random item from {1, 2, 3}

        // Testing dequeue()
        System.out.println("Dequeued item: " + queue.dequeue());  // Expected: Random item from {1, 2, 3}

        // Testing iterator()
        System.out.print("Iterator items: ");
        for (Integer item : queue) {
            System.out.print(item + " ");  // Expected: Random order of remaining items from {1, 2, 3}
        }
        System.out.println();

        // Testing isEmpty() on a non-empty queue
        System.out.println("Is queue empty? " + queue.isEmpty());  // Expected: false

        // Testing size()
        System.out.println("Queue size: " + queue.size());  // Expected: 2
    }


}
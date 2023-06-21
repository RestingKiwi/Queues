import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node{
        Item data;
        Node next = null;
        Node prev = null;
    }
    private Node head;
    private Node tail;
    private int size;

    // construct an empty deque
    public Deque(){
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the deque
    public int size(){
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null)
            throw new IllegalArgumentException("Item can't be null!");

        Node newNode = new Node();
        newNode.data = item;
        if (isEmpty()){
            head = newNode;
            tail = newNode;
        }
        else {
            head.prev    = newNode;
            newNode.next = head;
            head         = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item)  {
        if (item == null)
            throw new IllegalArgumentException("Item can't be null!");


        if (isEmpty()){
            tail.data = item;
            head.data = item;
        }
        else{
            Node newNode = new Node();
            newNode.data = item;
            newNode.prev = tail;
            tail.next    = newNode;
            tail = newNode;
        }

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty!");

        Item data = head.data;

        if (size == 1){
            head = null;
            tail = null;
        }
        else {
            head.next.prev = null;
            head =head.next;
        }

        size--;
        return  data;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty!");

        Item data = tail.data;
        if (size == 1){
            head  = null;
            tail  = null;
        }
        else {
            tail.prev.next = null;
            tail = tail.prev;
        }
        size--;

        return  data;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
        private Node curNode = head;

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() is unsupported!");
        }

        @Override
        public boolean hasNext(){
            return  curNode != null;
        }

        @Override
        public Item next(){
            if (curNode == null)
                throw  new NoSuchElementException("Deque is empty!");

            Item data = curNode.data;
            curNode = curNode.next;
            return  data;
        }
    }



    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<Integer>();

        // Empty deque?
        System.out.println("Empty deque? ->" + deque.isEmpty() );

        // Deque size?
        System.out.println("Deque size: " + deque.size());

        deque.addFirst(1);
        deque.addFirst(0);
        deque.addLast(2);
        deque.addLast(3);

        for (int ele : deque){
            System.out.print(ele+" ");
        }
        System.out.println();

        System.out.println("Removed from the beginning: " +deque.removeFirst());
        System.out.println("Removed from the end:       " + deque.removeLast());

        for (int ele : deque){
            System.out.print(ele+" ");
        }
        System.out.println();


    }

}
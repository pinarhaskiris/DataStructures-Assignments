/**
 * myStack Class
 *
 * This class is not written by Pınar Haskırış.
 * It was given by the instructor except for the printStack() function.
 *
 *
 * Program Explanation:
 * This program takes a txt file and checks whether the java code inside that file has matching parentheses.
 * To do this, the program takes the file, goes through it line by line, 
 * goes through the lines character by character and when it sees an open parentheses ( '(' or '{') it adds it to the stack.
 * When a closing parentheses comes ( ')' or '}' ) the last element of the stack is being  popped and the code checks whether
 * the opening parentheses is matching with the popped parentheses.
 * If they match, no problem. If they don't match, there is a problem.
 * In the end, the stack should be empty. If its not, there is problem.
 * The stack is being printed every time a new character gets in.
 * 
 */


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @param <Item> is a generic type (that can be String, Integer, Student, Chocolate etc.) of the stack
 */

public class myStack<Item> implements Iterable<Item> {
    private Item[] a; //array of items
    private int n; //number of elements on stack

  
    public myStack() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    /**
     * isEmpty function:
     * @return 1 if the stack is empty
     */
    
    public boolean isEmpty() {return n == 0; }
    
    /**
     * size function:
     * @return the size of the stack
     */
    
    public int size() {return n; }

    /**
     * resize function: 
     * @param capacity is going to be the new size of the stack
     */
    
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     * push function:
     * @param item is the item that is going to be pushed into the stack
     */
    
    public void push(Item item) {
        if (n == a.length) {
            resize(2 * a.length); //double size of array if necessary
            System.out.println("\n\n << stack doubled >>  \n\n");
        }
        a[n++] = item; //add item
    }

    /**
     * pop function:
     * @return the popped item (item that is being removed from the stack)
     */
    
    public Item pop() {
        if(isEmpty())
            throw new NoSuchElementException("Stack underflow");
        Item item = a[n - 1];
        a[n - 1] = null; // to avoid loitering
        n--;
        //shrink size of array if necessary
        if (n > 0 && n == a.length / 4)
            resize(a.length / 2);
        return item;
    }

    /**
     * peek function:
     * @return the last added element into the stack (returns stack's top element)
     */
    
    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        return a[n - 1];
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;
        public ReverseArrayIterator() {i = n - 1; }
        public boolean hasNext() { return i >= 0; }
        public void remove() {throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return a[i--];
        }
    }

    /**
     * printStack function: is a function that's added to the given myStack class.
     * This functions prints the stack elements.
     */
    
    public void printStack(){
        for(int i = n-1; i >= 0; i--){
            while(a[i] != null) {
                System.out.print(a[i] + " ");
            break;
            }
        }
        
    }

}
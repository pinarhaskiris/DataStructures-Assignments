/**
 * Main Class
 * Program Explanation: This program compares three sort algorithms. They are; heap sort, selection sort and java's own sort algorithm.
 * Selection sort has been implemented according to the instructions.
 * To implement heap sort; first, given array contents are stored in a heap. Then with remove function, the heap was stored in the given array in reverse order.
 * Since the remove function of heap always returns the biggest value (since we are removing the root), any other operation for this sort was not needed.
 *
 */

import java.util.Arrays;
import java.util.Random;

public class pınar_haskırış {
    public static void main(String[] args) {

        Integer[] randomArray = new Integer[10000]; //creating an array to sort

        //creating temporary arrays to use when sorting algorithms are called
        Integer tempArray[] = new Integer[randomArray.length];
        Integer tempArray1[] = new Integer[randomArray.length];
        Integer tempArray2[] = new Integer[randomArray.length];

        //arrays to store the time lapses of sorting algorithms
        double[] timeElapsesForHeapSort = new double[randomArray.length];
        double[] timeElapsesForJava = new double[randomArray.length];
        double[] timeElapsesForSelectionSort = new double[randomArray.length];

        //using the random class
        Random randomNumber = new Random();

        //arrays to store the total time elapsed for each sort (using these later to find the average)
        double totalTimeElapsedForSelectionSort = 0;
        double totalTimeElapsedForHeapSort = 0;
        double totalTimeElapsedForJavaSort = 0;

        System.out.println("Array Size: " + randomArray.length); //printing the array size

        //assigning random numbers to the array to start sorting
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = randomNumber.nextInt(10000000);
        }

        int isTen = 0; //the operation will repeat itself 10 times
        while (isTen != 10) {

            /**
             * USING JAVA SORT ALGORITHM
             */

            //copying the original array into temporary array to use the original array contents for other sort algorithms
            System.arraycopy(randomArray, 0, tempArray, 0, tempArray.length);

            long startForJavaSort = System.nanoTime(); //start time count
            Arrays.sort(tempArray); //sort the array using java's own sort algorithm
            long finishForJavaSort = System.nanoTime(); //finish the time count

            double elapsedTimeMilliSeconds_java = (finishForJavaSort - startForJavaSort) / 1000000.0; //calculating the elapsed time
            totalTimeElapsedForJavaSort = (totalTimeElapsedForJavaSort + elapsedTimeMilliSeconds_java); //updating the total elapsed time
            timeElapsesForJava[isTen] = elapsedTimeMilliSeconds_java; //storing the elapsed time values, to print later

            /**
             * USING HEAP SORT AGLORIHM
             */

            //copying the original array into temporary array to use the original array contents for other sort algorithms
            System.arraycopy(randomArray, 0, tempArray1, 0, tempArray1.length);

            long startForHeapSort = System.nanoTime(); //start time count
            heapSort(tempArray1); //sort the array using heap sort algorithm
            long finishForHeapSort = System.nanoTime(); //finish time count

            double elapsedTimeMilliSeconds_heap = (finishForHeapSort - startForHeapSort) / 1000000.0; // calculating the elapsed time
            totalTimeElapsedForHeapSort = (totalTimeElapsedForHeapSort + elapsedTimeMilliSeconds_heap); //updating the total elapsed time
            timeElapsesForHeapSort[isTen] = elapsedTimeMilliSeconds_heap; //storing the elapsed time values, to print later

            /**
             * USING SELECTION SORT ALGORITHM
             */

            //copying the original array into temporary array to use the original array contents for other sort algorithms
            System.arraycopy(randomArray, 0, tempArray2, 0, tempArray2.length);

            long startForSelectionSort = System.nanoTime(); //start time count
            selectionSort(tempArray2); //sort the array using selection sort algorithm
            long finishForSelectionSort = System.nanoTime(); //finish time count

            double elapsedTimeMilliSeconds_selection = (finishForSelectionSort - startForSelectionSort) / 1000000.0; //calculating the elapsed time
            totalTimeElapsedForSelectionSort = (totalTimeElapsedForSelectionSort + elapsedTimeMilliSeconds_selection); //updating the total elapsed time
            timeElapsesForSelectionSort[isTen] = elapsedTimeMilliSeconds_selection; //storing the elapsed time values, to print later

            //assigning random integers again to sort a different array
            for (int i = 0; i < randomArray.length; i++) {
                randomArray[i] = randomNumber.nextInt(10000000);
            }
            isTen++; //incresing isTen to do this operation 10 times
        }

        /**
         * TO PRINT
         */

        for (int w = 0; w < 10; w++) {
            System.out.println("  " + (w + 1) + ": " + timeElapsesForHeapSort[w] + " msec"); //printing elapsed time at each step
        }
        System.out.println("Average Sort Time for Heap Sort            :" + totalTimeElapsedForHeapSort / 10 + " msec\n"); //printing the average elapsed time

        for (int y = 0; y < 10; y++) {
            System.out.println("  " + (y + 1) + ": " + timeElapsesForJava[y] + " msec"); //printing elapsed time at each step
        }
        System.out.println("Average Sort Time for Java Sort            :" + totalTimeElapsedForJavaSort / 10 + " msec\n"); //printing the average elapsed time

        for (int x = 0; x < 10; x++) {
            System.out.println("  " + (x + 1) + ": " + timeElapsesForSelectionSort[x] + " msec"); //printing elapsed time at each step
        }
        System.out.println("Average Sort Time for Selection Sort            :" + totalTimeElapsedForSelectionSort / 10 + " msec\n"); //printing the average elapsed time

    }

    /**
     * @param list: list to sort
     *            This function sorts the given array using a heap.
     *            1st: Storing the given array contents in a heap.
     *            2nd: Removing each element of the heap from the root.
     *            3rd: Storing all the removed values in the given array.
     */

    //sorts an integer array in increasing order
    public static void heapSort(Integer[] list) {
        Heap<Integer> heapToStore = new Heap<>();

        //storing the array contents in the heap
        for (int i = 0; i < list.length; i++) {
            heapToStore.add(list[i]);
        }

        //storing the removed elements in the given array (in reverse order)
        Integer removedElement = 0;
        for (int k = 0; k < list.length; k++) {
            removedElement = heapToStore.remove();
            list[list.length - k - 1] = removedElement;
        }

    }

    /**
     * @param list: list to sort
     *            This function sorts the given array using selection sort algorithm.
     *            1st: Going through the array using for loop.
     *            2nd: Finding the smallest value.
     *            3rd: Swapping if necessary.
     */

    public static void selectionSort(Integer[] list) {

        int i, j;
        for (i = 0; i < list.length - 1; i++) {
            int smallest_index = i; //setting the smallest_index to the beginning of the array and incrementing as we go over

            //selecting the smallest element
            for (j = i + 1; j < list.length; j++) //starting from where we left off to search for a smaller integer in the list
                if (list[j] < list[smallest_index]) //if it is smaller than the smallest_index
                    smallest_index = j; //set it as smallest_index


            //swapping (since we are checking the smallest_index above, there is no need for an if statement
            int temp = list[smallest_index];
            list[smallest_index] = list[i];
            list[i] = temp;

        }
    }
}

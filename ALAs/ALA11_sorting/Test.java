import java.util.Collections;
import java.util.ArrayList;

public class Test{
    public static void main(String[]args){
        /***
         * Performance Analysis based on how the inital array was organized
         * 
         * Each sorting algorithm was tested with a random, sorted and reversed array. 
         * I will go through each sort method and explain its performance 
         * 
         * Selection Sort has the worst overall performance in all scenerios. 
         * It isn't a performance focused sorting method, it is just easy to write and straight forward
         * It has these results because its time complexity is also one of the highest, tied with the insertion
         * sort for big O(n^2). However it has worst performance than the insertion because it loops over the entire
         * array, and then the entire array again but from the next index regardless of if the array is sorted or not.
         * Therefore the performance of this sorting method is not determined by order of the array passed to it but
         * rather the size of the array passed, and the number of iterations will be constant regardless of the order.
         * Use this sorting algorithm with small data sets because it is simple but inefficient.
         * 
         * Insertion Sort has better performance than the selection sort but with the same time complexity of O(n^2) has 
         * selection sort. In the best case, insertion has complexity of O(n) when the array is already in order because
         * of its while loop structure, it can just read over the entire array and keep moving the index without
         * needed to swap. In addition, the random array is better than that of the selection sort because of this feature
         * and so therefore overall it is more efficient than selection. In the worst case however, when the array is in reverse order,
         * insertion sort gives the same or worst result than the selection sort algorithm. 
         * Use this sorting algorithm with nearly sorted arrays or larger arrays than that which you would feed the 
         * selection sort algorithm
         * 
         * Bubble Sort has O(n^2) complexity as well because of its nested for loop action. However, because it compares 
         * the next element to the previous, it is able to breeze over an array in O(n) complexity in the best case when the
         * array is already sorted. Use this algorithm with partially sorted arrays for a simple solution.
         * 
         * Merge Sort has the same performance regardless of the order the array, simularly to selection sort. however, 
         * it is much more efficient because it trades more space complexity for time complexity. It's time complexity
         * is O(nlogn)  and space complexity of O(n). The algorithm allows large data sets to be sorted quickly 
         * and efficiently -- use as such with large datasets. 
         * 
         * Quick sort is faster when the array is randomly ordered which makes it great for when you expect this
         * to be the situation. It has time complexity of O(nlogn) on average but can climb to O(n^2) in the worst
         * case when the array is sorted. It also don't use up as much space complexity as merge sort so if space is an
         * issue this would be your better option. If the array is partial sorted, you should use the middle of three rule
         * to select the pivot instead of the first. 
         * 
         * The heap sort does not waste time with a linear-time scan like selection does. Instead it keeps an 
         * unsorted region in a heap data structure to find the largest element in each step. Its time complexity is
         * O(nlogn) and but it uses more space with a space complexity of O(n) because it needs to make an entire heap data structure filled with all the items, n items. It does used less iterations than the 
         * merge sort method overall while it still has the same time and space complexity. The heap sort gave the worst performance
         * when the array was already sorted
         * 
         * The bucket gave the same amount iterations regardless of the original state of the array simular to the merge and 
         * selection sort algorithms. Its time and space complexity is O(n+t) with 't' being the number of buckets required.
         * In these circumstance, the bucket sort was the most efficient data strucutre overall in terms of iterations -- requiring
         * just under a 50,000 iterations in all 3 trials; however, it uses alot of space compared to the other algorithms. Bucket
         * sort is not partial when t is extremely large because it requires alot of space.
         * 
         * Finally the Radix sort, simularly to bucket sort, takes the same amount of iterations regardless of the original
         * state of the data set being passed to it. However, On average 
         * it requires nearly twice as many iterations as the bucket sort in this case. It only uses 10 buckets instead of t buckets but it sorts
         * these buckets several of times to sort the subgroups based on the position. It requires more iterations but its
         * space use efficiency compared to bucket sort is much better. The time and space complexity can be described as O(n*d) where
         * d is the number of classfiying each position passes over the data set.
         * 
         */

        
        final int SIZE = 10000;
        //data set 1: randomlist
        ArrayList<Integer> randomList = new ArrayList<>(SIZE);
        for(int i = 0; i < SIZE; i++){
            randomList.add((int)(Math.random() * SIZE));
        }

        //data set 2: sorted
        ArrayList<Integer> sortedList = (ArrayList<Integer>) (randomList.clone());
        Collections.sort(sortedList);

        //data set 3: reversed list
        ArrayList<Integer> reversedList = (ArrayList<Integer>) (sortedList.clone());
        Collections.reverse(reversedList);

        
        System.out.printf("%-20s\t%-10s\t%-10s\t%-10s\n", "Sorting Algorithms", "Random", "Sorted", "Reversed");

        
        // Selection sort
        Sort.selectionSort(randomList);
        System.out.printf("%-20s\t%-10d\t","Selection Sort", Sort.iterations);
        Sort.selectionSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations);
        Sort.selectionSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);
        // Insertion Sort
        Sort.insertionSort(randomList);
        System.out.printf("%-20s\t%-10d\t","Insertion Sort", Sort.iterations);
        Sort.insertionSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations);
        Sort.insertionSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);
        // Bubble Sort
        Sort.bubbleSort(randomList);
        System.out.printf("%-20s\t%-10d\t","Bubble Sort", Sort.iterations);
        Sort.bubbleSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations);
        Sort.bubbleSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);
        // Merge Sort
        Sort.iterations = 0;
        Sort.mergeSort(randomList);
        System.out.printf("%-20s\t%-10d\t","Merge Sort", Sort.iterations);
        Sort.iterations = 0;
        Sort.mergeSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations);
        Sort.iterations = 0;
        Sort.mergeSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);
        // Quick Sort
        Sort.quickSort(randomList);
        System.out.printf("%-20s\t%-10d\t","Quick Sort", Sort.iterations);
        Sort.quickSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations);
        Sort.quickSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);
        // Heap Sort
        Sort.heapSort(randomList);
        System.out.printf("%-20s\t%-10d\t","Heap Sort", Sort.iterations);
        Sort.heapSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations);
        Sort.heapSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);
        //Bucket Sort
        // System.out.print(randomList);
        Sort.bucketSort(randomList);
        System.out.printf("%-20s\t%-10d\t","Bucket Sort", Sort.iterations);
        Sort.bucketSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations);
        Sort.bucketSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations);

        Collections.shuffle(randomList);
        Collections.reverse(reversedList);
        // Radix Sort
        Sort.radixSort(randomList);
        System.out.printf("%-20s\t%-10d\t","Radix Sort", Sort.iterations);
        Sort.radixSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations);
        Sort.radixSort(reversedList);
        System.out.printf("%-10d\n", Sort.iterations);
    }   
}
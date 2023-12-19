import java.util.ArrayList;
/**
    Class Sort contains five sorting algorithms as static methods
    @author Houria Oudghiri
    Date of the creation: April 24, 2023
 */
public class Sort{
    public static int iterations;
    /**
        Selection sort method
        @param list to be sorted
        time complexity: O(n^2)
        space complexity: O(1)
     */
    public static <E extends Comparable<E>> void selectionSort(ArrayList<E> list) {
        iterations = 0;
        int minIndex;
        for (int i=0; i<list.size()-1; i++) {
            iterations++;
            E min = list.get(i); 
            minIndex = i;
            for (int j=i+1; j<list.size(); j++){
                iterations++;
                if (list.get(j).compareTo(min) < 0){
                    min = list.get(j);
                    minIndex = j;
                }
            }
            swap(list, i, minIndex);
        }      
    }
    /**
        Insertion sort method
        @param list to be sorted
        time complexity: O(n^2)
        space complexity: O(1)
     */
    public static <E extends Comparable<E>> void insertionSort(ArrayList<E> list) {
        iterations = 0;
        for (int i=1; i<list.size(); i++) {
            iterations++;
            //Insert element i in the sorted sub-list
            E currentVal = list.get(i);
            int j = i;
            while (j > 0 && currentVal.compareTo(list.get(j - 1)) < 0){
                iterations++;
                // Shift element (j-1) into element (j)
                list.set(j, list.get(j - 1));
                j--;
            }
            // Insert currentVal at index j 
            list.set(j, currentVal);
        }
    } 
    /**
        Bubble sort method
        @param list to be sorted
        time complexity: O(n^2)
        space complexity: O(1)
     */
    public static <E extends Comparable<E>>void bubbleSort(ArrayList<E> list) { 
        iterations = 0;
        boolean sorted = false; 
        for (int k=1; k < list.size() && !sorted; k++) { 
            iterations++;
            sorted = true; 
            for (int i=0; i<list.size()-k; i++) { 
                iterations++;
                if (list.get(i).compareTo(list.get(i+1)) > 0) { 
                    // swap 
                    swap(list, i, i+1);
                    sorted = false; 
                } 
            } 
        }     
    }  

    /**
        Merge sort method
        @param list to be sorted
        time complexity: O(nlogn)
        space complexity: O(n)
     */
    public static <E> ArrayList<E> subList(ArrayList<E> list,
                                   	    int start, int end){
        
        ArrayList<E> newList = new ArrayList<>();
        for(int i = start; i < end; i++){
            iterations++;
            newList.add(list.get(i));
        }
        return newList;
    }
    public static <E extends Comparable<E>> void mergeSort(ArrayList<E> list) {
        iterations++;
        if (list.size() > 1) { // ==1: base case
            ArrayList<E> firstHalf = subList(list, 0, list.size()/2);
            ArrayList<E> secondHalf = subList(list, list.size()/2, list.size());
            // System.arraycopy(list, 0, firstHalf, 0,
            //                  list.length/2);
            // System.arraycopy(list,list.length/2, secondHalf, 0, 
            //                  list.length-list.length/2);
            mergeSort(firstHalf);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
        }
    } 
    /**
        merge method used by mergeSort
        @param list where the merged elements will be stored
        @param list1 the first sorted list to be merged
        @param list2 the second sorted list to be merged
        time complexity: O(n)
        space complexity: O(1)
     */  
    public static <E extends Comparable<E>> void merge(ArrayList<E> list1, 
                                                       ArrayList<E> list2, 
                                                       ArrayList<E> list) {
        int list1Index = 0;
        int list2Index = 0;
        int listIndex = 0;
        while(list1Index < list1.size() && list2Index < list2.size()) {
            iterations++;
            if (list1.get(list1Index).compareTo(list2.get(list2Index)) < 0)
                list.set(listIndex++, list1.get(list1Index++));
            else
                list.set(listIndex++, list2.get(list2Index++));
        }
        while(list1Index < list1.size()){
            iterations++;
            list.set(listIndex++, list1.get(list1Index++));
        }
        while(list2Index < list2.size()){
            iterations++;
            list.set(listIndex++, list2.get(list2Index++));
        }
    }

    /**
        Quick sort method
        @param list to be sorted
        time complexity: O(nlogn) on average or O(n^2) in the worst case
        space complexity: O(1)
     */
    public static <E extends Comparable<E>> void quickSort(ArrayList<E> list) {
        iterations = 0;
        quickSort(list, 0, list.size()-1);
    }
    /**
        Recursive helper method used by quicksort
        @param list to be sorted
        @param first index of the first element in the part being sorted
        @param last index of the last element in the part being sorted
        time complexity: O(nlogn) on average ot O(n^2) in the worst case
        space complexity: O(1)
     */
    public static <E extends Comparable<E>> void quickSort(ArrayList<E> list, int first, int last) {
        iterations++;
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex-1);
            quickSort(list, pivotIndex+1, last);
        }
    }
    /**
        partition method used by quicksort
        @param list to be partitioned
        @param first index of the first element in the part being partitioned
        @param last index of the last element in the part being partitioned
        @return index of the pivot
        time complexity: O(n)
        space complexity: O(1)
     */
    public static <E extends Comparable<E>> int partition(ArrayList<E> list, int first, int last){
        E pivot;
        int index, pivotIndex;
        pivot = list.get(first);
        pivotIndex = first;
        for (index = first + 1; index <= last; index++){
            iterations++;
            if (list.get(index).compareTo(pivot) < 0){
                pivotIndex++;
                swap(list, pivotIndex, index);
            }
        }
        swap(list, first, pivotIndex);
        return pivotIndex;
    }
    /**
        swap method
        @param list where the two elements will be swapped
        @param i1 index of the first element to be swapped
        @param i2 index of the second element to be swapped
        time complexity: O(1)
        space complexity: O(1)
     */
    public static <E> void swap(ArrayList<E> list, int i1, int i2){
        E temp = list.get(i1);
        list.set(i1, list.get(i2));
        list.set(i2, temp);
    }
    
    
    
    /**
        Heap sort method
        @param list to be sorted
        time complexity: O(nlogn)
        space complexity: O(n)
     */
    public static <E extends Comparable<E>> void heapSort(ArrayList<E> list){
        Heap<E> heap = new Heap<>();
        iterations = 0;
        for(int i=0; i<list.size(); i++){// O(nlogn)
            iterations++;
            heap.add(list.get(i));
        }
        for (int i=list.size()-1; i>=0; i--) {
            iterations++;
            list.set(i, heap.remove());
        }
    }




    /**
        bucket sort method
        @param list to be sorted
        time complexity: O(n+t)
        space complexity: O(n+t)
     */
    public static void bucketSort(ArrayList<Integer> list){
        iterations = 0;
        Integer t = max(list);
        // System.out.println("Max val: " + t);
        // System.out.println("Size: " + list.size());
        ArrayList<ArrayList<Integer>> buckets;
        buckets = new ArrayList<>(t+1);
        // create t+1 buckets
        for(int i=0; i<t+1; i++){
            iterations++;
            buckets.add(new ArrayList<>());
        }
        //Distribute the data on the buckets
        for(int i=0; i<list.size(); i++) {
            iterations++;
            // System.out.println(list.get(i));
            // System.out.println(buckets.get(list.get(i)));
            ArrayList<Integer> bucket = buckets.get(list.get(i));
            bucket.add(list.get(i));
        }
        // Move the data from the buckets back to the list
        int k = 0;
        for(int i=0; i<buckets.size(); i++) {
            iterations++;
            ArrayList<Integer> bucket = buckets.get(i);
            for(int j=0; j<bucket.size(); j++){
                iterations++;
                list.set(k++, bucket.get(j));
            }
        }
    }
    public static Integer max(ArrayList<Integer> list){
        Integer maxi = list.get(0);
            for(int  i = 1; i < list.size(); i++){
                iterations++;
                if(list.get(i) > maxi)
                    maxi = list.get(i);
            }
        //System.out.println(max);
        return maxi;
    }
    
    

    // TIME COMPLEXITY O(n*d)
    // SPACE COMPLEXITY O(n*d)
    public static void radixSort(ArrayList<Integer> list) {
        ArrayList<ArrayList<Integer>> buckets;
        buckets = new ArrayList<>();
        iterations = 0;
        Integer maxValue = max(list);
        int digits = maxValue.toString().length();
        for(int d=0; d<digits; d++) {
            iterations++;
            // create 10 buckets
            for(int j=0; j<10; j++) {
                iterations++;
                buckets.add(new ArrayList<>());
            }
            //Distribute the data on the buckets
            for(int j=0; j<list.size(); j++){
                iterations++;
            // find the index of the bucket where list[j] should be placed
                int bucketIndex = (list.get(j) % (int)(Math.pow(10, d+1))) / (int)(Math.pow(10,d));
                ArrayList<Integer> bucket = buckets.get(bucketIndex);
                bucket.add(list.get(j));
            }
            // Move the data from the buckets back to the list
            int k=0;
            for(int j=0; j<10; j++) {
                iterations++;
                ArrayList<Integer> bucket = buckets.get(j);
                for(int l=0; l<bucket.size(); l++){
                    iterations++;
                    list.set(k++, bucket.get(l));
                }
            }
            // clear all the buckets for the next iteration
            buckets.clear();
        }
    }

}
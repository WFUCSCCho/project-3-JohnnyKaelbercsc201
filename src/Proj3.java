import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Proj3 {
    private static java.util.Collections Collections;

    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        if (left < right) {
            int mid = (left + right) / 2;
            //sort left half, right half, then merge sorted halves
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }

    }

    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me
        int n1 = mid - left + 1; //size of left subarray
        int n2 = right - mid; //size of right subarray

        ArrayList<T> L = new ArrayList<>(n1); //left temp array creation
        ArrayList<T> R = new ArrayList<>(n2); //right temp array creation
        for (int i = 0; i < n1; i++) { //filling in data for left sub-array
            L.add(a.get(left + i));
        }
        for (int j = 0; j < n2; j++) {//"" for right sub-array
            R.add(a.get(mid + j + 1));
        }

        //initializing positions for left, right, and original array lists respectively...
        int i = 0; int j = 0; int k = left;
        while (i < n1 && j < n2) { //while loop that merges the left and right sub-arrays if they contain values
            if (L.get(i).compareTo(R.get(j)) <= 0) { //comparing values: if value in left is <= value in right...
                a.set(k, L.get(i)); //place value in original array from left
                i++;
            }
            else {
                a.set(k, R.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) { //adding whatever values are left in the left array to original array
            a.set(k, L.get(i));
            i++;
            k++;
        }
        while (j < n2) { //same for right array
            a.set(k, R.get(j));
            j++;
            k++;
        }
    }

    // Quick Sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        if (left < right) {
            int pivIndex = partition(a, left, right);
            quickSort(a, left, pivIndex - 1); //sort elements to left of pivot
            quickSort(a, pivIndex + 1, right); //sort elements to right of pivot
        }
    }

    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        // Finish Me
        T pivot = a.get(right); //element on far right of array list assigned as pivot
        int i = left - 1; //initializing index variable
        for (int j = left; j < right; j++) {
            if (a.get(j).compareTo(pivot) <= 0) {//compare each element to pivot
                i++;
                swap(a, i, j); //calling swap method to swap element at position i with element at position j
            }
        }
        swap(a, i+1, right); //swapping pivot with element at i+1
        return i + 1; //return current position of pivot
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        //create max heap
        for (int i = (right-1)/2; i >= left; i--) { //begin on node that is not a leaf
            heapify(a, i, right);
        }
        //sort
        for(int i = right; i > left; i--) {
            swap(a, left, i); //swap current root with end node
            heapify(a, left, i-1); //heapify again
        }

    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // Finish Me
        int largest = left; //index position of largest element
        int leftChild = 2 * left + 1;
        int rightChild = 2 * left + 2;
        //comparing children to parent node...
        if (leftChild <= right && a.get(leftChild).compareTo(a.get(largest)) > 0) {// if left child greater than largest, left child is now largest
            largest = leftChild;
        }
        if (rightChild <= right && a.get(rightChild).compareTo(a.get(largest)) > 0) { // if right child is greater than largest, right child is now largest
            largest = rightChild;
        }
        if (largest != left) { //if root is not largest, swap left node with it then heapify a recursively
            swap(a, left, largest);
            heapify(a, largest, right);
        }
    }

    // Bubble Sort
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {
        // Finish Me
        int comparisons=0;
        for (int i = 0; i < size-1; i++) {//outer loop for each iteration
            for (int j = i+1; j < size-1-i; j++) {//inner loop for going down the line comparing and sorting
                comparisons++;//comparisons # tracker
                if (a.get(j).compareTo(a.get(j+1)) > 0) {//comparing adjacent elements if element at index j is larger than j+1 then swap
                    swap(a, j, j+1);
                }
            }
        }
        return comparisons; //return int number of comparisons
    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me
        int comparisons = 0;
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            //sorting at odd indices
            for (int i = 1; i < size - 1; i += 2) {
                comparisons++;
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(a, i, i + 1); //same as bubble sort
                    isSorted = false;
                }
            }
            //sorting at even indices
            for (int i = 0; i < size - 1; i += 2) {
                comparisons++;
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(a, i, i + 1); //same as bubble sort
                    isSorted = false;
                }
            }
        }
        return comparisons; //return int number of comparisons
    }

    public static void main(String [] args)  throws IOException {
        // Finish Me
        if (args.length != 3) {
            System.out.println("Usage: java Proj3 <filename> <sortAlgorithm> <numLines> ");
            return;
        }
        String filename = args[0]; //Health_Sleep_Statistics.csv
        String sortAlgorithm = args[1];
        int numLines = Integer.parseInt(args[2]);
        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(filename);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        //ArrayList to store the Sleep data
        ArrayList<SleepData> sleepDataArrayList = new ArrayList<SleepData>();

        // Read the file line by line
        while (inputFileNameScanner.hasNext() && sleepDataArrayList.size() < numLines) {
            String line = inputFileNameScanner.nextLine();
            String[] parts = line.split(","); // split the string into multiple parts


            //User ID, Age, Gender, Sleep Quality, Bedtime, Wake-up Time, Daily Steps, Calories Burned, Physical Activity, Dietary Habits, Sleep Disorders, Medication Usage
            // New sleep data object
            SleepData data = new SleepData(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    parts[2],
                    Integer.parseInt(parts[3]),
                    parts[4],
                    parts[5],
                    Integer.parseInt(parts[6]),
                    Integer.parseInt(parts[7]),
                    parts[8],
                    parts[9],
                    parts[10],
                    parts[11]
            );

            sleepDataArrayList.add(data); // add the data onto the ArrayList
        }
        inputFileNameStream.close(); // closing file stream

        PrintWriter analysispw = new PrintWriter(new FileWriter("analysis.txt", true)); //appends to analysis.txt file each time
        PrintWriter sortedpw = new PrintWriter(new FileWriter("sorted.txt")); //overwrites sorted.txt file each time

        //making analysis.txt file human-readable with description of info order of what is being printed
        analysispw.println("Sorting Algorithm, Array List Format, Number of Lines, Running Time (ns), Comparisons");

        //creating sorted, shuffled, and reversed array lists of SleepData objects
        ArrayList<SleepData> sortedArrayList = new ArrayList<>(sleepDataArrayList);
        ArrayList<SleepData> shuffledArrayList = new ArrayList<>(sleepDataArrayList);
        ArrayList<SleepData> reversedArrayList = new ArrayList<>(sleepDataArrayList);

        //creating sorted, shuffled, and reverse lists
        Collections.sort(sortedArrayList); //sorting
        Collections.shuffle(shuffledArrayList); //shuffling
        Collections.sort(reversedArrayList, Collections.reverseOrder()); //reversing


        //for bubble, merge, quick, and heap sort: use System.nanoTime() for already sorted, shuffled, and reverse lists...

        ArrayList<ArrayList<SleepData>> allLists = new ArrayList<>(); //creating list of the 3 list types
        allLists.add(sortedArrayList);
        allLists.add(shuffledArrayList);
        allLists.add(reversedArrayList);

        for (ArrayList<SleepData> list : allLists) { //for each list type in the overarching data list (list of sorted, shuffled, and reverse lists)...
            String listName;
            if (list == sortedArrayList) {
                listName = "sorted";
            } else if (list == shuffledArrayList) {
                listName = "shuffled";
            } else if (list == reversedArrayList) {
                listName = "reversed";
            }
            else {
                listName = "error";
            }

            System.out.println(listName);
            analysispw.append(listName + " Number of Lines: " + list.size() + "\n"); //writing to analysis txt file list name and number of lines


            //tracking running times for cases: Bubble, Merge, Quick, and Heap Sort
            //tracking comparisons for cases: Bubble and Odd-Even Transposition Sort
            switch (sortAlgorithm.toLowerCase()) {
                case "bubble":
                    long bubbleStartTime = System.nanoTime(); //start run time
                    int bubbleComparisons = bubbleSort(list, list.size());
                    long bubbleEndTime = System.nanoTime(); //end
                    long bubbleTotalTime = bubbleEndTime - bubbleStartTime; //net

                    //write to analysis file...
                    analysispw.append("Bubble Sort; Running Time: " + bubbleTotalTime + " ns, Number of Comparisons: " + bubbleComparisons + "\n");
                    //print statement
                    System.out.println("Bubble Sort; Running Time: " + bubbleTotalTime + " ns, Number of Comparisons: " + bubbleComparisons);
                    break;
                case "merge":
                    long mergeStartTime = System.nanoTime();
                    mergeSort(list, 0, list.size()); //list, left, right
                    long mergeEndTime = System.nanoTime();
                    long mergeTotalTime = mergeEndTime - mergeStartTime;

                    //write to analysis file...
                    analysispw.append("Merge Sort; Running Time: " + mergeTotalTime + " ns\n");

                    //print statement
                    System.out.println("Merge Sort; Running Time: " + mergeTotalTime + " ns");
                    break;
                case "quick":
                    long quickStartTime = System.nanoTime();
                    quickSort(list, 0, list.size()); //list, left, right
                    long quickEndTime = System.nanoTime();
                    long quickTotalTime = quickEndTime - quickStartTime;

                    //write to analysis file...
                    analysispw.append("Quick Sort; Running Time: " + quickTotalTime + " ns\n");
                    //print statement
                    System.out.println("Quick Sort; Running Time: " + quickTotalTime + " ns");
                    break;
                case "heap":
                    long heapStartTime = System.nanoTime();
                    heapSort(list, 0, list.size()); //list, left, right
                    long heapEndTime = System.nanoTime();
                    long heapTotalTime = heapEndTime - heapStartTime;

                    //write to analysis file...
                    analysispw.append("Heap Sort; Running Time: " + heapTotalTime + " ns\n");
                    //print statement
                    System.out.println("Heap Sort; Running Time: " + heapTotalTime + " ns");

                    break;
                case "transposition":
                    long transpositionStartTime = System.nanoTime();
                    int transpositionComparisons = transpositionSort(list, list.size());
                    long transpositionEndTime = System.nanoTime();
                    long transpositionTotalTime = transpositionEndTime - transpositionStartTime;

                    //write to analysis file...
                    analysispw.append("Odd-Even Transposition Sort; Running Time: " + transpositionTotalTime + " ns, Number of Comparisons: " + transpositionComparisons + "\n");
                    //print statement
                    System.out.println("Odd-Even Transposition Sort; Running Time: " + transpositionTotalTime + " ns, Number of Comparisons: " + transpositionComparisons);
                    break;

            }
            //need to write sorted lists to sorted.txt file that overwrites each run
            for (SleepData sleepData : list) {
                sortedpw.println(sleepData.toString());
                sortedpw.println();
            }

        }


        

    }
}

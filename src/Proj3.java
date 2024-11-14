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
    }

    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me
    }

    // Quick Sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    // Bubble Sort
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {
        // Finish Me
        int comparisons;
        return comparisons; //return int number of comparisons
    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me
        int comparisons;
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
            analysispw.println(listName + " Number of Lines: " + list.size()); //writing to analysis txt file list name and number of lines


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

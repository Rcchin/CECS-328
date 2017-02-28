//answer 53.5 and 52
//Raymond Chin 012754597
//credits to: www.programcreek.com LeetCode section
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.*;
import java.io.*;

// Organizes the dataset into a min and max heap. If the size of the 2 heaps are equal then the max heap root 
// will have the median otherwise the median will be the average of min heap and max heap root.
class MedianFinder {
    PriorityQueue<Integer> maxHeap;//lower half
    PriorityQueue<Integer> minHeap;//higher half
 
    public MedianFinder(){
    	//Collections.reverseOrder makes the heap into a pseudo Max Heap
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        minHeap = new PriorityQueue<Integer>();
    }
 
    // Adds a number into the data structure.
    // poll serves a similar function as remove
    // offer inserts a value
    public void addNum(int num) {
    	//add new numbers first to max. Then remove and add to min
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        
        //maxHeap should be greater than if not move a value from 
        //min to max
        if(maxHeap.size() < minHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }
 
    // Returns the median of current data stream
    // peek returns the value at the top of the stack
    public double findMedian() {
        if(maxHeap.size()==minHeap.size()){
            return (double)(maxHeap.peek()+(minHeap.peek()))/2;
        }else{
            return maxHeap.peek();
        }
    }
    public static void main(String[] args) throws FileNotFoundException
    {
    	//create references to use MedianFinder
    	MedianFinder mf1 = new MedianFinder();
    	MedianFinder mf2 = new MedianFinder();
    	//create variables for both files(datasets)
    	File dataset01 = new File("dataset1.txt");
    	File dataset02 = new File("dataset2.txt");
    	//Create 2 scanners to read both files(datasets)
    	Scanner reader1 = new Scanner(dataset01);
    	Scanner reader2 = new Scanner(dataset02);
    	
    	
    
    	
    	//These while loops will go through the entire dataset and add them into the Heap.
    	while(reader1.hasNextLine())
    	{
    		int number = Integer.parseInt(reader1.nextLine());
    		mf1.addNum(number);
    	}
    	while(reader2.hasNextLine())
    	{
    		int number = Integer.parseInt(reader2.nextLine());
    		mf2.addNum(number);
    	}
    	
    	//now use the findMedian method to find the median of each dataset
    	double median1 = mf1.findMedian();
    	double median2 = mf2.findMedian();
    	//display results
    	System.out.println("The median for data set 1 is " + median1);
    	System.out.println("The median for data set 2 is " + median2);
    	
    }
}
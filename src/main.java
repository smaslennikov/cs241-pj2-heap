import java.util.Scanner;

// Assignment: 
//
// Implement a heap in Java and use it to sort double arrays using the approach discussed in
// class. The heap constructor should accept an array of numbers and build a heap in O(n)
// time as discussed. The heap class should have methods poll, size, toString, siftDown,
// smallerChild and swap. Here are the steps you should follow
//
//	1. Build a heap class as designed above, preferably as an inner class in the main program.
//
//	2. In the main program, construct a random array of double numbers of length n = 10k,
//	where k is obtained from the command line.
//
//	3. Use the heap class constructor and methods to sort the array of numbers.
//
//	4. Verify using a procedure that the resulting array is indeed sorted.
//
//	5. Test your program for k = 1, ... , 6
//

public class main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		// The following fulfills part 2:
		//
		// 2. In the main program, construct a random array of double numbers of
		// length n = 10k, where k is obtained from the command line.
		//
		System.out.print("Please enter k: ");
		double[] array = new double[(int) Math.pow(10, s.nextInt())];
		for (int i = 0; i < array.length; i++) {
			array[i] = Math.random();
			// System.err.print(array[i] + " ");
		}
		System.err.print("\n");
		MyHeap h = new main().new MyHeap(array);
		//
		// --------------------------------------------------
		// Without the following, printing to console lagged,
		// misplacing future prints within previous ones.
		// try {
		// Thread.sleep(200);
		// } catch (Exception e) {
		// }
		// --------------------------------------------------
		//
		// System.out.println(h.toString());
		System.out.print("Verifying sort: ");
		if (h.verifySorted(0))
			System.out.println("Sorted");
		else
			System.out.println("Not sorted");
		System.out.print("\n");
	}
	
	// Part 1 of the assignment fulfilled here:
	//
	// 1. Build a heap class as designed above, preferably as an inner class in
	// the main program.
	//
	// a) The heap constructor should accept an array of numbers and build a
	// heap in O(n) time as discussed.
	// b) The heap class should have methods
	// i. poll,
	// ii. size,
	// iii. toString,
	// iv. siftDown,
	// v. smallerChild and
	// vi. swap.
	//
	class MyHeap {
		private double[]	heap;
		private int			size	= 0;
		
		// The following fulfills part (a) of part 1:
		//
		// 1. a) The heap constructor should accept an array of numbers and
		// build a heap in O(n) time as discussed.
		public MyHeap(double array[]) {
			heap = array;
			size = heap.length;
			//
			// The following fulfills part 3:
			// 3. Use the heap class constructor and methods to sort the
			// array of numbers.
			//
			if (!verifySorted(0)) {
				System.out.println("Given array not sorted. Sorting. ");
				//
				// From the guideline pdf:
				//
				// heapify(A)
				// {convert input array A into a heap}
				// 1. k=index of last node that has at least one child
				// {k=n/2-1}
				// 2. for a=k down to 0
				// 2.1.sift down(a)
				//
				for (int i = size(); i > -1; i--) { // part 1 and 2
					if (hasChild(i))
						siftDown(i); // part 2.1
				}
			}
		}
		
		// The following fulfills part (b)(i):
		//
		// From the guideline pdf:
		//
		// poll()
		// {delete and return minimum queue element and suitably reorganize
		// queue}
		// 1. let e be the Þrst element in array
		// 2. exchange Þrst and last elements in array
		// 3. delete last element
		// 4. sift down(0)
		// 5. return e
		//
		public double poll() {
			double e = heap[0]; // part 1
			swap(0, size() - 1); // part 2
			size--; // part 3
			siftDown(0); // part 4
			return e; // part 5
		}
		
		// The following fulfills part (b)(ii):
		//
		public int size() {
			return size;
		}
		
		// The following fulfills part (b)(iii):
		//
		public String toString() {
			String s = "";
			for (int i = 0; i < size(); i++) {
				s += heap[i] + " ";
			}
			return s;
		}
		
		// The following fulfills part (b)(iv):
		//
		// From the guideline pdf:
		//
		// sift down(a)
		// {sift down node at position a as needed to restore heap property}
		// 1. b=a
		// 2. while node b has at least one child {b<n/2}, and node b is bigger
		// than its smaller child node c
		// 2.1.swap value of b with value of c
		// 2.2.set b=c
		//
		public void siftDown(int parent) {
			if (hasChild(parent)) {// part 2
				int smallerChild = smallerChild(parent); // part 1
				if (heap[parent] > heap[smallerChild]) { // part 2
					swap(parent, smallerChild); // part 2.1
					siftDown(smallerChild); // part 2.2
				}
			}
		}
		
		// Returns true if parent has at least one child:
		//
		public boolean hasChild(int parent) {
			if (parent * 2 + 1 >= size())
				return false;
			return true;
		}
		
		// The following fulfills part (b)(v):
		//
		public int smallerChild(int parent) {
			if (parent * 2 + 2 < size()) {
				if (heap[parent * 2 + 1] < heap[parent * 2 + 2])
					return parent * 2 + 1;
				else
					return parent * 2 + 2;
			} else if (parent * 2 + 1 < size())
				return parent * 2 + 1;
			else
				return -1;
		}
		
		// The following fulfills part (b)(vi):
		//
		public void swap(int a, int b) {
			double temp = heap[a];
			heap[a] = heap[b];
			heap[b] = temp;
		}
		
		// The following fulfills part 4:
		//
		// 4. Verify using a procedure that the resulting array is indeed
		// sorted.
		//
		public boolean verifySorted(int parent) {
			int childOne = parent * 2 + 1;
			int childTwo = parent * 2 + 2;
			if (hasChild(parent))
				if (childTwo < size()) {
					if (heap[parent] <= heap[childTwo]) {
						if (!verifySorted(childTwo))
							return false;
					} else
						return false;
				} else
					return verifySorted(childOne);
			return true;
		}
	}
}

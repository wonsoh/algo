import java.util.ArrayList;

/**
 * This is a class of static methods for sorting and searching algorithms.
 * 
 * @author Won S. Oh (<a href="mailto:wonsoh@live.com">wonsoh@live.com</a>)
 */
public class SortSearch {
	/**
	 * @deprecated Constructor is deprecated for this class
	 */
	private SortSearch() {
		/* DEPRECATED */
	}

	/**
	 * Sorts an array passed in using the Bubble Sort algorithm
	 * 
	 * @param array
	 *            array to be sorted
	 */
	public static void bubbleSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int k = 0; k < array.length - i; k++) {
				if (array[k + 1] < array[k]) {
					swap(array, k + 1, k);
				}
			}
		}
	}

	/**
	 * Sorts an array passed in using the Selection Sort algorithm
	 * 
	 * @param array
	 *            array to be sorted
	 */
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int smallestIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[smallestIndex] > array[j]) {
					smallestIndex = j;
				}
			}
			swap(array, smallestIndex, i);
		}

	}

	/**
	 * Sorts an array passed in using the Insertion Sort algorithm
	 * 
	 * @param array
	 *            array to be sorted
	 */
	public static void insertionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = array.length - 1; j > i; j--) {
				if (array[j] < array[j - 1]) {
					swap(array, j, j - 1);
				}
			}
		}
	}

	/**
	 * Swaps two elements of given indices in the array
	 * 
	 * @param array
	 *            array to be sorted
	 * @param ind1
	 *            first index
	 * @param ind2
	 *            second index
	 */
	private static void swap(int[] array, int ind1, int ind2) {
		if (ind1 >= array.length || ind2 >= array.length) {
			return;
		}
		int temp = array[ind1];
		array[ind1] = array[ind2];
		array[ind2] = temp;
	}

	/**
	 * Iteratively searches for target by exhaustion, returns -1 if target not
	 * found otherwise, the index of the first occurrence of target element is
	 * returned.
	 * 
	 * @param array
	 *            array being searched
	 * @param target
	 *            target element to be searched
	 * @return -1 if target not found, otherwise, the index of the first
	 *         occurrence of target element is returned
	 */
	public static int seqSearch(int[] array, int target) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Iteratively searches for target by divide-and-conquer approach, returns
	 * -1 if target not found; otherwise, the index of the first occurrence of
	 * target element is returned <br>
	 * <b>Precondition:</b> array is sorted in ascending order
	 * 
	 * @param array
	 *            array being searched
	 * @param target
	 *            target element to be searched
	 * @return -1 if target not found, otherwise, the index of the first
	 *         occurrence of target element is returned
	 */
	public static int binSearch(int[] array, int target) {
		int start = 0;
		int end = array.length - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (array[mid] == target) {
				return mid;
			}

			else if (array[mid] < target) {
				start = mid + 1;
			}

			else {
				end = mid - 1;
			}
		}
		return -1;
	}

	/**
	 * Recursively searches for target by divide-and-conquer approach, returns
	 * -1 if target not found; otherwise, the index of the first occurrence of
	 * target element is returned <br>
	 * <b>Precondition:</b> array is sorted in ascending order
	 * 
	 * @param array
	 *            array being searched
	 * @param target
	 *            target element to be searched
	 * @param start
	 *            the starting index (0)
	 * @param end
	 *            the index of the last element
	 * @return -1 if target not found, otherwise, the index of the first
	 *         occurrence of target element is returned
	 */
	public static int binSearch(int[] array, int target, int start, int end) {
		if (start > end) {
			return -1;
		}

		int mid = (start + end) / 2;
		if (array[mid] == target) {
			return mid;
		} else if (array[mid] < target) {
			return binSearch(array, target, mid + 1, end);
		} else {
			return binSearch(array, target, start, mid - 1);
		}
	}

	/**
	 * Sorts an array using the Merge Sort algorithm
	 * 
	 * @param array
	 *            array to be sorted
	 * @param start
	 *            the starting index (0)
	 * @param size
	 *            the size of the array
	 */
	public static void mergeSort(int[] array, int start, int size) {
		int size1; // size of the first half
		int size2; // size of the second half

		if (size > 1) {
			size1 = size / 2;
			size2 = size - size1;

			mergeSort(array, start, size1);
			mergeSort(array, start + size1, size2);

			merge(array, start, size1, size2);
		}
		return;
	}

	/**
	 * Helper method for the Merge Sort algorithm Remerges the subarrays in
	 * sorted order
	 * 
	 * @param array
	 *            array being remerged
	 * @param start
	 *            starting bound for the array
	 * @param size1
	 *            size of the first subarray
	 * @param size2
	 *            size of the second subarray
	 */
	private static void merge(int[] array, int start, int size1, int size2) {
		int[] temp = new int[size1 + size2];
		int copied = 0;
		int copied1 = 0;
		int copied2 = 0;

		while ((copied1 < size1) && (copied2 < size2)) {
			if (array[start + copied1] < array[start + size1 + copied2]) {
				temp[copied] = array[start + copied1];
				copied++;
				copied1++;
			} else {
				temp[copied] = array[start + size1 + copied2];
				copied++;
				copied2++;

			}
		}

		while (copied1 < size1) {
			temp[copied] = array[start + copied1];
			copied++;
			copied1++;
		}

		while (copied2 < size2) {
			temp[copied] = array[start + size1 + copied2];
			copied++;
			copied2++;
		}

		for (int i = 0; i < size1 + size2; i++) {
			array[start + i] = temp[i];
		}
		return;
	}

	/**
	 * Sorts an array using the Quicksort algorithm
	 * 
	 * @param array
	 *            array to be sorted
	 * @param left
	 *            the starting index (0)
	 * @param right
	 *            the index of the last element
	 */
	public static void quickSort(int[] array, int left, int right) {
		int index = partition(array, left, right);
		if (left < index - 1) {
			quickSort(array, left, index - 1);
		}
		if (right > index) {
			quickSort(array, index, right);
		}
	}

	/**
	 * Helper method for the Quicksort algorithm Partitions the array and
	 * returns the index of the pivot element Postcondition: Every element left
	 * to the pivot index will be less than the pivot element and every element
	 * right to the pivot index will be greater than the pivot element.
	 * 
	 * @param array
	 *            array to be sorted
	 * @param left
	 *            the starting index (0)
	 * @param right
	 *            the index of the last element
	 * @return the pivot index
	 */
	private static int partition(int[] array, int left, int right) {
		int i = left;
		int j = right;
		int pivot = array[(left + right) / 2];

		while (i <= j) {
			while (array[i] < pivot) {
				i++;
			}
			while (array[j] > pivot) {
				j--;
			}
			if (i <= j) {
				swap(array, i, j);
				i++;
				j--;
			}

		}
		return i;
	}

	/**
	 * Prints all elements in the array in order
	 * 
	 * @param array
	 *            array containing elements to be printed
	 */
	public static void print(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	/**
	 * Main execution point; parameter <code>args</code> unused
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		int[] array = new int[6];
		java.util.ArrayList<Integer> alist = new java.util.ArrayList<Integer>(6);
		while (alist.size() != 6) {
			int e = new java.util.Random().nextInt(15);
			if (!alist.contains(e)) {
				alist.add(e);
			}
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = alist.get(i).intValue();
		}
		java.util.Scanner scan = new java.util.Scanner(System.in);
		System.out.println("Before Sorting : ");
		print(array);
		System.out.println();
		System.out.println("Please enter your terget: ");
		int target = scan.nextInt();
		System.out.println("Your target is " + target);
		System.out.println("Binary Search (Iterative): "
				+ binSearch(array, target));
		System.out.println("Binary Search (Recursive): "
				+ binSearch(array, target, 0, array.length - 1));
		System.out.println("Sequential Search : " + seqSearch(array, target));
		System.out.println();
		System.out.println("Please select an option:");
		System.out.println("\t1: Bubble Sort");
		System.out.println("\t2: Selection Sort");
		System.out.println("\t3: Insertion Sort");
		System.out.println("\t4: Merge Sort");
		System.out.println("\t5: Quicksort");
		int input = scan.nextInt();
		boolean flag = false;
		while (!flag) {
			flag = true;
			switch (input) {
			case 1:
				bubbleSort(array);
				break;
			case 2:
				selectionSort(array);
				break;
			case 3:
				insertionSort(array);
				break;
			case 4:
				mergeSort(array, 0, array.length);
				break;
			case 5:
				quickSort(array, 0, array.length - 1);
				break;
			default:
				try {
					throw new java.lang.IllegalArgumentException(
							"Invalid input");
				} catch (java.lang.IllegalArgumentException e) {
					System.err.println(e);
					flag = false;
					System.err.println("Please reenter the option");
					input = scan.nextInt();
				}
				break;
			}
		}

		System.out.println("Sorting completed : ");
		print(array);
		System.out.println("Binary Search (Iterative): "
				+ binSearch(array, target));
		System.out.println("Binary Search (Recursive): "
				+ binSearch(array, target, 0, array.length - 1));
		System.out.println("Sequential Search : " + seqSearch(array, target));
		scan.close();
		return;
	}
}
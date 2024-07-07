public class QuickSort extends SortAlgorithm {
	// Constructor
	public QuickSort(int input_array[]) {
		super(input_array);
	}

	// Partition the array around the pivot
	private int partition(int low, int high) {
		int pivot = arr[high]; // Use the last element as the pivot
		int i = low - 1; // Index of smaller element
		for (int j = low; j < high; j++) {
			// Compare elements against the pivot
			comparison_counter++;
			if (arr[j] < pivot) {
				i++;
				swap(i, j); // Swap elements smaller than pivot
			}
		}
		// Place the pivot at its correct position
		swap(i + 1, high);
		return i + 1; // Return pivot index
	}

	// Quick Sort Algorithm Implementation
	private void sort(int low, int high) {
		if (low < high) {
			// Partition the array and get the pivot index
			int pivotIndex = partition(low, high);
			// Recursively sort the sub-arrays
			sort(low, pivotIndex - 1);
			sort(pivotIndex + 1, high);
		}
	}

	// Override the base class sort method
	@Override
	public void sort() {
		sort(0, arr.length - 1);
	}

	// Print results for Quick Sort
	@Override
	public void print() {
		System.out.print("Quick Sort\t=>\t");
		super.print();
	}
}

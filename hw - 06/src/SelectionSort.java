public class SelectionSort extends SortAlgorithm {
	// Constructor
	public SelectionSort(int input_array[]) {
		super(input_array);
	}

	// Selection Sort Algorithm Implementation
	@Override
	public void sort() {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i; // Assume the last index is the minimum
			for (int j = i + 1; j < n; j++) {
				// Compare each subsequent element to find the minimum
				comparison_counter++;
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			// Swap the found minimum element with the first unsorted element
			if (minIndex != i) {
				swap(i, minIndex);
			}
		}
	}

	// Print results for Selection Sort
	@Override
	public void print() {
		System.out.print("Selection Sort\t=>\t");
		super.print();
	}
}

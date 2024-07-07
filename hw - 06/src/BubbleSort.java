public class BubbleSort extends SortAlgorithm {
	// Constructor
	public BubbleSort(int input_array[]) {
		super(input_array);
	}

	// Bubble Sort Algorithm Implementation
	@Override
	public void sort() {
		int n = arr.length;
		boolean swapped;
		for (int i = 0; i < n - 1; i++) {
			swapped = false; // Flag to detect if any swaps occurred
			for (int j = 0; j < n - i - 1; j++) {
				// Compare adjacent elements
				comparison_counter++;
				if (arr[j] > arr[j + 1]) {
					// Swap if in wrong order
					swap(j, j + 1);
					swapped = true;
				}
			}
			// If no swaps occurred, the array is already sorted
			if (!swapped) break;
		}
	}

	// Print results for Bubble Sort
	@Override
	public void print() {
		System.out.print("Bubble Sort\t=>\t");
		super.print();
	}
}

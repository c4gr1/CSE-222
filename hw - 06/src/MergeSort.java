public class MergeSort extends SortAlgorithm {
	// Constructor
	public MergeSort(int input_array[]) {
		super(input_array);
	}

	// Merge two sorted sub-arrays into a single sorted array
	private void merge(int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;

		// Create temporary arrays to hold sub-array data
		int[] leftArray = new int[n1];
		int[] rightArray = new int[n2];

		// Copy data to temporary arrays
		for (int i = 0; i < n1; i++) leftArray[i] = arr[left + i];
		for (int j = 0; j < n2; j++) rightArray[j] = arr[mid + 1 + j];

		// Initial indices for sub-arrays and merged array
		int i = 0, j = 0;
		int k = left;

		// Merge the sub-arrays back into the original array
		while (i < n1 && j < n2) {
			comparison_counter++;
			if (leftArray[i] <= rightArray[j]) {
				arr[k] = leftArray[i];
				i++;
			} else {
				arr[k] = rightArray[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements of leftArray
		while (i < n1) {
			arr[k] = leftArray[i];
			i++;
			k++;
		}

		// Copy remaining elements of rightArray
		while (j < n2) {
			arr[k] = rightArray[j];
			j++;
			k++;
		}
	}

	// Recursive Merge Sort Algorithm Implementation
	private void sort(int left, int right) {
		if (left < right) {
			// Find the middle point
			int mid = left + (right - left) / 2;
			// Recursively sort the left and right halves
			sort(left, mid);
			sort(mid + 1, right);
			// Merge the sorted halves
			merge(left, mid, right);
		}
	}

	// Override the base class sort method
	@Override
	public void sort() {
		sort(0, arr.length - 1);
	}

	// Print results for Merge Sort
	@Override
	public void print() {
		System.out.print("Merge Sort\t=>\t");
		super.print();
	}
}

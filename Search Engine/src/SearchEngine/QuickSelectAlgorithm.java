package SearchEngine;

import java.util.Random;

public class QuickSelectAlgorithm {
 
    /**
	*	In quicksort, there is a subprocedure called partition that can, in 
	*	linear time, group a list (ranging from indices left to right) into two 
	*	parts, those less than a certain element, and those greater than or 
	*	equal to the element. Below code performs a partition about
	*	the element list[pivotIndex]
	*/
	
	private static <E extends Comparable<? super E>> int partition(E[] arr, int left, int right, int PivotIndex) {
		E pivotVal = arr[PivotIndex];
		swap(arr, PivotIndex, right); // move PivotIndex to the end
		int storeIndex = left;
		
		for (int k = left; k < right; k++) 
		{
			if (arr[k].compareTo(pivotVal) < 0) 
			{
				swap(arr, k, storeIndex);
				storeIndex++;
			}
		}
		swap(arr, right, storeIndex); // Move PivotIndex to its final place
		return storeIndex;
	}
 
	public static <E extends Comparable<? super E>> E select(E[] arr, int n) {
		int left = 0;
		int right = arr.length - 1;
		Random rand = new Random();
		while (right >= left) {
			int pivotIndex = partition(arr, left, right, rand.nextInt(right - left + 1) + left);
			if (pivotIndex == n) 
			{
				return arr[pivotIndex];
			} 
			else if (pivotIndex < n) 
			{
				left = pivotIndex + 1;
			} 
			else 
			{
				right = pivotIndex - 1;
			}
		}
		return null;
	}
 
	private static void swap(Object[] arr, int Val1, int Val2) {
		if (Val1 != Val2) {
			Object temp = arr[Val1];
			arr[Val1] = arr[Val2];
			arr[Val2] = temp;
		}
	}
 
	public static void main(String[] args) {
		for (int k = 9; k >= 0; k--) 
		{
			Integer[] input = {9, 8, 7, 6, 5, 0, 1, 2, 3, 4};
			System.out.print(select(input, k));
			if (k < 10) System.out.print(", ");
		}
		System.out.println();
	}
 
}
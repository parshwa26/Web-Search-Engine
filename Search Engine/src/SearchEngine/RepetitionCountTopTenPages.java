package SearchEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * 
 * @author 
 *this class gives the top 10 web pages that found the match to the text or suggesting text
 */
public class RepetitionCountTopTenPages {

	public static ArrayList<File> tenBestPages(String[] searched) {

		File[] thisFile = ConstructTrie.thisFile;
		TST<Integer> tst = ConstructTrie.tst;
		int[][] array = InvertedIndex.array;

		Integer[] myList = new Integer[thisFile.length];
		ArrayList<File> myFiles = new ArrayList<File>();

		for (int i = 0; i < thisFile.length; i++)
			myList[i] = 0;

		for (int k = 0; k < searched.length; k++) {

			if (tst.get(searched[k].toLowerCase()) == null) {
				// System.out.print(" No match found");
			} else {
				int indexOfArray = tst.get(searched[k].toLowerCase());
				for (int t = 0; t < thisFile.length; t++) {
		
					myList[t] = myList[t] + array[t + 1][indexOfArray];
				}
			}
		}

		ArrayList<Integer> previousList = new ArrayList<Integer>(Arrays.asList(myList));

		int it = myList.length - 11;

		for (int j = myList.length - 1; j > it; j--) {
			int rank = QuickSelectAlgorithm.select(myList, j);
			myFiles.add(thisFile[previousList.indexOf(rank)]);
			previousList.set(previousList.indexOf(rank), null);
		}
		return myFiles;
	}

}

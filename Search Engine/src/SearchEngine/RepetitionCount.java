package SearchEngine;

/**
 * 
 * @author this class is used for counting the number of occurrence of word in a
 *         web page
 */
public class RepetitionCount {
	/**
	 * 
	 * @param searched word to be searched
	 * @return frequency of word
	 */
	public static int findoccurences(String searched) {
		int wordCount = 0;
		TST<Integer> tst = ConstructTrie.tst;// it creates object of TST class
		if (tst.get(searched) == null) {
			return 0;
		} else {
			for (int i = 0; i < ConstructTrie.thisFile.length; i++) {
				wordCount += InvertedIndex.array[i + 1][ConstructTrie.tst.get(searched)];
			}

			return wordCount;
		}
	}

}

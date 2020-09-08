package SearchEngine;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
/**
 * @author 
 *this class provides the suggestions of different web pages that match the word pattern
 */
public class SearchInsteadFor {
	public static List<Entry<String, Integer>> givesuggestion(String incorrectWord) {

		Hashtable<String, Integer> hashTable = new Hashtable<String, Integer>();
		int editDistance = 0;
		for (String key : ConstructTrie.tst.keys()) {
			editDistance = Sequences.editDistance(incorrectWord.toLowerCase(), key);
			if (editDistance != 0)
				hashTable.put(key, editDistance);
		}
		List<Entry<String, Integer>> numberOfSuggestions = hashTable.entrySet().stream()
				.sorted(Comparator.comparing(Entry::getValue)).limit(3).collect(Collectors.toList());

		return numberOfSuggestions;
	}
}
package SearchEngine;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import SearchEngine.In;
/**
 * 
 * @author 
 *this class is used to assign index number to web pages
 */
public class InvertedIndex {

	public static int[][] array;
/**
 * assign index number to the web pages.
 */
	public static void createInvertedIndex() {
		File[] thisFile = ConstructTrie.thisFile;
		TST<Integer> tst = ConstructTrie.tst;
		array = new int[thisFile.length + 1][tst.size() + 1];

		int numberOfPages = 1;
		for (File file1 : thisFile) {
			HashMap<String, Integer> frequncy = new HashMap<String, Integer>();
			for (int i = 0; i < thisFile.length + 1; i++) {
				array[i][0] = i;
			}

			for (int j = 0; j < tst.size() + 1; j++) {
				array[0][j] = j;
			}

			if (file1.isFile()) {
				In file = new In(file1);

				String text = file.readAll();
				text = text.replaceAll("\\W", " ");
				String domain = "\\w+";
				Pattern p = Pattern.compile(domain);
				Matcher mt = p.matcher(text);
				int bt = 1;

				while (mt.find()) {
					String token = mt.group(0).toLowerCase();

					Integer counter = frequncy.get(token.toLowerCase()); // get the previous counter for this word
					if (counter == null)
						counter = 0; // if not present in map, set previous counter is zero
					frequncy.put(token, 1 + counter);
				}

				for (Entry<String, Integer> entry : frequncy.entrySet()) {
					array[0][0] = 0;
					int index = tst.get(entry.getKey());
					array[numberOfPages][index] = entry.getValue();
				}
			}
			numberOfPages++;
		}
		
	}
}

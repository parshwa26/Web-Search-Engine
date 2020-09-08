package SearchEngine;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class EditDistanceTopTenPages {
	public static HashMap<File, Integer> hashtable;

	public static List<Entry<File, Integer>> CalculateEditDistance(String keywords[]) {

		hashtable = new HashMap<File, Integer>();
		File[] myfile = ConstructTrie.thisFile;
		
		for (File inputfile : myfile) {
			In file = new In(inputfile);
			String ReadAllText = file.readAll();
			int Small_Val = 0;
			for (int i = 0; i < keywords.length; i++) {
				int m = 0;
				StringTokenizer s = new StringTokenizer(ReadAllText, " -.;, ()\n\t");
				Integer[] dist = new Integer[s.countTokens()];
				
				////Tests if there are more tokens available from this tokenizer's string
				while (s.hasMoreTokens()) { 
					String token = s.nextToken();
					dist[m] = Sequences.editDistance(keywords[i], token);
					m++;
				}
				Integer[] inp = new Integer[10];

				for (int j = 0; j < 10; j++) {
					inp[j] = QuickSelectAlgorithm.select(dist, j);
				}
				for (int j = 0; j < 10; j++) {
					if(inp[j]==null)
						inp[j]=1000000;
				}
				for (int j = 0; j < 5; j++) {
					Small_Val = Small_Val + inp[j];
				}
			}
			//Associates the Smallest value with the specified key in this map 
			hashtable.put(inputfile, Small_Val);
		}
		List<Entry<File, Integer>> LowestTenValues = hashtable.entrySet().stream()
				.sorted(Comparator.comparing(Entry::getValue)).limit(10).collect(Collectors.toList());
		
		return LowestTenValues;
	}
}

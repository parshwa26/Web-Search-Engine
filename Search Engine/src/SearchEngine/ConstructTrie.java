package SearchEngine;

import SearchEngine.In;
import SearchEngine.QuickSelectAlgorithm;
import SearchEngine.TST;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/** 
 * @author 
 *this java file is used to create TST and insert the values into generated tree
 */
public class ConstructTrie {

	public static TST<Integer> tst;
	public static File[] thisFile;
/**
 * this method match the pattern and then get the value and put the values into tree
 */
	public static void createTST() {

		tst = new TST<Integer>();
		thisFile = new File("Input_Folder").listFiles();
		int i = 1;
		for (File ft : thisFile) {
			if (ft.isFile()) {
				In file = new In(ft);
				String text = file.readAll();
				text = text.replaceAll("\\W", " ");
				String domain = "\\w+";
				Pattern p = Pattern.compile(domain);
				Matcher mt = p.matcher(text);//match pattern and word 

				while (mt.find()) {
					String token = mt.group(0).toLowerCase();
					if (tst.get(token) == null) {
						tst.put(token, i);
						i++;
					}
				}
			}
		}
	}
}

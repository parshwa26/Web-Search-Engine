package SearchEngine;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;

public class SearchUsingKeywords {
	
	public static void main(String args[]) {
		
		System.out.println("Enter word: ");
		Scanner inp = new Scanner(System.in);
		String key=inp.nextLine();	
		String[] search_inp = key.split("\\s+");
		long start_time,end_time;
		
		ConstructTrie.createTST(); //Searching words in the database
		InvertedIndex.createInvertedIndex(); //Get all the occurrence for the search_inp word
		
		int fl=1;
		System.out.print("\nWord     |  Occurence");
		System.out.print("\n-------------------");
		for(int m=0; m<search_inp.length;m++) 
		{
			int wordoccurrence=RepetitionCount.findoccurences(search_inp[m].toLowerCase());
			if(wordoccurrence==0) 
			{
				fl=0;
				List<Entry<String, Integer>> suggessions = SearchInsteadFor.givesuggestion(search_inp[m]);
				System.out.print("\n"+search_inp[m]+"  :   "+RepetitionCount.findoccurences(search_inp[m].toLowerCase())+"   (Try: ");
				for(int k=0;k<suggessions.size();k++) 
					 System.out.print(suggessions.get(k).getKey()+", ");
				System.out.print("...)");
			}
			else
				System.out.print("\n"+search_inp[m]+"  :   "+RepetitionCount.findoccurences(search_inp[m].toLowerCase()));	
		}
		
		System.out.println("\n\nResults can be found in the below text files:");		
		if(fl==0)  
		{
			start_time=System.currentTimeMillis();
			List<Entry<File, Integer>> topTenPagesByEditDistance=EditDistanceTopTenPages.CalculateEditDistance(search_inp);			 
			end_time=System.currentTimeMillis();
			for(int n=0;n<topTenPagesByEditDistance.size();n++) 
				System.out.println(topTenPagesByEditDistance.get(n).getKey()); 
			System.out.println("\nTime Taken to complete the Task - "+(end_time-start_time)+" MilliSeconds");
		}
		else if (fl!=0) 
		{
			start_time=System.currentTimeMillis();
			ArrayList<File> files=RepetitionCountTopTenPages.tenBestPages(search_inp);
			end_time=System.currentTimeMillis();
			for (File f : files) { System.out.println(f); }
			System.out.println("\nTime Taken to complete the Task - "+(end_time-start_time)+" MilliSeconds");
		}	
	}

}

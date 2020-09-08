package SearchEngine;

import java.io.*;
import java.util.ArrayList;
import org.jsoup.Jsoup;


/**
 * This Class converts the accesses the various URLs and converts
 * them to corresponding text files.
 * Which are used for searching and page ranking.
 */


public class URL_To_Text {

	public static void main(String[] args
			) {
		
		ArrayList<String> urlList = new ArrayList<>();
		
		//Use URL of own PC while running on local computer
		In in = new In("C:\\Users\\Mitul Pranjay\\eclipse-workspace\\ACC_Final_Project\\websites.txt");
		
        while (!in.isEmpty()) {
        	
        	String myText = in.readLine();
        	//System.out.println(myText);
        	urlList.add(myText);
        	    	
        }
        
        
        for(int i = 0; i < urlList.size(); i++) {
        	
        	try {
        		org.jsoup.nodes.Document doc = Jsoup.connect(urlList.get(i)).get();
        		String text = doc.text();
            	String FilePath = "C:\\Users\\Mitul Pranjay\\Desktop\\Advanced Computing Concepts\\ACC_Final\\Input_Folder\\" + (i)+".txt" ;
            	PrintWriter out = new PrintWriter(FilePath);
        		out.println(urlList.get(i));
            	out.println(text);
        		System.out.println(urlList.get(i));
        		out.close();
        		
        		
        	}catch(Exception e){
        		
        		System.out.println("Exception, Cannot be converted to text: "+ urlList.get(i));
        	}
        	
        
        }
	}
}

package com.word.wordfrequency;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WordfrequencyApplication {

	public static void main(String[] args) {
		final Set<String> wordsToRemove = new HashSet<>(Arrays.asList(
                "a", "an", "the", "and", "or", "but", "in", "on", "at", "of", "for", "with", "to", "from",
                "by", "he", "she", "it", "they", "them", "his", "her", "him", "its", "i", "you", "we", "us", "me", "my",
                "mine", "your", "yours", "this", "that", "these", "those", "as", "so", "do", "does", "did",
                "is", "was", "are", "were", "been", "being", "be", "out", "not", "nor", "between", "with", "about", "against",
                "into", "onto", "undeer", "over", "behind", "to", "into", "onto", "across", "through", "at", "since", "for", 
                "because of", "due to", "by", "with", "via", "had", "there", "where", "all", "have", "had", "then",
                "now", "which", "what" , "their", "when", "some", "upon"
             ));
		SpringApplication.run(WordfrequencyApplication.class, args);
		
		 try 
		  {
		        URI uri=URI.create("https://courses.cs.washington.edu/courses/cse390c/22sp/lectures/moby.txt");
		        URL url=uri.toURL();
		        BufferedReader reader= new BufferedReader(new InputStreamReader(url.openStream()));
		        Map<String,Integer> WordFrequency = new HashMap<>();
		        Set<String> uniqueWords = new TreeSet<>();
		        String line;int count =0, count1=0; String[] words = {};
		        while((line = reader.readLine() )!= null) {
		        line = line.replaceAll("[^a-zA-Z]"," ").toLowerCase();
		        words = line.split("\\s+");
		        for(String word : words) {	 
		            if(!wordsToRemove.contains(word)  && !word.contains(" ") && word.length() > 1)
		             {
		               count++;
		               WordFrequency.put(word,WordFrequency.getOrDefault(word,0)+1);
                      uniqueWords.add(word);
		             }
		           }
		        }
	            System.out.println("wordcount = " +count);
	            List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(WordFrequency.entrySet());
		        Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
		            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
		                return e2.getValue().compareTo(e1.getValue()); // descending order
		            }
		        });
		         
		        System.out.println("Top 5 frequent words:");
		        for(int i = 0; i < 5; i++) {
		           Map.Entry<String, Integer> entry = sortedList.get(i);
		           System.out.println(entry.getKey() + ": " + entry.getValue());
		        }  
		         
	        	System.out.print("unique words : ");
		        for(String s : uniqueWords)
		        {
		         System.out.print(s+ ", ");
		         count1++;
		         if(count1 == 50)
		         break;
		        }
         }
		  catch(Exception e)
		  {
			System.out.println(e);
		  }
	}

}

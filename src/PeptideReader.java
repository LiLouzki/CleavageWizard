import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reads peptides file (Max Quant) and creates a map with experiment name/intensity and lists 
 * with name, id etc. of peptide.
 * @author Lisa
 *
 */
public class PeptideReader {
	public static List<Peptide> read (String path) throws IOException{
		
		  List<String> st ;
	      BufferedReader bufRead = new BufferedReader(new FileReader(path+"\\peptides.txt"));
	      
	      Map<String, Integer> expNameColumnMap = new HashMap<String, Integer>();
	      int integerStream;
	      int intensityColumnIndex = 0;
	      int sequenceColumnIndex = 0;
	      int aaBeforeColumnIndex = 0;
	      int aaLastColumnIndex = 0;
	      int startPositionColumnIndex = 0;
	      int endPositionColumnIndex = 0;
	      int peptideIntensitiesColumnIndex = 0;
	      int peptideIDsColumnIndex = 0;
	   
	      String fileLine = "";
	      List<Map<String, String>> peptideMapList = new ArrayList<Map<String, String>>();
	      List<String>peptideIDs = new ArrayList<String>();
	      List<String>peptideSequences = new ArrayList<String>();
	      List<String>aaBefores = new ArrayList<String>();
	      List<String>aaLasts = new ArrayList<String>();
	      List<String>peptideIntensities = new ArrayList<String>();
	
	      List<String>startPositions = new ArrayList<String>();
	      List<String>endPositions = new ArrayList<String>();
	      
	      
	      String peptideStream = bufRead.readLine(); // Read first line.
	      
	
	      st = Arrays.asList(peptideStream.split("\t"));
	          
	      int counter = 0;
	          
	      for (String content : st){
	          	
	      // Create map with name of experiment and index of column.
		        if (content.contains("Intensity ")){
		          	intensityColumnIndex = counter;
		          	String[] spaceSplit = content.split(" ");
			        String expName = spaceSplit[1];
			        expNameColumnMap.put(expName, intensityColumnIndex);
		        }
	          	else if (content.matches("Sequence")){ 
	          		sequenceColumnIndex = counter;
	          	}
	          	else if (content.matches("Amino acid before")){ 
	          		aaBeforeColumnIndex = counter;
	          	}
	          	else if (content.matches("Last amino acid")){ 
	          		aaLastColumnIndex = counter;
	          	}
	          	else if (content.matches("Start position")){ 
	          		startPositionColumnIndex = counter;
	          	}
	          	else if (content.matches("Amino acid before")){ 
	          		aaBeforeColumnIndex = counter;
	          	}
	          	else if (content.matches("End position")){ 
	          		endPositionColumnIndex = counter;
	          	}
	          	else if (content.matches("Intensity")){ 
	          		peptideIntensitiesColumnIndex = counter;
	          	}
	          	else if (content.matches("id")){ 
	          		peptideIDsColumnIndex = counter;
	          	}
	          
		          counter++;
		   }
	      
	       while (( integerStream = bufRead.read()) != -1) //Read proteome file until the end.
	       {
	    	   String peptideFileString = String.valueOf((char) integerStream);// translate ints to chars
	    	   if (peptideFileString.equals("\n") & fileLine.length() != 0){
	      		
	    		   String[] tabSplit = fileLine.split("\t");
	    		   if (tabSplit.length > 1){
	    			   	Map<String,String> expIntensityMap = new HashMap<String,String>();
	    			   	for (String key : expNameColumnMap.keySet()) {
	    			   		String finalIntensity = tabSplit[expNameColumnMap.get(key)];
	    			   		expIntensityMap.put(key, finalIntensity);
	    			   	}
		    			   
			      		peptideMapList.add(expIntensityMap);
			      		 
			      		
			        	
			      		peptideIDs.add(tabSplit[peptideIDsColumnIndex]);
			      		// if sequence substring only contains word characters add it to respective list
			      		if ((tabSplit[sequenceColumnIndex].replace("\n", "")).matches("^[-\\w.]+")){
			      			peptideSequences.add(tabSplit[sequenceColumnIndex].replace("\n", ""));
			      		}else{
			      			//replace all (Unicode) characters that are neither letters nor (decimal) digits
			      			peptideSequences.add(tabSplit[sequenceColumnIndex].replace("\n", "").replaceAll("[^\\p{L}\\p{Nd}]+", ""));
			      		}
			      	
			      		aaBefores.add(tabSplit[aaBeforeColumnIndex]);
			      		aaLasts.add(tabSplit[aaLastColumnIndex]);
			      		startPositions.add(tabSplit[startPositionColumnIndex]);
			      		endPositions.add(tabSplit[endPositionColumnIndex]);
			      		peptideIntensities.add(tabSplit[peptideIntensitiesColumnIndex]);
			      		
				        fileLine = "";
	    		   }
	    	   }
	    	   
	    	   fileLine += peptideFileString;
		}
	    bufRead.close();
	    
	    
		List<Peptide> peptides = new ArrayList<>();
		int i;
	  	for (i = 0; i< peptideIDs.size(); i++){
	  		Peptide p = new Peptide (peptideIDs.get(i), peptideSequences.get(i), aaBefores.get(i), aaLasts.get(i), startPositions.get(i), endPositions.get(i), peptideIntensities.get(i), peptideMapList.get(i));
	  		peptides.add(p);
	  	} 
	  	
	  	return peptides;
	}
	
}
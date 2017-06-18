import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reads proteinGroups file (Max Quant) and creates a map with experiment name/intensity and lists 
 * with name, id etc.of protein.
 * @author Lisa
 *
 */
public class ProteinReader {
	public static List<Protein> read (String path) throws IOException{
		  List<String> st ;
	      BufferedReader bufRead = new BufferedReader(new FileReader(path+"\\proteinGroups.txt"));
	      int integerStream;
	      int proteinIDsColumnIndex = -1;
	      int proteinNamesColumnIndex = -1;
	      int geneNamesColumnIndex = -1;
	      int peptideIDsColumnIndex = -1;
	      int counter = 0;
	      
	      String fileLine = "";
	      List<String>proteinIDs = new ArrayList<String>();
	      List<String>proteinNames = new ArrayList<String>();
	      List<String>geneNames = new ArrayList<String>();
	      List<String>peptideIDs = new ArrayList<String>();
	      
	      
	      String proteinStream = bufRead.readLine();//Read first line.


          st = Arrays.asList(proteinStream.split("\t"));
          
          
          
          for (String content : st){
        	 
	          	if (content.matches("Protein IDs")){ 
	          		proteinIDsColumnIndex = counter;
	          	}
	          	else if (content.matches("Protein names")){ 
	          		proteinNamesColumnIndex = counter;
	          	}
	          	else if (content.matches("Gene names")){ 
	          		geneNamesColumnIndex = counter;
	          		
	          	}
	          	else if (content.matches("Peptide IDs")){ 
	          		peptideIDsColumnIndex = counter;
	          	}
		            counter++;
		            
          }
	           
	      while (( integerStream = bufRead.read()) != -1) //Read proteome file until the end.
	      {
	      	String proteinFileString = String.valueOf((char) integerStream);// translate ints to chars
	      	
	      	if (proteinFileString.equals("\n") & fileLine.length() != 0){
	      		String[] tabSplit = fileLine.split("\t");
	      		
	      			if (tabSplit.length > 1){
			      		proteinIDs.add(tabSplit[proteinIDsColumnIndex].replace("\n", ""));
			      		geneNames.add(tabSplit[geneNamesColumnIndex].replace("\n", ""));
			      		peptideIDs.add(tabSplit[peptideIDsColumnIndex]);
			      		proteinNames.add(tabSplit[proteinNamesColumnIndex]);
				        fileLine = "";
	      			}
	      	}
	      	fileLine += proteinFileString;
	      }
		
	    bufRead.close();
	    List<Protein> proteins = new ArrayList<>();
	    //For Index i makes new Protein p containing name, ID and gene name and adds p to list proteins
	    int i;
		for (i = 0; i< proteinIDs.size(); i++){
			Protein p = new Protein( proteinIDs.get(i), proteinNames.get(i), geneNames.get(i),peptideIDs.get(i));
			proteins.add(p);
				
	  	} 
  	 	return proteins;
	}
}
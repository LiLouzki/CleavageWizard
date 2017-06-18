import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Reads fasta file and extracts sequence and id.
 * @return map containing id and sequence
 * @author Lisa
 *
 */
public class FastaReader {
	public static Map<String,String> read (String fastaPath) throws IOException{
		// reads input line, reads Fasta file creating a map with protein names / sequences and compares in+out to print out protein sequence
			
		
		
	        BufferedReader br = new BufferedReader (new FileReader(fastaPath));
	        int c;
	        String as = "";
	        Map<String, String> proteinIdSequenceMap = new HashMap<String,String>();// create ArrayList

	        while (( c = br.read()) != -1) //read proteome file until the end
	        {
	        	String myc = String.valueOf((char) c);// translate ints to chars
	        	if (myc.equals(">") & as.length() != 0){//reads protein line
	        		
	        		
	        			//splits protein line and puts names and sequences into map
	        			String[] spaceSplit = as.split(" ");
	        			String spaceSplit1 = spaceSplit[0];
	        			String[] verlineSplit = spaceSplit1.split("\\W");
	        			String finalProteinID = verlineSplit[2];
	        			
	        			
	        			//removes new line characters and numbers
	        			String noNewline = as.replace("\n", "");
	        			String[] noNewLineSplit = noNewline.split("\\W");
	        			String finalSeq = noNewLineSplit[noNewLineSplit.length - 1].replaceAll("[0-9]", "");
	        	
	        		  	
	        		proteinIdSequenceMap.put(finalProteinID, finalSeq);
	        		as = "";

	        		}
	        	as += myc;// elongates string as by char stream of buffered reader
	        	}
	        br.close();

		return proteinIdSequenceMap;
	}
}
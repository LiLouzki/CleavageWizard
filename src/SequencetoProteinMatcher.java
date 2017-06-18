import java.util.List;
import java.util.Map;

/**
 * Matches the sequence to the protein using the id of the protein.
 * @author Lisa
 *
 */
public class SequencetoProteinMatcher {

	public static void matchStP (List <Protein> proteins, Map<String, String> map){
		
			for (Protein protein : proteins){
			String id = protein.getproteinID();// for each protein in proteins get proteinID
			String sequence = map.get(id);
			
			protein.setSequence(sequence);
			}
		
	}

}
import java.util.Arrays;
import java.util.List;
/**
 * Matches peptides to proteins using the id of the peptide.
 * @author Lisa
 *
 */
public class PeptidetoProteinMatcher {
	//matches proteins and peptides lists via peptide id
	public static void match(List<Protein> proteins, List<Peptide>peptides){
		
		for (Protein proteinX : proteins){
			String pepIds = proteinX.getPeptideIDs();// peptideIds as string in Protein getter
			List<String> idList = Arrays.asList(pepIds.split(";"));// creates list with string peptideIds, e.g. "234;25;4;89;"
			for (String id : idList){
				proteinX.addPeptide(peptides.get(Integer.parseInt(id)));// addpeptide object in protein class adds the peptide of ID id to peptides
			}
		}

	}
	
}
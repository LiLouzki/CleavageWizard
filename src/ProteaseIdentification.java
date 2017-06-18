import java.util.List;

/**
 * Sets n/c terminal proteases of peptides using the amino acid before and the last amino acid of the peptide.
 * @author Lisa
 *
 */

public class ProteaseIdentification {
	public static void matchProtease(List<Peptide> peptides ){

		EnzymeMapFactory nTerminalfactory = new EnzymeMapFactory();// the map with protein name and cleavage site is filled in factory
		for (Peptide peptide : peptides){
			for (String enzymeName : nTerminalfactory.getEnzymeNames()){// For every enzyme in this map
				List<String> aminoAcids = nTerminalfactory.createenzymeList( enzymeName);//make a list of cleavage sites
					for (String aminoAcid : aminoAcids){// For every cleavage site in the list
					
						if (aminoAcid.equals(peptide.getaaBefore())){//look if aabefore is contained
						peptide.setnTerminalProtease(enzymeName);
						}
					}
			}
		}
		EnzymeMapFactory cTerminalfactory = new EnzymeMapFactory();// the map with protein name and cleavage site is filled in factory
		for (Peptide peptide : peptides){
			for (String enzymeName : cTerminalfactory.getEnzymeNames()){// For every enzyme in this map
				List<String> aminoAcids = cTerminalfactory.createenzymeList( enzymeName);//make a list of cleavage sites
					for (String aminoAcid : aminoAcids){// For every cleavage site in the list
					
						if (aminoAcid.equals(peptide.getaaLast())){//look if aabefore is contained
						
						peptide.setcTerminalProtease(enzymeName);
						}
					}
			}
		}
	}
}
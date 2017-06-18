import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Protein class containing all objects used in protein table, their StringProperties,
 * the cleavages object and a protein list (filled in ProteinReader).
 * @author Lisa
 *
 */
//Constructor with proteinID , name, gene name as StringProperty
public class Protein {
	
		private String peptideIDs;
		private List<Peptide> peptides = new ArrayList<>();
		private StringProperty proteinID = new SimpleStringProperty();
		private StringProperty proteinName = new SimpleStringProperty();
		private StringProperty geneName = new SimpleStringProperty();
		private StringProperty sequence = new SimpleStringProperty();
		private Cleavages cleavages = null;
		public Protein(String proteinID, String proteinName, String geneName, String peptideIDs){
	
			this.proteinID.set(proteinID);
			this.proteinName.set(proteinName);
			this.geneName.set(geneName);
			this.peptideIDs = peptideIDs;
			}
		
		public Cleavages getCleavages(){
			return cleavages;
		}
		
		public void setCleavages(Cleavages cleavages) {
			this.cleavages = cleavages;
			
		}
	
		public String getPeptideIDs() {
			return peptideIDs;
		}
	
		public void setPeptideIDs(String peptideIDs) {
			this.peptideIDs = peptideIDs;
			
		}
	
		public List<Peptide> getPeptides(){
		return peptides;
		}
	
		public void addPeptide(Peptide peptide){//object that adds peptide lists to peptides
			peptides.add(peptide);
		}
	
		public StringProperty proteinIDsProperty() {
			return proteinID;
		}	
	
		public String getproteinID () {
			return proteinID.get();
		}
	
		public StringProperty proteinNamesProperty() {
			return proteinName;
		}	
		
		public String getproteinName () {
			return proteinName.get();
		}
		
		public StringProperty geneNamesProperty() {
			return geneName;
		}	
		
		public String getgeneName () {
			return geneName.get();
		}
		
		public StringProperty sequencesProperty() {
			return sequence;
		}	
		
		public String getsequence() {
			return sequence.get();
		}
	
		public void setSequence(String sequence) {
			this.sequence.set(sequence);
			
		}

}
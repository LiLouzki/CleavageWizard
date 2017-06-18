import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Peptide class containing all objects used in peptide table, their StringProperties 
 * a map with experiment and intensity and a peptides list (filled in PeptideReader).
 * @author Lisa
 *
 */
//Constructor with peptideID , name, gene name as StringProperty
public class Peptide {

	private StringProperty peptideID = new SimpleStringProperty();
	private StringProperty peptideSequence = new SimpleStringProperty();
	private StringProperty aaBefore = new SimpleStringProperty();
	private StringProperty aaLast = new SimpleStringProperty();;
	private StringProperty peptideIntensity = new SimpleStringProperty();
	private StringProperty nTerminalProtease = new SimpleStringProperty();
	private StringProperty cTerminalProtease = new SimpleStringProperty();
	private String startPosition;
	private String endPosition;
	private Map<String,String> expIntensityMap = new HashMap<String,String>();

	public Peptide(String peptideID, String peptideSequence, String aaBefore, String aaLast, String startPosition, String endPosition, String peptideIntensity, 
			Map<String, String> expIntensityMap){

		this.peptideID.set(peptideID);
		this.peptideSequence.set(peptideSequence);
		this.aaBefore.set(aaBefore);
		this.aaLast.set(aaLast);
		this.peptideIntensity.set(peptideIntensity);
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.expIntensityMap= expIntensityMap;
	}
	
	public Map<String, String> getExpIntensityMap() {
		return expIntensityMap;
	}

	public String getStartPositions(){
		return startPosition;
	}
	public String getEndPositions(){
		return endPosition;
	}
	
	public StringProperty peptideIDsProperty() {
		return peptideID;
	}	
	public String getpeptideID () {
		return peptideID.get();
	}
	
	public StringProperty peptideSequencesProperty() {
		return peptideSequence;
	}	
	public String getpeptideSequence () {
		return peptideSequence.get();
	}
	
	public StringProperty aaBeforesProperty() {
		return aaBefore;
	}	
	
	public String getaaBefore () {
		return aaBefore.get();
	}
	
	public StringProperty aaLastsProperty() {
		return aaLast;
	}	
	
	public String getaaLast () {
		return aaLast.get();
	}
	
	public StringProperty peptideIntensitiesProperty(){
		return peptideIntensity;
	}
	public String getPeptideIntensity(){
		return peptideIntensity.get();
	}
	
	public StringProperty cTerminalProteasesProperty(){
		return cTerminalProtease;
	}
	public String getcTerminalProtease(){
		return cTerminalProtease.get();
	}
		
	public StringProperty nTerminalProteasesProperty(){
		return nTerminalProtease;
	}
	
	public String getnTerminalProtease(){
		return nTerminalProtease.get();
	}

	public void setcTerminalProtease(String cTerminalProtease) {
		this.cTerminalProtease.set(cTerminalProtease);
	}
		
	public void setnTerminalProtease(String nTerminalProtease) {
		this.nTerminalProtease.set(nTerminalProtease);
	}
		
}
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Input: Position, n-/c-terminal protease, overall intensity, intensities of respective experiments
 * 3 maps consisting of: position and overall intensity, 
 * position and protease, position and map containing experiment name and intensities
 * and one set containing all experiment names.
 */
public class Cleavages {
	
		private Map<Integer, Long> cleavagesIntensityMap = new HashMap<Integer, Long>();
		private Map<Integer, String> cleavagesProteaseMap = new HashMap<Integer, String>();
		private Map<Integer, Map<String, String>> positionIntensitiesMap = new HashMap<Integer, Map<String, String>>();
		private Set<String> experimentSet;
		
		public Set<String> getExperimentSet() {
			return experimentSet;
		}
		
		public Map<Integer, Long> getCleavagesIntensityMap() {
			return cleavagesIntensityMap;
		}
		
		public void setCleavagesIntensityMap(Map<Integer, Long> cleavagesIntensityMap) {
			this.cleavagesIntensityMap = cleavagesIntensityMap;
		}
		
		public Map<Integer, String> getCleavagesProteaseMap() {
			return cleavagesProteaseMap;
		}
		
		public void setCleavagesProteaseMap(Map<Integer, String> cleavagesProteaseMap) {
			this.cleavagesProteaseMap = cleavagesProteaseMap;
		}
		
		public void setPositionIntensityMap (Map<Integer, Map<String,String>> positionIntensitiesMap){
			this.positionIntensitiesMap = positionIntensitiesMap;
		}
		
		public Map<Integer, Map<String, String>> getPositionIntensitiesMap() {
			return positionIntensitiesMap;
		}
		
		public void setCleavagesMaps(int position, Long intensity, String terminalProtease,
				Map<String, String> expIntensityMap, int proteinlength){
			
				Map<String, String> expIntensityMapCopy = new HashMap<>();
				
				expIntensityMapCopy.clear();
				expIntensityMapCopy.putAll(expIntensityMap);
				
				experimentSet = expIntensityMapCopy.keySet();
				//if (position != proteinlength){
					
						if (cleavagesIntensityMap.containsKey(position)){
							long intensitySum = intensity + cleavagesIntensityMap.get(position);
							cleavagesIntensityMap.put(position, intensitySum);
						}else{
							cleavagesIntensityMap.put(position, intensity);
						}
						
						if (!cleavagesProteaseMap.containsKey(position)){
							cleavagesProteaseMap.put(position, terminalProtease);
						}else{
							cleavagesProteaseMap.put(position, terminalProtease);
						}
				
				Map <String, String> expIntensityMapCopyCleavages = new HashMap<String, String>();
				expIntensityMapCopyCleavages = positionIntensitiesMap.get(position);
				
				if (positionIntensitiesMap.containsKey(position)){
					
					for (String experiment : expIntensityMapCopy.keySet()){
						
							long expIntensitySum = Long.parseLong(expIntensityMapCopy.get(experiment)) + 
									Long.parseLong(expIntensityMapCopyCleavages.get(experiment));
							expIntensityMapCopyCleavages.replace(experiment, String.valueOf(expIntensitySum));
						
					}	
				}else{	
					positionIntensitiesMap.put(position, expIntensityMapCopy);
						
				}
			
		}
			
}
	


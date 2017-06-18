import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates maps containing a protease and the corresponding amino acids at cleavage sites 
 * and a list containing the proteases.
 * @author Lisa
 *
 */
public class EnzymeMapFactory {
	
	private static Map<String, List<String>> map = new HashMap<String, List<String>>();
	public EnzymeMapFactory(){

		List<String> resSitesTrypsin = new ArrayList<String>();
		List<String> resSitesElastase = new ArrayList<String>();
		
		resSitesTrypsin = Arrays.asList("K", "R");
		resSitesElastase = Arrays.asList("A", "V", "S", "G", "I", "L");
		map.put("Trypsin", resSitesTrypsin);
		map.put("Elastase",resSitesElastase);
	}

	public List<String> createenzymeList(String enzymeName){
		return	map.get(enzymeName);
	}
		
	public static List<String> getEnzymeNames(){
		List<String> newList = new ArrayList<String>();
		newList.addAll(map.keySet());// a newList must be created as map.keySet is a Set 
		return newList;
	}
		
}
	

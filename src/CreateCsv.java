import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 * Writes items of result table into a CSV file.
 * @author Lisa
 *
 */
	 
public class CreateCsv{
	public static void Createcsv(ObservableList<ObservableList<String>> observableList, Cleavages cleavages, String filepath) { 
	
	  try{  
		  
		  FileWriter fw = new FileWriter(filepath);
		  List <String> experiments = new ArrayList<>();
		  experiments.addAll(cleavages.getExperimentSet());
	
		  fw.append("position"+'\t');
		  for (int i = 0; i<  experiments.size(); i++)
		  {
			  fw.append(experiments.get(i));
			  fw.append('\t');
			  
			  
		  }
		  fw.append('\n');
		  for (ObservableList<String> innerObservable : observableList){
			  for (int i = 0; i<  innerObservable.size(); i++)
			  {
				  fw.append(innerObservable.get(i));
				  fw.append('\t');
				
				 
			  }
			  fw.append('\n');
		  }
		  fw.flush();
		  fw.close();
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	} 
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * Delivers the data for the bottom panes including the 2 linecharts and the result table.
 * Creates a list with amino acids for the sequence view.
 * @author Lisa
 *
 */
public class FillBottomPanes {
	
	public static Map<Integer, Map<String, String>> newPositionIntensitiesMap = new HashMap<Integer, Map<String, String>>();
	
	public static LineChart<Number, Number> getLineChart(Cleavages cleavages, int proteinLength) {
		
		NumberAxis xAxis = new NumberAxis();
	    NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Position");
		xAxis.setTickLabelFill(Color.CHOCOLATE);
		yAxis.setTickLabelFill(Color.CHOCOLATE);
		yAxis.setTickLabelsVisible(false);
		
	    //create new chart
	    LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
	    lineChart.getData().clear();
	    lineChart.setData(FXCollections.observableArrayList(getLineChart1Data(cleavages, proteinLength)));
	   
	    // set length of xAxis to the length of available datapoints
	    xAxis.setAutoRanging(false);
	    xAxis.setLowerBound(0);
	    xAxis.setUpperBound(proteinLength);
	    xAxis.setTickUnit(10);
	    // remove spacing on the right of linechart
	    lineChart.setTranslateX(xAxis.getWidth());
	    		
		return lineChart;
			
	}
	
	private static List<XYChart.Series<Number, Number>> getLineChart1Data(Cleavages cleavages, int proteinLength) {
		List<XYChart.Series<Number, Number>> seriesList = new ArrayList<XYChart.Series<Number, Number>>();
		
		for (String experiment : cleavages.getExperimentSet()){
				//create series
				XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
				series.setName(experiment);
				
			for (int position = 0; position < proteinLength; position++){
				
				long intensity = 0;
				
				if (cleavages.getPositionIntensitiesMap().containsKey(position)){
				intensity = Long.parseLong(cleavages.getPositionIntensitiesMap().get(position).get(experiment));}
				
				//create datapoint
				XYChart.Data<Number, Number> datapoint = new XYChart.Data<Number, Number>(position, intensity);
				//add data to series
				series.getData().add(datapoint);
				
			}
			
			seriesList.add(series); 
		}
		
		return seriesList;

	}
		
	public static List<XYChart.Series<Number, Number>> getLineChart2Data(Cleavages cleavages, String enzyme) {
		List<XYChart.Series<Number, Number>> seriesList = new ArrayList<XYChart.Series<Number, Number>>();
		newPositionIntensitiesMap.clear();
				//add cleavage positions that are cleaved by chosen protease
				for (Map.Entry<Integer, String> entry : cleavages.getCleavagesProteaseMap().entrySet())
				{
					if (entry.getValue() == enzyme){ 
						newPositionIntensitiesMap.put(entry.getKey(), cleavages.getPositionIntensitiesMap().get(entry.getKey()));
					}
				}
				
				for (String experiment : cleavages.getExperimentSet()){
						//create series
						XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
						series.setName(experiment);
						
					for (Integer cleavagePosition: newPositionIntensitiesMap.keySet()){
						long intensity = 0;
						if (newPositionIntensitiesMap.containsKey(cleavagePosition))
						{
							intensity = Long.parseLong(newPositionIntensitiesMap.get(cleavagePosition).get(experiment));
						}
						//create datapoint
						XYChart.Data<Number, Number> datapoint = new XYChart.Data<Number, Number>(cleavagePosition, intensity);
						//add data to series
						series.getData().add(datapoint);
					
					}
				
					seriesList.add(series); 
					
				}
		return seriesList;
	} 

	@SuppressWarnings("unchecked")
	public static void FillBottomTable(Cleavages cleavages, TableView <ObservableList<String>>table3){
		
		//create experiment list
		List<String> columns = new ArrayList<String>();
		columns.addAll(cleavages.getExperimentSet());
		
			// For each experiment create tableColumn
	        TableColumn<ObservableList<String>, String> positionCol = new TableColumn<ObservableList<String>, String>("Position");
	        positionCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList<String>,String>,ObservableValue<String>>(){                   
			public ObservableValue<String> call(CellDataFeatures<ObservableList<String>, String> param) {                                                                                             
	                return new SimpleStringProperty(param.getValue().get(0).toString());                       
	           }                   
	        });
	        
	        table3.getColumns().addAll(positionCol);
		    for (int i=1 ; i<columns.size() + 1; i++) {
		        final int j = i;
		        TableColumn<ObservableList<String>, String> col = new TableColumn<ObservableList<String>, String>(columns.get(i-1));
		        col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList<String>,String>,ObservableValue<String>>(){                   
		        	public ObservableValue<String> call(CellDataFeatures<ObservableList<String>, String> param) {                                                                                             
		                return new SimpleStringProperty(param.getValue().get(j).toString());                       
		           }                   
		        });
		        table3.getColumns().addAll(col);
		    }  
		    
			// for each position/row create observableList
			for (Integer position : newPositionIntensitiesMap.keySet()){
				ObservableList<String> row = FXCollections.observableArrayList();
			    row.add(String.valueOf(position));
			    //for each experiment add intensity to respective observableList at respective index
				for (String experiment : newPositionIntensitiesMap.get(position).keySet()){
					row.addAll(newPositionIntensitiesMap.get(position).get(experiment));
				}    
				//add row to table
				table3.getItems().add(row);
				}
		
	}
		
	public static List<String> getAAList(Protein selectedProtein){
		List<String> aaList = new ArrayList<String>();
		for (int i = 0; i < selectedProtein.getsequence().length(); i++){
    		String aminoAcid = String.valueOf(selectedProtein.getsequence().charAt(i));
			aaList.add(aminoAcid);
		}
		return aaList;
		
	}
}
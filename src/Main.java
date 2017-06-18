import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Creates stage (primarystage), sets the title and graphical properties.
 * Loads font awesome.
 * @author Lisa
 *
 */
public class Main extends Application{

		Stage window;
		static GraphicsContext gc;
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	    	
	    	window = primaryStage;
	       // Add title
	    	window.setTitle("CleavageWizard");
	    	Canvas canvas = new Canvas();
			gc = canvas.getGraphicsContext2D();
		
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			primaryStage.setX(bounds.getMinX());
			primaryStage.setY(bounds.getMinY());
			primaryStage.setWidth(bounds.getWidth());
			primaryStage.setHeight(bounds.getHeight());
			
	    	Font.loadFont(Main.class.getResourceAsStream("/font/fontawesome-webfont.ttf"), 10);
	    	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
	    	loader.setResources(ResourceBundle.getBundle("fontawesome"));
	    	
	    	Parent root = loader.load();
	    	
	        Scene scene = new Scene(root);
	        window.getIcons().add(new Image("analysis.png"));
	    	window.setScene(scene);
	        window.show();
	        
	        primaryStage.setOnCloseRequest(event -> {
				if (!shutdown()) {
					event.consume();
				}
			});

	    }
	
		/**
		 * User dialog asking if program can be closed.
		 * @return yes/no
		 */
		private boolean shutdown() {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Close program");
			alert.setHeaderText("CleavageWizard is going to close.");
			alert.setContentText("Are you ok with this?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			    return true;
			} else {
			    return false;}
		}
		

		public static void main(String[] args) {
	        launch(args);
	    }
	 
}
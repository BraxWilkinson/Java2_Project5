import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class JavaFX_Proj5 extends Application {
	private static ArrayList<String> mesonet = new ArrayList<String>();	// Mesonet.txt ArrayList
	private static int count=0;	// Number of items in the mesonet ArrayList
	
	@Override
	public void start(Stage applicationStage) {
		// Create a pane 
        Pane pane = new Pane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        
        // Create a label and display box for slider output
        
        // Create Hamming Distance slider
        
        
        // Create show station button
        
        // Create show station text field
        
		
		// Create a label 
        Label dropbox_label = new Label("Compare with:");
        dropbox_label.relocate(10, 400);
        // Create a combo box 
        ComboBox dropbox = new ComboBox(FXCollections.observableArrayList(mesonet)); 
        dropbox.relocate(100, 400);
        // Add dropbox and label to the pane
        pane.getChildren().add(dropbox_label);
        pane.getChildren().add(dropbox);
        
        // Create calculate HD button
        Button calcButton = new Button("Calculate HD");
        calcButton.relocate(10, 450);
        pane.getChildren().add(calcButton);
        
        
        
        // Create labels and display boxes for calculate button
        for (int i=0; i<5; i++) {
        	Label calculateLabel = new Label("Distance " + (i));
        	TextField calculateField = new TextField();
        }
        
        // Create add station button
        Button addStationButton = new Button("Add Station");
        
        // Create a scene 
        Scene scene = new Scene(pane, 625, 750); 
        // Set the scene 
        applicationStage.setScene(scene); 
        applicationStage.setTitle("Hamming Distance"); 
        applicationStage.show(); 
	}
	
	public static void read(String filename) throws IOException {
		BufferedReader rd = new BufferedReader(new FileReader("Mesonet.txt"));
		String lineRead = rd.readLine();
		
		while(lineRead != null) 
		{
			mesonet.add(lineRead);
			count++;
			
			lineRead = rd.readLine();
		}
		rd.close();
	}
	
	public static void main(String [] args) throws IOException {
		read("Mesonet.txt");
		launch(args); // Launch application
	}
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class JavaFX_Proj5 extends Application {
	private static ArrayList<String> mesonet = new ArrayList<String>();	// Mesonet.txt ArrayList
	private Integer yesCounter;
	
	@Override
	public void start(Stage applicationStage) {
		// Create a pane 
        Pane pane = new Pane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        
        // Create a label and display box for slider output
        Label HDlabel = new Label("Enter Hamming Dist:");
        HDlabel.relocate(10, 10);
        pane.getChildren().add(HDlabel);
        TextField HDfield = new TextField();
        HDfield.setEditable(false);
        HDfield.relocate(130, 8);
        pane.getChildren().add(HDfield);
        
        // Create Hamming Distance slider
        Slider slider = new Slider(1,4,1);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);
        slider.setSnapToTicks(true);
        slider.relocate(10, 35);
        pane.getChildren().add(slider);
        
        // Create show station button
        Button stidButton = new Button("Show Station");
        stidButton.relocate(10, 80);
        pane.getChildren().add(stidButton);
        
        // Set an event handler to handle button presses
        stidButton.setOnAction(new EventHandler<ActionEvent>() {
           /* Method is automatically called when an event 
              occurs (e.g, button is pressed) */
           @Override
           public void handle(ActionEvent event) {
              String userInput; 
              int hourlyWage;    
              int yearlySalary;    
              
              // Get user's wage input and calculate yearly salary
              userInput = wageField.getText();            
              hourlyWage = Integer.parseInt(userInput);
              yearlySalary = hourlyWage * 40 * 50;
              
              // Display calculated salary
              salField.setText(Integer.toString(yearlySalary));
           }
        });
        
        // Create show station text field
        TextField stidTextField = new TextField();
        stidTextField.setPrefWidth(175);
        stidTextField.setPrefHeight(275);
        stidTextField.setEditable(false);
        stidTextField.relocate(10, 110);
        pane.getChildren().add(stidTextField);
		
		// Create a label 
        Label dropbox_label = new Label("Compare with:");
        dropbox_label.relocate(10, 403);
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
        
        // Set an event handler to handle button presses
        calcButton.setOnAction(new EventHandler<ActionEvent>() {
           /* Method is automatically called when an event 
              occurs (e.g, button is pressed) */
           @Override
           public void handle(ActionEvent event) {
        	   String dropStid = dropbox.getValue().toString();
        	   int hamm;
        	   for (int i=0; i<mesonet.size(); i++) {
        		   hamm = calcHammingDist(dropStid, mesonet.get(i));
        		   if (hamm == slider.getValue()) {
        			   stidTextField.setText(mesonet.get(i));
        		   }
        	   }
           }
        });
        
        // Create labels and display boxes for calculate button
        for (int i=0; i<5; i++) {
        	Label calcLabel = new Label("Distance " + (i));
        	TextField calcField = new TextField();
        	calcLabel.relocate(20, 503 + 40*i);
        	calcField.relocate(100, 500 + 40*i);
        	pane.getChildren().add(calcLabel);
        	pane.getChildren().add(calcField);
        }
        
        // Create add station button
        Button addStationButton = new Button("Add Station");
        addStationButton.relocate(10, 700);
        pane.getChildren().add(addStationButton);
        TextField addStationField = new TextField();
        addStationField.setEditable(true);
        addStationField.relocate(100, 700);
        pane.getChildren().add(addStationField);
        
        // Set an event handler to handle button presses
        addStationButton.setOnAction(new EventHandler<ActionEvent>() {
           /* Method is automatically called when an event 
              occurs (e.g, button is pressed) */
           @Override
           public void handle(ActionEvent event) {
        	   String stationToAdd = addStationField.getText();
        	   mesonet.add(stationToAdd);
        	   try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("Mesonet.txt"));
				writer.write(stationToAdd);
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
           }
        });
        
     // Create say yes button
        Button sayYesButton = new Button("Say Yes");
        sayYesButton.relocate(400, 80);
        yesCounter = 0;
        pane.getChildren().add(sayYesButton);
        
        TextField yesTextField = new TextField();
        yesTextField.setEditable(false);
        yesTextField.relocate(400, 110);
        yesTextField.setText(Integer.toString(yesCounter));
        pane.getChildren().add(yesTextField);
        
        // Set an event handler to handle button presses
        sayYesButton.setOnAction(new EventHandler<ActionEvent>() {
        	/* Method is automatically called when an event 
			occurs (e.g, button is pressed) */
        	@Override
        	public void handle(ActionEvent event) {
        		yesTextField.setText(Integer.toString(yesCounter++));
        	}
        });
        
        // Create a scene
        Scene scene = new Scene(pane, 625, 740);
        // Set the scene
        applicationStage.setScene(scene);
        applicationStage.setTitle("Hamming Distance");
        applicationStage.show();
	}
	
	public static int calcHammingDist(String stid, String stid2) {
		int hammCount = 0;
		String firstCompare;
		String secondCompare;
		
		for (int i=0; i<4; i++) {
			firstCompare = stid.substring(i, i+1);
			secondCompare = stid2.substring(i,i+1);
			if (firstCompare.equalsIgnoreCase(secondCompare)) {
				hammCount++;
			}
		}
		return hammCount;
	}
	
	public static void read(String filename) throws IOException {
		BufferedReader rd = new BufferedReader(new FileReader("Mesonet.txt"));
		String lineRead = rd.readLine();
		
		while(lineRead != null) 
		{
			mesonet.add(lineRead);
			
			lineRead = rd.readLine();
		}
		rd.close();
	}
	
	public static void main(String [] args) throws IOException {
		read("Mesonet.txt");
		launch(args); // Launch application
	}
}
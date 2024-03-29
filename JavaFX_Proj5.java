import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @author Braxton Wilkinson
 * @date 12/5/2019
 */
public class JavaFX_Proj5 extends Application {
	private static ArrayList<String> mesonet = new ArrayList<String>();	// Mesonet.txt ArrayList
	private Integer yesCounter;
	private ComboBox<String> dropbox;
	private TextArea stidTextField;
	
	/**
	 * Program start method containing code for the JavaFX GUI
	 * @param applicationStage
	 */
	@Override
	public void start(Stage applicationStage) {
		// Create a pane 
        Pane pane = new Pane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        
        // Create a label and display box for slider output
        Label HDlabel = new Label("Enter Hamming Dist:");
        HDlabel.relocate(10, 10);
        pane.getChildren().add(HDlabel);
        TextField HDfield = new TextField("1.0");
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
        
        // Set a change listener for the slider
        slider.valueProperty().addListener(new ChangeListener<Number>() { 
        	// Method is automatically called when the slider is changed
        	@Override
			public void changed(ObservableValue <? extends Number> observable, Number oldValue, Number newValue) 
        		{ 
        			HDfield.setText(newValue.toString()); 
        		} 
        }); 
        
        // Create show station button
        Button stidButton = new Button("Show Station");
        stidButton.relocate(10, 80);
        pane.getChildren().add(stidButton);
        
        // Set an event handler to handle button presses in Show Station
        stidButton.setOnAction(new EventHandler<ActionEvent>() {
           // Method is automatically called when the button is pressed
           @Override
		public void handle(ActionEvent event) {
        	   if (dropbox.getValue()==null) {
        		   Alert alert = new Alert(AlertType.ERROR,"Please select a station from the dropbox");
        		   alert.showAndWait();
        	   } else {
        	   int sliderDist = (int)slider.getValue();
        	   String items = "";
        	   for (int i=0; i<mesonet.size(); i++) {
        		   int calcDist = calcHammingDist(mesonet.get(i), dropbox.getValue());
        		   if (sliderDist==calcDist) {
        			   items += mesonet.get(i) + "\n";
        		   }
        	   }
        	   stidTextField.setText(items);
           }
           }
        });
        
        // Create show station text field
        stidTextField = new TextArea();
        stidTextField.setPrefWidth(175);
        stidTextField.setPrefHeight(275);
        stidTextField.setEditable(false);
        stidTextField.relocate(10, 110);
        pane.getChildren().add(stidTextField);
		
		// Create a combo box label
        Label dropboxLabel = new Label("Compare with:");
        dropboxLabel.relocate(10, 403);
        // Create new combo box
        dropbox = new ComboBox<String>(FXCollections.observableArrayList(mesonet));
        dropbox.relocate(100, 400);
        // Add dropbox and label to the pane
        pane.getChildren().add(dropboxLabel);
        pane.getChildren().add(dropbox);
        
     // Create labels and display boxes for calculate button
        for (int i=0; i<5; i++) {
        	Label calcLabel = new Label("Distance " + (i));
        	calcLabel.relocate(20, 503 + 40*i);
        	pane.getChildren().add(calcLabel);
        }
        
    	TextField calcField0 = new TextField();
    	calcField0.relocate(100, 500);
    	pane.getChildren().add(calcField0);
    	TextField calcField1 = new TextField();
    	calcField1.relocate(100, 540);
    	pane.getChildren().add(calcField1);
    	TextField calcField2 = new TextField();
    	calcField2.relocate(100, 580);
    	pane.getChildren().add(calcField2);
    	TextField calcField3 = new TextField();
    	calcField3.relocate(100, 620);
    	pane.getChildren().add(calcField3);
    	TextField calcField4 = new TextField();
    	calcField4.relocate(100, 660);
    	pane.getChildren().add(calcField4);
        
        // Create calculate HD button
        Button calcButton = new Button("Calculate HD");
        calcButton.relocate(10, 450);
        pane.getChildren().add(calcButton);
        
        // Set an event handler to handle button presses in calcButton
        calcButton.setOnAction(new EventHandler<ActionEvent>() {
           // Method is automatically called when the button is pressed
           @Override
		public void handle(ActionEvent event) {
        	   int hammDist0 = calcHammingDist(0, dropbox.getValue()).size();
        	   calcField0.setText("" + hammDist0);
        	   int hammDist1 = calcHammingDist(1, dropbox.getValue()).size();
        	   calcField1.setText("" + hammDist1);
        	   int hammDist2 = calcHammingDist(2, dropbox.getValue()).size();
        	   calcField2.setText("" + hammDist2);
        	   int hammDist3 = calcHammingDist(3, dropbox.getValue()).size();
        	   calcField3.setText("" + hammDist3);
        	   int hammDist4 = calcHammingDist(4, dropbox.getValue()).size();
        	   calcField4.setText("" + hammDist4);
           }
        });
        
        // Create add station button
        Button addStationButton = new Button("Add Station");
        addStationButton.relocate(10, 700);
        pane.getChildren().add(addStationButton);
        TextField addStationField = new TextField();
        addStationField.setEditable(true);
        addStationField.relocate(100, 700);
        pane.getChildren().add(addStationField);
        
        // Set an event handler to handle button presses in add station
        addStationButton.setOnAction(new EventHandler<ActionEvent>() {
           // Method is automatically called when the button is pressed
           @Override
		public void handle(ActionEvent event) {
        	   // Check if the station ID is valid
        	   if (addStationField.getText().length()==4) {
        		   String stationToAdd = addStationField.getText().toUpperCase();
        		   boolean stopBlock = false;
            	   for (int i=0; i< mesonet.size(); i++) {
            		   if (stationToAdd.equalsIgnoreCase(mesonet.get(i))) {
            			   stopBlock = true;
            			   Alert alert = new Alert(AlertType.ERROR,"Station already in file");
            			   alert.showAndWait();
            			   break;
            		   } 
            	   }
            	   if (stopBlock!=true)		
            		   try {
            				BufferedWriter bw = new BufferedWriter(new FileWriter("Mesonet.txt", true));
            				PrintWriter write = new PrintWriter(bw);
            				write.println(stationToAdd);
            				write.close();
            				mesonet.add(stationToAdd);
            				dropbox.getItems().add(stationToAdd);
            			} catch (IOException e) {
            				System.out.println(e);
            				e.printStackTrace();
            			}
        	   // Display alert if station is invalid
        	   } else {
        		   Alert alert = new Alert(AlertType.ERROR,"Please enter a valid station");
    			   alert.showAndWait();
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
        	// Method is automatically called when the button is pressed
        	@Override
			public void handle(ActionEvent event) {
        		yesTextField.setText(Integer.toString(++yesCounter));
        	}
        });
        
        // Create a scene
        Scene scene = new Scene(pane, 625, 740);
        // Set the scene
        applicationStage.setScene(scene);
        applicationStage.setTitle("Hamming Distance");
        applicationStage.show();
	}
	
	/**
	 * Takes two station IDs and calculates their Hamming Distance
	 * @param stid Station ID 1
	 * @param stid2 Station ID 2
	 * @return Hamming distance of two Station IDs
	 */
	public static int calcHammingDist(String stid, String stid2) {
		int hammCount = 0;
		String firstCompare;
		String secondCompare;
		
		for (int i=0; i<4; i++) {
			firstCompare = stid.substring(i, i+1);
			secondCompare = stid2.substring(i,i+1);
			if (!firstCompare.equalsIgnoreCase(secondCompare)) {
				hammCount++;
			}
		}
		return hammCount;
	}
	
	/**
	 * Takes a station ID and a given Hamming Distance and finds the
	 * stations in Mesonet with that Hamming Distance and enters them
	 * into an ArrayList
	 * @param hammDist Given Hamming Distance
	 * @param stid Given Station ID
	 * @return ArrayList of Station IDs matching Hamming Distance
	 */
	public static ArrayList<String> calcHammingDist(Integer hammDist, String stid) {
		int hammCount = 0;
		ArrayList<String> mesoCount = new ArrayList<String>();
		
		for (int i=0; i<mesonet.size(); i++) {
			hammCount = calcHammingDist(mesonet.get(i), stid);
			if (hammCount == hammDist) {
				mesoCount.add(mesonet.get(i));
			}
		}
		return mesoCount;
	}
	
	/**
	 * Reads Mesonet.txt and exports the Station IDs to an ArrayList in
	 * the private field
	 * @param filename File to read
	 * @throws IOException Exception thrown
	 */
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
	
	/**
	 * Main method, read Mesonet.txt and begin program
	 * @param args Standard argument
	 * @throws IOException Exception thrown
	 */
	public static void main(String [] args) throws IOException {
		read("Mesonet.txt");
		launch(args); // Launch application
	}
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;

public class JavaFX_Proj5 extends Application {
	private ArrayList<String> mesonet = new ArrayList<String>();
	private int count=0;
	
	@Override
	public void start(Stage applicationStage) {
		
	}
	
	public void read(String filename) throws IOException {
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
	
	public static void main(String [] args) {
		launch(args); // Launch application
	}
}
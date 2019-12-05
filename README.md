# Java2_Project5

## Private field

private static ArrayList<String> mesonet - ArrayList containing Station IDs from Mesonet.txt
  
private Integer yesCounter - Counter for "Say Yes" button

private ComboBox<String> dropbox - ComboBox containing selectable Station IDs
  
private TextArea stidTextField - Text field displaying the output of the "Show Station" button

## Methods
Program start method containing code for the JavaFX GUI

	 * @param applicationStage
	 
public void start(Stage applicationStage)

Takes two station IDs and calculates their Hamming Distance

	 * @param stid Station ID 1
	 
	 * @param stid2 Station ID 2
	 
	 * @return Hamming distance of two Station IDs
	 
public static int calcHammingDist(String stid, String stid2)

Takes a station ID and a given Hamming Distance and finds the

	 * stations in Mesonet with that Hamming Distance and enters them into an ArrayList
	 
	 * @param hammDist Given Hamming Distance
	 
	 * @param stid Given Station ID
	 
	 * @return ArrayList of Station IDs matching Hamming Distance
	 
public static ArrayList<String> calcHammingDist(Integer hammDist, String stid)
  
  Reads Mesonet.txt and exports the Station IDs to an ArrayList in
  
	 * the private field
	 
	 * @param filename File to read
	 
	 * @throws IOException Exception thrown
	 
public static void read(String filename) throws IOException

Main method, read Mesonet.txt and begin program

	 * @param args Standard argument
	 
	 * @throws IOException Exception thrown
	 
public static void main(String [] args) throws IOException

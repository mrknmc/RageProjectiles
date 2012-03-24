import java.util.ArrayList;

import javax.xml.xpath.XPathExpressionException;



public class GameHandler {
	static ArrayList<Level> levels = new ArrayList<Level>();
	
	public static void loadLevels() {
		try {
			levels = XMLParser.xPathXML();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	public static void wait(int milliseconds) {                                             // Wait for specified time  
		long t0 = System.currentTimeMillis();
		long t1;
		do { 
			t1 = System.currentTimeMillis();
		}
		while (t1 - t0 < milliseconds);
	}
	
    public static void main(String[] args) {
    	loadLevels();
    	/*
		World world = new World(2,2); 
		world.setVisible(true);
		world.startWorld();
		*/
    }
}


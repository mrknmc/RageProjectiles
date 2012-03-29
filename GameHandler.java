import java.util.ArrayList;

import javax.xml.xpath.XPathExpressionException;



public class GameHandler {
	static ArrayList<Level> levels = new ArrayList<Level>();
	//static Level menu;
	static int levelCount = 0;
	
	public static void loadLevels() {
		XMLParser parser = new XMLParser("levels.xml");
		try {
			levels = parser.parseLevels();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	/*
	public static void loadMenu() {
		XMLParser parser = new XMLParser("levels.xml");
		try {
		menu = parser.parseMenu();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	*/
	public static void wait(int milliseconds) {                                             // Wait for specified time  
		long t0 = System.currentTimeMillis();
		long t1;
		do { 
			t1 = System.currentTimeMillis();
		}
		while (t1 - t0 < milliseconds);
	}
	
	public static void nextLevel() {
		levelCount++;
		startLevel(levels.get(levelCount));
	}
	
	public static void startLevel(Level l) {
		World world = new World(l); 
		world.setVisible(true);
		world.startWorld();
	}
	
    public static void main(String[] args) {
    	loadLevels();
    	Level l1 = levels.get(levelCount);
		startLevel(l1);
    }
}


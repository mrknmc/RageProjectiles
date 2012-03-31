import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.xml.xpath.XPathExpressionException;

public class GameHandler {
	static ArrayList<Level> levels = new ArrayList<Level>();
	//static Level menu;
	static int levelCount = 0;
	static World world;
	
	// Parse levels from an XML File
	public static void loadLevels() {
		XMLParser parser = new XMLParser("levels.xml");
		try {
			levels = parser.parseLevels();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	/*
	// Parse the menu from the XML
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
	
	// Load next level
	public static void nextLevel() {
		levelCount++;
		world.setLevel(levels.get(levelCount));
	}
	
	// Play music
	public static void playAudio() throws Exception {
		AudioInputStream stream = AudioSystem.getAudioInputStream(new File("trololo.wav"));
		
		AudioFormat format = stream.getFormat();
	    if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
	      format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format
	          .getSampleRate(), format.getSampleSizeInBits() * 2, format
	          .getChannels(), format.getFrameSize() * 2, format.getFrameRate(),
	          true); // big endian
	      stream = AudioSystem.getAudioInputStream(format, stream);
	    }

	    DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat(),
	        ((int) stream.getFrameLength() * format.getFrameSize()));
	    Clip clip = (Clip) AudioSystem.getLine(info);

	    clip.open(stream);

	    clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
    public static void main(String[] args) {
    	loadLevels();
		world = new World(levels.get(0)); 
		world.setVisible(true);
		
        // Music
	    try {
	    	playAudio();
	    } catch (Exception e) {
	    	
	    }
		
		world.startWorld();
    }
}


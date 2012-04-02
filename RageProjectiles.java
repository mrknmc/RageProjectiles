import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.xml.xpath.XPathExpressionException;

public class RageProjectiles {
	static ArrayList<Level> levels = new ArrayList<Level>();
	//static Level menu;
	static int levelCount = 0;
	static World world;
	static XMLParser parser;
	
	// Parse levels from an XML File
	public static void loadLevels() {
		parser = new XMLParser("levels.xml");
		try {
			levels = parser.parseLevels();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	public static Level loadMenu() {
		ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
		for (int i = 0; i < 4; i++) {
			Point2D.Double projPoint = new Point2D.Double(50 + i*30, 400);
			Projectile p = new Projectile(projPoint, 54, 54);
			projectiles.add(p);
		}
		Level menu = new Level(projectiles, null, null);
		return menu;
	}
	
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
		if (levelCount < levels.size()) {
			world.setLevel(levels.get(levelCount));
			world.nextLevel();
			world.startWorld();
		} else {
			world.finishedGame();
			levelCount = 0;
			try {
				world.setLevel(parser.getLevel(levelCount+1));
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			world.startWorld();
		}
	}
	
	public static void resetLevel() {
		try {
			world.setLevel(parser.getLevel(levelCount+1));
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		world.failedGame();
		world.startWorld();
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
    	//world = new World(loadMenu());
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


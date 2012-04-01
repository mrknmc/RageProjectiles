/*import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Menu extends JPanel {
	
	private static final long serialVersionUID = 1L;
	BufferedImage bgImage;
	ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, null);
		for (Projectile p : projectiles) {
			g.drawImage(p.getImage(), (int) p.getPosition().x, (int) p.getPosition().y, null);
		}
	}
	
	public Menu() {
		try {                
			bgImage = ImageIO.read(new File("img/menu.jpg"));
			images.add(ImageIO.read(new File("img/lolGuy.png")));
			images.add(ImageIO.read(new File("img/troll.png")));
		} catch (IOException ex) {
			// handle exception...
		}
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			Point pt = new Point(i*100+100, 400+10*i);
			Projectile p = new Projectile(pt, 54, 54);
			p.setImage(images.get(r.nextInt(2)));
			projectiles.add(p);
		}
	}
}*/

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Animator extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private Projectile projectile;
	private BufferedImage bgImage;
	
	private Point initialPoint;
	private Point endPoint;
	private boolean havePoints = false;
	private int xdiff;
	private int ydiff;
	
	
	/*
	private BufferedImage projImage;
	private BufferedImage tarImage;
	private BufferedImage trollImage;
	 */
	
	private ArrayList<Target> targets = new ArrayList<Target>();
	private ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(bgImage, 0, 0, null);
		g.setColor(new Color (0,0,0));
		
		g.drawImage(projectile.getImage(), projectile.getPosition().x, projectile.getPosition().y, null);
		g.setColor(new Color(255,0,0));
		/*
		for(Obstruction o : obstructions){
			g.fillRect((int) o.getPosition().getX(), (int) o.getPosition().getY(), o.getWidth(), o.getHeight());
		}
		*/
		g.setColor(new Color(0,255,0));

		for(Target t : targets){
			if (projectile.gonnaHit(t) && t.isAlive()) {
				t.destroy();
				projectile.setHit(true);
			}
			if (t.isAlive()) {
				g.drawImage(t.getImage(), t.getPosition().x, t.getPosition().y, null);
			}
		}
		g.setColor(new Color(0,0,0));
		g.drawLine(initialPoint.x, initialPoint.y, endPoint.x,endPoint.y);
		
	}

	// Constructor
	public Animator(Projectile p, ArrayList<Obstruction> o, ArrayList<Target> t) {
		projectile = p;
		obstructions = o;
		targets = t;
		try {                
			bgImage = ImageIO.read(new File("img/bg.jpg"));
		} catch (IOException ex) {
			// handle exception...
		}
		this.addMouseListener(this);
		this.initialPoint = new Point(0,0);
		this.endPoint = new Point(0,0);
	}
	
	public boolean getHavePoints(){
		return havePoints;
	}
	
	public void setHavePoints(boolean b){
		this.havePoints = b;
	}
	
	public int getAngle(){
		int angle = (int) (Math.toDegrees(Math.atan((double) ydiff/xdiff)));
		if (angle < 0){
			angle = -angle;
		}
		//System.out.println("Angle: " + angle + "; y=" + ydiff + "; x=" + xdiff + "; y/x: " + ((double)ydiff/xdiff)); 
		return angle;
	}
	
	public int getSpeed(){
		double length = Math.sqrt(xdiff * xdiff + ydiff * ydiff);
		if(length > 450){
			length = 450;
		}
		return (int)  (2 * length);		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		initialPoint = new Point(arg0.getX(),arg0.getY());
		//System.out.println(initialPoint);
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		endPoint = new Point(arg0.getX(),arg0.getY());
		ydiff = this.endPoint.y - this.initialPoint.y;
		xdiff = this.initialPoint.x - this.endPoint.x;
		this.havePoints = true;
		//System.out.println(endPoint);
		
	}
}
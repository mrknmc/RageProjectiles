import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Animator extends JPanel{
	
	private int projX;
	private int projY;
	private int projW;
	private int projH;
	
	private ArrayList<Target> targets = new ArrayList<Target>();
	private ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();
	
	
	
	public static void printProgress(int x, int y) {
		System.out.printf("Projectile is at %d, %d.\n", x, y);
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
				
		
		g.setColor(new Color (0,0,0));
		
		g.fillOval(this.projX, this.projY, this.projW, this.projH);
		
		g.setColor(new Color(255,0,0));
		for(Obstruction o : obstructions){
			g.fillRect((int) o.getPosition().getX(), (int) o.getPosition().getY(), o.getWidth(), o.getHeight());
		}
		
		g.setColor(new Color(0,255,0));
				
		for(Target t : targets){
			g.fillRect((int) t.getPosition().getX(), (int) t.getPosition().getY(), t.getWidth(), t.getHeight());
		}
	}
	
	public void setProjSize(int W, int H){
		this.projW = W;
		this.projH = H;
	}
	
	public void addTarget(Target t){
		this.targets.add(t);
	}
	
	public void addObstruction(Obstruction o){
		this.obstructions.add(o);
	}
	
	public void updateProjPos(int X, int Y){
		this.projX = X;
		this.projY = Y;
		this.repaint();
	}
	
	// Constructor
	public Animator(int pX, int pY, int pW, int pH){
		this.projX = pX;
		this.projY = pY;
		this.projW = pW;
		this.projH = pH;
		
	}
}
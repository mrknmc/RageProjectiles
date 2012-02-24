public class Animator extends JPanel {
	
	// This method should be the one that paints the projectile and other components with their position on GUI
	/*
	protected void paintComponent(Graphics g) {
  		g.fillRect()
    }
	*/
		
	// Just a temporary method to print the progress of a projectile to the console
	public void printProgress() {
		int x = projectile.getPosition().x;
		int y = projectile.getPosition().y;
		System.out.printf("Projectile is at %d, %d.\n", x, y);
	}
}
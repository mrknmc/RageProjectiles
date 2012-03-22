public class GameHandler {
	
	public static void wait(int milliseconds){                                             // Wait for specified time  
		long t0 = System.currentTimeMillis();
		long t1;
		do { 
			t1 = System.currentTimeMillis();
		}
		while (t1 - t0 < milliseconds);
}
    
    public static void main(String[] args) {
		World world = new World(2,2); 
		world.setVisible(true);
		world.startWorld();
    }
}


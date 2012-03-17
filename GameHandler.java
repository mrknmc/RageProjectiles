public class GameHandler {
    
    public static void main(String[] args) {
		int counter;
		World world = new World(2,2); 
		world.setVisible(true);
		for (counter = 1; counter < 2; counter++) {
		    world.startWorld();
		}
    }
}


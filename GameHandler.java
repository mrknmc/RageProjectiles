public class GameHandler {
    
    public static void main(String[] args) {
		int counter;
		World world = new World(2,2); 
		
		for (counter = 1; counter < 2; counter++) {
			world.printProjectilePosition();
			world.printTargetPositions();
		    world.startWorld();
		}
    }
}


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
/*
	Scanner input = new Scanner(System.in);
	int x = 1;
	int y = 0;
	Random generator = new Random();
	double xDiff = (generator.nextDouble()) * 50 + 100;
	double yDiff = (generator.nextDouble()) * 50 + 100;
		
	while (x <= 3 && y == 0) {
	    System.out.println ("Guess: " + x);
	    System.out.print ("Angle: ");
	    double angle = input.nextDouble();
	    System.out.print ("Speed: ");
	    double speed = input.nextDouble();    
		System.out.printf("X Distance: %f \n", xDiff);
//		    double xDiff = input.nextDouble();
	    System.out.printf("Y Distance: %f \n", yDiff);
//		    double yDiff = input.nextDouble();
	    y = AngryBirds.calculate(angle,speed,xDiff,yDiff);
	    x++;    
		}
	}
*/

package pandemicgame;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PandemicGameMain {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Creating the game world and players
		World myWorld = new World("The Pandemic Game/files/carte2.json");
		ArrayList<Player> players = new ArrayList<Player>();
		
		// Selecting a city for the players to start
		City ville = myWorld.getCities().get(0);
		
		// Creating player instances
		Globetrotter karim = new Globetrotter("karim", ville, myWorld);
        Doctor timo = new Doctor("timo", ville, myWorld);
        Expert leon = new Expert("leon", ville, myWorld);
        Scientific lucas = new Scientific("lucas", ville, myWorld);
        
        // Adding players to the list
        players.add(timo);
        players.add(leon);
        players.add(karim);
        players.add(lucas);
        
        // Creating the game instance
		PandemicGame game = new PandemicGame(myWorld, players);
		
		// Running the game
		game.run();
	}
}
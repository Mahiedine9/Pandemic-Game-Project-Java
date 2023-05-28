package pandemicgame;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PandemicGameMain {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// créeation de jeu et des joeurs
		// il s'agit d'une version initale de cette suite d'instructions preparatives
		World myWorld = new World("The Pandemic Game/files/carte2.json") ;
		ArrayList<Player> players = new ArrayList<Player>() ;
		City ville = myWorld.getCities().get(0) ;
		Globetrotter karim = new Globetrotter("karim", ville, myWorld) ;
        Doctor timo = new Doctor("timo", ville, myWorld) ;
        Expert leon = new Expert("leon", ville, myWorld) ;
        Scientific lucas = new Scientific("lucas", ville, myWorld) ;
        players.add(timo) ;
        players.add(leon) ;
        players.add(karim) ;
        players.add(lucas) ;
		PandemicGame game = new PandemicGame(myWorld, players) ;
		

	  
	    
		
	    
		game.initiate() ;
	    game.run() ;

}
		
	
	
	
	
	
	
	
	
	
	
	}

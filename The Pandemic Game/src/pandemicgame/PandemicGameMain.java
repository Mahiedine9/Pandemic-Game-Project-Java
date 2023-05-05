package pandemicgame;

import java.io.FileNotFoundException;
import java.util.ArrayList ;
import java.util.Iterator;

public class PandemicGameMain {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// création du plateau du jeu et de jeu
		World myWorld = new World("The Pandemic Game/files/carte1.json") ;
		
		
		// selection d'un ville
		City city = myWorld.getCities().get(0) ;
		
		// création des 4 joueurs
		Globetrotter timo = new Globetrotter("Timo", city, myWorld) ;
		Globetrotter leon = new Globetrotter("Leon", city, myWorld) ;
		Globetrotter john = new Globetrotter("John", city, myWorld) ;
		Globetrotter lucas = new Globetrotter("Lucas", city, myWorld) ;
		ArrayList<Player> players = new ArrayList<Player>() ;
		players.add(timo) ;
		players.add(leon) ;
		players.add(john) ;
		players.add(lucas) ;
		
		// création et initiation du jeu
		PandemicGame game = new PandemicGame(myWorld, players) ;
		game.initiate() ;
		System.out.print("Plteau de jeu a été crée. En ce moment il exist "+myWorld.getPlayerDeck().size()+" cartes joueuers (dont cartes épidimie) et "+myWorld.getInfectionDeck().size()+" cartes infection.") ;
		System.out.println() ;
		System.out.println() ;
		
	
		//Tirge 5 cartes infection et infection des villes
		for  (int i = 0 ; i<5 ; i++) {
			InfectionCard card = myWorld.drawInfectionDeck() ;
			Disease cardDisease = card.getDisease() ;
			City  cardCity = card.getCity() ;
			cardCity.addDisease(cardDisease) ;
			System.out.println("carte infecition tirée => ville :"+cardCity.getName()+"   maladie :"+cardDisease+". la ville est désormais infectée") ;
			
		/**for (Player player : game.getPlayers()) {
			PlayableCard carte1 = myWorld.drawPlayerDeck() ;
			  if (carte1 instanceof PlayerCard) player.getHand().add((PlayerCard) carte1) ;
			   System.out.println("CARTE EPIDIMIE TIREE !!! ") ;
			PlayableCard carte2 = myWorld.drawPlayerDeck() ;
			   if (carte1 instanceof PlayerCard) player.getHand().add((PlayerCard) carte2) ;
			   System.out.println("CARTE EPIDIMIE TIREE !!! ") ;
		}
		*/

			
			

}
		
	
	
	
	
	
	
	
	
	
	
	
	}}

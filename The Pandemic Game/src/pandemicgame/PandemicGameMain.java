package pandemicgame;

import java.io.FileNotFoundException;
import java.util.ArrayList ;
import java.util.Iterator;

public class PandemicGameMain {
	
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
        System.out.println("Usage: java PandemicGameMain <JSON file path>");
        return;
        }
		// création du plateau du jeu et de jeu
		World myWorld = new World(args[0]) ;
		// création du jeu
		PandemicGame game = new PandemicGame(myWorld) ;
		
		
		Scanner sc = new Scanner(System.in);
        int nbPlayers = 0;
        
        while (nbPlayers < 1 || nbPlayers > 4) {
        	System.out.println("Combien de joueurs voulez-vous créer ? (1 à 4 joueurs)");
        	if (sc.hasNextInt()) {
        		nbPlayers = sc.nextInt();
        	} else {
        		System.out.println("Veuillez entrer un nombre entre 1 et 4.");
        		sc.next();
        	}
        }
        
        
		
		// sélection d'une ville
		City city = myWorld.getCities().get(0) ;
		
		// création des joueurs
		ArrayList<Player> players = new ArrayList<Player>() ;
		
		for (int i = 0; i < nbPlayers; i++) {
			System.out.println("Création du joueur #" + (i+1));
			System.out.println("Entrez le nom du joueur :");
			String playerName = sc.next();
			System.out.println("Entrez le rôle du joueur (doctor, scientist, expert, globetrotter) :");
			String playerRole = sc.next();
			
			Player newPlayer = createPlayer(playerName, playerRole, city, myWorld);
			
			if (newPlayer != null) {
				players.add(newPlayer);
			} else {
				System.out.println("Le rôle entré n'est pas valide.");
				i--; // on décrémente i pour recréer un joueur
			}
		}
		
		sc.close();
		
		// initialisation du jeu
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

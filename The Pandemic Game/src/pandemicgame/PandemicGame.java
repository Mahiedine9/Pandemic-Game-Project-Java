package pandemicgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Random;

import exception.NoMoreResearchStationAvailableException;
import exception.NotANeighbourCityException;
import exception.NotAResearchStationException;
import exception.NotEnoughCardsToFindARemedyException;
import exception.PlayerDoesNotHaveProperCardException;

public class PandemicGame {
	private World world ;
	private ArrayList<Player> players ;
	private Iterator<Player> currentlyPlaying ;
	private HashMap<Disease, Integer> diseaseCubes ;
	
	public PandemicGame (World world, ArrayList<Player> players ) {
		this.world = world ;
		this.players = players;
		this.currentlyPlaying = players.iterator() ;
		this.diseaseCubes = new HashMap<Disease, Integer>() ;
	}
	
	public World getWorld() {
		return this.world ;
	}
	
	public ArrayList<Player> getPlayers() {
		return this.players ;
	}
	
	public Iterator<Player> getCurrentlyPlaying() {
		return this.currentlyPlaying ;
	}
	
	public HashMap<Disease, Integer> getDiseasesCubes() {
		return this.diseaseCubes ;
	}
	
	
	private  Boolean isGameOver() {
		
		// TODO 
		// Ici on réponds à la question si le jeu est terminée ?
		// nous allons vérifier si le jeu est terminée et puis c'est si le cas , la partie est-elle ganée ou perdue ??
		return false ;
	}
	
	public void run () {
		// TODO
		// ici on doit lancer la partie et et donner le tour au joueur jusq'au ou la partie est terminée
		// cette méthode est la méthode principle de jeu ( à cote de initiate())
		// cette méthode va utiliser l'ensemble des méthodes privée définies au dessous
	}
	
	public void initiate() {
		// ajout des cubes maladies
		for (Disease disease : this.world.getDiseases()) {
			this.diseaseCubes.put(disease, 24) ;
		}
		
		// creation des cartes et leur ajouts dans les deux piles du plateau
		ArrayList<InfectionCard> cartesinfection = new ArrayList<InfectionCard>() ;
		ArrayList<PlayerCard> cartesjoueur = new ArrayList<PlayerCard>() ;
		ArrayList<Disease> maladiessurcartes = new ArrayList<Disease>() ;
		
		for ( int i = 0 ; i < (this.world.getCities().size()/4) ; i++) {
			maladiessurcartes.add(Disease.DISEASE1) ;
			maladiessurcartes.add(Disease.DISEASE2) ;
			maladiessurcartes.add(Disease.DISEASE3) ;
			maladiessurcartes.add(Disease.DISEASE4) ;
		}
		Collections.shuffle(maladiessurcartes);
		
		Iterator<City> iterateurvilles = this.world.getCities().iterator() ;
		Iterator<Disease> iterateurmaladies = maladiessurcartes.iterator() ;
		
		while (iterateurvilles.hasNext()) {
			City ville = iterateurvilles.next();
			Disease maladie = iterateurmaladies.next();
			cartesinfection.add(new InfectionCard(ville,maladie)) ;
			cartesjoueur.add(new PlayerCard(ville,maladie)) ;
		}
		
		Collections.shuffle(cartesinfection);
		Collections.shuffle(cartesjoueur);
		
		while (!cartesinfection.isEmpty()) {
			InfectionCard carte = cartesinfection.get(0) ;
			cartesinfection.remove(0) ;
			this.world.addInfectionCard(carte) ;
		}
		while (!cartesjoueur.isEmpty()) {
			PlayerCard carte = cartesjoueur.get(0) ;
			cartesjoueur.remove(0) ;
			this.world.addPlayableCard(carte) ;	
		}
		Collections.shuffle(this.world.getInfectionDeck());
		Collections.shuffle(this.world.getPlayerDeck());
		
		
		// distrubtion des cartes joueurs et insertion des cartes épidimies
		for (Player joueur : this.players) {
			joueur.getHand().add((PlayerCard) this.world.drawPlayerDeck()) ;
			joueur.getHand().add((PlayerCard) this.world.drawPlayerDeck()) ;
		}
		@SuppressWarnings("unchecked")
		ArrayList<PlayableCard>[] sousgroupesdescartesjouers = new ArrayList[4];
		for ( int i = 0 ; i < sousgroupesdescartesjouers.length ; i++) {
			sousgroupesdescartesjouers[i]=new ArrayList<PlayableCard>() ;
		}
		
		Integer newSizeOfPlayerDeck = this.world.getPlayerDeck().size();
		Integer numsousgroupe = 0 ;
		for (int i = 0 ; i < newSizeOfPlayerDeck ; i++) {
			PlayerCard carte = (PlayerCard) this.world.drawPlayerDeck() ;
			sousgroupesdescartesjouers[numsousgroupe].add(carte) ;
			if (PandemicGame.getQuartilePositions(newSizeOfPlayerDeck).contains(i)) numsousgroupe ++ ;
		}
		
		for (int i = 0 ; i < sousgroupesdescartesjouers.length ; i++) {
			 sousgroupesdescartesjouers[i].add(new EpidemicCard()) ;
			 Collections.shuffle( sousgroupesdescartesjouers[i]);
		}
		
		for( ArrayList<PlayableCard> sousgroupe : sousgroupesdescartesjouers ) {
			for (PlayableCard carte : sousgroupe) {
				if (carte.getClass().equals(EpidemicCard.class)) this.world.addPlayableCard((EpidemicCard) carte) ;
				else this.world.addPlayableCard((PlayerCard) carte) ;
			}
		}
		
		
		// lancement de la phase d'infection initaile
		this.launchInitialInfection() ;
		
		// placement des joueerus dans une ville choisi au hasard et construction d'une station d recherche
		
		City itBegins = this.world.getCities().get(0) ; // cette selectioon reste aléatoire : voir méthode World.getCities()
		
		for (Player joueur : this.players) {
			joueur.initPosition(itBegins) ;
		}
		
		itBegins.turnResearchStation() ;
		this.world.countUsedResearchStation();
		
		System.out.print("Jeu initalisé avec succeès !") ;
		}

	private void launchInitialInfection() {
		for ( int i = 0; i < 3 ; i++) {
			InfectionCard carte = this.world.drawInfectionDeck() ;
			carte.getCity().addDisease(carte.getDisease());
			carte.getCity().addDisease(carte.getDisease());
			carte.getCity().addDisease(carte.getDisease());
			this.world.DiscardInfectionCard(carte);}
		for ( int i = 0; i < 3 ; i++) {
			InfectionCard carte = this.world.drawInfectionDeck() ;
			carte.getCity().addDisease(carte.getDisease());
			carte.getCity().addDisease(carte.getDisease());
			this.world.DiscardInfectionCard(carte);}
		for ( int i = 0; i < 3 ; i++) {
			InfectionCard carte = this.world.drawInfectionDeck() ;
			carte.getCity().addDisease(carte.getDisease());
			this.world.DiscardInfectionCard(carte);}
		}
	
	private void playturn(Player player) throws NotANeighbourCityException, NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException, NotAResearchStationException, NotEnoughCardsToFindARemedyException {
		// le joueuer fait ses actions (ou pas)
		while (player.hasActionsRemaning())  this.performAction(player)  ;
		
		// le joueur tire deux carte joueur et les ajoute à sa main ( à moins si ces dernière étaient EpidimicCard ou il possède déja 7 cartes) 
		PlayableCard carte1 = this.world.drawPlayerDeck() ;
		PlayableCard carte2 = this.world.drawPlayerDeck() ;
		PlayableCard[] cartes = {carte1,carte2} ;
		for (PlayableCard carte : cartes) {
		  if (carte.getClass().equals(EpidemicCard.class)) this.handlEpidemicCard() ;
		  else player.getHand().add((PlayerCard) carte) ;}
		
		// un joueur un peut pas avoir plus de 7 cartes à moment un donnée
		if (player.getHand().size() > 7 ) this.forceSevenCards(player) ;
		
		//enfin on lance la phase d'infection
		this.launchInfectionPhase() ;
		}
	
	
	
	
	private void forceSevenCards(Player player) {
		
		while (player.getHand().size() > 7 ) {
		Integer handSize = player.getHand().size();

		String message = """
			    Joueur %s n'possède actuellement plus que 7 cartes joueurs dans sa main. Veuillez sélectionner une carte à supprimer. Choisissez un numéro de 0 à %d:
			    """.formatted(player.getName(), handSize - 1);
	    System.out.println(message) ;
	    for (PlayerCard carte : player.getHand()) {
	    	System.out.println("carte joueur => numéro :"+player.getHand().indexOf(carte)+" ville :"+carte.getCity().getName()+" maladie :"+carte.getDisease().name()) ;
	    }
	    
	    try (Scanner scanner = new Scanner(System.in)) {
			Integer index = scanner.nextInt();  
			
			player.getHand().remove(index) ;
		}
	    
	    catch (Exception e ) {}
	    
		}
			
	}

	private void launchInfectionPhase() {
		Integer currentInfectionRate = this.world.getInfectionRate() ;
		ArrayList<InfectionCard> cartes = new ArrayList<InfectionCard>() ;
		
		for (int i = 0 ; i < currentInfectionRate ; i++) {
			cartes.add(this.world.drawInfectionDeck()) ;
		}
		
		for (InfectionCard carte : cartes ) {
			carte.getCity().addDisease(carte.getDisease());
			
		// il manque des choses ici
		}
		
	}
     
	
	private static void addDiseaseCube(City city, Disease disease) {
		// TODO
		// cette méthode , statique et recursive, va gérer l'ajout d'un cube de maladie à une ville donne
		// elle doit donc gérer le cas ou la ville est un foyer d'infection, en appeleant elle meme avec le meme paramère sur l'ensemble des villes voisines
		
	}
	private void handlEpidemicCard() {
		// TODO Auto-generated method stub
		
	}

	private void performAction(Player player) throws NotANeighbourCityException, NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException, NotAResearchStationException, NotEnoughCardsToFindARemedyException {
		Scanner scanner = new Scanner(System.in) ;
		Integer action ;
		
		String message = """
			    Joueur %s doit choiser une action. Choisissez un numéro de 0 à 4:
			    """.formatted(player.getName());
	    System.out.println(message) ;
	    System.out.println("0 : se déplacer, 1 : construire une station de recherche, 2 :trouver un remède, 3:traiter une maladie, 4 :ne rien faire   ") ;
	    action = scanner.nextInt(); 
	    
		
			
		if (action == 0 ) {
			
			ArrayList<City> neighbours = this.world.getCityNeighbours(player.getCity()) ;
			ArrayList<String> neighboursNames = new ArrayList<String>() ;
 			for (City neigbour : neighbours) { neighboursNames.add(neigbour.getName()) ;
 			
 			System.out.println(neighboursNames) ;
 			String message_move = """
 					Selectionner une ville voisine.  Choisissez un numéro de 0 à %d  :
 					""".formatted(neighboursNames.size()-1) ;
 			System.out.println(message_move) ;
 			action = scanner.nextInt(); 
 			City nouvelleCity = neighbours.get(action) ;
			player.move(nouvelleCity) ;
			
		}}
 			
 		if (action == 1 ) {
 			String message_build = """
 					Voulez-vous construire une nouvelle station ou bien  déplacer une d'une autre ville ? .Choisissez un numéro de 1 à 2 :
 					""" ;
 			System.out.println(" 1 : construire une nouvelle station, 2 : déplacer une station existante d'une autre ville") ;
 			
 			// il manque des choses ici
 		}
 		
 		if (action == 2 ) {
 			String message_find = """
 					Selectionner la maladie pour laquelle vous voulez trouver un remède.Choisissez un numéro de 1 à 4 :
 					""" ;
 			System.out.println(message_find) ;
 			System.out.println(Arrays.asList(Disease.values())) ;
 			action = scanner.nextInt(); 
 			if (action == 1) player.discoverRemedy(Disease.DISEASE1) ;
 			if (action == 2) player.discoverRemedy(Disease.DISEASE2) ;
 			if (action == 3) player.discoverRemedy(Disease.DISEASE3) ;
 			if (action == 4) player.discoverRemedy(Disease.DISEASE4) ;	 		  
 		}
 		
 		if (action == 3) { 
 			String message_treat = """
 					Selectionner la maladie dont vous voulez traiter ( supprimer un cube) .Choisissez un numéro de 1 à 4 :
 					""" ;
 			System.out.println(message_treat) ;
 			System.out.println(Arrays.asList(Disease.values())) ;
 			action = scanner.nextInt(); 
 			if (action == 1) player.treatDisease(Disease.DISEASE1) ;
 			if (action == 2) player.treatDisease(Disease.DISEASE2) ;
 			if (action == 3) player.treatDisease(Disease.DISEASE3) ;
 			if (action == 4) player.treatDisease(Disease.DISEASE4) ;	
 		}
 		
 		
			
			
	}
	    

	private static ArrayList<Integer> getQuartilePositions(int size) {
        ArrayList<Integer> quartilePositions = new ArrayList<>();
        
        quartilePositions.add(size / 4);        // Position representing 25%
        quartilePositions.add(size / 2);        // Position representing 50%
        quartilePositions.add((3 * size) / 4);  // Position representing 75%
        
        return quartilePositions;
    }
	
	
	
	
	
	// méthode qui crée un joueur d'une facon interactive, à utilsier dans  PandemicGameMain ???
	public static  Player createPlayer(String name, String role, World world, ArrayList<Player> players) {
	    ArrayList<City> cities = world.getCities();
	    Random random = new Random();

	    // Choisir une ville au hasard parmi les villes disponibles
	    City city = cities.get(random.nextInt(cities.size()));
		Player player;
	    // Cr�er le joueur avec le r�le, le nom et la ville s�lectionn�s
	    switch (role.toUpperCase()) {
	        case "DOCTOR":
	             player = new Doctor(name, city, world);
				 players.add(player);
				 return player;
	        case "EXPERT":
	            player = new Expert(name, city, world);
				players.add(player);
				return player;
	        case "SCIENTIFIC":
	            player = new Scientific(name, city, world);
				players.add(player);
				return player;
	        case "GLOBETROTTER":
	            player = new Globetrotter(name, city, world);
				players.add(player);
				return player;
	        default:
	            throw new IllegalArgumentException("Role not recognized");
	    }
	}


}

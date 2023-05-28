package pandemicgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
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
	private Integer index;
	private ArrayList<City> citiesAlreadyInfectedDuringCurrentInfectionPhase ;
	
	public PandemicGame (World world, ArrayList<Player> players ) {
		this.world = world ;
		this.players = players;
		this.currentlyPlaying = players.iterator() ;
		this.diseaseCubes = new HashMap<Disease, Integer>() ;
		this.citiesAlreadyInfectedDuringCurrentInfectionPhase =  new ArrayList<City>() ;
		
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
	
	
	public void run () {
		
		System.out.println();
		//this.world.addRemedy(Disease.DISEASE1);
		Player p = this.players.get(0) ;
		System.out.println(p.getClass()) ;
		
		City playerCity = p.getCity() ;
		this.addDiseaseCube(playerCity, Disease.DISEASE1);
		this.addDiseaseCube(playerCity, Disease.DISEASE1);
		this.addDiseaseCube(playerCity, Disease.DISEASE1);
		
		System.out.println(this.diseaseCubes);
		System.out.println(playerCity.getDiseases()) ;
		this.performAction(p);
		System.out.println(this.diseaseCubes);
		System.out.println(playerCity.getDiseases()) ;
		
		
	
		
		
		
		
		
		
		
	}
	
	private  Boolean isGameOver() {
		
		//4 remèdes sont trouvées
		Boolean scenario1 = true ; ;
		for ( Disease remedy : Disease.values()) {
			if (! this.world.getRemedies().contains(remedy)) scenario1 = false  ;
		}
		
		//le nombre de foyers d’infection atteint 8,
		Boolean scenario2 = this.world.getNbOutBreaks() >= 8 ;
		
		// un joueur ne peut pas prendre les 2 cartes joueur à son tour
		Boolean scenario3 = this.world.getPlayerDeck().isEmpty();
		
		// il n’y a plus de cube disponible pour une maladie alors qu’il faut en placer un sur une ville,
				// on gère ce cas dans la méthode qui concerne l'ajout des cubes  this.addDiseaseCube()
		
		return scenario1 || scenario2 || scenario3 ;
	}
	
	private Boolean isGameWon() {
		//4 remèdes sont trouvées
		Boolean won = true ; ;
		for ( Disease remedy : Disease.values()) {
			if (! this.world.getRemedies().contains(remedy)) won = false  ;
		}
		
		return won ;
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
		
		ArrayList<City> cityRandomizer = this.world.getCities();
		Collections.shuffle(cityRandomizer) ;
		
		City itBegins = cityRandomizer.get(0) ; // cette selectioon est aléatoire
		
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
			//carte.getCity().addDisease(carte.getDisease());
			this.addDiseaseCube(carte.getCity(), carte.getDisease());
			this.addDiseaseCube(carte.getCity(), carte.getDisease());
			this.addDiseaseCube(carte.getCity(), carte.getDisease());
			this.world.DiscardInfectionCard(carte);}
		for ( int i = 0; i < 3 ; i++) {
			InfectionCard carte = this.world.drawInfectionDeck() ;
			this.addDiseaseCube(carte.getCity(), carte.getDisease());
			this.addDiseaseCube(carte.getCity(), carte.getDisease());
			this.world.DiscardInfectionCard(carte);}
		for ( int i = 0; i < 3 ; i++) {
			InfectionCard carte = this.world.drawInfectionDeck() ;
			this.addDiseaseCube(carte.getCity(), carte.getDisease());
			this.addDiseaseCube(carte.getCity(), carte.getDisease());
			this.world.DiscardInfectionCard(carte);}
		}
	
	
	
	
	
	
	private void forceSevenCards(Player player) {
		
		while (player.getHand().size() > 0 ) {
			ArrayList<PlayerCard> hand = player.getHand() ;
			HashMap <String,PlayerCard> handCards = new HashMap<String,PlayerCard>() ;
		for (PlayerCard carte : hand ) handCards.put((carte.getCity().getName()+"-"+carte.getDisease().name()).toLowerCase(),carte) ;
		System.out.println();
		System.out.println(handCards.keySet()) ;
		System.out.println("Enter the name of the card you want to delete:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        
        if (handCards.containsKey(input)) {
            PlayerCard cardToDelete = handCards.get(input);
            player.getHand().remove(cardToDelete);
            System.out.println("Card deleted successfully.");
        } else {
            System.out.println("Invalid card name. Please try again.");
        }
        
    }
		
		
		
	}

	private void launchInfectionPhase(Boolean epidemicCard) {
		this.clearInfectedCitiesForNextPhase();
		
		
		Integer currentInfectionRate = this.world.getInfectionRate() ;
		if (epidemicCard)  currentInfectionRate = 1 ;
		
		ArrayList<InfectionCard> cartes = new ArrayList<InfectionCard>() ;
		
		for (int i = 0 ; i < currentInfectionRate ; i++) {
			cartes.add(this.world.drawInfectionDeck()) ;
		}
		for (InfectionCard carte : cartes ) {
			this.addDiseaseCube(carte.getCity(), carte.getDisease());
			this.world.DiscardInfectionCard(carte);
		}		
	}
     
	private  void addDiseaseCube(City city, Disease disease) {
		
		if (this.diseaseCubes.get(disease) <= 0)  {}  // gérer fin de jeu
		
		else if (!this.citiesAlreadyInfectedDuringCurrentInfectionPhase.contains(city)) {
			
			if (city.isOutBreakOfInfection(disease)) {
				this.handleAddDiseaseCubeWhenOutBreak(city,disease) ;
			}
			
			 else {
				 
				 city.getDiseases().add(disease) ;
				 this.diseaseCubes.put(disease, (this.diseaseCubes.get(disease))-1) ;
				 if (city.checkIfIsOutBreakOnNextAdd(disease)) {
					 city.declareOutBReakOfInfection(disease);
				 };
			 }
		}
	}

	private void removeDiseaseCube(Disease disease, int cubeCount) {
	    int currentCount = diseaseCubes.getOrDefault(disease, 0);
	    int updatedCount = currentCount + cubeCount;
	    diseaseCubes.put(disease, updatedCount);
	}
	
	private void handleAddDiseaseCubeWhenOutBreak(City city, Disease disease) {
		for (City neighbourCity : this.world.getCityNeighbours(city)) {
			this.addDiseaseCube(neighbourCity, disease);
		}	
	} 
	
	private void clearInfectedCitiesForNextPhase() {
		this.citiesAlreadyInfectedDuringCurrentInfectionPhase.clear();
	}
	
	
	
	private void handlEpidemicCard(EpidemicCard epidemicCard) {
		// augmente le taux global d’infection 
		this.world.upInfectionRate();
		
		// déclenche une phase d’infection avec une seule carte
		this.launchInfectionPhase(true);
		
		//les cartes de la défausse des cartes infection sont mélangées puis remises sur le dessus de la pile des cartes infection
		Collections.shuffle(this.world.getInfectionDiscardPile());
		while (!this.world.getInfectionDiscardPile().isEmpty()) {
			this.world.addInfectionCard(this.world.getInfectionDiscardPile().pop()) ; }
		
		// la carte épidémie est défaussée
		this.world.getplayerDiscardPile().add(epidemicCard) ;
	}

	
	private void playturn(Player player)  {
		
		// un joueur doit réaliser quatre actions parmi celles possibles
		while (player.hasActionsRemaning()) {
			this.performAction(player) ;
		}
		 
		//il tire 2 cartes joueur de la pile des cartes joueur qu’il ajoute à sa main
		player.getHand().add((PlayerCard) this.world.drawPlayerDeck()) ;
		player.getHand().add((PlayerCard) this.world.drawPlayerDeck()) ;
		
		if (player.getHand().size() > 7) this.forceSevenCards(player);
		
		// une phase d’infection est lancée
		this.launchInfectionPhase(false);
}
	
	private void performAction(Player player) {
	    System.out.println("Available actions:");
	    System.out.println("1. Move");
	    System.out.println("2. Build research station");
	    System.out.println("3. Find remedy");
	    System.out.println("4. Treat disease");
	    System.out.println("5. Do nothing");
	    
	    int choice = getUserChoice(1, 5); // Get user input for the action choice
	    
	    switch (choice) {
	        case 1:
	        	
	             this.performMoveAction(player);
	             
	            break;
	        case 2:
	        	
	            this.perfromBuildAction(player);
	            
	            break;
	        case 3:
	        	
	            this.perfromFindAction(player);
	            
	            break;
	        case 4:
	            
	        	this.perfromTreatAction(player);
	        	
	            break;
	        case 5:
	            // Do nothing
	            System.out.println("You chose to do nothing.");
	            break;
	        default:
	            System.out.println("Invalid choice. Please try again.");
	            break;
	    }
	}

	private void perfromTreatAction(Player player) {
		System.out.println("Choose a disease to treat:");
	    ArrayList<Disease> diseases = player.getCity().getDiseases();

	    // Display the available diseases in the current city
	    for (int i = 0; i < diseases.size(); i++) {
	        System.out.println((i + 1) + ". " + diseases.get(i).name());
	    }

	    int diseaseChoice = getUserChoice(1, diseases.size()); // Get user input for the disease choice

	    Disease selectedDisease = diseases.get(diseaseChoice - 1);
	    
	    Integer nbCubesBeforeRemoval = player.getCity().howManyCubes(selectedDisease) ;
	    player.treatDisease(selectedDisease);
	    Integer nbCubesAfterRemoval = player.getCity().howManyCubes(selectedDisease) ;
	    
	    Integer treatedCubesCount = nbCubesBeforeRemoval-nbCubesAfterRemoval ;
	    System.out.println("Disease " + selectedDisease.name() + " has been treated."+ treatedCubesCount + " cube(s) removed.");
	    
	    
	    if (player.getClass().equals(Doctor.class) || this.world.getRemedies().contains(selectedDisease)) {
	    	
	    	
	       this.removeDiseaseCube(selectedDisease,treatedCubesCount) ;
	    }
	    else 
	    	 this.removeDiseaseCube(selectedDisease,1) ;
	    
	}

	

	private void perfromFindAction(Player player) {
		System.out.println("Choose a disease to find a remedy for:");
	    ArrayList<Disease> diseases =  new ArrayList<Disease>(Arrays.asList(Disease.values()));

	    // Display the available diseases
	    for (int i = 0; i < diseases.size(); i++) {
	        System.out.println((i + 1) + ". " + diseases.get(i).name());
	    }

	    int diseaseChoice = getUserChoice(1, diseases.size()); // Get user input for the disease choice
	    Disease selectedDisease = diseases.get(diseaseChoice - 1);
	    
	    try {
	  
	        player.discoverRemedy(selectedDisease);
	        System.out.println("A remedy has been discovered for " + selectedDisease.name() + ".");
	    } catch (NotAResearchStationException e) {
	        System.out.println("Invalid action: " + player.getCity().getName() + " is not a research station.");
	    } catch (NotEnoughCardsToFindARemedyException e) {
	        System.out.println("Not enough cards to find a remedy for " + selectedDisease.name() + ".");
	    }
		
	}

	private void perfromBuildAction(Player player) {
		System.out.println("Choose an action:");
	    System.out.println("1. Build a new research station");
	    if (this.world.getResearchStationsAvailable() <= 0) {
	        System.out.println("2. Move an existing research station");
	    }

	    int choice = getUserChoice(1, (this.world.getResearchStationsAvailable() <= 0) ? 2 : 1); // Get user input for the build action choice

	    switch (choice) {
	        case 1:
	            try {
	                player.buildResearchStation();
	                System.out.println("A new research station has been built in " + player.getCity().getName() + ".");
	            } catch (NoMoreResearchStationAvailableException e) {
	                System.out.println("No more research stations available.");
	            } catch (PlayerDoesNotHaveProperCardException e) {
	                System.out.println("You don't have the proper card to build a research station in " + player.getCity().getName() + ".");
	            }
	            break;
	        case 2:
	            if (this.world.getResearchStationsAvailable() > 0) {
	                System.out.println("Invalid choice. Please try again.");
	                break;
	            }
	            
	            System.out.println("Choose a city to move the research station from:");
	            ArrayList<City> researchStationCities = this.world.getResearchStationCities();

	            // Display the cities with research stations
	            for (int i = 0; i < researchStationCities.size(); i++) {
	                System.out.println((i + 1) + ". " + researchStationCities.get(i).getName());
	            }

	            int cityChoice = getUserChoice(1, researchStationCities.size()); // Get user input for the city choice

	            try {
	                City selectedCity = researchStationCities.get(cityChoice - 1);
	                player.moveResearchStation(selectedCity);
	                System.out.println("The research station has been moved from " + selectedCity.getName() + " to " + player.getCity().getName() + ".");
	            } catch (NotAResearchStationException e) {
	                System.out.println("Invalid move: " + player.getCity().getName() + " is not a research station.");
	            } catch (PlayerDoesNotHaveProperCardException e) {
	                System.out.println("You don't have the proper card to move the research station from " + player.getCity().getName() + ".");
	            }
	            break;
	        default:
	            System.out.println("Invalid choice. Please try again.");
	            break;
	    }
	}

	private void performMoveAction(Player player) {
		System.out.println("Available cities to move:");
	    ArrayList<City> neighborCities = this.world.getCityNeighbours(player.getCity());

	    // Display the neighbor cities
	    for (int i = 0; i < neighborCities.size(); i++) {
	        System.out.println((i + 1) + ". " + neighborCities.get(i).getName());
	    }

	    int choice = getUserChoice(1, neighborCities.size()); // Get user input for the city choice

	    try {
	        // Move the player to the selected city by calling the existing move method in the Player class
	        player.move(neighborCities.get(choice - 1));
	        System.out.println("Moved to " + player.getCity().getName() + " successfully!");
	    } catch (NotANeighbourCityException e) {
	        System.out.println("Invalid move: Not a neighbor city!");
	    }

	  
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private int getUserChoice(int minChoice, int maxChoice) {
	    int choice;
	    while (true) {
	        try {
	            Scanner scanner = new Scanner(System.in);
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();
	            if (choice >= minChoice && choice <= maxChoice) {
	                break;
	            } else {
	                System.out.println("Invalid choice. Please try again.");
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid choice. Please try again.");
	        }
	    }
	    return choice;
	}
	
	
	
	private static ArrayList<Integer> getQuartilePositions(int size) {
        ArrayList<Integer> quartilePositions = new ArrayList<>();
        
        quartilePositions.add(size / 4);        // Position representing 25%
        quartilePositions.add(size / 2);        // Position representing 50%
        quartilePositions.add((3 * size) / 4);  // Position representing 75%
        
        return quartilePositions;
    }
	
	
	
	
	



	
	
	
	
}

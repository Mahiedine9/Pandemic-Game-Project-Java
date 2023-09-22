package pandemicgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map.Entry;
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
	private ArrayList<City> citiesAlreadyInfectedDuringCurrentInfectionPhase ;
	private Boolean gameIsOver ;
	private Integer currentPlayerIndex;
	private ArrayList<City> outBreakCities ;
	
	public PandemicGame (World world, ArrayList<Player> players ) {
		this.world = world ;
		this.players = players;
		this.currentlyPlaying = players.iterator() ;
		this.diseaseCubes = new HashMap<Disease, Integer>() ;
		this.citiesAlreadyInfectedDuringCurrentInfectionPhase =  new ArrayList<City>() ;
		this.gameIsOver = false ;
		this.currentPlayerIndex = 0;
		outBreakCities = new ArrayList<City>() ;
		
	}
	
	private void nothing () {} 
	
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
	
	
	public void run() {
	    this.initiate();

	    while (!this.gameIsOver) {
	        this.playTurn(this.whosTurnIsIt());

	        // Prompt the user to choose an action
	        System.out.println("1. View game state");
	        System.out.println("2. Proceed to the next turn");

	        int choice = getUserChoice(1, 2);

	        if (choice == 1 && ! this.gameIsOver) {
	        	
	            this.displayGameState();
	        }
	       
	        this.checkGameOver();
	    }
	}
	
	private Player whosTurnIsIt() {
		Player currentPlayer = this.players.get(this.currentPlayerIndex);
	    // Update currentPlayerIndex to point to the next player
	    this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
	    return currentPlayer;
	}

	private void displayGameState() {
	    boolean exit = false;

	    while (!exit) {
	    	displayWithDelay("", 1000);
	        System.out.println("Please choose which information you want to view:");
	        System.out.println("1. List of players, their roles, and their current locations");
	        System.out.println("2. Whose turn it is");
	        System.out.println("3. List of cities and their neighbors");
	        System.out.println("4. List of research stations built and the number of remaining stations");
	        System.out.println("5. List of remedies/discoveries");
	        System.out.println("6. Number of outbreaks that have occurred");
	        System.out.println("7. Current infection rate");
	        System.out.println("8. Cubes present in each city");
	        System.out.println("9. Current disease cube distribution");
	        System.out.println("10. Number of cards in each pile");
	        System.out.println("11. Exit");

	        int choice = getUserChoice(1, 11);

	        switch (choice) {
	            case 1:
	            	displayWithDelay("", 1000);
	                System.out.println("List of players, their roles, and their current locations:");
	                for (Player player : players) {
	                    System.out.println("Player: " + player.getName());
	                    System.out.println("Role: " + player.getClass().getSimpleName());
	                    System.out.println("Current Location: " + player.getCity().getName());
	                    System.out.println("-----------------------");
	                }
	                
	                break;
	            case 2:
	            	displayWithDelay("", 1000);
	                Player currentPlayer = whosTurnIsIt();
	                System.out.println("Current turn: " + currentPlayer.getName());
	                break;
	            case 3:
	            	displayWithDelay("", 1000);
	                System.out.println("List of cities and their neighbors:");
	                for (City city : world.getCities()) {
	                    ArrayList<City> neighbors = world.getCityNeighbours(city);
	                    StringBuilder neighborNames = new StringBuilder();
	                    for (City neighbor : neighbors) {
	                        neighborNames.append(neighbor.getName()).append(", ");
	                    }
	                    neighborNames.delete(neighborNames.length() - 2, neighborNames.length()); // Remove trailing comma and space
	                    System.out.println(city.getName() + " neighbors: " + neighborNames);
	                    
	                }
	                
	                break;
	            case 4:
	            	displayWithDelay("", 1000);
	                System.out.println("List of research stations built:");
	                for (City city : world.getCities()) {
	                    if (city.isResearchStation()) {
	                        System.out.println("- " + city.getName());
	                    }
	                }
	                int remainingStations = world.getResearchStationsAvailable();
	                System.out.println("Number of remaining research stations: " + remainingStations);
	               
	                break;
	            case 5:
	            	displayWithDelay("", 1000);
	                System.out.println("List of diseases with available remedies:");
	                for (Disease disease : world.getRemedies()) {
	                    System.out.println("- " + disease);
	                }
	                System.out.println("\nList of diseases not yet eradicated:");
	                for (Disease disease : Disease.values()) {
	                    System.out.println("- " + disease);
	                }
	                
	                break;
	            case 6:
	            	displayWithDelay("", 1000);
	                System.out.println("Number of outbreaks occurred: " + world.getNbOutBreaks());
	                
	                break;
	            case 7:
	            	displayWithDelay("", 1000);
	                int infectionRate = world.getInfectionRate();
	                System.out.println("Current infection rate: " + infectionRate);
	                
	                break;
	                
	            case 8:
	            	displayWithDelay("", 1000);
	                System.out.println("Diseases present in each city:");
	                for (City city : world.getCities()) {
	                    ArrayList<Disease> diseases = city.getDiseases();
	                    System.out.print(city.getName() + ": ");
	                    if (diseases.isEmpty()) {
	                        System.out.print("No diseases present");
	                    } else {
	                        for (int i = 0; i < diseases.size(); i++) {
	                            System.out.print(diseases.get(i));
	                            if (i < diseases.size() - 1) {
	                                System.out.print(", ");
	                            }
	                        }
	                    }
	                    System.out.println();
	                }
	                
	                break;
	            case 9:
	            	displayWithDelay("", 1000);
	                System.out.println("Current disease cube distribution:");
	                HashMap<Disease, Integer> diseaseCubes = this.diseaseCubes;
	                for (Entry<Disease, Integer> entry : diseaseCubes.entrySet()) {
	                    Disease disease = entry.getKey();
	                    int cubeCount = entry.getValue();
	                    System.out.println(disease + ": " + cubeCount + " cubes");
	                }   
	                break;
	            case 10:
	            	displayWithDelay("", 1000);
	                System.out.println("Number of cards in each pile:");
	                System.out.println("Player Deck: " + this.world.getPlayerDeck().size() + " cards");
	                System.out.println("Player Discard Pile: " + this.world.getplayerDiscardPile().size() + " cards");
	                System.out.println("Infection Deck: " + this.world.getInfectionDeck().size() + " cards");
	                System.out.println("Infection Discard Pile: " + this.world.getInfectionDiscardPile().size() + " cards");
	                
	                break;
	            case 11:
	            	displayWithDelay("", 1000);
	                exit = true;
	                displayWithDelay("", 1000);
	                break;
	                
	        }

	        displayWithDelay("", 1000);
	    }
	}

	private void checkGameOver() {
	    // 4 remedies are found
	    boolean scenario1 = true;
	    for (Disease remedy : Disease.values()) {
	        if (!world.getRemedies().contains(remedy)) {
	            scenario1 = false;
	            break;
	        }
	    }

	    // The number of outbreaks reaches 8
	    boolean scenario2 = world.getNbOutBreaks() >= 8;

	    // A player cannot draw 2 player cards in their turn
	    boolean scenario3 = world.getPlayerDeck().isEmpty();

	    // There are no available disease cubes to place on a city
	    // This case is handled in the method that adds disease cubes: addDiseaseCube()

	    // Update the gameIsOver attribute based on the scenarios
	    this.gameIsOver = scenario1 || scenario2 || scenario3;

	    // Print game state
	    if (this.gameIsOver) {
	        System.out.println("The game is over.");
	        if (scenario1) {
	            System.out.println("Congratulations! You have found all four remedies. You win the game!");
	        }
	        if (scenario2) {
	            System.out.println("Too many outbreaks have occurred. The world is in chaos. You lose the game!");
	        }
	        if (scenario3) {
	            System.out.println("No more player cards available. The situation is dire. You lose the game!");
	        }
	        if (!scenario1 && !scenario2 && !scenario3) {
	            System.out.println("Unfortunately, you were unable to find all four remedies. You have lost the game.");
	            System.out.println("The world has succumbed to the diseases. Better luck next time!");
	        }
	    } else {
	        System.out.println("The game is still ongoing.");
	    }
	    
	    displayWithDelay("", 1000);
	}
	
	
	private void initiate() {
		displayWithDelay("Welcome to the Pandemic game. Made by Sidahmed & Mahiedine.", 1000);
		displayWithDelay("Initializing the game....", 1000);
		
	    // Adding disease cubes
	    for (Disease disease : this.world.getDiseases()) {
	        this.diseaseCubes.put(disease, 24);
	    }
	    
	    // Creating and adding cards to the decks
	    ArrayList<InfectionCard> infectionCards = new ArrayList<InfectionCard>();
	    ArrayList<PlayerCard> playerCards = new ArrayList<PlayerCard>();
	    ArrayList<Disease> diseasesOnCards = new ArrayList<Disease>();
	    
	    // Generating disease distribution on cards
	    for (int i = 0; i < (this.world.getCities().size() / 4); i++) {
	        diseasesOnCards.add(Disease.DISEASE1);
	        diseasesOnCards.add(Disease.DISEASE2);
	        diseasesOnCards.add(Disease.DISEASE3);
	        diseasesOnCards.add(Disease.DISEASE4);
	    }
	    Collections.shuffle(diseasesOnCards);
	    
	    // Creating infection and player cards
	    Iterator<City> cityIterator = this.world.getCities().iterator();
	    Iterator<Disease> diseaseIterator = diseasesOnCards.iterator();
	    
	    while (cityIterator.hasNext()) {
	        City city = cityIterator.next();
	        Disease disease = diseaseIterator.next();
	        infectionCards.add(new InfectionCard(city, disease));
	        playerCards.add(new PlayerCard(city, disease));
	    }
	    
	    Collections.shuffle(infectionCards);
	    Collections.shuffle(playerCards);
	    
	    // Adding cards to the respective decks
	    while (!infectionCards.isEmpty()) {
	        InfectionCard infectionCard = infectionCards.get(0);
	        infectionCards.remove(0);
	        this.world.addInfectionCard(infectionCard);
	    }
	    
	    while (!playerCards.isEmpty()) {
	        PlayerCard playerCard = playerCards.get(0);
	        playerCards.remove(0);
	        this.world.addPlayableCard(playerCard);
	    }
	    
	    Collections.shuffle(this.world.getInfectionDeck());
	    Collections.shuffle(this.world.getPlayerDeck());
	    
	    // Distributing player cards and inserting epidemic cards
	    for (Player player : this.players) {
	        player.getHand().add((PlayerCard) this.world.drawPlayerDeck());
	        player.getHand().add((PlayerCard) this.world.drawPlayerDeck());
	    }
	    
	    @SuppressWarnings("unchecked")
	    ArrayList<PlayableCard>[] playerCardSubgroups = new ArrayList[4];
	    for (int i = 0; i < playerCardSubgroups.length; i++) {
	        playerCardSubgroups[i] = new ArrayList<PlayableCard>();
	    }
	    
	    Integer newSizeOfPlayerDeck = this.world.getPlayerDeck().size();
	    Integer subgroupNum = 0;
	    
	    for (int i = 0; i < newSizeOfPlayerDeck; i++) {
	        PlayerCard card = (PlayerCard) this.world.drawPlayerDeck();
	        playerCardSubgroups[subgroupNum].add(card);
	        if (PandemicGame.getQuartilePositions(newSizeOfPlayerDeck).contains(i)) {
	            subgroupNum++;
	        }
	    }
	    
	    for (int i = 0; i < playerCardSubgroups.length; i++) {
	        playerCardSubgroups[i].add(new EpidemicCard());
	        Collections.shuffle(playerCardSubgroups[i]);
	    }
	    
	    for (ArrayList<PlayableCard> subgroup : playerCardSubgroups) {
	        for (PlayableCard card : subgroup) {
	            if (card.getClass().equals(EpidemicCard.class)) {
	                this.world.addPlayableCard((EpidemicCard) card);
	            } else {
	                this.world.addPlayableCard((PlayerCard) card);
	            }
	        }
	    }
	    
	    // Launching the initial infection phase
	    this.launchInitialInfection();
	    
	    // Placing players in a randomly chosen city and constructing a research station
	    ArrayList<City> cityRandomizer = this.world.getCities();
	    Collections.shuffle(cityRandomizer);
	    
	    City startingCity = cityRandomizer.get(0); // Random selection
	    
	    for (Player player : this.players) {
	        player.initPosition(startingCity);
	    }
	    
	    startingCity.turnResearchStation();
	    this.world.countUsedResearchStation();

	    displayWithDelay("Game initiated successfully!", 1000);

	}


	private void launchInitialInfection() {
	    System.out.println("=== Initial Infection ===");

	    // Infect 3 cities with 3 disease cubes each
	    for (int i = 0; i < 3; i++) {
	        InfectionCard card = this.world.drawInfectionDeck();
	        City city = card.getCity();
	        Disease disease = card.getDisease();

	        System.out.println("Infecting " + city.getName() + " with " + disease.name() + " (3 cubes)");

	        this.addDiseaseCube(city, disease);
	        this.addDiseaseCube(city, disease);
	        this.addDiseaseCube(city, disease);
	        this.world.DiscardInfectionCard(card);
	    }

	    // Infect 3 more cities with 2 disease cubes each
	    for (int i = 0; i < 3; i++) {
	        InfectionCard card = this.world.drawInfectionDeck();
	        City city = card.getCity();
	        Disease disease = card.getDisease();

	        System.out.println("Infecting " + city.getName() + " with " + disease.name() + " (2 cubes)");

	        this.addDiseaseCube(city, disease);
	        this.addDiseaseCube(city, disease);
	        this.world.DiscardInfectionCard(card);
	    }

	    // Infect 3 additional cities with 1 disease cubes each
	    for (int i = 0; i < 3; i++) {
	        InfectionCard card = this.world.drawInfectionDeck();
	        City city = card.getCity();
	        Disease disease = card.getDisease();

	        System.out.println("Infecting " + city.getName() + " with " + disease.name() + " (1 cubes)");

	        this.addDiseaseCube(city, disease);
	        this.world.DiscardInfectionCard(card);
	    }
	    displayWithDelay("", 1000);
	}
	
	
	private void forceSevenCards(Player player) {
	    while (player.getHand().size() > 7) {
	        ArrayList<PlayerCard> hand = player.getHand();
	        HashMap<String, PlayerCard> handCards = new HashMap<>();

	        // Display the cards in the player's hand
	        for (PlayerCard card : hand) {
	            handCards.put((card.getCity().getName() + "-" + card.getDisease().name()).toLowerCase(), card);
	        }
	        System.out.println("=== Excess Cards ===");
	        System.out.println("You have more than 7 cards in your hand. Please select a card to remove:");
	        System.out.println("Available cards:");
	        System.out.println(handCards.keySet());

	        // Prompt the user to enter the name of the card they want to delete
	        Scanner scanner = new Scanner(System.in);
	        String input = scanner.nextLine().toLowerCase();

	        if (handCards.containsKey(input)) {
	            PlayerCard cardToDelete = handCards.get(input);
	            player.getHand().remove(cardToDelete);
	            System.out.println("Card deleted successfully.");
	        } else {
	            System.out.println("Invalid card name. Please try again.");
	        }
	        System.out.println();
	    }
	    displayWithDelay("", 1000);
	}
	
	
	private void launchInfectionPhase(Boolean epidemicCard) {
	    // Clear the list of cities infected during the current infection phase
	    this.clearInfectedCitiesForNextPhase();

	    // Determine the current infection rate based on whether an epidemic card was played
	    Integer currentInfectionRate = this.world.getInfectionRate();
	    if (epidemicCard) {
	        currentInfectionRate = 1;
	    }

	    // Start the infection phase
	    System.out.println("=== Infection Phase ===");
	    System.out.println("Current infection rate: " + currentInfectionRate);

	    // Draw infection cards from the infection deck based on the current infection rate
	    ArrayList<InfectionCard> cards = new ArrayList<>();
	    for (int i = 0; i < currentInfectionRate; i++) {
	        cards.add(this.world.drawInfectionDeck());
	    }

	    // Infect cities based on the drawn infection cards
	    for (InfectionCard card : cards) {
	        City city = card.getCity();
	        Disease disease = card.getDisease();
	        
	        // Add a disease cube to the city and discard the infection card
	        System.out.println("Infecting " + city.getName() + " with " + disease.name() + ".");
	        this.addDiseaseCube(city, disease);
	        this.world.DiscardInfectionCard(card);
	    }
	    displayWithDelay("", 1000);
	}
	
	
	private void addDiseaseCube(City city, Disease disease) {
	    if (this.diseaseCubes.get(disease) <= 0) {
	        // Handle end of game when there are no more disease cubes available for the disease
	        System.out.println("No more disease cubes available for " + disease.name() + ". The game is in a critical state.");
	        // Handle end of game logic here...
	        this.gameIsOver = true ;
	        System.out.println("The game is over. You lose the game!");
	    } else if (!this.citiesAlreadyInfectedDuringCurrentInfectionPhase.contains(city)) {
	        if (city.isOutBreakOfInfection(disease)) {
	            // Handle outbreak of infection in the city
	            System.out.println("An outbreak of " + disease.name() + " occurred in " + city.getName() + ".");
	            this.handleAddDiseaseCubeWhenOutBreak(city, disease);
	        } else {
	            // Add a disease cube to the city
	            System.out.println("Adding a " + disease.name() + " cube to " + city.getName() + ".");
	            city.getDiseases().add(disease);
	            this.diseaseCubes.put(disease, this.diseaseCubes.get(disease) - 1);
	            
	            if (city.checkIfIsOutBreakOnNextAdd(disease)) {
	                // Handle potential outbreak in the city on the next cube addition
	                System.out.println("An outbreak of " + disease.name() + " may occur in " + city.getName() + " on the next cube addition.");
	                city.declareOutBReakOfInfection(disease);
	                
	            }
	        }
	    }
	    
	}
	
	
	private void removeDiseaseCube(Disease disease, int cubeCount) {
	    // Retrieve the current count of disease cubes for the given disease
	    int currentCount = diseaseCubes.getOrDefault(disease, 0);
	    
	    // Calculate the updated count by adding the cubeCount to the current count
	    int updatedCount = currentCount + cubeCount;
	    
	    // Update the disease cube count in the diseaseCubes HashMap
	    diseaseCubes.put(disease, updatedCount);
	}
	
	private void handleAddDiseaseCubeWhenOutBreak(City city, Disease disease) {
		if (! this.outBreakCities.contains(city)) {
			this.outBreakCities.add(city) ;
			this.world.addOutBreak();
		}
		
		this.world.addOutBreak();
	    // Add disease cubes to neighboring cities during an outbreak
	    for (City neighbourCity : this.world.getCityNeighbours(city)) {
	        this.addDiseaseCube(neighbourCity, disease);
	    }
	}
	
	
	private void clearInfectedCitiesForNextPhase() {
	    // Clear the list of cities already infected during the current infection phase
	    this.citiesAlreadyInfectedDuringCurrentInfectionPhase.clear();
	}
	
	
	private void handleEpidemicCard(EpidemicCard epidemicCard) {
	    // Increase the global infection rate
	    System.out.println("An epidemic has occurred! Increasing the global infection rate.");
	    this.world.upInfectionRate();

	    // Trigger an infection phase with a single card
	    System.out.println("Initiating an infection phase with one card.");
	    this.launchInfectionPhase(true);

	    // Shuffle the infection discard pile and place it on top of the infection deck
	    System.out.println("Shuffling the infection discard pile and placing it on top of the infection deck.");
	    Collections.shuffle(this.world.getInfectionDiscardPile());
	    while (!this.world.getInfectionDiscardPile().isEmpty()) {
	        this.world.addInfectionCard(this.world.getInfectionDiscardPile().pop());
	    }

	    // Discard the epidemic card
	    System.out.println("Discarding the epidemic card.");
	    this.world.getplayerDiscardPile().add(epidemicCard);
	    
	    displayWithDelay("", 1000);
	}
	
	
	private void playTurn(Player player) {
	    // A player needs to perform four actions from the available options
	    while (player.hasActionsRemaining()) {
	        System.out.println(player.getName() + ", it's your turn to take action.");
	        this.performAction(player);
	    }

	    // Draw 2 player cards from the player deck and add them to the player's hand
	    PlayableCard card1 = this.world.drawPlayerDeck();
	    PlayableCard card2 = this.world.drawPlayerDeck();
	    
	    // Handle the first card
	    if (card1 instanceof EpidemicCard) {
	        this.handleEpidemicCard((EpidemicCard) card1);
	    } else {
	        player.getHand().add((PlayerCard) card1);
	    }
	    
	    // Handle the second card
	    if (card2 instanceof EpidemicCard) {
	        this.handleEpidemicCard((EpidemicCard) card2);
	    } else {
	        player.getHand().add((PlayerCard) card2);
	    }

	    // Check if the player's hand size exceeds 7 cards and take necessary actions
	    if (player.getHand().size() > 7) {
	        this.forceSevenCards(player);
	    }

	    // Initiate the infection phase
	    System.out.println("Initiating the infection phase.");
	    this.launchInfectionPhase(false);
	    
	    System.out.println(); // Add an empty line after each information display
        try {
            Thread.sleep(1000); // Adjust the duration as needed (e.g., 1000 milliseconds = 1 second)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
	            // Perform move action
	            System.out.println("You chose to move.");
	            this.performMoveAction(player);
	            displayWithDelay("", 1000);
	            break;
	        case 2:
	            // Perform build action
	            System.out.println("You chose to build a research station.");
	            this.perfromBuildAction(player);
	            displayWithDelay("", 1000);
	            break;
	        case 3:
	            // Perform find action
	            System.out.println("You chose to find a remedy.");
	            this.perfromFindAction(player);
	            displayWithDelay("", 1000);
	            break;
	        case 4:
	            // Perform treat action
	            System.out.println("You chose to treat a disease.");
	            this.perfromTreatAction(player);
	            displayWithDelay("", 1000);
	            break;
	        case 5:
	            // Do nothing
	            System.out.println("You chose to do nothing.");
	            player.doNothing() ;
	            displayWithDelay("", 1000);
	            break;
	        default:
	            System.out.println("Invalid choice. Please try again.");
	            displayWithDelay("", 1000);
	            break;
	    }
	    
	    displayWithDelay("", 1000);
	}
	
	
	private void perfromTreatAction(Player player) {
	    System.out.println("Choose a disease to treat:");
	    ArrayList<Disease> diseases = this.world.getDiseases();

	    // Display the available diseases in the current city
	    for (int i = 0; i < diseases.size(); i++) {
	        System.out.println((i + 1) + ". " + diseases.get(i).name());
	    }

	    int diseaseChoice = getUserChoice(1, diseases.size()); // Get user input for the disease choice

	    Disease selectedDisease = diseases.get(diseaseChoice - 1);

	    // Get the count of cubes before and after treatment
	    Integer nbCubesBeforeRemoval = player.getCity().howManyCubes(selectedDisease);
	    player.treatDisease(selectedDisease);
	    Integer nbCubesAfterRemoval = player.getCity().howManyCubes(selectedDisease);

	    // Calculate the number of cubes treated
	    Integer treatedCubesCount = nbCubesBeforeRemoval - nbCubesAfterRemoval;
	    
	    // Display the result of the treatment
	    System.out.println("Disease " + selectedDisease.name() + " has been treated. " + treatedCubesCount + " cube(s) removed.");

	    // Check if the player is a doctor or a remedy exists for the disease
	    if (player.getClass().equals(Doctor.class) || this.world.getRemedies().contains(selectedDisease)) {
	        this.removeDiseaseCube(selectedDisease, treatedCubesCount);
	    } else {
	        this.removeDiseaseCube(selectedDisease, 1);
	    }
	    
	    displayWithDelay("", 1000);
	}
	
	
	private void perfromFindAction(Player player) {
	    System.out.println("Choose a disease to find a remedy for:");
	    ArrayList<Disease> diseases = this.world.getDiseases();

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
	        System.out.println("Invalid action: " + player.getCity().getName() + " is not a research station. You need to be at a research station to find a remedy.");
	    } catch (NotEnoughCardsToFindARemedyException e) {
	        System.out.println("Not enough cards to find a remedy for " + selectedDisease.name() + ". Collect more cards to discover a remedy.");
	    }
	    
	    displayWithDelay("", 1000);
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
	                System.out.println("Unable to build a new research station. No more research stations available.");
	            } catch (PlayerDoesNotHaveProperCardException e) {
	                System.out.println("Unable to build a new research station. You don't have the proper card for " + player.getCity().getName() + ".");
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
	                System.out.println("Invalid move. " + player.getCity().getName() + " is not a research station.");
	            } catch (PlayerDoesNotHaveProperCardException e) {
	                System.out.println("Unable to move the research station. You don't have the proper card for " + player.getCity().getName() + ".");
	            }
	            break;
	        default:
	            System.out.println("Invalid choice. Please try again.");
	            break;
	    }
	    
	    displayWithDelay("", 1000);
	}
	
	
	private void performMoveAction(Player player) {
	    System.out.println("Available cities to move:");
	    City currentCity = player.getCity();

	    ArrayList<City> neighborCities = this.world.getCityNeighbours(player.getCity());

	    if (neighborCities == null) {
	        System.out.println("Invalid move: Sorry you can not move more than one time in a turn.");
	        return; // Exit the method to prevent further execution
	    }

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
	        System.out.println("Invalid move: The selected city is not a neighbor!");
	    }

	    displayWithDelay("", 1000);
	}
	
	
	private int getUserChoice(int minChoice, int maxChoice) {
	    int choice;
	    
	    // Loop until a valid choice is entered
	    while (true) {
	        try {
	            Scanner scanner = new Scanner(System.in);
	            System.out.print("Enter your choice: ");
	            
	            // Read user input as choice
	            choice = scanner.nextInt();
	            
	            // Check if the choice is within the valid range
	            if (choice >= minChoice && choice <= maxChoice) {
	                break;  // Valid choice, exit the loop
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

	    // Calculate the positions representing 25%, 50%, and 75% of the size
	    quartilePositions.add(size / 4);        // Position representing 25%
	    quartilePositions.add(size / 2);        // Position representing 50%
	    quartilePositions.add((3 * size) / 4);  // Position representing 75%

	    return quartilePositions;
	}
	
	
	private void displayWithDelay(String message, int delay) {
	    System.out.println(message);
	    try {
	        Thread.sleep(delay);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
}

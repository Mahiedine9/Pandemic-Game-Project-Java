package pandemicgame;

import java.util.ArrayList;

import exception.NoMoreResearchStationAvailableException;
import exception.NotANeighbourCityException;
import exception.NotAResearchStationException;
import exception.NotEnoughCardsToFindARemedyException;
import exception.PlayerDoesNotHaveProperCardException;

/**
 * The Player class represents a player in the Pandemic game.
 * It is an abstract class that provides common functionality and attributes for different player types.
 */
public abstract class Player {
	
	protected String name;
	protected City currentCity;
	protected ArrayList<PlayerCard> hand = new ArrayList<PlayerCard>();
	protected World world;
	protected Integer nbActionsRemaining = 4;
	
	public Player(String name, City city, World world) {
		this.name = name;
		this.currentCity = city;
		this.world = world;
	}
	
	/**
	 * Retrieves the name of the player.
	 *
	 * @return The player's name.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Retrieves the current city of the player.
	 *
	 * @return The current city of the player.
	 */
	public City getCity() {
		return this.currentCity;
	}
	
	/**
	 * Retrieves the hand of player cards.
	 *
	 * @return The player's hand of cards.
	 */
	public ArrayList<PlayerCard> getHand() { 
		return this.hand;
	}
	
	/**
	 * Moves the player to the specified city if it is a neighbor of the current city.
	 * Throws a NotANeighbourCityException if the specified city is not a neighbor.
	 * Decreases the remaining action count by 1.
	 *
	 * @param newCity The city to move to.
	 * @throws NotANeighbourCityException If the specified city is not a neighbor.
	 */
	public void move(City newCity) throws NotANeighbourCityException {
		if (this.world.getCityNeighbours(currentCity).contains(newCity))
			this.currentCity = newCity;
		else
			throw new NotANeighbourCityException();
		
		this.nbActionsRemaining--;
	}
	
	/**
	 * Builds a research station in the current city if the player has a card for that city.
	 * Throws a NoMoreResearchStationAvailableException if there are no more research stations available.
	 * Throws a PlayerDoesNotHaveProperCardException if the player does not have the proper card for the current city.
	 * Decreases the remaining action count by 1.
	 *
	 * @throws NoMoreResearchStationAvailableException If there are no more research stations available.
	 * @throws PlayerDoesNotHaveProperCardException If the player does not have the proper card for the current city.
	 */
	public void buildResearchStation() throws NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException {
		if (this.world.getResearchStationsAvailable() <= 0) 
			throw new NoMoreResearchStationAvailableException();
		
		Boolean buildHasBeenSuccessful = false;
		for (PlayerCard card : this.hand) {
			if (card.getCity().equals(this.currentCity)) {
				this.currentCity.turnResearchStation();
				buildHasBeenSuccessful = true;
				this.hand.remove(card); // Card is discarded
				// Card needs to be added to the discard pile
				break;
			}
		}
		   
		if (!buildHasBeenSuccessful) 
			throw new PlayerDoesNotHaveProperCardException();
		   
		this.world.countUsedResearchStation();
		this.nbActionsRemaining--;
	}
	
	/**
	 * Moves the research station from the current city to the specified city if the player has a card for the current city.
	 * Throws a NotAResearchStationException if the specified city is not a research station.
	 * Throws a PlayerDoesNotHaveProperCardException if the player does not have the proper card for the current city.
	 * Decreases the remaining action count by 1.
	 *
	 * @param otherCity The city to move the research station to.
	 * @throws NotAResearchStationException If the specified city is not a research station.
	 * @throws PlayerDoesNotHaveProperCardException If the player does not have the proper card for the current city.
	 */
	public void moveResearchStation(City otherCity) throws NotAResearchStationException, PlayerDoesNotHaveProperCardException {
		if (!otherCity.isResearchStation())
			throw new NotAResearchStationException();
		
		Boolean buildHasBeenSuccessful = false;
		for (PlayerCard card : this.hand) {
			if (card.getCity().equals(this.currentCity)) {
				otherCity.turnNotResearchStation();
				this.currentCity.turnResearchStation();
				buildHasBeenSuccessful = true;
				this.hand.remove(card); // Card is discarded
				// Card needs to be added to the discard pile
				break;
			}
		}
		if (!buildHasBeenSuccessful)
			throw new PlayerDoesNotHaveProperCardException();
		
		this.nbActionsRemaining--;
	}
	
	/**
	 * Discovers a remedy for the specified disease if the current city has a research station and the player has enough cards of that disease.
	 * Throws a NotAResearchStationException if the current city is not a research station.
	 * Throws a NotEnoughCardsToFindARemedyException if the player does not have enough cards of the specified disease.
	 * Decreases the remaining action count by 1.
	 *
	 * @param disease The disease for which to discover a remedy.
	 * @throws NotAResearchStationException If the current city is not a research station.
	 * @throws NotEnoughCardsToFindARemedyException If the player does not have enough cards of the specified disease.
	 */
	public void discoverRemedy(Disease disease) throws NotAResearchStationException, NotEnoughCardsToFindARemedyException {
		if (!this.currentCity.isResearchStation())
			throw new NotAResearchStationException();
		
		Boolean hasFiveCards = false;
		ArrayList<PlayerCard> theCards = new ArrayList<PlayerCard>();
		
		for (PlayerCard card : this.hand) {
			if (card.getDisease().equals(disease))
				theCards.add(card);
			
			if (theCards.size() == 5) {
				hasFiveCards = true;
				break;
			}
		}
		
		if (hasFiveCards) {
			for (PlayerCard card : theCards)
				this.hand.remove(card);
			// Cards need to be added to the discard pile
			this.world.addRemedy(disease);
		} else {
			throw new NotEnoughCardsToFindARemedyException();
		}
		
		this.nbActionsRemaining--;
	}
	
	/**
	 * Treats the specified disease in the current city.
	 * If the player has discovered a remedy for the disease, removes all disease cubes of that disease from the current city.
	 * Otherwise, removes one disease cube of that disease from the current city.
	 * Decreases the remaining action count by 1.
	 *
	 * @param disease The disease to treat.
	 */
	public void treatDisease(Disease disease) {
		if (!this.world.getRemedies().contains(disease))
			this.currentCity.removeDisease(disease);
		else {
			while (this.currentCity.getDiseases().contains(disease))
				this.currentCity.removeDisease(disease);
		}
		
		this.nbActionsRemaining--;
	}
	
	/**
	 * Initializes the player's position to the specified city.
	 *
	 * @param city The city to set as the player's current city.
	 */
	public void initPosition(City city) {
		this.currentCity = city;
	}
	
	/**
	 * Checks if the player has remaining actions.
	 *
	 * @return True if the player has remaining actions, false otherwise.
	 */
	public Boolean hasActionsRemaining() {
		return (this.nbActionsRemaining > 0);
	}
	
	/**
	 * Performs no action and decreases the remaining action count by 1.
	 */
	public void doNothing() {
		this.nbActionsRemaining--;
	}
}
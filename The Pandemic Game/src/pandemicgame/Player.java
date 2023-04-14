/**
 * The Player class represents a player in the Pandemic board game.
 * It contains information about the player's name, role, hand of cards, number of cards, current position on the map and remaining actions.
 */
 package pandemicgame;

import java.util.ArrayList;
import java.util.List;

public class Player {

	// The player's hand of cards
	private ArrayList<Card> playerHand;

	// The player's name
	private String name;

	// The number of cards in the player's hand
	private int cardsNB;

	// The city the player is currently in
	private City position;

	// The number of actions remaining for the player on their turn
	private int NBActionsRemaining;

	/**
	 * Constructor for the Player class. Initializes the player's name, role, hand of cards, number of cards, and remaining actions.
	 *
	 * @param name The name of the player
	 * @param role The role of the player
	 */
	public Player(String name) {
		this.name = name;
		this.playerHand = new ArrayList<>();
		this.cardsNB = 0;
		this.NBActionsRemaining = 4;
	}

	/**
	 * Returns the name of the player.
	 *
	 * @return The player's name
	 */
	public String GetName(){
		return this.name;
	}

	/**
	 * Returns the player's hand of cards.
	 *
	 * @return The player's hand of cards
	 */
	public List<Card> GetPlayerHand(){
		return this.playerHand;
	}

	/**
	 * Discards a card from the player's hand.
	 *
	 * @param card The card to discard
	 */
	public void Discard(Card card){
		this.playerHand.remove(card);
		this.cardsNB-=1;
	}

	/**
	 * Decrements the number of remaining actions for the player.
	 */
	public void UpdateNbActionsRemaining(){
		this.NBActionsRemaining -=1;
	}

	/**
	 * Returns the number of cards in the player's hand.
	 *
	 * @return The number of cards in the player's hand
	 */
	public int GetNBCards(){
		return this.cardsNB;
	}

	/**
	 * Adds a card to the player's hand.
	 *
	 * @param card The card to add
	 */
	public void AddCard(Card card){
		this.playerHand.add(card);
		this.cardsNB+=1;
	}

	/**
	 * Returns the city the player is currently in.
	 *
	 * @return The city the player is currently in
	 */
	public City GetPosition(){
		return this.position;
	}

	/**
	 * Sets the city the player is currently in.
	 *
	 * @param city The city to set as the player's current position
	 */
	public void SetPosition(City city){
		this.position = city;
	}

	/**
	 * Returns the number of actions remaining for the player.
	 *
	 * @return The number of actions remaining for the player
	 */
	public int GetNBActionsRemaining(){
		return this.NBActionsRemaining;
	}
}

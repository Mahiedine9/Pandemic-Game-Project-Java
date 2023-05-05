/**
 * The Player class represents a player in the Pandemic board game.
 * It contains information about the player's name, role, hand of cards, number of cards, current position on the map and remaining actions.
 */
 package old;

import java.util.ArrayList;
import java.util.List;

import old.PlayerCard;
import pandemicgame.Card;
import pandemicgame.City;
import pandemicgame.Disease;

public class Player2 {

	// The player's hand of cards
	private ArrayList<PlayerCard> playerHand;

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
	public Player2(String name) {
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
	public List<PlayerCard> GetPlayerHand(){
		return this.playerHand;
	}

	/**
	 * Discards a card from the player's hand.
	 *
	 * @param card The card to discard
	 */
	public void Discard(PlayerCard card){
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
	
	public void CureDisease() {
	    // Loop through all the possible diseases
	    for (Disease disease : Disease.values()) {
	        // Count how many cards the player has for this disease
	        int nbCards = 0;
	        for (PlayerCard card : player.GetPlayerHand()) {
	            if (card.getDisease() == disease) {
	                nbCards++;
	            }
	        }
	        // If the player has 5 cards of the same disease, cure it and remove the cards from their hand
	        if (nbCards == 5) {
	            for (PlayerCard card : player.GetPlayerHand()) {
	                if (card.getDisease() == disease) {
	                    player.Discard(card);
	                }
	            
	            }
	            this.UpdateNbActionsRemaining();
	            this.position.addCure(disease);  
	        }
	}
	
	    
	public void TreatDisease(Disease disease) {
		//check if the disaese is cured 
		if (this.position.getCures().contains(disease)) {
			//if the disease is cured, remove all cubes of that disease from the city
			this.position.removeAllCubes(disease);
			this.UpdateNbActionsRemaining();
		} else {
			//if the disease is not cured, remove one cube of that disease
			this.position.removeCube(disease);
			this.UpdateNbActionsRemaining();
		}
	}	

	
}

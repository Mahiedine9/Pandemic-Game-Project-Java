/**
 * The Scientific class represents a player who is able to cure diseases in the game Pandemic.
 * This class extends the Player class and has a method CureDisease which allows the player to find a cure for a given disease.
 */
public class Scientific extends Player{

    /**
     * Constructs a new Scientific player.
     */
    public Scientific(){
        super();
    }

    /**
     * Allows the player to find a cure for a given disease.
     * @param disease The disease to find a cure for.
     * @return A new instance of the Cure class representing the cure found.
     */
    /**
     * Allows the player to find a cure for a given disease.
     * @param disease The disease to find a cure for.
     * @return A new instance of the Cure class representing the cure found.
     */
    public Cure CureDisease() {
	    // Loop through all the possible diseases
	    for (Disease disease : Disease.values()) {
	        // Count how many cards the player has for this disease
	        int nbCards = 0;
	        for (PlayerCard card : super.GetPlayerHand()) {
	            if (card.getDisease() == disease) {
	                nbCards++;
	            }
	        }
	        // If the player has 5 cards of the same disease, cure it and remove the cards from their hand
	        if (nbCards >= 4) {
	            for (PlayerCard card : super.GetPlayerHand()) {
	                if ( card.getDisease() == disease) {
	                    super.Discard(card);
	                }
	            }
	            super.UpdateNbActionsRemaining();
	            return new Cure(disease);
	        }
	    }
	    return null;
	}


}
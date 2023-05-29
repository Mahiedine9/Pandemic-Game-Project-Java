package pandemicgame;

import java.util.ArrayList;

import exception.NotAResearchStationException;
import exception.NotEnoughCardsToFindARemedyException;

public class Scientific extends Player {

	public Scientific(String name, City city, World world) {
		super(name, city, world);
	}

	/**
	 * Attempts to discover a remedy for a disease as a Scientific player.
	 *
	 * @param disease The disease for which a remedy is being discovered.
	 * @throws NotAResearchStationException      If the current city does not have a research station.
	 * @throws NotEnoughCardsToFindARemedyException If the player does not have enough cards of the given disease to discover a remedy.
	 */
	public void discoverRemedy(Disease disease) throws NotAResearchStationException, NotEnoughCardsToFindARemedyException {

		if (!this.currentCity.isResearchStation())
			throw new NotAResearchStationException();

		Boolean hasFourCards = false;
		ArrayList<PlayerCard> theCards = new ArrayList<PlayerCard>();

		for (PlayerCard carte : this.hand) {
			if (carte.getDisease().equals(disease))
				theCards.add(carte);
			if (theCards.size() == 4) {
				hasFourCards = true;
				break;
			}
		}

		if (hasFourCards) {
			for (PlayerCard carte : theCards)
				this.hand.remove(carte);
			// cartes besoin d'etre ajoutée la pile de discard
			this.world.addRemedy(disease);
		} else {
			throw new NotEnoughCardsToFindARemedyException();
		}
	}

}
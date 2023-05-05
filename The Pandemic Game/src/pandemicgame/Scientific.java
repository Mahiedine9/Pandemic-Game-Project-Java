package pandemicgame;

import java.util.ArrayList;

import exception.NotAResearchStationException;
import exception.NotEnoughCardsToFindARemedyException;

public class Scientific extends Player {
	
	public Scientific  (String name, City city, World world) {
		   super(name, city, world) ;
		}
	
    public void discoverRemedy(Disease disease) throws NotAResearchStationException, NotEnoughCardsToFindARemedyException {
		
		if  (! this.currentCity.isResearchStation())
			throw new NotAResearchStationException() ; 
		
		Boolean hasFourCards = false ;
		ArrayList<PlayerCard> theCards = new ArrayList<PlayerCard>() ;
		
		for (PlayerCard carte : this.hand) {
			if (carte.getDisease().equals(disease))  theCards.add(carte) ;
			if ((theCards.size() == 4)) { hasFourCards = true ; break ;}
		}
		
		if (hasFourCards) {
			for (PlayerCard carte : theCards) this.hand.remove(carte) ;
			// cartes besoin d'etre ajoutée la pile de discard
			this.world.addRemedy(disease) ;}
		
		else throw new NotEnoughCardsToFindARemedyException() ;
}
	

}

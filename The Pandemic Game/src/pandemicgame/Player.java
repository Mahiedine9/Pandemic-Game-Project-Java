package pandemicgame;

import java.util.ArrayList;

import exception.NoMoreResearchStationAvailableException;
import exception.NotANeighbourCityException;
import exception.NotAResearchStationException;
import exception.NotEnoughCardsToFindARemedyException;
import exception.PlayerDoesNotHaveProperCardException;

public abstract class Player {
	
	protected String name ;
	protected City currentCity ;
	protected ArrayList<PlayerCard> hand =  new ArrayList<PlayerCard>()  ;
	protected World world ;
	protected Integer nbActionsRemaining = 4 ;
	
	public Player(String name, City city, World world) {
		this.name = name ;
		this.currentCity = city ;
		this.world = world ;
	}
	
	public String getName() {
		return this.name ;
	}
	
	public City getCity() {
		return this.currentCity ;
	}
	
	public ArrayList<PlayerCard> getHand() { 
		return this.hand ;
	}
	
	public void move (City newCity) throws NotANeighbourCityException {
		if (this.world.getCityNeighbours(currentCity).contains(newCity ))
			this.currentCity = newCity ;
		else throw new NotANeighbourCityException() ;
		
		this.nbActionsRemaining -- ;
	}
	
	public void buildResearchStation() throws NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException {
		
			if (this.world.getResearchStationsAvailable() <= 0 ) 
			throw new NoMoreResearchStationAvailableException() ;
		
		
		   Boolean buildHasBeenSuccesfull  = false ;
		   for (PlayerCard carte : this.hand) {
			if (carte.getCity().equals(this.currentCity)){
				this.currentCity.turnResearchStation();
				buildHasBeenSuccesfull = true ;
				this.hand.remove(carte) ; // carte est défaussée
				// carte besoin d'etre ajoutée la pile de discard
				break ;
				}
		   }
		   
		   
		   if (! buildHasBeenSuccesfull ) 
			throw new PlayerDoesNotHaveProperCardException() ;
		   
		   this.world.countUsedResearchStation();
		   this.nbActionsRemaining -- ;
		   }
		
	
	public void moveResearchStation( City otherCity) throws NotAResearchStationException, PlayerDoesNotHaveProperCardException {
		
		
	if  (! otherCity.isResearchStation())
		throw new NotAResearchStationException() ; 
	
	Boolean buildHasBeenSuccesfull  = false ;	
	for (PlayerCard carte : this.hand) {
		if (carte.getCity().equals(this.currentCity)){
			otherCity.turnNotResearchStation() ;
			this.currentCity.turnResearchStation() ;
			buildHasBeenSuccesfull = true ;
			this.hand.remove(carte) ; // carte est défaussée
			// carte besoin d'etre ajoutée la pile de discard
			break ;
			}
		}
	if (! buildHasBeenSuccesfull ) 
		throw new PlayerDoesNotHaveProperCardException() ;
	
	this.nbActionsRemaining -- ;
	}
	
	public void discoverRemedy(Disease disease) throws NotAResearchStationException, NotEnoughCardsToFindARemedyException {
		
		if  (! this.currentCity.isResearchStation())
			throw new NotAResearchStationException() ; 
		
		Boolean hasFiveCards = false ;
		ArrayList<PlayerCard> theCards = new ArrayList<PlayerCard>() ;
		
		for (PlayerCard carte : this.hand) {
			if (carte.getDisease().equals(disease))  theCards.add(carte) ;
			if ((theCards.size() == 5)) { hasFiveCards = true ; break ;}
		}
		
		if (hasFiveCards) {
			for (PlayerCard carte : theCards) this.hand.remove(carte) ;
			// cartes besoin d'etre ajoutée la pile de discard
			this.world.addRemedy(disease) ;}
		
		else throw new NotEnoughCardsToFindARemedyException() ;
		
		this.nbActionsRemaining -- ;
}
	
	public void treatDisease(Disease disease) {
		

		if (! this.world.getRemedies().contains(disease))
				this.currentCity.removeDisease(disease) ;
		
		else while (this.currentCity.getDiseases().contains(disease)) 
			 this.currentCity.removeDisease(disease) ;
		
		
		this.nbActionsRemaining -- ;}
	
	
	public void initPosition(City city) {
		this.currentCity = city ;
	}
	
	
	public Boolean hasActionsRemaning() {
		return (this.nbActionsRemaining > 0) ;
	}
	
	
	

}

package pandemicgame;

import exception.NoMoreResearchStationAvailableException;
import exception.NotAResearchStationException;

public class Expert extends Player {
	
	public Expert (String name, City city, World world) {
	   super(name, city, world) ;
	}
	
	public void buildResearchStation() throws NoMoreResearchStationAvailableException {
		
		if (this.world.getResearchStationsAvailable() <= 0 ) 
			throw new NoMoreResearchStationAvailableException() ;
		  
		this.currentCity.turnResearchStation() ;
	}
	
	public void moveResearchStation( City otherCity) throws NotAResearchStationException  {
		
		
		if  (! otherCity.isResearchStation())
			throw new NotAResearchStationException() ; 
		
		
		otherCity.turnNotResearchStation() ;
		this.currentCity.turnResearchStation() ;
				
	
		
		this.nbActionsRemaining -- ;
		}

}

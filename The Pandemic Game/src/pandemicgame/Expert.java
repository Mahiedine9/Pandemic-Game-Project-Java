package pandemicgame;

import exception.NoMoreResearchStationAvailableException;
import exception.NotAResearchStationException;

// The Expert class represents a player with the ability to build and move research stations
public class Expert extends Player {
	
	// Constructor for creating an Expert player
	public Expert(String name, City city, World world) {
	   super(name, city, world);
	}
	
	// Build a research station in the current city
	public void buildResearchStation() throws NoMoreResearchStationAvailableException {
		// Check if there are any research stations available
		if (this.world.getResearchStationsAvailable() <= 0) {
			throw new NoMoreResearchStationAvailableException();
		}
		  
		// Turn the current city into a research station
		this.currentCity.turnResearchStation();
	}
	
	// Move a research station from the current city to another city
	public void moveResearchStation(City otherCity) throws NotAResearchStationException {
		// Check if the other city is a research station
		if (!otherCity.isResearchStation()) {
			throw new NotAResearchStationException();
		}
		
		// Turn the other city into a non-research station
		otherCity.turnNotResearchStation();
		
		// Turn the current city into a research station
		this.currentCity.turnResearchStation();
		
		this.nbActionsRemaining--;
	}
}
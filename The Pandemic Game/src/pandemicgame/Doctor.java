package pandemicgame;

// The Doctor class represents a player with the ability to treat diseases
public class Doctor extends Player {
	
	// Constructor for creating a Doctor player
	public Doctor(String name, City city, World world) {
		super(name, city, world);
	}
	
	// Method for treating a specific disease in the current city
	public void treatDisease(Disease disease) {
		
		// Continue treating the disease as long as it exists in the current city
		while (this.currentCity.getDiseases().contains(disease)) {
			this.currentCity.removeDisease(disease); // Remove one instance of the disease from the city
		}
		 
		// If the disease is not yet cured, decrement the remaining action count
		if (!this.world.getRemedies().contains(disease)) {
			this.nbActionsRemaining--;
		}
	}
}
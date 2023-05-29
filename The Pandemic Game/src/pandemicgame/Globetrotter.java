package pandemicgame;

// The Globetrotter class represents a player with the ability to freely move between cities
public class Globetrotter extends Player {

	// Constructor for creating a Globetrotter player
	public Globetrotter(String name, City city, World world) {
		super(name, city, world);
	}
	
	// Move the player to a new city
	public void move(City newCity) {
		this.currentCity = newCity;
	}
}
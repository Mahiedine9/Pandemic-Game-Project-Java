package pandemicgame;

// The InfectionCard class represents a card containing information about a city and a disease
public class InfectionCard {
	private City city; // The city associated with the card
	private Disease disease; // The disease associated with the card
	
	// Constructor for creating an InfectionCard
	public InfectionCard(City city, Disease disease) {
		this.city = city;
		this.disease = disease;
	}
	
	// Get the city associated with the card
	public City getCity() {
		return this.city;
	}
	
	// Get the disease associated with the card
	public Disease getDisease() {
		return this.disease;
	}
}
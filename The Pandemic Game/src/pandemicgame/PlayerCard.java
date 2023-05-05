package pandemicgame;

public class PlayerCard implements   PlayableCard {
	private City city ;
	private Disease disease;
	
	public PlayerCard(City city,Disease disease ) {
		this.city = city;
		this.disease = disease ;
	}
	public City  getCity() {
		return this.city;
	}
	public Disease getDisease() {
		return this.disease ;
	}
	
	
	
}

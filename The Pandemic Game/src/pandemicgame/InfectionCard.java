package pandemicgame;

public class InfectionCard  {
	private City city;
	private Disease disease ;
	
	public InfectionCard(City city, Disease disease){
		this.city = city;
		this.disease = disease;
			
	}
	public City getCity() {
		return this.city;
	}
	public Disease getDisease() {
		return this.disease;
	}

}

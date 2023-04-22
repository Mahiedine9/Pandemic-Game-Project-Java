package pandemicgame;


public class InfectionCard implements Card {
	private City city;
	
	public InfectionCard(City city, Disease disease){
		this.city = city;
		this.disease = disease;
			
	}
	public City GetCity() {
		return this.city;
	}
	public Disease GetDisease() {
		return this.disease();
	}

}

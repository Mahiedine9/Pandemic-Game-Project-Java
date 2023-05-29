package pandemicgame;

public class PlayerCard implements PlayableCard {
	private City city;
	private Disease disease;

	public PlayerCard(City city, Disease disease) {
		this.city = city;
		this.disease = disease;
	}

	/**
	 * Returns the city associated with this player card.
	 *
	 * @return The city associated with this player card.
	 */
	public City getCity() {
		return this.city;
	}

	/**
	 * Returns the disease associated with this player card.
	 *
	 * @return The disease associated with this player card.
	 */
	public Disease getDisease() {
		return this.disease;
	}

}
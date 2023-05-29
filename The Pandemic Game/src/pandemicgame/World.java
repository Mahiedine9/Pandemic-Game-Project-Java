package pandemicgame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class World {

	private HashMap<City, ArrayList<City>> cities = new HashMap<City, ArrayList<City>>();
	private ArrayList<Disease> diseases = new ArrayList<Disease>(Arrays.asList(Disease.values()));
	private ArrayList<Disease> remedies = new ArrayList<Disease>();
	private Integer infectionRate = 2;
	private Integer outBreaks = 0;
	private Integer researchStationsAvailable = 6;
	private Stack<InfectionCard> infectionDeck = new Stack<InfectionCard>();
	public Stack<InfectionCard> infectionDiscardPile = new Stack<InfectionCard>();
	private Stack<PlayableCard> playerDeck = new Stack<PlayableCard>();
	public Stack<PlayableCard> playerDiscardPile = new Stack<PlayableCard>();

	/**
	 * Creates a WorldMap from a JSON file that contains the list of cities and their respective neighbors.
	 *
	 * @param cities The filename of the JSON file.
	 * @throws FileNotFoundException If the file is not found.
	 */
	public World(String cities) throws FileNotFoundException {

		FileReader reader = new FileReader(cities);
		JSONObject map = new JSONObject(new JSONTokener(reader));

		// Adding cities
		Iterator<String> entries = map.keys();
		while (entries.hasNext()) {
			String entryKey = entries.next();
			if (entryKey.equals("neighbors"))
				break;
			JSONObject entry = map.getJSONObject(entryKey);
			Iterator<String> datakeys = entry.keys();
			while (datakeys.hasNext()) {
				String cityName = datakeys.next();
				City city = new City(cityName);
				this.cities.put(city, new ArrayList<City>());
			}
		}

		// Adding neighbors to each city
		Iterator<String> entries2 = map.keys();
		entries2.next();
		String entryKey = entries2.next();
		JSONObject entry = map.getJSONObject(entryKey);
		Iterator<String> datakeys = entry.keys();
		while (datakeys.hasNext()) {
			City myCity = null;
			String cityName = datakeys.next();
			for (City city : this.cities.keySet()) {
				if (city.getName().equals(cityName)) {
					myCity = city;
				}
			}
			JSONArray a = entry.getJSONArray(cityName);

			ArrayList<String> voisines_name = new ArrayList<String>();
			ArrayList<City> voisines = new ArrayList<City>();

			for (Object chaine : a)
				voisines_name.add((String) chaine);
			for (String name : voisines_name)
				voisines.add(new City(name));

			this.cities.put(myCity, voisines);
		}
	}

	/**
	 * Returns a list of the cities present in the world.
	 *
	 * @return The list of cities.
	 */
	public ArrayList<City> getCities() {
		return new ArrayList<City>(this.cities.keySet());
	}

	/**
	 * Get the neighbor cities for a given city.
	 *
	 * @param city The city.
	 * @return The list of its neighbor cities.
	 */
	public ArrayList<City> getCityNeighbours(City city) {
		return this.cities.get(city);
	}

	/**
	 * Returns the current infection rate of the world.
	 *
	 * @return The infection rate.
	 */
	public Integer getInfectionRate() {
		return this.infectionRate;
	}

	/**
	 * Returns the list of diseases present in the world.
	 *
	 * @return The list of diseases.
	 */
	public ArrayList<Disease> getDiseases() {
		return this.diseases;
	}

	/**
	 * Increases the infection rate by one.
	 */
	public void upInfectionRate() {
		this.infectionRate++;
	}

	/**
	 * Returns the number of cities currently in an outbreak.
	 *
	 * @return The number of outbreaks.
	 */
	public Integer getNbOutBreaks() {
		return this.outBreaks;
	}

	/**
	 * Increases the number of outbreaks by one.
	 */
	public void addOutBreak() {
		this.outBreaks++;
	}

	/**
	 * Adds an infection card to the infection deck.
	 *
	 * @param card The infection card to add.
	 */
	public void addInfectionCard(InfectionCard card) {
		this.infectionDeck.add(card);
	}

	/**
	 * Adds a playable card to the player deck.
	 *
	 * @param card The playable card to add.
	 */
	public void addPlayableCard(PlayableCard card) {
		this.playerDeck.add(card);
	}

	/**
	 * Returns the player deck.
	 *
	 * @return The player deck.
	 */
	public Stack<PlayableCard> getPlayerDeck() {
		return this.playerDeck;
	}

	/**
	 * Returns the infection deck.
	 *
	 * @return The infection deck.
	 */
	public Stack<InfectionCard> getInfectionDeck() {
		return this.infectionDeck;
	}

	/**
	 * Returns the infection discard pile.
	 *
	 * @return The infection discard pile.
	 */
	public Stack<InfectionCard> getInfectionDiscardPile() {
		return this.infectionDiscardPile;
	}

	/**
	 * Returns the player discard pile.
	 *
	 * @return The player discard pile.
	 */
	public Stack<PlayableCard> getplayerDiscardPile() {
		return this.playerDiscardPile;
	}

	/**
	 * Draws a card from the player deck.
	 *
	 * @return The drawn card.
	 */
	public PlayableCard drawPlayerDeck() {
		return this.playerDeck.pop();
	}

	/**
	 * Draws a card from the infection deck.
	 *
	 * @return The drawn card.
	 */
	public InfectionCard drawInfectionDeck() {
		return this.infectionDeck.pop();
	}

	/**
	 * Discards a playable card.
	 *
	 * @param card The card to discard.
	 */
	public void DiscardPlayableCard(PlayerCard card) {
		this.playerDiscardPile.push(card);
	}

	/**
	 * Discards an infection card.
	 *
	 * @param card The card to discard.
	 */
	public void DiscardInfectionCard(InfectionCard card) {
		this.infectionDiscardPile.push(card);
	}

	/**
	 * Shuffles the player deck.
	 */
	public void shufflePlayerDeck() {
		Collections.shuffle(this.playerDeck);
	}

	/**
	 * Shuffles the infection deck.
	 */
	public void shuffleInfectionDeck() {
		Collections.shuffle(this.infectionDeck);
	}

	/**
	 * Eradicates a disease from the world.
	 *
	 * @param disease The disease to eradicate.
	 */
	public void eradicateDisease(Disease disease) {
		this.diseases.remove(disease);
	}

	/**
	 * Returns the number of research stations available in the world.
	 *
	 * @return The number of research stations available.
	 */
	public Integer getResearchStationsAvailable() {
		return this.researchStationsAvailable;
	}

	/**
	 * Decreases the number of research stations available by one.
	 */
	public void countUsedResearchStation() {
		this.researchStationsAvailable--;
	}

	/**
	 * Returns the list of remedies discovered in the world.
	 *
	 * @return The list of remedies.
	 */
	public ArrayList<Disease> getRemedies() {
		return this.remedies;
	}

	/**
	 * Adds a remedy to the list of discovered remedies.
	 *
	 * @param disease The disease for which a remedy is discovered.
	 */
	public void addRemedy(Disease disease) {
		if (!this.remedies.contains(disease))
			this.remedies.add(disease);
	}

	/**
	 * Returns a list of cities that have research stations.
	 *
	 * @return The list of cities with research stations.
	 */
	public ArrayList<City> getResearchStationCities() {
		ArrayList<City> researchStationCities = new ArrayList<City>();
		for (City city : this.getCities()) {
			if (city.isResearchStation())
				researchStationCities.add(city);
		}
		return researchStationCities;
	}
}
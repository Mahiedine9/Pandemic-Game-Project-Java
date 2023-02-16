package PandemicGame ;

import java.util.HashMap ;

public class WorldMap {

	private ArrayList<City> cities ;
	private ArrayList<Player> players ;
	private HashMap<Player, City> locations = new HashMap<Player,City>() ;
	private Integer globalInfectionRate  = 0
	private Integer nbOfResearchStations = 0;
	private Integer nbOfOutbreakCities = 0 ;

	public Map(ArrayList<City> cities, ArrayList<Player> players) {
		this.cities = cities ;
		this.players = players ;
	}



	public Map() {}


	public movePlayer (Player player, City newCity) {
		this.cities.put(player,newCity) ;
	}


	public  getNeighbourCities(City mycity) {
		City previous ;
		City next ;

		Iterator<City> iterator = cities.iterator() ;

		While (iterator.hasNext()) {
			actual = iterator.next() ;
			if actual.equals(mycity) {next = iterator.next(); }
		}

		Iterator<City> iterator2 = cities.iterator() ;

		While (iterator2.hasNext()) {
			actual = iterator2.next() ;
			if iterator2.next().equals(mycity) { previous = actual; }

		}

		ArrayList(City) neighbourCities = new ArrayList<City> ;
		neighbourCities.add(previous) ;
		neighbourCities.add(next) ;

		return neighbourCities ;

	}


	public ArrayList<Plyaer> getPlayers() {
		return this.players ;
	}

	public ArrayList<City> getCities() {
		return this.cities ;
	} 

	public City getLocation (Player player) {
		return this.locations.get(player) ;
	}

	public Integer getGlobalInfectionRate() {
		return this.globalinfection ;
	}

	public Integer getNbOfResearchStations() {
		return this.nbOfResearchStations
	}

	public Integer getNbOfOutbreakCities() {
		return this.nbOfOutbreakCities ;
	}
}
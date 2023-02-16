package PandemicGame ;

import java.util.HashMap ;


public class WorldMap {

	private ArrayList<City> cities ;
	private ArrayList<Player> players ;
	private HashMap<Player, City> locations = new HashMap<Player,City>() ;
	private Integer globalInfectionRate  = 0
	private Integer nbOfResearchStations = 0;
	private Integer nbOfOutbreakCities = 0 ;
	private JSONObject mapJSON;

	public Map(ArrayList<City> cities, ArrayList<Player> players) {
		this.cities = cities ;
		this.players = players ;
	}



	public Map(String fileName, int nbOfPlayers, int globalInfectionRate) {
		FileReader reader = new FileReader(fileName);
		JSONObject map = new JSONObject(new JSONTokener(reader));
		this.mapJSON = map;
		JSONObject jsonCities = map.getJSONObject("cities");
		Iterator<String> data = jsonCities.keys();
		while(data.hasNext()) {
			City city = new City(data.next());
			city.nbOutbreaks = data.getInt(data.next());
			this.cities.add(city);
		}

		for(int i=0; i<nbOfPlayers;i++) {
			this.players.add(new Player());
		}

		this.globalInfectionRate = globalInfectionRate;
	}


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
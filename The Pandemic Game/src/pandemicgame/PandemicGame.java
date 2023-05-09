package pandemicgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class PandemicGame {
	private World world ;
	private ArrayList<Player> players ;
	private Iterator<Player> currentlyPlaying ;
	private HashMap<Disease, Integer> diseaseCubes ;
	
	public PandemicGame(World world ) {
		this.world = world ;
		this.players = new ArrayList<Player>();
		this.currentlyPlaying = players.iterator() ;
	}


	public static Player createPlayer(String name, String role, World world) {
    List<City> cities = world.getCities();
    Random random = new Random();

    // Choisir une ville au hasard parmi les villes disponibles
    City city = cities.get(random.nextInt(cities.size()));
	Player player;
    // Créer le joueur avec le rôle, le nom et la ville sélectionnés
    switch (role.toUpperCase()) {
        case "DOCTOR":
             player = new Doctor(name, city, world);
			 this.players.add(player);
			 return player;
        case "EXPERT":
            player = new Expert(name, city, world);
			this.players.add(player);
			return player;
        case "SCIENTIFIC":
            player = new Scientific(name, city, world);
			this.players.add(player);
			return player;
        case "GLOBETROTTER":
            player = new Globetrotter(name, city, world);
			this.players.add(player);
			return player;
        default:
            throw new IllegalArgumentException("Role not recognized");
    }
}

		
	public void initiate() {
		
		this.diseaseCubes = new HashMap<Disease, Integer>() ;
		for (Disease disease : this.world.getDiseases()) this.diseaseCubes.put(disease, 24) ;
		
		ArrayList<Disease> diseaseList = new ArrayList<>();
        diseaseList.addAll(Arrays.asList(Disease.values()));
        Random rand = new Random();
        int randomIndex ;  
        Disease randomDisease ;
		
		for (City city : this.world.getCities()) {
			randomIndex = rand.nextInt(diseaseList.size());
			randomDisease = diseaseList.get(randomIndex);
			InfectionCard carteinfection = new InfectionCard(city, randomDisease) ;
			this.world.addInfectionCard(carteinfection) ; }
		
		this.world.shuffleInfectionDeck();
		
			
		for (City city : this.world.getCities()) {
			randomIndex = rand.nextInt(diseaseList.size());
			randomDisease = diseaseList.get(randomIndex);
			PlayerCard cartedisease = new PlayerCard(city, randomDisease) ;
			this.world.addPlayableCard(cartedisease) ; }
		
		this.world.addPlayableCard(new EpidemicCard()) ;
		this.world.addPlayableCard(new EpidemicCard()) ;
		this.world.addPlayableCard(new EpidemicCard()) ;
		this.world.addPlayableCard(new EpidemicCard()) ;
		
		this.world.shufflePlayerDeck();
		
		
		
		}
		
	
	
	
	public World getWorld() { return this.world ; }
	public ArrayList<Player> getPlayers() { return this.players ; }
	public Player nextTurn() {
		if (this.currentlyPlaying.hasNext())
		 return this.currentlyPlaying.next() ;
		else  {
			this.currentlyPlaying = this.players.iterator() ;
			return this.currentlyPlaying.next() ;
		}
	}
	
	
	
	

}

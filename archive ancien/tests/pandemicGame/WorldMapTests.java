package pandemicGame ;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class WorldMapTest {

    private WorldMap worldMap;
    private ArrayList<City> cities;
    private ArrayList<Player> players;

    @Before
    public void setUp() throws Exception {
        cities = new ArrayList<City>();
        players = new ArrayList<Player>();

        cities.add(new City("New York"));
        cities.add(new City("Paris"));
        cities.add(new City("Tokyo"));

        players.add(new Player());
        players.add(new Player());

        worldMap = new WorldMap(cities, players);
    }

    @Test
    public void testMovePlayer() {
        Player player = players.get(0);
        City newCity = cities.get(1);
        worldMap.movePlayer(player, newCity);
        assertEquals(newCity, worldMap.getLocation(player));
    }

    @Test
    public void testGetNeighbourCities() {
        City city = cities.get(0);
        ArrayList<City> neighbours = worldMap.getNeighbourCities(city);
        assertEquals(2, neighbours.size());
        assertTrue(neighbours.contains(cities.get(1)));
        assertTrue(neighbours.contains(cities.get(2)));
    }

    @Test
    public void testGetPlayers() {
        assertEquals(players, worldMap.getPlayers());
    }

    @Test
    public void testGetCities() {
        assertEquals(cities, worldMap.getCities());
    }

    @Test
    public void testGetGlobalInfectionRate() {
        Integer globalInfectionRate = 2;
        worldMap = new WorldMap("worldmap.json", 2, globalInfectionRate);
        assertEquals(globalInfectionRate, worldMap.getGlobalInfectionRate());
    }

    @Test
    public void testGetNbOfResearchStations() {
        worldMap = new WorldMap("worldmap.json", 2, 2);
        assertEquals(0, worldMap.getNbOfResearchStations());
        worldMap.buildResearchStation(cities.get(0));
        assertEquals(1, worldMap.getNbOfResearchStations());
    }

    @Test
    public void testGetNbOfOutbreakCities() {
        worldMap = new WorldMap("worldmap.json", 2, 2);
        assertEquals(0, worldMap.getNbOfOutbreakCities());
        worldMap.increaseOutbreakCounter(cities.get(0));
        assertEquals(1, worldMap.getNbOfOutbreakCities());
    }
    public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(pandemicGame.WorldMapTests.class);
   }

}
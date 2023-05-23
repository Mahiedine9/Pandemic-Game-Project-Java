package pandemicgame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameTest{
    private PandemicGame game;
    private World world;
    private ArrayList<Player> players;

    @Before
    public void setup() {
        world = new World("");
        game = new PandemicGame(world);
        players = new ArrayList<Player>();
    }

    @Test
    public void testCreatePlayer() {
        Player player = game.createPlayer("John", "DOCTOR", world, players);
        assertNotNull(player);
        assertEquals("John", player.getName());
        assertTrue(players.contains(player));
    }

    @Test
    public void testInitiate() {
        game.initiate();
        List<City> cities = world.getCities();
        for (City city : cities) {
            assertEquals(1, city.getDiseases().size());
        }

        assertEquals(48, world.getPlayerDeck().size());
        assertEquals(48, world.getInfectionDeck().size());
    }

    @Test
    public void testGetWorld() {
        assertEquals(world, game.getWorld());
    }

    @Test
    public void testGetPlayers() {
        assertEquals(players, game.getPlayers());
    }

    @Test
    public void testNextTurn() {
        Player player1 = game.createPlayer("John", "DOCTOR", world, players);
        Player player2 = game.createPlayer("Alice", "EXPERT", world, players);

        assertEquals(player1, game.nextTurn());
        assertEquals(player2, game.nextTurn());
        assertEquals(player1, game.nextTurn());
    }
}

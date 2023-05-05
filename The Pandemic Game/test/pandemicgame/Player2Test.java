package pandemicgame;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import old.Player;
import old.PlayerCard;
import pandemicgame.City;
import pandemicgame.Disease;

class Player2Test {

	@Test
	public void NameTest(){
		   Player player = new Player("paul");
	       assertEquals("paul", player.GetName());
	}
	
	@Test 
    public void TestGetPlayerHand(){
		Player player = new Player("paul");
		City city = new City("bejaia");
		Disease disease1 = Disease.DISEASE1;
		Disease disease2 = Disease.DISEASE2;
		Card card1 = new PlayerCard(city, disease1);
		Card card2 = new PlayerCard(city, disease2);
		ArrayList<Card> hand = new ArrayList();
		hand.add(card1);
		hand.add(card2);
		player.AddCard(card1);
		player.AddCard(card2);
		assertEquals(player.GetPlayerHand(),hand);
	}
	@Test 
	public void TestDiscard(){
		City city = new City("bejaia");
		Disease disease1 = Disease.DISEASE1;
		Disease disease2 = Disease.DISEASE2;
		Player player1 = new Player("paul");
		Card card1 = new PlayerCard(city, disease1);
		Card card2 = new PlayerCard(city, disease2);
		ArrayList<Card> hand = new ArrayList();
		hand.add(card1);
		hand.add(card2);
		player1.AddCard(card1);
		player1.AddCard(card2);
		assertEquals(player1.GetPlayerHand(),hand);
		assertSame(2, player1.GetNBCards());
		hand.remove(card1);
		player1.Discard(card1);
		assertEquals(player1.GetPlayerHand(),hand);
		assertSame(1, player1.GetNBCards());
	}
	
	@Test
	public void TestActionsRemaining(){
		Player player1 = new Player("paul");
		assertTrue(player1.GetNBActionsRemaining() == 4);
		player1.UpdateNbActionsRemaining();
		assertTrue(player1.GetNBActionsRemaining() == 3);
	}
	

	
	
	
	
	

}

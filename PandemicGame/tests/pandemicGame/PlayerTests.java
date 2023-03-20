package pamdemicGame ;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;


public class PlayerTests {

	private Player player1 ;
	private Player player1 ;

    @Before
	public void before() {
		Role role = new Role();
		this.player1 =  new Player("paul", role);
		this.player2 = new Player("zabel", role);
	}

    @Test
    public void NameTest(){
       AssertEquals("paul", this.player1.GetName());
    } 

    @Test 
    public void TestGetPlayerHand(){
		Card card1 = new Card();
		Card card2 = new Card();
		this.player1.AddCard(card1);
		this.player1.AddCard(card2);
		AssertEquals(this.player1.GetPlayerHand(),[card1, card2]);
	}

	@Test 
	public void TestDiscard(){
		Card card1 = new Card();
		Card card2 = new Card();
		this.player1.AddCard(card1);
		this.player1.AddCard(card2);
		AssertEquals(this.player1.GetPlayerHand(),[card1, card2] );
		AssertTrue(this.player1.GetNBCards()==2);
		this.player1.Discard(card1);
		AssertEquals(this.player1.GetPlayerHand(),[card1] );
		AssertTrue(this.player1.GetNBCards()==1);
	}
	@Test
	public void TestActionsRemaining(){
		AssertTrue(this.player1.GetNBActionsRemaining() == 4);
		this.player1.UpdateNbActionsRemaining();
		AssertTrue(this.player1.GetNBActionsRemaining() == 3);
	}      




} 

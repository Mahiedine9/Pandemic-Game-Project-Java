package pandemicGame ;

public class Player {
	private List<Card> playerHand;
	private String name;
	private Role role;
	private int cardsNB;
	private City position;
	private List<Disease> curedDiseases;
	private int NBActionsRemaining;

	public Player(String name, Role role) {
		this.name = name;
		this.role = role;
	}

	public String GetName(){
		return this.name;

	} 
	public List<Card> GetPlayerHand(){
		return this.playerHand;
	} 
	public Role GetRole(){
		return this.role;
	}

	public void Discard(Card card){
		this.playerHand.remove(card);
		this.cardsNB-=1;
	} 

	public int GetNBCards(){
		return this.cardsNB;
	} 

	public void AddCard(Card card){
		this.playerHand.add(card);
		this.cardsNB+=1;
	}

	public City GetPosition(){
		return this.position;
	} 

	public void SetPosition(City city){
		this.position = city;
	}

	public int GetNBActionsRemaining(){
		return this.NBActionsRemaining;

	}
	public List<Disease> GetCuredDiseases(){
		return this.curedDiseases;
	} 










}

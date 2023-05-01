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
	
	private HashMap<City,ArrayList<City>> cities  = new HashMap<City,ArrayList<City>>() ;
	private ArrayList<Disease> diseases  = new ArrayList<Disease>(Arrays.asList(Disease.values()));
	private Integer infectionRate = 2;
	private Integer outBreaks = 0;
	public Stack<InfectionCard> infectionDeck =  new Stack<InfectionCard>() ;
	public Stack<InfectionCard> infectionDiscardPile = new Stack<InfectionCard>();
	public Stack<PlayerCard> playerDeck = new Stack<PlayerCard>() ;
	public Stack<PlayerCard> playerDiscardPile = new Stack<PlayerCard>();
	
	
 /**
  * creates a WorldMap from JSON file contains the list of cities and thier respectives neighbors
 * @param string
 * @throws FileNotFoundException if file not found
 */
	public World (String cities) throws FileNotFoundException {
	    
	       
        FileReader reader = new FileReader(cities);
        JSONObject map = new JSONObject(new JSONTokener(reader));
       
        //adding cities
        Iterator<String> entries = map.keys();
        while(entries.hasNext()) {
            String entryKey = entries.next();
            if ( entryKey.equals("neighbors"))
                    break ;
            JSONObject entry = map.getJSONObject(entryKey) ;
            Iterator<String> datakeys = entry.keys();
            while(datakeys.hasNext()) {
                String cityName = datakeys.next();
                City city = new City(cityName) ;
                this.cities.put(city, new ArrayList<City>()) ;
           }   
        }
        //adding neighbors to each city
        Iterator<String> entries2 = map.keys() ;
            entries2.next();
            String entryKey = entries2.next() ;
            JSONObject entry = map.getJSONObject(entryKey) ;
            Iterator<String> datakeys = entry.keys();
            while(datakeys.hasNext()) {
                City myCity = null ;
                String cityName = datakeys.next();
                for (City city : this.cities.keySet()) {
                    if (city.getName().equals(cityName)) {
                        myCity = city ;
                        //if (myCity == null) break ;
                        }
                        
                }
                JSONArray a = entry.getJSONArray(cityName);
               
                
               
                ArrayList<String> voisines_name = new ArrayList<String>() ;
                ArrayList<City> voisines = new ArrayList<City>() ;
               
                for (Object  chaine : a) voisines_name.add((String) chaine) ;
                for (String name : voisines_name) voisines.add(new City(name)) ;
                //System.out.println(myCity.getName()+" has "+voisines.get(0).getName()+" as n1") ;
               
                this.cities.put(myCity, voisines) ;
                
               
             }
	}
	/**
	 * return list of the cities present in world
	 * @return the cities
	 */
	public ArrayList<City> getCities() { return new ArrayList<City>(this.cities.keySet()) ; }
	
	/**
	 * get neighbors cities for a given city
	 * @param the cities
	 * @return the list of its neighbors cities
	 */
	public ArrayList<City> getCityNeighbours(City city) {return this.cities.get(city) ;}
	
	/**
	 * returns the current infection rate of the world
	 * @return the infectionRate
	 */
	public Integer getInfectionRate() { return this.infectionRate ; }
	
	/**
	 * increases infection rate by one 
	 */
	public void upInfectionRate() { this.infectionRate ++ ; }
	
	/**
	 * return this numbers of cities currently in an outbreak
	 * @return the outbreaks
	 */
	public Integer getNbOutBreaks() { return this.outBreaks ; }
	
	/**
	 * increases the numbers of outbreaks by one
	 */
	public void addOutBreak() { this.outBreaks ++ ; }
	
	/**
	 * draw a card from Either deck and place it onto the respective Discard pile
	 * @param cardType the type of deck
	 * @return the card drawen
	 */
	public Card drawDeck (Card cardType) {
		Card carte ;
		if (cardType.getClass().equals(InfectionCard.class)) { 
			 carte = (InfectionCard) this.infectionDeck.pop();
			this.infectionDiscardPile.push((InfectionCard) carte) ;}
		else {
			 carte = (PlayerCard) this.playerDeck.pop();
			this.playerDiscardPile.push((PlayerCard) carte) ;}
		return carte ;
	 }
	
	/**
	 * shuffles either deck or Discard pile in the game
	 * @param cardType PlayCard or InfectionCard
	 * @param discard true to shuffle the Discard Pile of the respective cardType
	 */
	public void shuffleDeck(Card cardType, Boolean discard) {
		
		if (cardType.getClass().equals(InfectionCard.class)) { 
			if (discard)  Collections.shuffle(this.infectionDiscardPile);
			else Collections.shuffle(this.infectionDeck) ;
			}
		else if (discard) Collections.shuffle(this.playerDiscardPile);
		else  Collections.shuffle(this.playerDeck) ;
	}
	
	
	public void eradicateDisease(Disease disease) {
		this.diseases.remove(disease) ;
	}
	
	
	
	
	
	
	
}
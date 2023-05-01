package pandemicgame;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

class WorldTest {

	@Test
    public void testCreationIsOk() throws FileNotFoundException {
        World myWorld = new World("The Pandemic Game/files/carte1.json") ;
        assertEquals(World.class, myWorld.getClass()) ;
    }
    
    @Test
    public void testGetCities() throws FileNotFoundException {
        World myWorld = new World("The Pandemic Game/files/carte1.json") ;
       ArrayList<City> cities = myWorld.getCities() ;
       assertSame(12,cities.size()) ;
       for (City c: cities) {
    	   String name = c.getName().substring(0,5) ;
    	   Integer number =  Integer.parseInt(c.getName().substring(6,7)) ;
    	   assertEquals("ville", name) ;
    	   assertTrue( number>= 1 && number<= 12) ;
       }
       
    }
    
    @Test
    public void testGetCityNeighbours() throws FileNotFoundException {
    	World myWorld = new World("The Pandemic Game/files/carte1.json") ;
    	Iterator<City> villes = myWorld.getCities().iterator() ;
    	while (villes.hasNext() ) { 
    		City city = villes.next();
    		String cityName = city.getName() ;
    		ArrayList<String> neighboursOfCityNames = new ArrayList<String>() ;
    		for (City n : myWorld.getCityNeighbours(city)) {
    			neighboursOfCityNames.add(n.getName()) ;
    		}
    		
    	System.out.println(cityName+" neighbours are:"+neighboursOfCityNames) ;			
    	}
    }
    
    @Test
    public void testGetInfectionRateTest() throws FileNotFoundException {
    	World myWorld = new World("The Pandemic Game/files/carte1.json") ;
    	assertSame(2,myWorld.getInfectionRate()) ;
    }
    
    @Test
    public void testUpInfectionRate() throws FileNotFoundException {
    	World myWorld = new World("The Pandemic Game/files/carte1.json") ;
    	myWorld.upInfectionRate() ;
    	assertNotSame(2,myWorld.getInfectionRate()) ;
    	assertSame(3,myWorld.getInfectionRate()) ;
    }
    
    @Test
    public void testGetNbOutBreaksAndAddOutBreak() throws FileNotFoundException  {
    	World myWorld = new World("The Pandemic Game/files/carte1.json") ;
    	assertSame(0,myWorld.getNbOutBreaks()) ;
    	myWorld.addOutBreak() ;
    	assertSame(1,myWorld.getNbOutBreaks()) ;
    }
    
    @Test
    public void testDrawDeck() throws FileNotFoundException {
    World myWorld = new World("The Pandemic Game/files/carte1.json") ;
    InfectionCard carte1 = new InfectionCard (new City("Paris"), Disease.DISEASE1) ;
	InfectionCard carte2 = new InfectionCard (new City("Lille"), Disease.DISEASE2) ;
	InfectionCard carte3 = new InfectionCard (new City("Lyon"), Disease.DISEASE3) ;
	myWorld.infectionDeck.push(carte1) ;
	myWorld.infectionDeck.push(carte2) ;
	myWorld.infectionDeck.push(carte3) ;
	assertEquals(carte3,myWorld.drawDeck(carte3) ) ;
	assertSame(2, myWorld.infectionDeck.size()) ;
	assertEquals(carte2,myWorld.infectionDeck.peek()) ;
    }
    
    @Test
    public void testShuffleDeckk() throws FileNotFoundException {
    	World myWorld = new World("The Pandemic Game/files/carte1.json") ;
    	InfectionCard carte1 = new InfectionCard (new City("Paris"), Disease.DISEASE1) ;
    	InfectionCard carte2 = new InfectionCard (new City("Lille"), Disease.DISEASE2) ;
    	InfectionCard carte3 = new InfectionCard (new City("Lyon"), Disease.DISEASE3) ;
    	myWorld.infectionDeck.push(carte1) ;
    	myWorld.infectionDeck.push(carte2) ;
    	myWorld.infectionDeck.push(carte3) ;
    	System.out.println(myWorld.infectionDeck) ;
    	myWorld.shuffleDeck(carte1 , false) ;
    	System.out.println(myWorld.infectionDeck) ;
    }
    
    
       


}
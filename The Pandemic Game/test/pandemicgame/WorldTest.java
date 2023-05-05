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
    public void testDrawInfectionrDeck() throws FileNotFoundException {
    World myWorld = new World("The Pandemic Game/files/carte1.json") ;
    InfectionCard carte1 = new InfectionCard (new City("Paris"), Disease.DISEASE1) ;
	InfectionCard carte2 = new InfectionCard (new City("Lille"), Disease.DISEASE2) ;
	InfectionCard carte3 = new InfectionCard (new City("Lyon"), Disease.DISEASE3) ;
	myWorld.getInfectionDeck().push(carte1) ;
	myWorld.getInfectionDeck().push(carte2) ;
	myWorld.getInfectionDeck().push(carte3) ;
	assertEquals(carte3,myWorld.drawInfectionDeck()) ;
	assertSame(2, myWorld.getInfectionDeck().size()) ;
	assertEquals(carte2,myWorld.getInfectionDeck().peek()) ;
    }
    
    @Test
    public void testDrawPlayerDeck() throws FileNotFoundException {
    World myWorld = new World("The Pandemic Game/files/carte1.json") ;
    PlayerCard carte1 = new PlayerCard (new City("Paris"), Disease.DISEASE1) ;
    PlayerCard carte2 = new PlayerCard (new City("Lille"), Disease.DISEASE2) ;
    PlayerCard carte3 = new PlayerCard (new City("Lyon"), Disease.DISEASE3) ;
	myWorld.getPlayerDeck().push(carte1) ;
	myWorld.getPlayerDeck().push(carte2) ;
	myWorld.getPlayerDeck().push(carte3) ;
	assertEquals(carte3,myWorld.drawPlayerDeck()) ;
	assertSame(2, myWorld.getPlayerDeck().size()) ;
	assertEquals(carte2,myWorld.getPlayerDeck().peek()) ;
    }
    
    @Test
    public void testShufflePlayerDeckAndShufflePlayerDeck() throws FileNotFoundException {
    	World myWorld = new World("The Pandemic Game/files/carte1.json") ;
        InfectionCard carte1 = new InfectionCard (new City("Paris"), Disease.DISEASE1) ;
    	InfectionCard carte2 = new InfectionCard (new City("Lille"), Disease.DISEASE2) ;
    	InfectionCard carte3 = new InfectionCard (new City("Lyon"), Disease.DISEASE3) ;
    	InfectionCard carte4 = new InfectionCard (new City("Paris"), Disease.DISEASE1) ;
     	InfectionCard carte5 = new InfectionCard (new City("Lille"), Disease.DISEASE2) ;
     	InfectionCard carte6 = new InfectionCard (new City("Lyon"), Disease.DISEASE3) ;
    	myWorld.getInfectionDeck().push(carte1) ;
    	myWorld.getInfectionDeck().push(carte2) ;
    	myWorld.getInfectionDeck().push(carte3) ;
    	myWorld.getInfectionDeck().push(carte4) ;
    	myWorld.getInfectionDeck().push(carte5) ;
    	myWorld.getInfectionDeck().push(carte6) ;
    	PlayerCard carte7 = new PlayerCard (new City("Paris"), Disease.DISEASE1) ;
        PlayerCard carte8 = new PlayerCard (new City("Lille"), Disease.DISEASE2) ;
        PlayerCard carte9 = new PlayerCard (new City("Lyon"), Disease.DISEASE3) ;
        PlayerCard carte10 = new PlayerCard (new City("Paris"), Disease.DISEASE1) ;
        PlayerCard carte11 = new PlayerCard (new City("Lille"), Disease.DISEASE2) ;
        PlayerCard carte12= new PlayerCard (new City("Lyon"), Disease.DISEASE3) ;
    	myWorld.getPlayerDeck().push(carte7) ;
    	myWorld.getPlayerDeck().push(carte8) ;
    	myWorld.getPlayerDeck().push(carte9) ;
    	myWorld.getPlayerDeck().push(carte10) ;
    	myWorld.getPlayerDeck().push(carte11) ;
    	myWorld.getPlayerDeck().push(carte12) ;
    	
    	
    	System.out.println(myWorld.getPlayerDeck()) ;
    	System.out.println(myWorld.getInfectionDeck()) ;
    	myWorld.shuffleInfectionDeck() ;
    	myWorld.shufflePlayerDeck() ;
    	System.out.println(myWorld.getPlayerDeck()) ;
    	System.out.println(myWorld.getInfectionDeck()) ;
    }
    
    @Test
    public void testEradicateDisease() throws FileNotFoundException {
    	World myWorld = new World("The Pandemic Game/files/carte1.json") ;
    	myWorld.eradicateDisease(Disease.DISEASE1) ;
    	assertSame(3,myWorld.getDiseases().size()) ;
    	assertFalse(myWorld.getDiseases().contains(Disease.DISEASE1)) ;
  
    }
  
  


}
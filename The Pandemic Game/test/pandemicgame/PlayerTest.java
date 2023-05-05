package pandemicgame;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import exception.NoMoreResearchStationAvailableException;
import exception.NotANeighbourCityException;
import exception.NotAResearchStationException;
import exception.NotEnoughCardsToFindARemedyException;
import exception.PlayerDoesNotHaveProperCardException;

class PlayerTest {

	@Test
	public void testCreationIsOk() throws FileNotFoundException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city = world.getCities().get(0) ;
		Globetrotter timo = new Globetrotter("Timo", city, world) ;
		assertEquals(Globetrotter.class,timo.getClass()) ;
		
	}
	
	@Test
	public void testGetName() throws FileNotFoundException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city = world.getCities().get(0) ;
		Globetrotter timo = new Globetrotter("Timo", city, world) ;
		assertEquals("Timo",timo.getName()) ;
		
	}
	
	@Test
	public void testGetCity() throws FileNotFoundException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city = world.getCities().get(0) ;
		Globetrotter timo = new Globetrotter("Timo", city, world) ;
		assertEquals(city,timo.getCity()) ;
		
	}
	
	@Test
	public void testMoveWhenPlayerIsnotGlobetrotter() throws FileNotFoundException, NotANeighbourCityException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city1 = world.getCities().get(0) ;
		City city2 = world.getCityNeighbours(city1).get(0) ;
		Doctor timo = new Doctor("Timo", city1, world) ;
		timo.move(city2) ;
		assertEquals(city2,timo.getCity()) ;
		
	}
	
	
	@Test
	public void testMoveWhenPlayerIsnotGlobetrotterAndCityIsNotNeighbour() throws FileNotFoundException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city1 = world.getCities().get(0) ;
		City city2 = world.getCities().get(1) ;
		Doctor timo = new Doctor("Timo", city1, world) ;
		assertThrows(NotANeighbourCityException.class, () -> timo.move(city2) );
		
		
	}
	
	@Test
	 public void testMoveWhenPlayerIsnGlobetrotter() throws FileNotFoundException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city1 = world.getCities().get(0) ;
		City city2 = world.getCities().get(1) ;
		Globetrotter timo = new Globetrotter("Timo", city1, world) ;
		timo.move(city2);
		assertEquals(city2,timo.getCity()) ;
		
	}
	
	@Test
	public void testBuildResearchStationWhenPlayerIsnotExpert() throws FileNotFoundException, NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city = world.getCities().get(0) ;
		Globetrotter timo = new Globetrotter("Timo", city, world) ;
		timo.getHand().add(new PlayerCard (city, Disease.DISEASE1)) ;
		assertFalse(city.isResearchStation()) ;
		timo.buildResearchStation() ;
		assertTrue(city.isResearchStation()) ;
	}
	
	@Test
	public void testBuildResearchStationWhenPlayerIsnotExpertAndHasNoCorrectCard() throws FileNotFoundException, NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city = world.getCities().get(0) ;
		Globetrotter timo = new Globetrotter("Timo", city, world) ;
		assertThrows(PlayerDoesNotHaveProperCardException.class, () -> timo.buildResearchStation()  );
	}
	
	@Test
	public void testBuildResearchStationWhenPlayerIsnotExpertAndNoResearchStationsAvailable() throws FileNotFoundException, NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		world.countUsedResearchStation();
		world.countUsedResearchStation();
		world.countUsedResearchStation();
		world.countUsedResearchStation();
		world.countUsedResearchStation();
		world.countUsedResearchStation();
		City city = world.getCities().get(0) ;
		Globetrotter timo = new Globetrotter("Timo", city, world) ;
		assertThrows(NoMoreResearchStationAvailableException.class, () -> timo.buildResearchStation()  );
	}
	
	@Test
	public void testBuildResearchStationWhenPlayerIsExpert() throws FileNotFoundException, NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city = world.getCities().get(0) ;
		Expert timo = new Expert("Timo", city, world) ;
		//timo.getHand().add(new PlayerCard (city, Disease.DISEASE1)) ;
		assertFalse(city.isResearchStation()) ;
		timo.buildResearchStation() ;
		assertTrue(city.isResearchStation()) ;
	}
	
	@Test
	public void testMoveResearchStation() throws FileNotFoundException, NotAResearchStationException, PlayerDoesNotHaveProperCardException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city1 = world.getCities().get(0) ;
		City city2 = world.getCities().get(1) ;
		city1.turnNotResearchStation() ;
		city2.turnResearchStation() ;
		Globetrotter timo = new Globetrotter("Timo", city1, world) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		timo.moveResearchStation(city2);
		assertTrue(city1.isResearchStation()) ;
		assertFalse(city2.isResearchStation()) ;
		
	}
	
	@Test
	public void testMoveResearchStationWhenCityNotAResearchStation() throws FileNotFoundException, NotAResearchStationException, PlayerDoesNotHaveProperCardException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city1 = world.getCities().get(0) ;
		City city2 = world.getCities().get(1) ;
		city1.turnNotResearchStation() ;
		city2.turnNotResearchStation() ;
		Globetrotter timo = new Globetrotter("Timo", city1, world) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		assertThrows(NotAResearchStationException.class, () -> timo.moveResearchStation(city2)  );
	}
	
	@Test
	public void testDiscoverRemedyWhenNotAScientific() throws FileNotFoundException, NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException, NotAResearchStationException, NotEnoughCardsToFindARemedyException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city1 = world.getCities().get(0) ;
		City city2 = world.getCities().get(1) ;
		Globetrotter timo = new Globetrotter("Timo", city1, world) ;
		timo.getHand().add(new PlayerCard (city2, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city2, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city2, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		timo.buildResearchStation() ;
		assertFalse(timo.world.getRemedies().contains(Disease.DISEASE1)) ;
		timo.discoverRemedy(Disease.DISEASE1) ;
		assertTrue(timo.world.getRemedies().contains(Disease.DISEASE1)) ;
	}
	
	@Test
	public void testDiscoverRemedyWhenNotAScientificAndEnoughtCards() throws FileNotFoundException, NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException, NotAResearchStationException, NotEnoughCardsToFindARemedyException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city1 = world.getCities().get(0) ;
		City city2 = world.getCities().get(1) ;
		Globetrotter timo = new Globetrotter("Timo", city1, world) ;
		timo.getHand().add(new PlayerCard (city2, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		timo.buildResearchStation() ;
		assertThrows(NotEnoughCardsToFindARemedyException.class, () ->	timo.discoverRemedy(Disease.DISEASE1)  ) ;
	}
	
	@Test
	public void testDiscoverRemedyWhenNotAScientificNotAResearchStationException() throws FileNotFoundException, NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException, NotAResearchStationException, NotEnoughCardsToFindARemedyException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city1 = world.getCities().get(0) ;
		City city2 = world.getCities().get(1) ;
		Globetrotter timo = new Globetrotter("Timo", city1, world) ;
		timo.getHand().add(new PlayerCard (city2, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city2, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city2, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		assertThrows(NotAResearchStationException.class, () ->	timo.discoverRemedy(Disease.DISEASE1)  ) ;
	}
	
	@Test
	public void testDiscoverRemedyWhenAScientific() throws FileNotFoundException, NoMoreResearchStationAvailableException, PlayerDoesNotHaveProperCardException, NotAResearchStationException, NotEnoughCardsToFindARemedyException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city1 = world.getCities().get(0) ;
		City city2 = world.getCities().get(1) ;
		Scientific timo = new Scientific ("Timo", city1, world) ;
		timo.getHand().add(new PlayerCard (city2, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city2, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		timo.getHand().add(new PlayerCard (city1, Disease.DISEASE1)) ;
		timo.buildResearchStation() ;
		assertFalse(timo.world.getRemedies().contains(Disease.DISEASE1)) ;
		timo.discoverRemedy(Disease.DISEASE1) ;
		assertTrue(timo.world.getRemedies().contains(Disease.DISEASE1)) ;
	}
	
	@Test
	public void testTreatDiseaseWhenNotADoctor() throws FileNotFoundException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city1 = world.getCities().get(0) ;
		city1.addDisease(Disease.DISEASE1) ;
		city1.addDisease(Disease.DISEASE1) ;
		city1.addDisease(Disease.DISEASE1) ;
		assertFalse(world.getRemedies().contains(Disease.DISEASE1)) ;
		Scientific timo = new Scientific ("Timo", city1, world) ;
		assertSame(3, Collections.frequency(city1.getDiseases(),Disease.DISEASE1)) ;
		timo.treatDisease(Disease.DISEASE1) ;
		assertSame(2, Collections.frequency(city1.getDiseases(),Disease.DISEASE1)) ;
	}
	
	@Test
	public void testTreatDiseaseWhenIsADoctor() throws FileNotFoundException {
		World world = new World("The Pandemic Game/files/carte1.json") ;
		City city1 = world.getCities().get(0) ;
		city1.addDisease(Disease.DISEASE1) ;
		city1.addDisease(Disease.DISEASE1) ;
		city1.addDisease(Disease.DISEASE1) ;
		assertFalse(world.getRemedies().contains(Disease.DISEASE1)) ;
		Doctor timo = new Doctor ("Timo", city1, world) ;
		assertSame(3, Collections.frequency(city1.getDiseases(),Disease.DISEASE1)) ;
		timo.treatDisease(Disease.DISEASE1) ;
		assertSame(0, Collections.frequency(city1.getDiseases(),Disease.DISEASE1)) ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

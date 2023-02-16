package pamdemicGame ;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * The CityTests class contains unit tests for the City class.
 */
public class CityTests {

	private City city1 ;
	private City city2 ;

	/**
     * Initializes the cities before each test.
     */
	@Before
	public void before() {
		this.city1 =  new City("Lille") ;
		this.city2 = new City("Lyon") ;
	}

	/**
     * Tests the getName function of the City class.
     */
	@Test
	public void testGetName() {
		AssertEquals("Lille", this.city1.getName()) ;
		AssertEquals("Lyon", this.city2.getName()) ;
	}

	/**
     * Tests the getNbOfCubes function of the City class.
     */
	@Test
	public void testGetNbOfCubes() {
		Disease d = new Disease() ;
		Cube c1 = new Cube(d) ;
		Cube c2 = new Cube(d) ;

		this.city1.addCube(c1) ;
		this.city1.addCube(c2) ;

		AssertSame(2, this.city1.getNbOfCubes(d)) ;	
	}

	/**
     * Tests the addCube function of the City class.
     */
	@Test
	public void testAddCube(){
		Cube c = new Cube() ;
		this.city1.addCube(c) ;

		AssertTrue(this.city1.getCubes.contains(c)) ;
		AssertEquals(c, this.city1.getCubes.get(0)) ;
	}

	/**
     * Tests the removeCube function of the City class.
     */
	@Test
	public void testRemoveCube(Cube cube) {
		Cube c = new Cube() ;
		this.city1.addCube(c) ;
		AssertTrue(this.city1.getCubes.contains(c)) ;

		this.city2.removeCube(c) ;
		AssertFalse(this.city1.getCubes.contains(c)) ;
	}

	/**
     * Tests the buildResearchStation and hasResearchStation functions of the City class.
     */
	@Test
	public void testBuildResearchStationAndHasResearchStation() {
		AssertFalse(this.city1.hasResearchStation())
		AssertFalse(this.city2.hasResearchStation()) ;

		this.city1.buildResearchStation() ;

		AssertFalse(this.city1.hasResearchStation()) ;
		AssertTrue(this.city2.hasResearchStation()) ;
	}

	/**
     * Tests the removeAllCubes function of the City class.
     */
	@Test
	public void testRemoveAllCubes() {
		Cube c1 = new Cube() ;
		Cube c2 = new Cube() ;
		this.city1.addCube(c) ;
		this.city2.addCube(c) ;

		AssertFalse(this.city1.getCubes.isEmpty()) ;
		this.city1.removeAllCubes() ;
		AssertTrue(this.city1.getCubes.isEmpty()) ;
	}

	/**
     * Tests the isOutBreak function of the City class.
     */
	@Test 
	public void testIsOutBreak() {
		AssertFalse(this.city1.isOutBreak()) ;

		Disease d = New Disease() ;
		Cube c = new Cube(d) ;

		this.city1.addCube(c);
		this.city1.addCube(c);
		this.city1.addCube(c);

		AssertTrue(this.city1.isOutBreak())
	}

	/**
     * Tests the addDisease function of the City class.
     */
	@Test void testAddDisease() {
		AssertTrue(this.city1.getDiseases.isEmpty()) ;

		Disease d = new Disease()
		 this.city1.addDisease(d) ;
		
		AssertFalse(this.city1.getDiseases.isEmpty()) ;
		AssertEquals(d, this.city1.getDiseases.get(0)) ;
	}

	/**
     * Tests the removeDisease function of the City class.
     */
	@Test void testRemoveDisease() {
		Disease d = new Disease()
		this.city1.addDisease(d) ;
		AssertFalse(this.city1.getDiseases.isEmpty()) ;

		this.city1.removeDisease(d) ;
		AssertTrue(this.city1.getDiseases.isEmpty()) ;
	}

	/**
     * Cleans up after each test by nullifying the city objects and running the garbage collector.
     */
	@After
	public void after() {
		this.city1 = null ;
		this.city2 = null ;
		System.gc() ;
	}
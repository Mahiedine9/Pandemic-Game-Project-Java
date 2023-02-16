package pamdemicGame ;

import org.junit.*;
import static org.junit.Assert.*;

public class CityTests {

	private City city1 ;
	private City city2 ;

	@Before
	public void before() {
		this.city1 =  new City("Lille") ;
		this.city2 = new City("Lyon") ;
	}


	@Test
	public void testGetName() {
		AssertEquals("Lille", this.city1.getName()) ;
		AssertEquals("Lyon", this.city2.getName()) ;
	}

	@Test
	public void testGetNbOfCubes() {
		
	}

	@Test
	public void testAddCube(){
		Cube c = new Cube() ;
		this.city1.addCube(c) ;

		AssertTrue(this.city1.getCubes.contains(c)) ;
		AssertEquals(c, this.city1.getCubes.get(0)) ;
	}

	@Test
	public void testRemoveCube(Cube cube) {
		Cube c = new Cube() ;
		this.city1.addCube(c) ;
		AssertTrue(this.city1.getCubes.contains(c)) ;

		this.city2.removeCube(c) ;
		AssertFalse(this.city1.getCubes.contains(c)) ;
	}

	@Test
	public void testBuildResearchStationAndHasResearchStation() {
		AssertFalse(this.city1.hasResearchStation())
		AssertFalse(this.city2.hasResearchStation()) ;

		this.city1.buildResearchStation() ;

		AssertFalse(this.city1.hasResearchStation()) ;
		AssertTrue(this.city2.hasResearchStation()) ;
	}

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

	@Test void testAddDisease() {
		AssertTrue(this.city1.getDiseases.isEmpty()) ;

		Disease d = new Disease()
		 this.city1.addDisease(d) ;
		
		AssertFalse(this.city1.getDiseases.isEmpty()) ;
		AssertEquals(d, this.city1.getDiseases.get(0)) ;
	}

	@Test void testRemoveDisease() {
		Disease d = new Disease()
		this.city1.addDisease(d) ;
		AssertFalse(this.city1.getDiseases.isEmpty()) ;

		this.city1.removeDisease(d) ;
		AssertTrue(this.city1.getDiseases.isEmpty()) ;
	}

	@After
	public void after() {
		this.city1 = null ;
		this.city2 = null ;
		System.gc() ;
	}
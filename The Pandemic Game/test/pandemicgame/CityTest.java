package pandemicgame;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class CityTest {
	
	private City city = new City("Lille") ;

	@Test
	public void testCreationAndGetName() {
		assertEquals(City.class, this.city.getClass()) ;
		assertEquals("Lille", this.city.getName()) ;
		for (Disease disease : Disease.values()) assertFalse(city.isOutBreakOfInfection(disease)) ; 
	}
	
	
	
	@Test
	public void testisResearchStationAndturnResearchStation() {
		assertFalse(this.city.isResearchStation()) ;
		this.city.turnResearchStation();
		assertTrue(this.city.isResearchStation()) ;
		
	}
	
	@Test
	public void  testisourBreakOfInfectionAndDeclareOutBReakOfInfection() {
		Disease d = Disease.DISEASE1 ;
		assertFalse(this.city.isOutBreakOfInfection(d)) ;
		this.city.declareOutBReakOfInfection(d) ;
		assertTrue(this.city.isOutBreakOfInfection(d)) ;
		
	}
	
	@Test
	public void testgetDiseasesAndaddDiseaseAndRemoveDisease() {
		assertTrue(this.city.getDiseases().isEmpty()) ;
		Disease d = Disease.DISEASE2 ;
		this.city.addDisease(d) ;
		assertFalse(this.city.getDiseases().isEmpty()) ;
		this.city.removeDisease(d) ;
		assertTrue(this.city.getDiseases().isEmpty()) ;
		
	}
	
	@Test
	public void testRemoveDiseaseWhenEmptyList() {
	     assertThrows(IndexOutOfBoundsException.class, () -> {
	    	Disease d = Disease.DISEASE3 ;
	        this.city.removeDisease(d);
	    });
	 
	}
	

}

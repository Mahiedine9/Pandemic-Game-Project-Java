
package pandemicgame;

import java.util.ArrayList;
import java.util.HashMap;




/**
 * Class City representing a city in the game
 */
public class City {
	private String name ;
	private Boolean researchStation = false ;
	private ArrayList<Disease> diseases = new ArrayList<Disease>() ;
	private HashMap<Disease,Boolean> OutBreakInfectionPerDisease = new HashMap<Disease, Boolean>() ;
	
	/**
	 * class constructor
	 * @param name name of the city
	 */
	public City(String name) { 
		this.name = name ;
		for (Disease disease : Disease.values()) this.OutBreakInfectionPerDisease.put(disease,false) ;
	}
	
	/**
	 *  return name of the city
	 * @return name of the city
	 */
	public String getName() { return this.name ;} 
	/**
	 * checks if the city has a research station
	 * @return true if a research station is installed within the city, false otherwise
	 */
	public Boolean isResearchStation() { return this.researchStation ;}
	
	/**
	 * add a research station to the city if there is none already
	 */
	public void turnResearchStation() { this.researchStation = true ;}
	
	public void turnNotResearchStation() { this.researchStation = false ;}
	
	/**
	 * tells if city had an outbreak of infection for a certain disease
	 *  @param the disease
	 *  @return true it is an outbreak , false otherwise.
	 */
	public Boolean isOutBreakOfInfection(Disease disease)  {return this.OutBreakInfectionPerDisease.get(disease);}
	
	/**
	 * declares city as an outBreak of a certain disease
	 * @param the disease
	 */
	public void declareOutBReakOfInfection(Disease disease) {this.OutBreakInfectionPerDisease.put(disease, true) ; }
	
	
	public Boolean checkIfIsOutBreakOnNextAdd(Disease disease) {
		
		Integer cubes = 0 ;
		for (Disease d :this.diseases) {if (d.equals(disease)) cubes ++ ; }
		
		return cubes >= 3 ;
	}
	
	/**
	 * returns a list of diseases (cubes in the physical game) found within the city
	 * @return the diseases
	 */
	public ArrayList<Disease> getDiseases() { return this.diseases ;}
	
	/**
	 * add a diseases to the city
	 * @param the disease
	 */
	public void addDisease(Disease disease) {
		Integer howManyCubes = 0 ;
		for (Disease presentDisease : this.diseases) {
			if (presentDisease.equals(disease) ) howManyCubes ++ ;
		}
		if (howManyCubes < 3 && ! this.isOutBreakOfInfection(disease))  this.diseases.add(disease) ; 
		else this.declareOutBReakOfInfection(disease) ;
		// add count to nbofOutBReaks in world
		}
	
	
	
	 
	
	public void removeDisease(Disease disease) throws IndexOutOfBoundsException {
		if (this.getDiseases().isEmpty()) throw new IndexOutOfBoundsException() ;
		this.getDiseases().remove(disease) ;}

	public int howManyCubes(Disease selectedDisease) {
	    int count = 0;
     
	    
	    
	    
	    for (Disease disease : this.diseases) {
	        if (disease.equals(selectedDisease)) {
	            count++;
	        }
	    }

	   
	    return count;
	}

}

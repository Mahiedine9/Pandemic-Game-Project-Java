
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
	private ArrayList<Cube> cubes = new ArrayList<Cube>();
	private ArrayList<Cure> cures = new ArrayList<Cure>();
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
	 * changes name of the city
	 * @param name
	 */
	public void setName(String name) { this.name = name ;}
	
	/**
	 * checks if the city has a research station
	 * @return true if a research station is installed within the city, false otherwise
	 */
	public Boolean isResearchStation() { return this.researchStation ;}
	
	/**
	 * add a research station to the city if there is none already
	 */
	public void turnResearchStation() { this.researchStation = true ;}
	
	/**
	 * tells if city had an outbreak of infection for a certain disease
	 *  @param the disease
	 *  @return true it is an outbreak , false otherwise.
	 */
	public Boolean isourBreakOfInfection(Disease disease)  {return this.OutBreakInfectionPerDisease.get(disease);}
	
	/**
	 * declares city as an outBreak of a certain disease
	 * @param the disease
	 */
	public void declareOutBReakOfInfection(Disease disease) {this.OutBreakInfectionPerDisease.put(disease, true) ;}
	
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
		if (howManyCubes < 3 && ! this.isourBreakOfInfection(disease))  this.diseases.add(disease) ; 
		else this.declareOutBReakOfInfection(disease) ;}
	
	/**
	 * remove a disease from the city
	 * @param the disease
	 */
	public void removeDisease(Disease disease) throws IndexOutOfBoundsException {
		if (this.getDiseases().isEmpty()) throw new IndexOutOfBoundsException() ;
		this.getDiseases().remove(disease) ;}
	
	/**
	 * returns the number of cubes of a certain disease present in the city
	 * @param disease the disease
	 * @return the number of cubes of the disease present in the city
	 */
	public int getCubeCount(Disease disease) {
	    int count = 0;
	    for (Cube cube : this.cubes) {
	        if (cube.getDisease() == disease) {
	            count++;
	        }
	    }
	    return count;
	}
	
	/**
	 * adds a cube of a certain disease to the city
	 * @param disease the disease
	 */
	public void addCube(Disease disease) {
	    if (!this.isOutbreakOfInfection(disease) && getCubeCount(disease) < 3) {
	        cubes.add(new Cube(disease));
	    } else {
	        declareOutbreakOfInfection(disease);
	    }
	}
	
	
	/**
	 * removes a cube of a certain disease from the city
	 * @param disease the disease
	 * @throws IllegalArgumentException if there are no cubes of the disease in the city
	 */
	public void removeCube(Disease disease) throws IllegalArgumentException {
	    boolean removed = false;
	    for (int i = 0; i < this.cubes.size(); i++) {
	        if (cubes.get(i).getDisease() == disease) {
	            cubes.remove(i);
	            removed = true;
	            break;
	        }
	    }
	    if (!removed) {
	        throw new IllegalArgumentException("There are no cubes of " + disease + " in " + name);
	    }
	
	}
	
	/**
	 * removes all cubes of a certain disease from the city
	 * @param disease the disease
	 */
	public void removeAllCubes(Disease disease) {
	    this.cubes.removeIf(cube -> cube.getDisease() == disease);
	}
	
	public ArrayList<Cure> getCures(){
		return this.cures;
	}
	
	public void addCure(Disease disease) {
		cubes.add(new Cure(disease));
	}
	
	
	public boolean isEradicated(Disease disease) {
	    boolean cureFound = false;
	    boolean cubesFound = false;
	    
	    // Check if there is a cure for the disease
	    for (Cure cure : this.cures) {
	        if (cure.getDisease() == disease) {
	            cureFound = true;
	            break;
	        }
	    }
	    
	    // Check if there are cubes of the disease in the city
	    for (Cube cube : this.cubes) {
	        if (cube.getDisease() == disease) {
	            cubesFound = true;
	            break;
	        }
	    }
	    
	    return cureFound && !cubesFound;
	}

		
	}
	
	
	
	
	
	

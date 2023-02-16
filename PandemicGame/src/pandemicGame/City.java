package pandemicGame;

import java.lang.reflect.Array;

public class City {
	
	// The name of the city.
	private String name;
	
	// Whether the city has a research station or not.
	private boolean researchStation = false ;

	// The diseases present in the city.
	private List<Disease> diseases = new ArrayList<Disease>();
	
	// The cubes on the city representing the diseases.
	private List<Cube> cubes = this.cubes = new ArrayList<Cube>();
	
	// Whether an outbreak of infection has occurred in the city.
	private boolean isOutbreakOfInfection =  false ;
	
	// The number of outbreaks that have occurred in the city.
	private int nbOutbreaks = 0 ;
	
	
	/**
	 * Constructs a new city with the specified name.
	 * @param name name of the city 
	 */

	public City(String name) {
		this.name = name ;
	}


	/**
	 * Returns the name of the city.
	 * @return the name of the city
	 */
	public String getName() {
		return this.name;
	}


	/**
	 * Returns the number of cubes for the specified disease in the city.
	 * @param disease the disease in question
	 * @return the number of cubes for the specified disease
	 */
	public int getNbOfCubes(Disease disease) {
		int count = 0;
		for (Cube cube : this.cubes) {
			if (cube.getDisease().equals(disease)) {
				count++;
			}
		}
		return count;
	}


	/**
	 * Adds the specified cube to the city.
	 * @param cube the cube to add.
	 */
	public void addCube(Cube cube) {
		this.cubes.add(cube);
	}
	

	/**
	 * Removes the specified cube from the city.
	 * @param cube the cube to remove
	 */
	public void removeCube(Cube cube) {
		this.cubes.remove(cube);
	}

	public ArrayList<Cube> getCubes() {
		return this.cubes ;
	}

	public ArrayList<Disease> getDiseases() {
		return this.diseases ;
	}
	
	/**
	 * Returns whether the city has a research station or not.
	 * @return true if the city has a research station, false otherwise
	 */
	public boolean hasResearchStation() {
		return this.researchStation;
	}
	
	/**
	 * Builds a research station in the city.
	 */
	public void buildResearchStation() {
		this.researchStation = true;
	}
	
	/**
	 * Removes all cubes from the city.
	 */
	public void removeAllCubes() {
		this.cubes.clear();
	}
	
	/**
	 * Returns whether an outbreak of infection has occurred in the city.
	 * @return true if an outbreak has occurred, false otherwise
	 */
	public boolean isOutbreak() {
		boolean result = false;
		for (Disease disease : this.diseases) {
			if (this.getNbOfCubes(disease) >= 3) {
				this.nbOutbreaks++;
				this.isOutbreakOfInfection = true;
				result = true;
			}
		}
		return result;
	}


	/**
	 * Adds the specified disease to the city.
	 * @param disease the disease to add
	 */
	public void addDisease(Disease disease) {
		this.diseases.add(disease);
	}

	
	/**
	 * remove the specified disease from the city.
	 * @param disease the disease to remove
	 */
	public void removeDisease(Disease disease) {
		this.diseases.remove(disease);
	}

	public ArrayList<City> getDiseases() {
        return this.cities;
    }

    public int getCubes() {
        return this.Cubes;
    }
}

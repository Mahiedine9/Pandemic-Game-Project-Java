package Game;

public class City {
	private string name;
	private boolean ResearchStation;
	private ArrayList<Disease> diseases;
	private ArrayList<Cube> cubes; //chaque element est un nb de cubes pour une maladie
	private boolean isoutbreakofinfection;
	private int nbInfections;
	
	public City(string name) {
		this.name = name;
		this.ResearchStation = false;
		this.isoutbreakofinfection = false;
		this.nbInfections = 0;  //nb of outbreak infections
		this.diseases = new ArrayList<Disease>();
		this.cubes = new ArrayList<Cube>();
	}
	public string GetName() {
		return this.name;
	}
	
	public int GetNbOfInfections() {
		return this.nbInfections;
	}
	public int GetNbCubes(Disease disease) {
		int cmp = 0;
		for (Cube cube : this.cubes) {
			if (cube.GetDisease() == disease) {
				cmp+=1;
			}
		}
		return cmp
	}
	
	public void AddCube(Cube cube) {
		this.cubes.add(cube);
	}
	
	public void DeleteCube(Cube cube) {
		int index = this.cubes.indexOf(cube);
		this.cubes.remove(index);
	}
	
	public boolean ThereIsStation() {
		return this.ResearchStation;
	}
	
	public void BuildStation(ResearchStation station) {
		this.ResearchStation = true;
	}
	
	public void DeleteAllCubes() {
		this.cubes.clear();
	}
	
	public boolean IsOutBreak() {
		for (Disease disease : this.diseases) {
			if (this.GetNbCubes(disease)>=3) {
				this.nbInfections ++;
				this.isoutbreakofinfection = true;
			}
		}
		
		return this.isoutbreakofinfection;
	}
	
	
	public void AddDisease(Disease disease) {
		this.diseases.add(disease);
	}
	
	public void DeleteDisease(Disease disease) {
		int index = this.disease.indexOf(disease);
		this.disease.remove(index);
	}
	
	

	
	

}

package pandemicgame;

public class Doctor extends Player {
		
	
	
		public Doctor(String name, City city, World world) {
			super(name, city, world) ;
		}
		
		public void treatDisease(Disease disease) {
		
		 while (this.currentCity.getDiseases().contains(disease)) 
			 this.currentCity.removeDisease(disease) ;
		 
		if ( ! this.world.getRemedies().contains(disease) )
			this.nbActionsRemaining -- ;
	}
}

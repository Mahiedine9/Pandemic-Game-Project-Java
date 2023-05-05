package pandemicgame;

public class Globetrotter extends Player  {

		public Globetrotter(String name, City city, World world) {
			super(name, city, world) ;
		}
		
		public void move (City newCity) {
			this.currentCity = newCity ;
		}
					
}
		
		
		
		
		



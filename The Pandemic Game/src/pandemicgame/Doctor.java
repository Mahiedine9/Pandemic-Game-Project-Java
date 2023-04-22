/**
 * The Doctor class is a subclass of the Player class and represents the doctor character in the Pandemic game.
 * The doctor has a special ability that allows them to remove all cubes of a disease in a given city at once, even if no cure has been found.
 */
public class Doctor extends Player {

    /**
     * Constructor for the Doctor class that initializes a new doctor by calling the constructor of the superclass Player.
     */
    public Doctor(){
        super();
    }

    /**
     * Method that allows the doctor to treat a disease in a given city by removing all cubes of the disease present in that city.
     * This method takes a City object representing the city where the doctor is located, and a Disease object representing the disease to be treated.
     * The method calls the removeDisease() method of the City object to remove all cubes of the disease.
     * 
     * @param city The city where the disease is being treated
     * @param disease The disease to be treated
     */
    public void treatingDisease(City city, Disease disease){
        city.removeDisease(disease);
    }

}


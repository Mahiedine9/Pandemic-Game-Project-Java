/**
 * The Expert class represents the role of an Expert in the Pandemic game.
 * The Expert can build a research station in a city without having to discard a City card.
 */
public class Expert extends Player {

    /**
     * Constructor for the Expert class that initializes a new Expert with default values.
     */
    public Expert(){
        super();
    }

    /**
     * Method that builds a research station in a given City.
     * 
     * @param city The City object in which to build the research station
     */
    public void buildStation(City city){
        city.turnResearchStation();
    }
}

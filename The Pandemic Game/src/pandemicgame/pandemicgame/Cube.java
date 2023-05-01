package pandemicgame;

/**
 * Class Cube representing a cube of disease in the game
 */
public class Cube {
    private Disease disease;

    /**
     * class constructor
     * @param disease the disease associated with the cube
     */
    public Cube(Disease disease) {
        this.disease = disease;
    }

    /**
     * gets the disease associated with the cube
     * @return the disease associated with the cube
     */
    public Disease getDisease() {
        return disease;
    }
}

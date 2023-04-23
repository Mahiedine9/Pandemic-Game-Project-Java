/**
 * The Scientific class represents a player who is able to cure diseases in the game Pandemic.
 * This class extends the Player class and has a method CureDisease which allows the player to find a cure for a given disease.
 */
public class Scientific extends Player{

    /**
     * Constructs a new Scientific player.
     */
    public Scientific(){
        super();
    }

    /**
     * Allows the player to find a cure for a given disease.
     * @param disease The disease to find a cure for.
     * @return A new instance of the Cure class representing the cure found.
     */
    public Cure CureDisease(Disease disease){
        return new Cure(disease);
    }

}
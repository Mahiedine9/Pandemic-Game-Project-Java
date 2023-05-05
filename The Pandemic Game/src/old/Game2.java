package old;

import pandemicgame.Disease;

public class Game2 {
    private List<Cure2> curesFound = new ArrayList<>();

    public void addCure(Cure2 cure) {
        curesFound.add(cure);
    }

    public boolean hasCure(Disease disease) {
        for (Cure2 cure : curesFound) {
            if (cure.getDisease() == disease) {
                return true;
            }
        }
        return false;
    }
}

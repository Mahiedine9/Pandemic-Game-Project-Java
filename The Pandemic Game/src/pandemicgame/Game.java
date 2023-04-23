public class Game {
    private List<Cure> curesFound = new ArrayList<>();

    public void addCure(Cure cure) {
        curesFound.add(cure);
    }

    public boolean hasCure(Disease disease) {
        for (Cure cure : curesFound) {
            if (cure.getDisease() == disease) {
                return true;
            }
        }
        return false;
    }
}

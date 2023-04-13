package pandemicGame;

public class Scientific extends Role{

    public Scientific(){
        super();
    }

    public void CureDisease(Disease disease){
        disease.CureDisease();
    }



}

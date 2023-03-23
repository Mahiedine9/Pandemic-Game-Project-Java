public class Globetrotter extends Role{
    private WorldMap map;
    public Globetrotter(WorldMap map){
        super();
        this.map = map;
    } 

    public void MoveAnyCity(City city){
        this.map.movePlayer(super.player, city);
    }







} 
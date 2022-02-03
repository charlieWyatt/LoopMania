package unsw.loopmania;

public class Tower extends MovingEntity {

    /**
     * create a tower class
     * 
     * @param position where the tower is, - not used
     */    
    public Tower(PathPosition position) {
        super(position);
        super.setDamage(4);
        super.setCritChance(0);
        super.setType("Tower");
    }
        
}

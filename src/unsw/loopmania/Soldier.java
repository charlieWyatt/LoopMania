package unsw.loopmania;

public class Soldier extends MovingEntity {

    /**
     * create a soldier class
     * 
     * @param position where the soldier is, - not used
     */    
    public Soldier(PathPosition position) {
        super(position);
        super.setHealth(10);
        super.setDamage(2);
        super.setCritChance(0);
        super.setType("Soldier");
    }
        
}

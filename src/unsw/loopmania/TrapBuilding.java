package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a building which is placed on a path tile killing enemies
 */
public class TrapBuilding extends Building {
    private int radius = 0;
    private TrapEffect buildingEffect = new TrapEffect();

    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setRadius(radius);
        super.setBuildingEffect(buildingEffect);
    }

    public void updateStatus() {
        if(buildingEffect.getUses() <= 0) {
            destroy();
        } 
        return;
    }

}

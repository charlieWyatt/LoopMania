package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a building which regenerates health as the Character passes through
 */
public class VillageBuilding extends Building {
    private int radius = 0;
    private BuildingEffect buildingEffect = new RegenerationEffect();
    
    public VillageBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setBuildingEffect(buildingEffect);
        super.setRadius(radius);
    }
}

package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A building which gives the character an allied soldier
 */
public class BarracksBuilding extends Building {
    private int radius = 0; // must be passed through
    private BuildingEffect buildingEffect = new GiveSoldierEffect();

    public BarracksBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setBuildingEffect(buildingEffect);
        super.setRadius(radius);
    }

}

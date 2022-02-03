package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a campire building in the world which doubles the damage to the character when in radius
 */
public class CampfireBuilding extends Building {
    private int radius = 4;


    public CampfireBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setBuildingEffect(new FireEffect());
        super.setRadius(radius);
    }
}

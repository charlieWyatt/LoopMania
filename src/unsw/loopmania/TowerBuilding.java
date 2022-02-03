package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a building with a long radius which shoots enemies
 */
public class TowerBuilding extends Building {
    private int radius = 4; // a long range
    

    public TowerBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setRadius(radius);
    }

}

package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a barracks card in the backend game world
 */
public class BarracksCard extends Card {
    
    public BarracksCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // this works to dynamically set placement
        super(x, y);
        super.setPlacement(new PathPlacement());
    }

    /**
     * Creates a barracks building at an x, y coordinate
     */
    @Override
    public BarracksBuilding placeBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // child classes will override this
        return new BarracksBuilding(x, y);
    }

}

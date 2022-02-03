package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a trap card in the backend game world
 */
public class TrapCard extends Card {
    
    public TrapCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // this works to dynamically set placement
        super(x, y);
        super.setPlacement(new PathPlacement());
    }

    /**
     * Describes what kind of building the card will produce
     * @param x the x coordinate to place the building
     * @param y the y coordinate to place the building
     * @return A trap building
     */
    @Override
    public TrapBuilding placeBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // child classes will override this
        return new TrapBuilding(x, y);
    }

}

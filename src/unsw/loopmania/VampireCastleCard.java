package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a vampire castle card in the backend game world
 */
public class VampireCastleCard extends Card {
    
    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // this works to dynamically set placement
        super(x, y);
        super.setPlacement(new AdjacentPlacement());
    }

    /**
     * Describes what kind of building the card will produce
     * @param x the x coordinate to place the building
     * @param y the y coordinate to place the building
     * @return A vampire castle building
     */
    @Override
    public VampireCastleBuilding placeBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // child classes will override this
        return new VampireCastleBuilding(x, y);
    }

}

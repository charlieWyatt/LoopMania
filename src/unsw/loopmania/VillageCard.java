package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a village card in the backend game world
 */
public class VillageCard extends Card {
    
    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // this works to dynamically set placement
        super(x, y);
        super.setPlacement(new PathPlacement());
    }

    /**
     * Describes what kind of building the card will produce
     * @param x the x coordinate to place the building
     * @param y the y coordinate to place the building
     * @return A village building
     */
    @Override
    public VillageBuilding placeBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // child classes will override this
        return new VillageBuilding(x, y);
    }

}

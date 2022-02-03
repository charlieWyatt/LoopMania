package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a Tower card in the backend game world
 */
public class TowerCard extends Card {
    private PlacementBehaviour placementBehaviour = new AdjacentPlacement();


    public TowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // this works to dynamically set placement
        super(x, y);
        super.setPlacement(placementBehaviour);
    }

    /**
     * Places a tower building at an x, y coordinate
     * @param x x coordinate to place building
     * @param y y coordinate to place building
     * @return the tower building
     */
    @Override
    public TowerBuilding placeBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // child classes will override this
        return new TowerBuilding(x, y);
    }

}

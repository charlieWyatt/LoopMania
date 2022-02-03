package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a campfire card in the backend game world
 */
public class CampfireCard extends Card {
    
    public CampfireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // this works to dynamically set placement
        super(x, y);
        super.setPlacement(new GrassPlacement());
    }

    /**
     * Places a campfire builing in the world
     */
    @Override
    public CampfireBuilding placeBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // child classes will override this
        return new CampfireBuilding(x, y);
    }

}

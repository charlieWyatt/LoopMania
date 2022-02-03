package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;
import org.javatuples.Pair;

/**
 * a Card in the world
 * which doesn't move. Represents the parent class for all other cards.
 */
public abstract class Card extends StaticEntity {
    // Behaviours are set in the child class
    PlacementBehaviour placementBehaviour;
    
    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * A simple setter for how placing a card will behave
     * @param placementBehaviour
     */
    public void setPlacement(PlacementBehaviour placementBehaviour) {
        this.placementBehaviour = placementBehaviour;
    }

    /**
     * A boolean strategy pattern which shows wether a building can be placed at a location
     * @param pathposition
     * @param x
     * @param y
     * @return
     */
    public boolean checkPlacement(List<Pair<Integer, Integer>> pathposition, int x, int y) {
        return placementBehaviour.checkPlacement(pathposition, x, y);
    }

    /**
     * A method will be overridden by child classes. It will place the type of building associated with each card
     * @param x
     * @param y
     * @return
     */
    public Building placeBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // child classes will override this
        return null;
    }
}

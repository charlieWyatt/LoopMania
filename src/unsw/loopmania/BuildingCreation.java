package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

// A strategy for cards in creating buildings
public interface BuildingCreation {
    public Building placeBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y);
}
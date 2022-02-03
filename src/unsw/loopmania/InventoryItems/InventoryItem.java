package unsw.loopmania.InventoryItems;

import unsw.loopmania.StaticEntity;
import unsw.loopmania.Character;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class InventoryItem extends StaticEntity {

    public InventoryItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public abstract void equip(Character character);

    public abstract void unEquip(Character character);

    public void addPassive(Character character) {
        return;
    }

    public void removePassive(Character character) {
        return;
    }
}

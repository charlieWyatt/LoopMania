package unsw.loopmania.InventoryItems.Armour;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.InventoryItems.InventoryItem;

/**
 * represents a parent class for all equippable items that will affect the Character's armour
 */
public class Armour extends InventoryItem {
    private static int armour = 0;
    
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * A simple getter for the amount of armour this equipment adds to the character
     * @return  the amount of armour added to the character on equip
     */
    public static int getArmour() {
        return armour;
    }

    /**
     * A simple setter for the amount of armour
     * @param armour
     */
    public void setArmour(int armour) {
        Armour.armour = armour;
    }

    /**
     * Removes this item from the character to change its armour stats back to base level.
     * @param character
     */
    public void unEquip(Character character) {
        character.addEquipmentArmour(-armour);
    }

    /**
     * Equips this item to the character to change its armour stats
     * @param character
     */
    public void equip(Character character) {
        character.addEquipmentArmour(armour);
    }
}

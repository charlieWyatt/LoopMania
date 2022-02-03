package unsw.loopmania.InventoryItems.Armour;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped chestplate in the backend world
 */
public class Chest extends Armour {
    private int armour = 50;
    private static int price = 200;

    public static int getPrice(){
        return price;
    }

    public Chest(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setArmour(armour);
    }
}

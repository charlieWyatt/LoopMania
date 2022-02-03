package unsw.loopmania.InventoryItems.Armour;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped shield in the backend world
 */
public class Shield extends Armour {
    private static int armour = 5;
    private static int price = 100;
    private static double shieldCritModifier = 0.6;

    public static int getPrice(){
        return price;
    }

    public static double getShieldCritModifier() {
        return shieldCritModifier;
    }

    public void setShieldCritModifier(double shieldCritModifier) {
        Shield.shieldCritModifier = shieldCritModifier;
    }

    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setArmour(armour);
    }
}

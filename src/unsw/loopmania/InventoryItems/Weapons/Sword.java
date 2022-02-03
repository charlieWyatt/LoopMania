package unsw.loopmania.InventoryItems.Weapons;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends Weapon {
    private int damage = 5;
    private static int price = 50;

    public static int getPrice(){
        return price;
    }

    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setDamage(damage);
    }    
}

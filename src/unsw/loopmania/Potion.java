package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.InventoryItems.InventoryItem;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Potion extends InventoryItem {
    private static int price = 35;

    public static int getPrice() {
        return price;
    }

    public Potion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * The character drinks a poition and restores some health
     * @Param List<Potion> Potions
     * @Param Character character
     */

    public void drink(List<Potion> Potions, Character character) {
        if(Potions.size() > 0) {
            int health = character.getHealth();
            health += character.getMaxHealth() * 0.1;
            character.setHealth(health);
            Potions.remove(Potions.size()-1);
        }
    }

    @Override
    public void equip(Character character) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unEquip(Character character) {
        // TODO Auto-generated method stub
        
    }

}

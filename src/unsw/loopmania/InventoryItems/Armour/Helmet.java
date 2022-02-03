package unsw.loopmania.InventoryItems.Armour;
import unsw.loopmania.Character;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped helmet in the backend world
 */
public class Helmet extends Armour {
    private int armour = 20;
    private static int price = 50;
    private int damagePenalty = 1;

    public static int getPrice(){
        return price;
    }

    /**
     * Removes this item from the character to change its armour stats back to base level.
     * @param character
     */
    @Override
    public void unEquip(Character character) {
        character.addEquipmentArmour(-armour);
        character.addWeaponDamage(damagePenalty);
    }

    /**
     * Equips this item to the character to change its armour stats
     * @param character
     */
    @Override
    public void equip(Character character) {
        character.addEquipmentArmour(armour);
        character.addWeaponDamage(-damagePenalty);
    }

    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setArmour(armour);
    }
}

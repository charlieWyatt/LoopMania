package unsw.loopmania.InventoryItems.Weapons;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.InventoryItems.InventoryItem;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Weapon extends InventoryItem  {

    private int damage = 0;

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    public Weapon(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public void equip(Character character) {
        character.addWeaponDamage(damage);
    }

    @Override
    public void unEquip(Character character) {
        character.addWeaponDamage(-damage);
    }
}

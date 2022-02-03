package unsw.loopmania.InventoryItems.Weapons;
import unsw.loopmania.Character;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Stake extends Weapon {
    private int damage = 4;
    private static int price = 75;
    private String attackBonus = "Vampire";

    public static int getPrice(){
        return price;
    }

    public String getAttackBonus() {
        return this.attackBonus;
    }

    public void setAttackBonus(String attackBonus) {
        this.attackBonus = attackBonus;
    }


    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setDamage(damage);
    }    

    @Override
    public void equip(Character character) {
        character.addWeaponDamage(damage);
        character.addAttackBonus(attackBonus);
    }

    @Override
    public void unEquip(Character character) {
        character.addWeaponDamage(-damage);
        character.removeAttackBonus(attackBonus);
    }
}

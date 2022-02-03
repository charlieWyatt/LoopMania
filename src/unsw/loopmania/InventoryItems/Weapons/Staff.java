package unsw.loopmania.InventoryItems.Weapons;
import unsw.loopmania.Character;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Staff extends Weapon {
    private int damage = 3;
    private static int price = 100;
    private static int critChance = 5;

    public static int getPrice(){
        return price;
    }

    public static int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        Staff.critChance = critChance;
    }
    private String attackBonus = "Magic";

    public String getAttackBonus() {
        return this.attackBonus;
    }

    public void setAttackBonus(String attackBonus) {
        this.attackBonus = attackBonus;
    }

    
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
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

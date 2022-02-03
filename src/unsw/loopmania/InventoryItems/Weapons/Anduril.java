package unsw.loopmania.InventoryItems.Weapons;
import unsw.loopmania.Character;
import unsw.loopmania.InventoryItems.InventoryItem;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped anduril in the backend world
 */
public class Anduril extends Weapon {
    private int damage = 20;
    private String attackBonus = "Boss";
    private static InventoryItem confusingBonusEffects = null;

    
    public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setDamage(damage);
    }    

    @Override
    public void equip(Character character) {
        character.addWeaponDamage(damage);
        character.addAttackBonus(attackBonus);
        if(confusingBonusEffects != null) {
            confusingBonusEffects.addPassive(character); // this wont unequip items since it directly applies damage here;
        }
    }

    @Override
    public void unEquip(Character character) {
        character.addWeaponDamage(-damage);
        character.removeAttackBonus(attackBonus);
        if(confusingBonusEffects != null) {
            confusingBonusEffects.removePassive(character);
        }
    }

    @Override
    public void addPassive(Character character) {
        character.addWeaponDamage(damage);
        character.addAttackBonus(attackBonus);
    }

    @Override
    public void removePassive(Character character) {
        character.addWeaponDamage(-damage);
        character.removeAttackBonus(attackBonus);
    }

    public static void giveConfusingBonus(InventoryItem item) {
        confusingBonusEffects = item;
    }
}
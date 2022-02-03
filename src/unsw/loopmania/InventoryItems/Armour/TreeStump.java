package unsw.loopmania.InventoryItems.Armour;
import unsw.loopmania.Character;
import unsw.loopmania.InventoryItems.InventoryItem;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped treeStump in the backend world
*/

public class TreeStump extends Shield {
    private int armour = 10;
    private String defenceBonus = "Boss";
    private static InventoryItem confusingBonusEffects = null;

    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setArmour(armour);
    }

    @Override
    public void equip(Character character) {
        character.addEquipmentArmour(armour);
        character.addDefenceBonus(defenceBonus);
        if(confusingBonusEffects != null) {
            confusingBonusEffects.addPassive(character);
        }
    }

    @Override
    public void unEquip(Character character) {
        character.addEquipmentArmour(-armour);
        character.removeDefenceBonus(defenceBonus);
        if(confusingBonusEffects != null) {
            confusingBonusEffects.removePassive(character);
        }
    }

    @Override
    public void addPassive(Character character) {
        character.addEquipmentArmour(armour);
        character.addDefenceBonus(defenceBonus);
    }

    @Override
    public void removePassive(Character character) {
        character.addEquipmentArmour(-armour);
        character.removeDefenceBonus(defenceBonus);
    }

    public static void giveConfusingBonus(InventoryItem item) {
        confusingBonusEffects = item;
    }

}

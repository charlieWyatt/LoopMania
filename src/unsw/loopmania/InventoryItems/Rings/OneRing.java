package unsw.loopmania.InventoryItems.Rings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.InventoryItems.InventoryItem;


/**
 * represents an equipped or unequipped ring in the backend world. This ring adds an extra life when equipped
 */
public class OneRing extends InventoryItem  {
    private int lives = 1;
    private static InventoryItem confusingBonusEffects = null;

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public OneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public void equip(Character character) {
        character.addLives(lives);
        if(confusingBonusEffects != null) {
            confusingBonusEffects.addPassive(character);
        }
    }

    @Override
    public void unEquip(Character character) {
        character.addLives(-lives);
        if(confusingBonusEffects != null) {
            confusingBonusEffects.removePassive(character);
        }
    }

    public int getLives() {
        return lives;
    }

    @Override
    public void addPassive(Character character) {
        character.addLives(lives);
    }

    @Override
    public void removePassive(Character character) {
        character.addLives(-lives);
    }

    public static void giveConfusingBonus(InventoryItem item) {
        confusingBonusEffects = item;
    }

}

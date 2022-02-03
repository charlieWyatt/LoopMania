package unsw.loopmania;

/**
 * A building effect strategy which applies a fire effect. Gives the character double damage
 */
public class FireEffect implements BuildingEffect{
    FireEffect() {
        super();
    }
 
    // Gives the character a fire bonus which allows it to deal double damage
    @Override
    public void applyEffect(Character character) {
        character.addAttackBonus("fire");
    }

    // Removes the characters fire double damage
    @Override
    public void removeEffect(Character character) {
        character.removeAttackBonus("fire");
    }   

    // Currently has no effect on enemies
    @Override
    public void applyEffect(BasicEnemy enemy) {
        // no effect to enemies
        return;
    }

    // Currently has no effect on enemies
    @Override
    public void removeEffect(BasicEnemy enemy) {
        // no effect to enemies
        return;
    }


}
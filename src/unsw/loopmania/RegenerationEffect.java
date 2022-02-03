package unsw.loopmania;

/**
 * A building effect strategy which regenerates health to the character
 */
public class RegenerationEffect implements BuildingEffect{

    RegenerationEffect() {
        super();
    }
 
    // Gives the character 10% of max health
    @Override
    public void applyEffect(Character character) {
        character.changeHealth(character.getMaxHealth()/10);
    }

    // Has no effect
    @Override
    public void removeEffect(Character character) {
        return;
    }   

    // Has no effect
    @Override
    public void applyEffect(BasicEnemy enemy) {
        // no effect to enemies
        return;
    }

    // Has no effect
    @Override
    public void removeEffect(BasicEnemy enemy) {
        // no effect to enemies
        return;
    }


}
package unsw.loopmania;

/**
 * A building effect which gives the character a soldier
 */
public class GiveSoldierEffect implements BuildingEffect{
    private int numSoldiers = 1;

    GiveSoldierEffect() {
        super();
    }
 
    // Gives the character a soldier
    @Override
    public void applyEffect(Character character) {
        character.addSoldier(numSoldiers);
        return;
    }

    // Has no effect
    @Override
    public void removeEffect(Character character) {
        return;
    }   

    // Has no effect
    @Override
    public void applyEffect(BasicEnemy enemy) {
        return;
    }

    // Has no effect
    @Override
    public void removeEffect(BasicEnemy enemy) {
        // no effect to enemies
        return;
    }


}
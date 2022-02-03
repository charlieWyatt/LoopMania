package unsw.loopmania;

/**
 * A trap strategy building effect which kills an enemy
 */
public class TrapEffect implements BuildingEffect{
    private int damage = 999;
    private int uses = 1;

    TrapEffect() {
        super();
    }

    public int getUses() {
        return uses;
    }
 
    // Does nothing to the character
    @Override
    public void applyEffect(Character character) {
        return;
    }

    // Does nothing to the character
    @Override
    public void removeEffect(Character character) {
        return;
    }   

    // Hurts all enemies on this square
    @Override
    public void applyEffect(BasicEnemy enemy) {
        enemy.changeHealth(-damage);
        uses -= 1;
    }

    // Has no effect
    @Override
    public void removeEffect(BasicEnemy enemy) {
        // no effect to enemies
        return;
    }


}
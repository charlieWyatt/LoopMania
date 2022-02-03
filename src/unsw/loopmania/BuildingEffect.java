package unsw.loopmania;

// A strategy for the effect a building has
public interface BuildingEffect {

    /**
     * Applies an effect to a character
     * @param character
     */
    public void applyEffect(Character character);

    /**
     * Removes an effect from a character
     * @param character
     */
    public void removeEffect(Character character);

    /**
     * Applies an effect to an enemy
     * @param enemy
     */
    public void applyEffect(BasicEnemy enemy);

    /**
     * Removes an effect from an enemy
     * @param enemy
     */
    public void removeEffect(BasicEnemy enemy);
}

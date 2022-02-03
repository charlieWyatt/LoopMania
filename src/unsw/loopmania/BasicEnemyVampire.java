package unsw.loopmania;

import java.util.Random;

/**
 * a basic form of enemy in the world
 */
public class BasicEnemyVampire extends BasicEnemy {
    // TODO = modify this, and add additional forms of enemy

    private int bonusHits = 0;
    private int bonusHitsAmount = 3;
    private int bonusHitsDamage = 3;

    public BasicEnemyVampire(PathPosition position) {
        super(position);
        super.setDamage(6);
        super.setHealth(10);
        super.setBattleRadius(3);
        super.setSupportRadius(4);
        super.setCritChance(10);
        super.setType("Vampire");
        super.setMovementChance(4);
    }

    /**
     * Attack Function
     * @param Target being attacked
     * @return void
     */
    @Override
    public void attack(MovingEntity target){
        //Vampires deal 6 damage
        target.changeHealth(-super.getDamage());
        //If they have recently crit, do a bonus 3 damage
        if (bonusHits > 0) {
            target.changeHealth(-bonusHitsDamage);
            bonusHits--;
        }
        int crit = (new Random()).nextInt(100);
        //If random 0-99 is below crit chance, vampire crits
        if (crit < super.getCritChance()){
            //Vampires do bonus hits on the next 3 attacks, this does not stack.
            bonusHits = bonusHitsAmount;
        }

    }
}

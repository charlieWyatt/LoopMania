package unsw.loopmania;

import java.util.Random;

/**
 * Boss Enemy Doggie
 */
public class BasicEnemyDoggie extends BasicEnemy {

    public BasicEnemyDoggie(PathPosition position) {
        super(position);
        super.setDamage(5);
        super.setHealth(100);
        super.setBattleRadius(1);
        super.setSupportRadius(1);
        super.setCritChance(15);
        super.setType("Boss");
    }

    /**
     * Move Function
     */
    @Override
    public void move(){
        //Doggie don't move
        return;
    }

    /**
     * Attack Function
     * @param Target being attacked
     * @return void
     */
    @Override
    public void attack(MovingEntity target){
        //Doggies deal 5 damage
        target.changeHealth(-super.getDamage());
        int crit = (new Random()).nextInt(100);
        //If random 0-99 is below crit chance, Doggie crits
        if (crit < super.getCritChance()){
            //Crits stun the character for their next 2 attacks
            if (target instanceof Character){
                target.setStunned(2);
            }
        }
    }
}

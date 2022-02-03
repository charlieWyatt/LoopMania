package unsw.loopmania;

import java.util.Random;

/**
 * a basic form of enemy in the world
 */
public class BasicEnemyZombie extends BasicEnemy {

    // TODO = modify this, and add additional forms of enemy
    public BasicEnemyZombie(PathPosition position) {
        super(position);
        super.setDamage(3);
        super.setHealth(6);
        super.setBattleRadius(2);
        super.setSupportRadius(3);
        super.setCritChance(5);
        super.setType("Zombie");
        super.setMovementChance(8);
    }

    /**
     * Attack Function
     * @param Target being attacked
     * @return void
     */
    @Override
    public void attack(MovingEntity target){
        //Zombies deal 3 damage
        target.changeHealth(-super.getDamage());
        int crit = (new Random()).nextInt(100);
        if (crit < super.getCritChance()){
            //if Zombie crits check if it is a soldier
            if (target.getType() == "Soldier"){
                //if it is a soldier, set the hp to -100 which is a trigger for spawning a zombie
                target.setHealth(-100);
            }
        }
    }
}

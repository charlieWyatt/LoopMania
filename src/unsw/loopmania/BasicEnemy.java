package unsw.loopmania;

import java.util.Random;

/**
 * a basic form of enemy in the world
 * Basic enemy and its subtypes implements the Template Method design pattern
 */
public class BasicEnemy extends MovingEntity {
    public BasicEnemy(PathPosition position) {
        super(position);
    }

    /**
     * Move Function
     */
    public void move(){
        int directionChoice = (new Random()).nextInt(super.getMovementChance()); //(new Random()).nextInt(0);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }

    /**
     * Attack Function
     * @param Target being attacked
     * @return void
     */
    public void attack(MovingEntity target){
        target.changeHealth(-super.getDamage());
    }


}

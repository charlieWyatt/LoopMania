package unsw.loopmania;

//import java.util.Random;

/**
 * a Slug
 */
public class BasicEnemySlug extends BasicEnemy {
    
    // TODO = modify this, and add additional forms of enemy
    public BasicEnemySlug(PathPosition position) {
        super(position);
        super.setDamage(2);
        super.setHealth(3);
        super.setBattleRadius(1);
        super.setSupportRadius(1);
        super.setCritChance(0);
        super.setType("Slug");
    }

    /**
     * Move Function
     */
    public void move(){
        //Slugs don't move
        return;
    }

    /**
     * Attack Function
     * @param Target being attacked
     * @return void
     */
    public void attack(MovingEntity target){
        //Slugs deal 1 damage
        target.changeHealth(-super.getDamage());
    }
}

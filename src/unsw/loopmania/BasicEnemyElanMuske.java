package unsw.loopmania;


/**
 * Boss Enemy Elan Muske
 */
public class BasicEnemyElanMuske extends BasicEnemy {

    private static double doggieCoinStdDev = 0.1;
    private static double doggieCoinMean = 1.02; // mean increase per tick

    public BasicEnemyElanMuske(PathPosition position) {
        super(position);
        super.setDamage(20);
        super.setHealth(1000);
        super.setBattleRadius(1);
        super.setSupportRadius(1);
        super.setCritChance(0);
        super.setType("Boss");
    }

    /**
     * Move Function
     */
    @Override
    public void move(){
        //Elan don't move
        return;
    }

    public static double getStdDev() {
        return doggieCoinStdDev;
    }

    public static double getMean() {
        return doggieCoinMean;
    }

}

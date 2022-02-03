package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * The moving entity
 */
public abstract class MovingEntity extends Entity {

    /**
     * object holding position in the path
     */
    private PathPosition position;

    private int damage;

    private int critChance;

    private int health;

    private double battleRadius;

    private double supportRadius;

    private int MovementChance;

    private int stunned = 0;
    
    private int duration = 0;

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void addDuration(int duration) {
        this.duration += duration;
    }

    public int getStunned() {
        return this.stunned;
    }

    public void setStunned(int stunned) {
        this.stunned = stunned;
    }

    public int getMovementChance() {
        return this.MovementChance;
    }

    public void setMovementChance(int MovementChance) {
        this.MovementChance = MovementChance;
    }

    public double getBattleRadius() {
        return this.battleRadius;
    }

    public void setBattleRadius(double battleRadius) {
        this.battleRadius = battleRadius;
    }

    public double getSupportRadius() {
        return this.supportRadius;
    }

    public void setSupportRadius(double supportRadius) {
        this.supportRadius = supportRadius;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void changeHealth(int change) {
        this.health = this.health + change;
    }

    public PathPosition getPosition() {
        return this.position;
    }

    public void setPosition(PathPosition position) {
        this.position = position;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void changeDamage(int change) {
        this.damage = this.damage + change;
    }

    public int getCritChance() {
        return this.critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    /**
     * Create a moving entity which moves up and down the path in position
     * @param position represents the current position in the path
     */
    public MovingEntity(PathPosition position) {
        super();
        this.position = position;
    }

    /**
     * move clockwise through the path
     */
    public void moveDownPath() {
        position.moveDownPath();
    }

    /**
     * move anticlockwise through the path
     */
    public void moveUpPath() {
        position.moveUpPath();
    }

    public SimpleIntegerProperty x() {
        return position.getX();
    }

    public SimpleIntegerProperty y() {
        return position.getY();
    }

    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public void attack(MovingEntity target){
        target.changeHealth(-damage);
    }

}

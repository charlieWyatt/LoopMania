package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;


/**
 * a basic form of building in the world. All other buildings are a child class of this.
 */
public class Building extends StaticEntity {
    private int radius = 2;
    private BuildingEffect buildingEffect;
    private int x = super.getX();
    private int y = super.getY();


    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        
    }

    /**
     * A simple setter for the radius. Will be called by child classes
     * @param radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * A setter for the building effect. This determines how a building may act.
     * @param buildingEffect
     */
    public void setBuildingEffect(BuildingEffect buildingEffect) {
        this.buildingEffect = buildingEffect;
    }

    /**
     * Strategy pattern for applying character effects across all buildings
     * @param character
     */
    public void radiusCharacterEffects(Character character) {
        if(Math.pow((character.getX()-x), 2) +  Math.pow((character.getY()-y), 2) <= radius) {
            buildingEffect.applyEffect(character);
        } else {
            buildingEffect.removeEffect(character);
        }
    }

    /**
     * Strategy pattern for applying enemy effects across all buildings
     * @param character
     */
    public void radiusEnemyEffects(List<BasicEnemy> enemies) {
        for(BasicEnemy e: enemies) {
            if(Math.pow((e.getX()-super.getX()), 2) +  Math.pow((e.getY()-super.getY()), 2) <= radius) {
                buildingEffect.applyEffect(e);
            } else {
                buildingEffect.removeEffect(e);
            }
        }
    }
}

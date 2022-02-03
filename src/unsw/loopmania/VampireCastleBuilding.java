package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

import org.javatuples.Pair;
import java.lang.Integer;
import java.util.List;

/**
 * a vampire castle building which spawns enemies
 */
public class VampireCastleBuilding extends Building {

    private int x = super.getX();
    private int y = super.getY();
    private int numCycles = 0;
    private int spawnCycle = 5;

    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    // adds one to the number of cycles, called by world each time a lap is completed
    public void updateCycle() {
        numCycles += 1;
    }

    /**
     * Simple getter for the number of cycles the character has made while this building is down
     * @return number of cycles this building has existed for
     */
    public int getCycles() {
        return numCycles;
    }
    
    /**
     * Spawns an enemy from this building
     * @param path the world's path
     * @return an enemy on success
     */
    public BasicEnemy spawnEnemy(List<Pair<Integer, Integer>> path) {
        if((numCycles != 0) && (numCycles % spawnCycle == 0)) {
            Pair<Integer, Integer> spawnPoint = findSpawnPoint(path);
            if(spawnPoint != null) {
                int indexInPath = path.indexOf(spawnPoint);
                numCycles = 0;
                return new BasicEnemyVampire(new PathPosition(indexInPath, path));
            }
        }
        return null;
    }

    /**
     * Finds valid places to spawn an enemy and selects one at random
     * @param path the worlds path
     * @return a randomly selected valid spawn point
     */
    private Pair<Integer, Integer> findSpawnPoint(List<Pair<Integer, Integer>> path) {
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                // check adjacent tiles to ensure that there is a path 
                Pair<Integer, Integer> otherTiles = new Pair<>(x+i, y+j);
                if(path.contains(otherTiles)) {
                    // found an adjacent tile
                    return otherTiles;
                }
            }
        }
        // on grass, but no adjacent tiles.
        return null;
    }
}

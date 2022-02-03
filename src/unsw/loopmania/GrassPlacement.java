package unsw.loopmania;

import org.javatuples.Pair;
import java.util.List;

// a strategy for buildings so that they can only be placed on the grass/
public class GrassPlacement implements PlacementBehaviour{
    GrassPlacement() {
        super();
    }

    /**
     * An algorithm to determine whether an x, y coordinate is a path or not.
     * 
     * @param pathposition  the path on the map given by a series of x,y coordinates
     * @param x             the x coordinate of where the building is trying to be placed
     * @param y             the y coordinate of where the building is trying to be placed
     * 
     * @return              whether this building can be placed at this position
     */
    @Override
    public boolean checkPlacement(List<Pair<Integer, Integer>> pathposition, int x, int y) {
        Pair<Integer, Integer> placement = new Pair<>(x, y);
        if(pathposition.contains(placement)) {
            return false;
        }
        return true;
    }   
}
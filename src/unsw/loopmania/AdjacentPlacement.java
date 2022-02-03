package unsw.loopmania;
import org.javatuples.Pair;
import java.util.List;

// a strategy which determines whether an x, y coordinate is next to a path and not on it.
public class AdjacentPlacement implements PlacementBehaviour{
    AdjacentPlacement() {
        super();
    }

    /**
     * An algorithm to determine whether an x, y coordinate is next to a path and not on it.
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
            // can't be on path
            return false;
        }
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                // check adjacent tiles to ensure that there is a path 
                Pair<Integer, Integer> otherTiles = new Pair<>(x+i, y+j);
                if(pathposition.contains(otherTiles)) {
                    // found an adjacent tile
                    return true;
                }
            }
        }
        // on grass, but no adjacent tiles.
        return false;
    }   
}
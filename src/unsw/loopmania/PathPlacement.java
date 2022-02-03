package unsw.loopmania;
import org.javatuples.Pair;
import java.util.List;

// A strategy for a building so that it can only be placed on the path
public class PathPlacement implements PlacementBehaviour{
    PathPlacement() {
        super();
    }

    /**
     * An algorithm to determine whether an x, y coordinate is on a path.
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
            return true;
        }
        return false;
    }   
}

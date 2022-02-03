package unsw.loopmania;

import java.util.List;
import org.javatuples.Pair;

// A strategy for the different ways of placing a building
public interface PlacementBehaviour {
    public boolean checkPlacement(List<Pair<Integer, Integer>> pathposition, int x, int y);
}

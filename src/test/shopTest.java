package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import java.util.List;

import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;

public class shopTest {

    // a helper function to make an arbitray ordered path so that there can be a character instance
    // path is a straight line across from (x, y)
    private List<Pair<Integer, Integer>> makeOrderedPath(int x, int y) {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList <Pair<Integer, Integer>>();
        for(int i = 0; i < 10; i++) {
            Pair<Integer, Integer> pair = new Pair<>(x+i, y);
            orderedPath.add(pair);
        }
        return orderedPath;
    }

    @Test
    public void openShopTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);
        
/*         LoopManiaWorldController controller = new LoopManiaWorldController(world, null);
        world.setShopOpen(true);
        controller.checkShop();

        assertEquals(Sword.getPrice(), controller.getBuySword().getText());

        world.setShopOpen(false);
        controller.checkShop(); 

        assertEquals("", controller.getBuySword().getText()); */

        //CANNOT TEST PROPERLY, CANNOT CREATE WORLD CONTROLLER
      
    }
    
}

package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import java.util.List;

import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;

public class goalTest {

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
    public void passGoalTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        ArrayList<Object> tempVictory = new ArrayList<>();
        tempVictory.add("Experience");
        tempVictory.add(1);
        world.setVictoryConditions(tempVictory);
        world.addTotalExperience(1);
        world.goalCheck();
        assertTrue(world.getGameWon());
        assertEquals(-1, world.getVictoryConditions().get(1));
    }

    @Test
    public void failGoalTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        ArrayList<Object> tempVictory = new ArrayList<>();
        tempVictory.add("Experience");
        tempVictory.add(2);
        world.setVictoryConditions(tempVictory);
        world.addTotalExperience(1);
        world.goalCheck();
        assertEquals(2, world.getVictoryConditions().get(1));
        assertEquals(1, world.getTotalExperience());
    }

    @Test
    public void originalValuesGoalTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        world.addTotalExperience(123456);
        world.goalCheck();
        assertTrue(world.getGameWon());
    }
}

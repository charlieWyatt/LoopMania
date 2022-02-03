package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



import unsw.loopmania.LoopManiaWorld;
import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;
import unsw.loopmania.HeroCastle;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;
import unsw.loopmania.DoggieCoin;
import unsw.loopmania.BasicEnemyElanMuske;

public class DoggieCoinTest {

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

    // Run tick moves once to check price changes
    @Test
    public void simpleFluctuationCheck(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        HeroCastle heroCastle = new HeroCastle(position);
        world.setHeroCastle(heroCastle);

        DoggieCoin doggieCoin = DoggieCoin.getDoggieCoin();
        world.setDoggieCoin(doggieCoin);

        assertEquals(1000, world.getDoggieCoinPrice());

        world.runTickMoves();
        world.runTickMoves();
        world.runTickMoves();
        assert(world.getDoggieCoinPrice() != 10);

    }

    // Run tick moves once to check price changes
    // Checks Elan's fluctuate
    @Test
    public void elanFluctuationCheck(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        HeroCastle heroCastle = new HeroCastle(position);
        world.setHeroCastle(heroCastle);

        DoggieCoin doggieCoin = DoggieCoin.getDoggieCoin();
        world.setDoggieCoin(doggieCoin);

        world.setElanSpawn(1);
        world.possiblySpawnEnemies();
        
        assert(doggieCoin.getMean() == BasicEnemyElanMuske.getMean());
        assert(doggieCoin.getStdDev() == BasicEnemyElanMuske.getStdDev());

    }
}

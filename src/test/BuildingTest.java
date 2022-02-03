package test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.VampireCastleBuilding;
import unsw.loopmania.ZombiePitBuilding;
import unsw.loopmania.VampireCastleCard;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.BasicEnemyVampire;
import unsw.loopmania.BasicEnemyZombie;

import java.util.ArrayList;

import java.util.List;

import org.javatuples.Pair;
import unsw.loopmania.HeroCastle;


/**
 * this class is a dummy class demonstrating how to setup tests for the project
 * you should setup additional test classes in a similar fashion, aiming to achieve high coverage.
 * A clickable "Run Test" link should appear if you have installed the Java Extension Pack properly.
 */
public class BuildingTest {

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

    // Creates a Vampire building
    @Test
    public void simpleVampireBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadVampireCard();

        assertEquals(0, world.getBuildings().size());

        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());

    }

    // Checks that you cant put vampire building on path
    @Test
    public void noPathVampireBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadVampireCard();

        assertEquals(0, world.getBuildings().size());
        // try to place on path. Should not be placed
        world.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        assertEquals(0, world.getBuildings().size());

        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());

    }

    // Checks that you cant put two buildings on same place
    @Test
    public void duplicateBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        // gets two cards
        world.loadVampireCard();
        world.loadVampireCard();

        assertEquals(0, world.getBuildings().size());
        // Place building on the grass
        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());

        // Place another building on the same spot
        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());

    }

    // checks must be adjacent to the path
    @Test
    public void noAdjacentPathVampireBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadVampireCard();

        assertEquals(0, world.getBuildings().size());
        // try to place away from path. Should not be placed
        world.convertCardToBuildingByCoordinates(0, 0, 250, 250);
        assertEquals(0, world.getBuildings().size());

        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());

    }

    // Creates a Zombie pit building
    @Test
    public void simpleZombieBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadZombieCard();

        assertEquals(0, world.getBuildings().size());
        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());
        assertEquals(0, world.getCards().size());

        // run a cycle
        // ensure that a zombie is produced
    }

    // Checks that you cant put zombie building on path
    @Test
    public void noPathZombieBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadZombieCard();

        assertEquals(0, world.getBuildings().size());
        // try to place on path. Should not be placed
        world.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        assertEquals(0, world.getBuildings().size());

        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());



    }

    // checks must be adjacent to the path
    @Test
    public void noAdjacentPathZombieBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadZombieCard();

        assertEquals(0, world.getBuildings().size());
        // try to place away from path. Should not be placed
        world.convertCardToBuildingByCoordinates(0, 0, 250, 250);
        assertEquals(0, world.getBuildings().size());

        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());
    }

    // Creates a Tower building
    @Test
    public void simpleTowerBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadTowerCard();

        assertEquals(0, world.getBuildings().size());
        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());
        assertEquals(0, world.getCards().size());

    }


    // Checks that you cant put tower building on path
    @Test
    public void noPathTowerBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadTowerCard();

        assertEquals(0, world.getBuildings().size());
        // try to place on path. Should not be placed
        world.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        assertEquals(0, world.getBuildings().size());

        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());

    }

    // checks must be adjacent to the path
    @Test
    public void noAdjacentPathTowerBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadTowerCard();

        assertEquals(0, world.getBuildings().size());
        // try to place away from path. Should not be placed
        world.convertCardToBuildingByCoordinates(0, 0, 250, 250);
        assertEquals(0, world.getBuildings().size());

        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());

    }

    // Creates a Village building
    @Test
    public void simpleVillageBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(8, arbitraryPath);
        Character myCharacter = new Character(position);
        HeroCastle heroCastle = new HeroCastle(position);
        world.setHeroCastle(heroCastle);
        world.setCharacter(myCharacter);

        world.loadVillageCard();

        assertEquals(0, world.getBuildings().size());
        world.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        assertEquals(1, world.getBuildings().size());
        assertEquals(0, world.getCards().size());

        // character runs through village, check that health goes up
        myCharacter.setHealth(2);
        world.runTickMoves();
        world.runTickMoves();
        assert(myCharacter.getHealth() > 2);
    }

    // checks must be on the path
    @Test
    public void noPathVillageBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadVillageCard();

        assertEquals(0, world.getBuildings().size());
        // try to place away from path. Should not be placed
        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(0, world.getBuildings().size());
    }

    // Creates a Barracks building
    @Test
    public void simpleBarracksBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(9, arbitraryPath);
        Character myCharacter = new Character(position);
        PathPosition position2 = new PathPosition(9, arbitraryPath);
        HeroCastle heroCastle = new HeroCastle(position2);
        world.setHeroCastle(heroCastle);
        world.setCharacter(myCharacter);

        world.loadBarracksCard();

        assertEquals(0, world.getBuildings().size());
        // on path
        world.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        assertEquals(1, world.getBuildings().size());
        assertEquals(0, world.getCards().size());

        // character in range of barracks, check that he has a soldier
        world.runTickMoves();
        assertEquals(1, myCharacter.getSoldiers());

    }

    // checks must be on the path
    @Test
    public void noPathBarracksBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadBarracksCard();

        assertEquals(0, world.getBuildings().size());
        // try to place away from path. Should not be placed
        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(0, world.getBuildings().size());
    }
    

    // Creates a Trap building
    @Test
    public void simpleTrapBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        PathPosition position2 = new PathPosition(5, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        HeroCastle heroCastle = new HeroCastle(position2);
        world.setHeroCastle(heroCastle);

        world.loadTrapCard();

        assertEquals(0, world.getBuildings().size());
        // checks must be on path
        world.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        assertEquals(1, world.getBuildings().size());
        assertEquals(0, world.getCards().size());

        world.loadZombieCard();
        ZombiePitBuilding zombieBuilding = (ZombiePitBuilding) world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(2, world.getBuildings().size());
        
        world.updateCycles();
        world.possiblySpawnEnemies();
        int numZombies = 0;
        for(BasicEnemy e : world.getEnemies()) {
            if(e instanceof BasicEnemyZombie) {
                numZombies++;
            }
        }
        assertEquals(1, numZombies);

        world.runTickMoves(); // use trap

        numZombies = 0;
        for(BasicEnemy e : world.getEnemies()) {
            if(e instanceof BasicEnemyZombie) {
                numZombies++;
            }
        }
        assertEquals(0, numZombies);
    

    }

    // checks must be on the path
    @Test
    public void noPathTrapBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadTrapCard();

        assertEquals(0, world.getBuildings().size());
        // try to place away from path. Should not be placed
        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(0, world.getBuildings().size());
    }


    // Creates a Campfire building
    @Test
    public void simpleCampfireBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(9, arbitraryPath);
        Character myCharacter = new Character(position);
        HeroCastle heroCastle = new HeroCastle(position);
        world.setHeroCastle(heroCastle);

        world.setCharacter(myCharacter);

        world.loadCampfireCard();

        assertEquals(0, world.getBuildings().size());
        // checks must be not on path
        world.convertCardToBuildingByCoordinates(0, 0, 0, 0);
        assertEquals(1, world.getBuildings().size());
        assertEquals(0, world.getCards().size());

        assertEquals(myCharacter.getBaseDamage(), myCharacter.getDamage());
        // Character is in radius, checks that it is being affected by the fire
        world.runTickMoves();
        assert(myCharacter.getAttackBonuses().contains("fire"));

        int i = 0;
        while(i < 5) {
            i++;
            world.runTickMoves();
        }
        world.runTickMoves();

        // out of range of campfire building
        assert(!myCharacter.getAttackBonuses().contains("fire"));

    }

    // checks can be anywhere not on path
    @Test
    public void noPathCampfireBuildingCreation(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadCampfireCard();

        assertEquals(0, world.getBuildings().size());
        // try to place away from path. Should be placed
        world.convertCardToBuildingByCoordinates(0, 0, 250, 250);
        assertEquals(1, world.getBuildings().size());

        world.loadCampfireCard();

        assertEquals(1, world.getBuildings().size());
        // try to place on from path. Should not be placed
        world.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        assertEquals(1, world.getBuildings().size());
    }

    
    // Puts the building second in the queue down first, then the next one
    @Test
    public void outOfOrderVampireBuildingPlacement(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadVampireCard();
        world.loadVampireCard();

        assertEquals(0, world.getBuildings().size());
        // second one in the queue
        world.convertCardToBuildingByCoordinates(1, 0, 0, 0);

        assertEquals(1, world.getBuildings().size());
        assertEquals(1, world.getCards().size());

        world.convertCardToBuildingByCoordinates(0, 0, 1, 2);
        assertEquals(2, world.getBuildings().size());
        assertEquals(0, world.getCards().size());
    }


    // Two Different cards at once
    @Test
    public void differentCards(){
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadVampireCard();
        world.loadZombieCard();

        assertEquals(0, world.getBuildings().size());
        // second one in the queue
        world.convertCardToBuildingByCoordinates(1, 0, 0, 0);

        assertEquals(1, world.getBuildings().size());
        assertEquals(1, world.getCards().size());

        world.convertCardToBuildingByCoordinates(0, 0, 2, 2);
        assertEquals(2, world.getBuildings().size());
        assertEquals(0, world.getCards().size());
    }

    // SPAWNING

    // vampire spawn
    @Test
    public void spawnVampire() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(5, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadVampireCard();

        assertEquals(0, world.getBuildings().size());
        VampireCastleBuilding castle = (VampireCastleBuilding) world.convertCardToBuildingByCoordinates(0, 0, 0, 1);

        int numberVampires = 0;

        for (BasicEnemy e : world.getEnemies()) {   
            if (e instanceof BasicEnemyVampire) {
                numberVampires++;
            }

        }
        assertEquals(0, numberVampires);

        assertEquals(0, world.getCards().size());

        world.possiblySpawnEnemies();
        for (BasicEnemy e : world.getEnemies()) {   
            if (e instanceof BasicEnemyVampire) {
                numberVampires++;
            }

        }
        assertEquals(0,numberVampires);

        // one loop - no spawn
        world.updateCycles();

        world.possiblySpawnEnemies();
        for (BasicEnemy e : world.getEnemies()) {   
            if (e instanceof BasicEnemyVampire) {
                numberVampires++;
            }

        }
        assertEquals(0,numberVampires);

        world.loadVampireCard();
        world.convertCardToBuildingByCoordinates(0, 0, 0, 2);

        // 4 loops no spawn
        world.updateCycles();
        for (BasicEnemy e : world.getEnemies()) {   
            if (e instanceof BasicEnemyVampire) {
                numberVampires++;
            }

        }
        assertEquals(0,numberVampires);
        world.updateCycles();
        for (BasicEnemy e : world.getEnemies()) {   
            if (e instanceof BasicEnemyVampire) {
                numberVampires++;
            }

        }
        assertEquals(0,numberVampires);
        world.updateCycles();
        world.possiblySpawnEnemies();
        for (BasicEnemy e : world.getEnemies()) {   
            if (e instanceof BasicEnemyVampire) {
                numberVampires++;
            }

        }
        assertEquals(0,numberVampires);

        // 5th loop spawn 1 vampires
        world.updateCycles();
        world.possiblySpawnEnemies();
        for (BasicEnemy e : world.getEnemies()) {   
            if (e instanceof BasicEnemyVampire) {
                numberVampires++;
            }

        }
        assertEquals(1,numberVampires);;

        // 6th loop spawn another vampire from the late castle
        world.updateCycles();
        world.possiblySpawnEnemies();
        numberVampires = 0;
        for (BasicEnemy e : world.getEnemies()) {   
            if (e instanceof BasicEnemyVampire) {
                numberVampires++;
            }

        }
        assertEquals(2,numberVampires);

    }

    @Test
    public void tooManyCards() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadVampireCard();

        assertEquals(1, world.getCards().size());

        // make a lot of cards
        for(int i = 0; i < 1000; i++) {
            world.loadVampireCard();
        }
        // check not all were made
        assert(world.getCards().size() == 300);


        // checks refund was given
        assert(world.getGold() > 0);


        // make a lot of cards
        for(int i = 0; i < 1000; i++) {
            world.loadZombieCard();
        }
        // check not all were made
        assert(world.getCards().size() == 300);

        // checks refund was given
        assert(world.getGold() > 0);

        // make a lot of cards
        for(int i = 0; i < 1000; i++) {
            world.loadTowerCard();
        }
        // check not all were made
        assert(world.getCards().size() == 300);

        // checks refund was given
        assert(world.getGold() > 0);

        // make a lot of cards
        for(int i = 0; i < 1000; i++) {
            world.loadBarracksCard();
        }
        // check not all were made
        assert(world.getCards().size() == 300);

        // checks refund was given
        assert(world.getGold() > 0);

        // make a lot of cards
        for(int i = 0; i < 1000; i++) {
            world.loadCampfireCard();
        }
        // check not all were made
        assert(world.getCards().size() == 300);

        // checks refund was given
        assert(world.getGold() > 0);

        // make a lot of cards
        for(int i = 0; i < 1000; i++) {
            world.loadTrapCard();
        }
        // check not all were made
        assert(world.getCards().size() == 300);

        // checks refund was given
        assert(world.getGold() > 0);

        // make a lot of cards
        for(int i = 0; i < 1000; i++) {
            world.loadVillageCard();
        }
        // check not all were made
        assert(world.getCards().size() == 300);

        // checks refund was given
        assert(world.getGold() > 0);
    }

    // zombie spawn
    @Test
    public void spawnZombie() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        HeroCastle heroCastle = new HeroCastle(position);
        world.setHeroCastle(heroCastle);
        world.setCharacter(myCharacter);

        world.runTickMoves();

        world.loadZombieCard();

        assertEquals(0, world.getBuildings().size());
        world.convertCardToBuildingByCoordinates(0, 0, 0, 1);
        assertEquals(1, world.getBuildings().size());
        assertEquals(0, world.getCards().size());

        world.loadZombieCard();
        world.convertCardToBuildingByCoordinates(0, 0, 0, 2);

        world.updateCycles();
        world.possiblySpawnEnemies();
        int numberZombies = 0;
        for (BasicEnemy e : world.getEnemies()) {   
            if (e instanceof BasicEnemyZombie) {
                numberZombies++;
            }

        }
        assertEquals(2,numberZombies);;
    }
}


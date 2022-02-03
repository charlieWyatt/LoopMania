package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import java.util.List;

import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.BasicEnemyDoggie;
import unsw.loopmania.BasicEnemyElanMuske;
import unsw.loopmania.BasicEnemySlug;
import unsw.loopmania.BasicEnemyVampire;
import unsw.loopmania.BasicEnemyZombie;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Soldier;
import unsw.loopmania.InventoryItems.Armour.Shield;
import unsw.loopmania.InventoryItems.Armour.TreeStump;



/**
 * this class is a dummy class demonstrating how to setup tests for the project
 * you should setup additional test classes in a similar fashion, aiming to achieve high coverage.
 * A clickable "Run Test" link should appear if you have installed the Java Extension Pack properly.
 */

public class EnemiesTest {

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
    public void slugAttackTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        BasicEnemy slug = new BasicEnemySlug(position);
        slug.attack(myCharacter);
        assertEquals(myCharacter.getMaxHealth()-slug.getDamage(), myCharacter.getHealth());

    }

    @Test
    public void slugMoveTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        BasicEnemy slug = new BasicEnemySlug(position);
        slug.setMovementChance(1);
        slug.move();
        //slug does not move
        assertEquals(slug.getX(), myCharacter.getX());
        assertEquals(slug.getY(), myCharacter.getY());
    }

    @Test
    public void zombieAttackTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        BasicEnemy zombie = new BasicEnemyZombie(position);
        zombie.attack(myCharacter);
        assertEquals(myCharacter.getMaxHealth()-zombie.getDamage(), myCharacter.getHealth());

    }

    @Test
    public void zombieCritTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        
        myCharacter.setHealth(10);
        myCharacter.setDamage(1);
        BasicEnemy zombie = new BasicEnemyZombie(position);
        zombie.setCritChance(100);
        zombie.setDamage(1);
        zombie.setHealth(3);
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        MovingEntity s = new Soldier(position);
        battleSoldiers.add(s);
        s.setDamage(0);
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;
        battleEnemies.add(zombie);
        world.setEnemies(battleEnemies);
    
        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);

        assertEquals(8, myCharacter.getHealth());

    }

    @Test
    public void zombieMoveTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        BasicEnemy zombie = new BasicEnemyZombie(position);
        zombie.setMovementChance(1);
        assertTrue(zombie.getX() == 1 && zombie.getY() == 1);
        zombie.move();
        assertFalse(zombie.getX() == 1 && zombie.getY() == 1);
    }

    @Test
    public void vampireAttackTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        BasicEnemy vampire = new BasicEnemyVampire(position);
        vampire.attack(myCharacter);
        assertEquals(myCharacter.getMaxHealth()-vampire.getDamage(), myCharacter.getHealth());

    }

    @Test
    public void vampireMoveTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        BasicEnemy vampire = new BasicEnemyVampire(position);
        vampire.setMovementChance(1);
        assertTrue(vampire.getX() == 1 && vampire.getY() == 1);
        vampire.move();
        assertFalse(vampire.getX() == 1 && vampire.getY() == 1);
    }

    @Test
    public void vampireCritTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
    
        BasicEnemy vampire = new BasicEnemyVampire(position);
        vampire.setCritChance(100);
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;
        battleEnemies.add(vampire);
    
        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);

        assertEquals(67, myCharacter.getHealth());

    }

    @Test
    public void doggieAttackTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        BasicEnemy doggie = new BasicEnemyDoggie(position);
        doggie.attack(myCharacter);
        assertEquals(myCharacter.getMaxHealth()-doggie.getDamage(), myCharacter.getHealth());

    }

    @Test
    public void doggieMoveTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        BasicEnemy doggie = new BasicEnemyDoggie(position);
        doggie.setMovementChance(1);
        assertTrue(doggie.getX() == 1 && doggie.getY() == 1);
        doggie.move();
        assertEquals(doggie.getX(), myCharacter.getX());
        assertEquals(doggie.getY(), myCharacter.getY());
    }

    @Test
    public void doggieCritTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);
    
        BasicEnemy doggie = new BasicEnemyDoggie(position);
        doggie.setCritChance(100);
        doggie.setDamage(1);
        myCharacter.setDamage(1);
        doggie.setHealth(2);
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;
        battleEnemies.add(doggie);
    
        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);

        assertEquals(0, myCharacter.getHealth());

    }

    @Test
    public void doggieCritWithBlockersTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);
    
        BasicEnemy doggie = new BasicEnemyDoggie(position);
        doggie.setCritChance(100);
        doggie.setDamage(1);
        myCharacter.setDamage(1);
        doggie.setHealth(2);
        
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;
        MovingEntity s = new Soldier(position);
        battleSoldiers.add(s);
        s.setDamage(0);
        battleEnemies.add(doggie);
    
        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);

        assertEquals(0, myCharacter.getStunned());

    }

    @Test
    public void elanAttackTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        BasicEnemy elan = new BasicEnemyElanMuske(position);
        elan.attack(myCharacter);
        assertEquals(myCharacter.getMaxHealth()-elan.getDamage(), myCharacter.getHealth());
    }

    @Test
    public void elanMoveTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        BasicEnemy elan = new BasicEnemyElanMuske(position);
        elan.setMovementChance(1);
        assertTrue(elan.getX() == 1 && elan.getY() == 1);
        elan.move();
        assertEquals(elan.getX(), myCharacter.getX());
        assertEquals(elan.getY(), myCharacter.getY());
    }

    @Test
    public void elanHealTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);
    
        BasicEnemy elan = new BasicEnemyElanMuske(position);
        elan.setDamage(1);
        myCharacter.setDamage(1);
        elan.setHealth(2);
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;
        battleEnemies.add(elan);
    
        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);

        assertEquals(0, myCharacter.getHealth());

    }

    @Test
    public void elanHealDeadTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);
    
        BasicEnemy elan = new BasicEnemyElanMuske(position);
        BasicEnemy slug = new BasicEnemySlug(position);
        slug.setHealth(3);
        slug.setDamage(0);

        elan.setHealth(1);
        elan.setDamage(1);

        myCharacter.setDamage(3);
        
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;

        battleEnemies.add(slug);
        battleEnemies.add(elan);
    
        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);

        assertEquals(99, myCharacter.getHealth());
    }

    @Test
    public void elanAttackTestDefendWithStump() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        BasicEnemy elan = new BasicEnemyElanMuske(position);
        elan.setDamage(20);
        elan.attack(myCharacter);
        assertEquals(myCharacter.getMaxHealth()-elan.getDamage(), myCharacter.getHealth());
        Shield treeStump = new TreeStump(null, null);
        assertEquals(myCharacter.getBaseArmour(), myCharacter.getArmour());
        myCharacter.equipShield(treeStump);
        myCharacter.addEquipmentArmour(30);
        assertEquals(40, myCharacter.getArmour());

        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();

        battleEnemies.add(elan);

        LoopManiaWorld.attackAlly(elan, battleSoldiers, myCharacter, battleEnemies);

        assertEquals(myCharacter.getMaxHealth()-elan.getDamage()-elan.getDamage()/2, myCharacter.getHealth());
    }

}

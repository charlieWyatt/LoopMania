package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import java.util.List;

import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.BasicEnemySlug;
import unsw.loopmania.BasicEnemyVampire;
import unsw.loopmania.BasicEnemyZombie;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Soldier;
import unsw.loopmania.Tower;
import unsw.loopmania.InventoryItems.Armour.Shield;
import unsw.loopmania.InventoryItems.Weapons.Staff;
import unsw.loopmania.InventoryItems.Weapons.Stake;

/**
 * this class is a dummy class demonstrating how to setup tests for the project
 * you should setup additional test classes in a similar fashion, aiming to achieve high coverage.
 * A clickable "Run Test" link should appear if you have installed the Java Extension Pack properly.
 */

public class BattleTest {

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
    public void FindBattleTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);
        BasicEnemy slug = new BasicEnemySlug(position);
        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        assertTrue(deadEnemies != null);
    }

    @Test
    public void runBattlesTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);
        BasicEnemy slug = new BasicEnemySlug(position);
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;
        battleEnemies.add(slug);
        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);
        assertTrue(deadEnemies.get(0) == slug);
    }

    @Test
    public void runBattlesTestwithAll() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);
        BasicEnemy vampire = new BasicEnemyVampire(position);
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        MovingEntity s = new Soldier(position);
        battleSoldiers.add(s);
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        MovingEntity t = new Tower(position);
        battleTowers.add(t);
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = true;
        battleEnemies.add(vampire);
        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);
        assertTrue(deadEnemies.get(0) == vampire);
    }

    @Test
    public void PlayerLose() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);
        BasicEnemy vampire = new BasicEnemyVampire(position);
        BasicEnemy vampire1 = new BasicEnemyVampire(position);
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        MovingEntity s = new Soldier(position);
        battleSoldiers.add(s);
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        MovingEntity t = new Tower(position);
        battleTowers.add(t);
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;
        vampire.setDamage(99);
        vampire.setHealth(99);
        battleEnemies.add(vampire);
        battleEnemies.add(vampire1);
        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);
        assertTrue(deadEnemies.size() == 0);
    }

    @Test
    public void testStaffTrance() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        Staff staff = new Staff(null, null);
        staff.setCritChance(100);
        myCharacter.equipWeapon(staff);

        BasicEnemy vampire = new BasicEnemyVampire(position);
        BasicEnemy vampire1 = new BasicEnemyVampire(position);

        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        MovingEntity s = new Soldier(position);
        battleSoldiers.add(s);

        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        MovingEntity t = new Tower(position);
        battleTowers.add(t);

        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;

        vampire.setDamage(99);
        vampire.setHealth(99);

        battleEnemies.add(vampire);
        battleEnemies.add(vampire1);

        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);
        assertTrue(myCharacter.getHealth() == 100);
    }

    @Test
    public void testTranceExpiring() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        Staff staff = new Staff(null, null);
        staff.setCritChance(0);
        myCharacter.equipWeapon(staff);

        BasicEnemy vampire = new BasicEnemyVampire(position);
        BasicEnemy vampire1 = new BasicEnemyVampire(position);

        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        MovingEntity s = new Soldier(position);
        s.addDuration(1);
        battleSoldiers.add(s);

        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        MovingEntity t = new Tower(position);
        battleTowers.add(t);

        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;

        vampire.setDamage(1);
        vampire.setHealth(99);

        battleEnemies.add(vampire);
        battleEnemies.add(vampire1);

        List<BasicEnemy> deadEnemies = LoopManiaWorld.findBattle();
        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);
        assertTrue(deadEnemies.size() == 0);
    }

    @Test
    public void testStake() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        Stake stake = new Stake(null, null);
        myCharacter.equipWeapon(stake);

        BasicEnemy vampire = new BasicEnemyVampire(position);
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;

        vampire.setDamage(1);
        vampire.setHealth(12);
        vampire.setCritChance(0);

        battleEnemies.add(vampire);

        List<BasicEnemy> deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);
        assertEquals(myCharacter.getMaxHealth(), myCharacter.getHealth());

        battleEnemies.remove(vampire);

        BasicEnemy zombie = new BasicEnemyZombie(position);

        zombie.setDamage(1);
        zombie.setHealth(12);

        battleEnemies.add(zombie);

        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);
        assertEquals(myCharacter.getMaxHealth()-1, myCharacter.getHealth());

        battleEnemies.remove(zombie);

        vampire.setDamage(1);
        vampire.setHealth(4);
        myCharacter.unEquipWeapon();

        battleEnemies.add(vampire);

        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);
        assertEquals(myCharacter.getMaxHealth()-2, myCharacter.getHealth());
    }

    @Test
    public void testShield() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setTesting(true);

        BasicEnemyVampire vampire = new BasicEnemyVampire(position);
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;

        vampire.setDamage(1);
        vampire.setHealth(6);
        vampire.setCritChance(100);

        battleEnemies.add(vampire);

        List<BasicEnemy> deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);
        assertEquals(myCharacter.getMaxHealth()-1-4, myCharacter.getHealth());

        battleEnemies.remove(vampire);

        vampire.setDamage(1);
        vampire.setHealth(4);
        vampire.setCritChance(100);
        myCharacter.setHealth(100);

        battleEnemies.add(vampire);

        Shield shield = new Shield(null, null);
        myCharacter.equipShield(shield);

        deadEnemies = LoopManiaWorld.runBattles(myCharacter, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);
        assertEquals(40, vampire.getCritChance());
    }
}

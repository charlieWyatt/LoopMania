package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import java.util.List;

import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.BarracksCard;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.InventoryItems.Weapons.Sword;
import unsw.loopmania.InventoryItems.Armour.Chest;
import unsw.loopmania.InventoryItems.Armour.Helmet;
import unsw.loopmania.InventoryItems.Armour.Shield;
import unsw.loopmania.InventoryItems.Weapons.Staff;
import unsw.loopmania.InventoryItems.Weapons.Stake;
import unsw.loopmania.InventoryItems.Rings.OneRing;
import unsw.loopmania.InventoryItems.Weapons.Anduril;
import unsw.loopmania.Card;
import unsw.loopmania.InventoryItems.Armour.TreeStump;



public class CharacterTest {
    
    // a helper function to make an arbitray ordered path so that there can be a character instance
    private List<Pair<Integer, Integer>> makeOrderedPath(int x, int y) {
        Pair<Integer, Integer> pair = new Pair<>(1, 1);
        List<Pair<Integer, Integer>> orderedPath = new ArrayList <Pair<Integer, Integer>>();
        orderedPath.add(pair);
        return orderedPath;
    }

    @Test 
    // Simple gaining expereince test
    public void addExperienceTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        assertEquals(0, myCharacter.getCurrentExp());

        world.gainExp(1);
        assertEquals(world.getCurrentExp(), myCharacter.getCurrentExp());
        assertEquals(1, myCharacter.getCurrentExp());

        world.gainExp(10000);
        assert(myCharacter.getLevel() > 5);
        assert(myCharacter.getNumUpgrades() > 0);
    }

    @Test 
    // Levelling up test including checking the number of upgrades
    public void addLevelTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        assertEquals(0, myCharacter.getCurrentExp());

        world.gainExp(10000);
        assert(myCharacter.getLevel() > 5);
        assert(myCharacter.getNumUpgrades() > 0);
    }

    @Test 
    // Upgrading the health after levelling up past 5 levels
    public void upgradeHealth() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        assertEquals(0, myCharacter.getCurrentExp());

        world.gainExp(10000);
        assert(myCharacter.getLevel() > 5);
        assert(myCharacter.getNumUpgrades() > 0);

        int initialHealth = myCharacter.getHealth();
        myCharacter.upgradeMaxHealth();
        assert(myCharacter.getHealth() != initialHealth);
    }

    @Test 
    // Upgrading the damage after levelling up past 5 levels
    public void upgradeDamage() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        assertEquals(0, myCharacter.getCurrentExp());

        world.gainExp(10000);
        assert(myCharacter.getLevel() > 5);
        assert(myCharacter.getNumUpgrades() > 0);

        int initialDamage = myCharacter.getDamage();
        myCharacter.upgradeBaseDamage();
        assert(myCharacter.getDamage() != initialDamage);
    }
}
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import java.util.List;

import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Character;
import unsw.loopmania.InventoryItems.Armour.Chest;
import unsw.loopmania.InventoryItems.Armour.Helmet;
import unsw.loopmania.InventoryItems.Armour.Shield;
import unsw.loopmania.InventoryItems.Armour.TreeStump;
import unsw.loopmania.InventoryItems.Rings.OneRing;
import unsw.loopmania.InventoryItems.Weapons.Staff;
import unsw.loopmania.InventoryItems.Weapons.Stake;
import unsw.loopmania.InventoryItems.Weapons.Sword;
import unsw.loopmania.InventoryItems.Weapons.Anduril;
import unsw.loopmania.PathPosition;



/**
 * this class is a dummy class demonstrating how to setup tests for the project
 * you should setup additional test classes in a similar fashion, aiming to achieve high coverage.
 * A clickable "Run Test" link should appear if you have installed the Java Extension Pack properly.
 */

public class ItemsTest {

    // a helper function to make an arbitray ordered path so that there can be a character instance
    private List<Pair<Integer, Integer>> makeOrderedPath(int x, int y) {
        Pair<Integer, Integer> pair = new Pair<>(1, 1);
        List<Pair<Integer, Integer>> orderedPath = new ArrayList <Pair<Integer, Integer>>();
        orderedPath.add(pair);
        return orderedPath;
    }

    // SWORD TESTS ///

    // a test for equiping and dequiping a sword to check that stats are changed
    @Test
    public void simpleSwordTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        PathPosition position = new PathPosition(0, arbitraryPath);
        
        Character character = new Character(position);

        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(character.getBaseDamage(), character.getDamage());
        character.equipWeapon(sword);
        assertEquals(character.getBaseDamage() + sword.getDamage(), character.getDamage());

    }

    // a test for equiping and dequiping two sword to check that stats are changed
    @Test
    public void twoSwordTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        PathPosition position = new PathPosition(0, arbitraryPath);

        Character character = new Character(position);

        Sword sword1 = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Sword sword2 = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertEquals(character.getBaseDamage(), character.getDamage());
        character.equipWeapon(sword1);
        assertEquals(character.getBaseDamage() + sword1.getDamage(), character.getDamage());
        character.equipWeapon(sword2);
        assertEquals(character.getBaseDamage() + sword2.getDamage(), character.getDamage());
        character.unEquipWeapon();
        assertEquals(character.getBaseDamage(), character.getDamage());
        character.equipWeapon(sword1);
        assertEquals(character.getBaseDamage() + sword1.getDamage(), character.getDamage());
        character.equipWeapon(sword2);
        assertEquals(character.getBaseDamage() + sword2.getDamage(), character.getDamage());
    }

    // STAFF TESTS

    // checks to see if staff can be equipped and deequipped. Checks to see if attack bonus changes on dequipping
    @Test
    public void simpleStaffTest() {
        
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        PathPosition position = new PathPosition(0, arbitraryPath);
        
        Character character = new Character(position);

        Staff staff = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(character.getBaseDamage(), character.getDamage());
        // need to add special property in here for staff

        List<String> attackBonuses = character.getAttackBonuses();
        assertEquals(0, attackBonuses.size());

        character.equipWeapon(staff);
        assertEquals(character.getBaseDamage() + staff.getDamage(), character.getDamage());
        assertEquals(1, attackBonuses.size());
        assert(attackBonuses.contains("Magic"));

        character.unEquipWeapon();
        assertEquals(0, attackBonuses.size());
    }

    // STAKE TESTS

    // checks to see if stake works and the attack bonus is actvated on equip
    @Test
    public void simpleStakeTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        PathPosition position = new PathPosition(0, arbitraryPath);
        
        Character character = new Character(position);

        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(character.getBaseDamage(), character.getDamage());
        List<String> attackBonuses = character.getAttackBonuses();
        assertEquals(0, attackBonuses.size());
        character.equipWeapon(stake);
        assertEquals(character.getBaseDamage() + stake.getDamage(), character.getDamage());
        assertEquals(1, attackBonuses.size());
        assert(attackBonuses.contains("Vampire"));
    }

    // Anduril TESTS

    // checks to see if Anduril works and the attack bonus is actvated on equip
    @Test
    public void simpleAndurilTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        PathPosition position = new PathPosition(0, arbitraryPath);
        
        Character character = new Character(position);

        Anduril Anduril = new Anduril(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(character.getBaseDamage(), character.getDamage());
        List<String> attackBonuses = character.getAttackBonuses();
        assertEquals(0, attackBonuses.size());
        character.equipWeapon(Anduril);
        assertEquals(character.getBaseDamage() + Anduril.getDamage(), character.getDamage());
        assertEquals(1, attackBonuses.size());
        assert(attackBonuses.contains("Boss"));
        Sword sword = new Sword(null, null);
        character.equipWeapon(sword);
        assertEquals(character.getBaseDamage() + sword.getDamage(), character.getDamage());
    }

    // COMBINED WEAPON TESTS

    // Equips a stake, then equips a staff. Tests to see if attack bonuses' change
    @Test
    public void stakeStaffTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        PathPosition position = new PathPosition(0, arbitraryPath);
        
        Character character = new Character(position);

        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Staff staff = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        
        assertEquals(character.getBaseDamage(), character.getDamage());
        List<String> attackBonuses = character.getAttackBonuses();
        assertEquals(0, attackBonuses.size());
        character.equipWeapon(stake);
        assertEquals(character.getBaseDamage() + stake.getDamage(), character.getDamage());
        assertEquals(1, attackBonuses.size());
        assert(attackBonuses.contains("Vampire"));

        character.equipWeapon(staff);
        assertEquals(character.getBaseDamage() + staff.getDamage(), character.getDamage());
        assertEquals(1, attackBonuses.size());
        assert(attackBonuses.contains("Magic"));
    }

    // SHIELD TESTS
    // Tests whether shield affects the characters stats
    @Test
    public void simpleShieldTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        PathPosition position = new PathPosition(0, arbitraryPath);
        
        Character character = new Character(position);
        ///
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(character.getBaseArmour(), character.getArmour());
        character.equipShield(shield);
        assertEquals(character.getBaseArmour() + shield.getArmour(), character.getArmour());

        Shield shield2 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(character.getBaseArmour() + shield.getArmour(), character.getArmour());
        character.equipShield(shield2);
        assertEquals(character.getBaseArmour() + shield2.getArmour(), character.getArmour());

    }

    
    // TREE STUMP TESTS
    @Test
    public void simpleStumpTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        PathPosition position = new PathPosition(0, arbitraryPath);
        
        Character character = new Character(position);
        ///
        TreeStump shield = new TreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(character.getBaseArmour(), character.getArmour());
        character.equipShield(shield);
        assertEquals(character.getBaseArmour() + shield.getArmour(), character.getArmour());

        TreeStump shield2 = new TreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(character.getBaseArmour() + shield.getArmour(), character.getArmour());
        character.equipShield(shield2);
        assertEquals(character.getBaseArmour() + shield2.getArmour(), character.getArmour());

    }

    // HELMET TESTS
    // Tests whether helmet affects the characters stats
    @Test
    public void simpleHelmetTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        PathPosition position = new PathPosition(0, arbitraryPath);
        
        Character character = new Character(position);
        ///
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(character.getBaseArmour(), character.getArmour());
        character.equipHelmet(helmet);
        assertEquals(character.getBaseArmour() + helmet.getArmour(), character.getArmour());

        Helmet helmet2 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(character.getBaseArmour() + helmet.getArmour(), character.getArmour());
        character.equipHelmet(helmet2);
        assertEquals(character.getBaseArmour() + helmet2.getArmour(), character.getArmour());

    }


    // CHEST TESTS
    // Tests whether chest affects the characters stats
    @Test
    public void simpleChestTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        PathPosition position = new PathPosition(0, arbitraryPath);
        
        Character character = new Character(position);
        ///
        Chest chest = new Chest(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(character.getBaseArmour(), character.getArmour());
        character.equipChest(chest);
        assertEquals(character.getBaseArmour() + chest.getArmour(), character.getArmour());

        Chest chest2 = new Chest(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        //assertEquals(character.getBaseArmour() + chest.getArmour(), character.getArmour());
        character.equipChest(chest2);
        //assertEquals(character.getBaseArmour() + chest2.getArmour(), character.getArmour());

    }


    // ONE RING TESTS
    // Tests whether ring affects the characters stats
    @Test
    public void simpleOneRingTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        PathPosition position = new PathPosition(0, arbitraryPath);

        Character character = new Character(position);
        ///
        OneRing ring = new OneRing(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(0, character.getLives());
        character.equipRing(ring);
        assertEquals(1, character.getLives());
        
        OneRing ring2 = new OneRing(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(1, character.getLives());
        character.equipRing(ring2);
        assertEquals(1, character.getLives());
    }

    // POTION TESTS
    // Tests whether potion affects the characters stats
    @Test
    public void simplePotionsTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);

        PathPosition position = new PathPosition(0, arbitraryPath);
        Character character = new Character(position);
        ///
        

        // reduce character health
        character.setHealth(1);
        assertEquals(1, character.getHealth());

        // give character potions to hold
        character.addPotion();
        assertEquals(1, character.getPotions());

        // drink a potion
        character.drinkPotion();
        assertEquals(11, character.getHealth());

        // show the potions a character is holding
        assertEquals(0, character.getPotions());
    }

    // Tests whether potion drinking fails, when character has no potion
    @Test
    public void noPotionsTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);

        PathPosition position = new PathPosition(0, arbitraryPath);
        Character character = new Character(position);
        ///
        

        // reduce character health
        character.setHealth(1);
        assertEquals(1, character.getHealth());

        // try to drink a potion when not having one
        assertEquals(0, character.getPotions());
        character.drinkPotion();
        assertEquals(1, character.getHealth());

        // give character potions to hold
        character.addPotion();
        assertEquals(1, character.getPotions());

        // drink a potion
        character.drinkPotion();
        assertEquals(11, character.getHealth());

        // show the potions a character is holding
        assertEquals(0, character.getPotions());
    }

    // checks to see if drinking the same potion twice fails
    @Test
    public void drinkPotionTwiceTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);

        PathPosition position = new PathPosition(0, arbitraryPath);
        Character character = new Character(position);
        ///
        
        // reduce character health
        character.setHealth(1);
        assertEquals(1, character.getHealth());

        // give character potions to hold
        character.addPotion();
        assertEquals(1, character.getPotions());

        // drink a potion
        character.drinkPotion();
        assertEquals(11, character.getHealth());

        // show the potions a character is holding
        assertEquals(0, character.getPotions());

        character.drinkPotion();
        assertEquals(11, character.getHealth());

        // show the potions a character is holding
        assertEquals(0, character.getPotions());

    }

    // checking to see if two potions can be stored and drunk seperately
    @Test
    public void twoPotionsTest() {
        // Making simple path for character
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);

        PathPosition position = new PathPosition(0, arbitraryPath);
        Character character = new Character(position);
        ///

        // reduce character health
        character.setHealth(1);
        assertEquals(1, character.getHealth());

        // give character potions to hold
        character.addPotion();
        assertEquals(1, character.getPotions());
        character.addPotion();
        assertEquals(2, character.getPotions());

        // drink a potion
        character.drinkPotion();
        assertEquals(11, character.getHealth());
        assertEquals(1, character.getPotions());

        character.drinkPotion();
        assertEquals(21, character.getHealth());
        assertEquals(0, character.getPotions());
    }

    // test gold stat changes
    @Test 
    public void simpleGold() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);

        PathPosition position = new PathPosition(0, arbitraryPath);
        Character character = new Character(position);

        assertEquals(0, character.getGold());
        character.addGold(50);

        assertEquals(50, character.getGold());
    }

    @Test
    public void addUnequippedTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
 
        assertEquals(0, world.getNumItems());

        world.addUnequippedSword();

        assertEquals(1, world.getNumItems());


    }

    @Test
    public void equippingRareItems() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
 
        assertEquals(0, world.getNumItems());
        
        world.addUnequippedSword();

        assertEquals(1, world.getNumItems());


    }

}



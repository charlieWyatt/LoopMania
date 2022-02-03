package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import java.util.List;

import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Card;
import unsw.loopmania.BarracksCard;
import unsw.loopmania.Character;
import unsw.loopmania.HeroCastle;
import unsw.loopmania.PathPosition;
import unsw.loopmania.InventoryItems.Weapons.Sword;
import unsw.loopmania.InventoryItems.Weapons.Anduril;
import unsw.loopmania.InventoryItems.Armour.Chest;
import unsw.loopmania.InventoryItems.Armour.Helmet;
import unsw.loopmania.InventoryItems.Armour.Shield;
import unsw.loopmania.InventoryItems.Armour.TreeStump;
import unsw.loopmania.InventoryItems.Weapons.Staff;
import unsw.loopmania.InventoryItems.Weapons.Stake;
import unsw.loopmania.InventoryItems.Rings.OneRing;



public class WorldTest {
    
    // a helper function to make an arbitray ordered path so that there can be a character instance
    private List<Pair<Integer, Integer>> makeOrderedPath(int x, int y) {
        Pair<Integer, Integer> pair = new Pair<>(1, 1);
        List<Pair<Integer, Integer>> orderedPath = new ArrayList <Pair<Integer, Integer>>();
        orderedPath.add(pair);
        return orderedPath;
    }

    @Test 
    // equipping items test
    public void addingUnequippedItems() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        int ExpectedNumItems = 0;

        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedChest();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedSword();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedHelmet();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedStaff();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedStake();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedShield();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedRing();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());


    }

    @Test 
    // equipping items test
    public void tooManyUnequippedItems() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        int ExpectedNumItems = 0;

        assertEquals(ExpectedNumItems, world.getNumItems());

        for(int i = 0; i < 100; i++) {
            world.addUnequippedChest();
        }
        ExpectedNumItems = 16;
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedSword();
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedHelmet();
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedStaff();
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedStake();
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedShield();
        assertEquals(ExpectedNumItems, world.getNumItems());

        world.addUnequippedRing();
        assertEquals(ExpectedNumItems, world.getNumItems());

        ExpectedNumItems--;
        world.removeUnequippedInventoryItemByCoordinates(0, 0);
        assertEquals(ExpectedNumItems, world.getNumItems());
    }

    // run potions tests
    @Test 
    public void potionTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        int expectedNumPotions = 0;
        assertEquals(expectedNumPotions, world.getPotions());
        world.addPotion();
        expectedNumPotions++;

        assertEquals(myCharacter.getHealth(), world.getHealth());
        world.drinkPotion();
        expectedNumPotions--;
        assertEquals(myCharacter.getHealth(), world.getHealth());
        assertEquals(expectedNumPotions, world.getPotions());
    }


    @Test
    public void equipSwordTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        int ExpectedNumItems = 0;

        Sword sword = world.addUnequippedSword();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        assertEquals(myCharacter.getBaseDamage(), myCharacter.getDamage());

        world.equipItemByCoordinates(0, 0);

        assertEquals(myCharacter.getBaseDamage() + sword.getDamage(), myCharacter.getDamage());
    }

    @Test
    // tests wether can equip staff in the world
    public void equipStaffTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        int ExpectedNumItems = 0;

        Staff staff = world.addUnequippedStaff();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        assertEquals(myCharacter.getBaseDamage(), myCharacter.getDamage());

        world.equipItemByCoordinates(0, 0);

        assertEquals(myCharacter.getBaseDamage() + staff.getDamage(), myCharacter.getDamage());
    }

    @Test
    // tests wether can equip stake in the world
    public void equipStakeTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        int ExpectedNumItems = 0;

        Stake stake = world.addUnequippedStake();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        assertEquals(myCharacter.getBaseDamage(), myCharacter.getDamage());

        world.equipItemByCoordinates(0, 0);

        assertEquals(myCharacter.getBaseDamage() + stake.getDamage(), myCharacter.getDamage());
    }

    @Test
    // tests wether can equip Chest in the world
    public void equipChestTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        int ExpectedNumItems = 0;

        Chest chest = world.addUnequippedChest();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        assertEquals(myCharacter.getBaseArmour(), myCharacter.getArmour());

        world.equipItemByCoordinates(0, 0);

        assertEquals(myCharacter.getBaseArmour() + chest.getArmour(), myCharacter.getArmour());
    }

    @Test
    // tests wether can equip shield in the world
    public void equipShieldTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        int ExpectedNumItems = 0;

        Shield shield = world.addUnequippedShield();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        assertEquals(myCharacter.getBaseArmour(), myCharacter.getArmour());

        world.equipItemByCoordinates(0, 0);

        assertEquals(myCharacter.getBaseArmour() + shield.getArmour(), myCharacter.getArmour());
    }

    @Test
    // tests wether can equip helmet in the world
    public void equipHelmetTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        int ExpectedNumItems = 0;

        Helmet helmet = world.addUnequippedHelmet();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        assertEquals(myCharacter.getBaseArmour(), myCharacter.getArmour());

        world.equipItemByCoordinates(0, 0);

        assertEquals(myCharacter.getBaseArmour() + helmet.getArmour(), myCharacter.getArmour());
    }

    @Test
    // tests wether can equip OneRing in the world
    public void equipOneRingTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        int ExpectedNumItems = 0;

        world.addUnequippedRing();
        ExpectedNumItems++;
        assertEquals(ExpectedNumItems, world.getNumItems());

        assertEquals(0, myCharacter.getLives());

        world.equipItemByCoordinates(0, 0);

        assertEquals(1, myCharacter.getLives());
    }
	
    @Test
    // tests wether can equip OneRing in the world

    public void getCardbyCoordinatesTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        world.loadBarracksCard();
        Card card = world.getCardbyCoordinates(0, 0);
        assert(card instanceof BarracksCard);
    }

    @Test
    // tests wether can equip OneRing in the world with confusing effects
    public void confusingEquipRing() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        LoopManiaWorld.setGameMode("confusing");

        int ExpectedNumItems = 0;

        OneRing ring = world.addUnequippedRing();
        TreeStump stump = world.addUnequippedTreeStump();
        Anduril anduril = world.addUnequippedAnduril();
        ExpectedNumItems++;
        ExpectedNumItems++;
        ExpectedNumItems++;

        assertEquals(ExpectedNumItems, world.getNumItems());

        assertEquals(0, myCharacter.getLives());

        world.equipItemByCoordinates(0, 0);

        assertEquals(1, myCharacter.getLives());
        
        // checks if the confusing mode applied one of the two other effects
        assert(myCharacter.getArmour() == myCharacter.getBaseArmour() + TreeStump.getArmour() || 
            myCharacter.getDamage() == myCharacter.getBaseDamage() + anduril.getDamage());
        

    }

    @Test
    // tests wether can equip Anduril in the world with confusing effects
    public void confusingEquipAnduril() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);

        LoopManiaWorld.setGameMode("confusing");

        int ExpectedNumItems = 0;

        Anduril anduril = world.addUnequippedAnduril();
        OneRing ring = world.addUnequippedRing();
        TreeStump stump = world.addUnequippedTreeStump();
        ExpectedNumItems++;
        ExpectedNumItems++;
        ExpectedNumItems++;

        assertEquals(ExpectedNumItems, world.getNumItems());

        assertEquals(0, myCharacter.getLives());

        world.equipItemByCoordinates(0, 0);

        assertEquals(myCharacter.getDamage(), myCharacter.getBaseDamage() + anduril.getDamage());
        
        // checks if the confusing mode applied one of the two other effects
        assert(myCharacter.getArmour() == myCharacter.getBaseArmour() + TreeStump.getArmour() || 
            myCharacter.getLives() == 1);
    }
    

    @Test
    // tests wether can equip TreeStump in the world with confusing effects
    public void confusingEquipStump() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        HeroCastle heroCastle = new HeroCastle(position);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setHeroCastle(heroCastle);
        world.setTesting(true);

        LoopManiaWorld.setGameMode("confusing");

        int ExpectedNumItems = 0;

        
        TreeStump stump = world.addUnequippedTreeStump();
        Anduril anduril = world.addUnequippedAnduril();
        OneRing ring = world.addUnequippedRing();
        ExpectedNumItems++;
        ExpectedNumItems++;
        ExpectedNumItems++;

        assertEquals(ExpectedNumItems, world.getNumItems());

        world.equipItemByCoordinates(0, 0);

        assert(myCharacter.getArmour() == myCharacter.getBaseArmour() + TreeStump.getArmour());
        
        // checks if the confusing mode applied one of the two other effects
        assert(myCharacter.getDamage() == myCharacter.getBaseDamage() + anduril.getDamage()|| 
            myCharacter.getLives() == 1);
        
        world.equipItemByCoordinates(0, 0);
        world.equipItemByCoordinates(0, 0);

        OneRing ring1 = new OneRing(null, null);
        OneRing ring2 = new OneRing(null, null);

        myCharacter.equipRing(ring1);
        myCharacter.equipRing(ring2);


        myCharacter.setHealth(-1);
        world.runTickMoves();
        assertEquals(100, myCharacter.getHealth());
    }

    @Test
    // tests wether the player ressurrects
    public void ressurectTest() {
        List<Pair<Integer, Integer>> arbitraryPath = makeOrderedPath(1,1);
        LoopManiaWorld world = new LoopManiaWorld(300, 300, arbitraryPath);
        PathPosition position = new PathPosition(0, arbitraryPath);
        HeroCastle heroCastle = new HeroCastle(position);
        Character myCharacter = new Character(position);
        world.setCharacter(myCharacter);
        world.setHeroCastle(heroCastle);
        world.setTesting(true);

        OneRing ring1 = new OneRing(null, null);
        OneRing ring2 = new OneRing(null, null);

        myCharacter.equipRing(ring1);
        myCharacter.equipRing(ring2);


        myCharacter.setHealth(-1);
        world.runTickMoves();
        assertEquals(100, myCharacter.getHealth());
    }



}

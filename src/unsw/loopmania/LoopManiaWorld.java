package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.InventoryItems.Weapons.Weapon;
import unsw.loopmania.InventoryItems.Armour.Helmet;
import unsw.loopmania.InventoryItems.Armour.Chest;
import unsw.loopmania.InventoryItems.Armour.Shield;
import unsw.loopmania.InventoryItems.Armour.TreeStump;
import unsw.loopmania.InventoryItems.Weapons.Sword;
import unsw.loopmania.InventoryItems.Weapons.Anduril;
import unsw.loopmania.InventoryItems.Weapons.Staff;
import unsw.loopmania.InventoryItems.Weapons.Stake;
import unsw.loopmania.InventoryItems.Rings.OneRing;


/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 */
public class LoopManiaWorld {
    // TODO = add additional backend functionality

    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private static Character character;

    private static HeroCastle heroCastle;

    private static DoggieCoin doggieCoin;

    
    private static List<String> battleLogs = new ArrayList<>();

    private int currentLoop;

    private int doggieSpawnLoops = 20;
    private int elanSpawnLoops = 40;
    private int elanSpawnExperience = 10000;
    private static int elanHealAmount = 2;

    private boolean shopOpen = false;

    public boolean getShopOpen() {
        return this.shopOpen;
    }

    public void setShopOpen(boolean shopOpen) {
        this.shopOpen = shopOpen;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public List<Entity> getNonSpecifiedEntities() {
        return this.nonSpecifiedEntities;
    }

    public void setNonSpecifiedEntities(List<Entity> nonSpecifiedEntities) {
        this.nonSpecifiedEntities = nonSpecifiedEntities;
    }

    public int getcurrentLoop() {
        return this.currentLoop;
    }

    public void setcurrentLoop(int currentLoop) {
        this.currentLoop = currentLoop;
    }

    public List<Card> getCardEntities() {
        return this.cardEntities;
    }

    public void setCardEntities(List<Card> cardEntities) {
        this.cardEntities = cardEntities;
    }

    public List<Entity> getUnequippedInventoryItems() {
        return this.unequippedInventoryItems;
    }

    public void setUnequippedInventoryItems(List<Entity> unequippedInventoryItems) {
        this.unequippedInventoryItems = unequippedInventoryItems;
    }

    public List<Building> getBuildingEntities() {
        return this.buildingEntities;
    }

    public void setBuildingEntities(List<Building> buildingEntities) {
        this.buildingEntities = buildingEntities;
    }

    public List<Pair<Integer,Integer>> getOrderedPath() {
        return this.orderedPath;
    }

    public void setOrderedPath(List<Pair<Integer,Integer>> orderedPath) {
        this.orderedPath = orderedPath;
    }

    // TODO = add more lists for other entities, for equipped inventory items, etc...

    // TODO = expand the range of enemies
    private static List<BasicEnemy> enemies;

    private static List<MovingEntity> soldiers;

    private static List<MovingEntity> towers;

    private static List<MovingEntity> campfires;

    // TODO = expand the range of cards
    private List<Card> cardEntities;

    // TODO = expand the range of items
    private List<Entity> unequippedInventoryItems;

    // TODO = expand the range of buildings
    private List<Building> buildingEntities;

    private static Goal endGoal;
    
    private ArrayList<Object> victoryConditions;

    private static boolean gameWon;

    private static String GameMode;

    private static boolean testing = false;

    private int doggieSpawn = 0;

    private int elanSpawn = 0;

    public int getDoggieSpawn() {
        return this.doggieSpawn;
    }

    public void setDoggieSpawn(int doggieSpawn) {
        this.doggieSpawn = doggieSpawn;
    }

    public int getElanSpawn() {
        return this.elanSpawn;
    }

    public void setElanSpawn(int elanSpawn) {
        this.elanSpawn = elanSpawn;
    }

    public boolean getTesting() {
        return LoopManiaWorld.testing;
    }

    public void setTesting(boolean testing) {
        LoopManiaWorld.testing = testing;
    }

    public static String getGameMode() {
        return LoopManiaWorld.GameMode;
    }

    public static void setGameMode(String NewGameMode) {
        LoopManiaWorld.GameMode = NewGameMode;
        if(NewGameMode.equals("confusing")) {
            // would like to do something like the next two lines to make this more dynamic
            // ArrayList<Class> rareItems = new ArrayList<>();
            // rareItems.add(Anduril)
            
            // Probably not good practice, didnt know of an alternative of how to pass in a static class
            Anduril nullAnduril = new Anduril(null, null);
            OneRing nullRing = new OneRing(null, null);
            TreeStump nullTreeStump = new TreeStump(null, null);

            Random rand = new Random();
            if(rand.nextInt(1) == 0) {
                Anduril.giveConfusingBonus(nullRing);
                // the bottom two have to flow out of the top one if all must be a hidden effect of another.
                OneRing.giveConfusingBonus(nullTreeStump);
                TreeStump.giveConfusingBonus(nullAnduril);
            } else {
                Anduril.giveConfusingBonus(nullTreeStump);
                // the bottom two have to flow out of the top one if all must be a hidden effect of another.
                OneRing.giveConfusingBonus(nullAnduril);
                TreeStump.giveConfusingBonus(nullRing);
            }
        }

    }

    public boolean getGameWon() {
        return LoopManiaWorld.gameWon;
    }

    public static void setGameWon(boolean gameWon) {
        LoopManiaWorld.gameWon = gameWon;
    }

    public ArrayList<Object> getVictoryConditions() {
        return this.victoryConditions;
    }

    public void setVictoryConditions(ArrayList<Object> victoryConditions) {
        this.victoryConditions = victoryConditions;
    }

    public int getCurrentLoop() {
        return this.currentLoop;
    }

    public void setCurrentLoop(int currentLoop) {
        this.currentLoop = currentLoop;
    }

    public Goal getEndGoal() {
        return LoopManiaWorld.endGoal;
    }

    public void setEndGoal(Goal endGoal) {
        LoopManiaWorld.endGoal = endGoal;
    }

    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    /**
     * create the world (constructor)
     * 
     * @param width width of world in number of cells
     * @param height height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        this.currentLoop = 1;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        heroCastle = null;
        enemies = new ArrayList<>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();

        soldiers = new ArrayList<>();
        towers = new ArrayList<>();
        campfires = new ArrayList<>();

        endGoal = new Goal();

        victoryConditions = new ArrayList<>();
        victoryConditions.add("Experience");
        victoryConditions.add(123456);

        gameWon = false;
        GameMode = "";

    }

    /**
     * Gets the width of the map
     * @return width of the map
     */
    public int getWidth() {
        return width;
    }

    /**
     * gets the height of the map
     * @return height of the map
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the amount of gold the player currently has
     * @return amount of gold of the player
     */
    public static int getGold() {
        return character.getGold();
    }

    /**
     * Gets the amount of damage the player can currently deal
     * @return damage of the player
     */
    public int getDamage() {
        return character.getDamage();
    }

    public List<String> getBattleLogs() {
        return battleLogs;
    }

    /**
     * Gets the characters current health
     * @return current health 
     */
    public int getHealth() {
        return character.getHealth();
    }

    public int getTotalExperience() {
        return character.getTotalExperience();
    }

    public int getNumItems() {
        return unequippedInventoryItems.size();
    }

    /**
     * Equips an item when given the coordinates of the item in the unequipped slots.
     * @param x
     * @param y
     * @return the item that was previously held
     */
    public Entity equipItemByCoordinates(int x, int y) {
        Entity oldEquipped = null;
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        if(item instanceof Weapon) {
            oldEquipped = character.getEquippedWeapon();
            Weapon weaponItem = (Weapon) item;
            character.equipWeapon(weaponItem);
        } else if (item instanceof Helmet) {
            oldEquipped = character.getEquippedHelmet();
            Helmet helmetItem = (Helmet) item;
            character.equipHelmet(helmetItem);
        } else if (item instanceof Shield) {
            oldEquipped = character.getEquippedShield();
            Shield shieldItem = (Shield) item;
            character.equipShield(shieldItem);
        } else if (item instanceof Chest) {
            oldEquipped = character.getEquippedChest();
            Chest chestItem = (Chest) item;
            character.equipChest(chestItem);
        } else if (item instanceof OneRing) {
            oldEquipped = character.getEquippedRing();
            OneRing ringItem = (OneRing) item;
            character.equipRing(ringItem);
        }
        return oldEquipped;
    }

    /**
     * Adds gold to the character
     * @param gold
     */
    public static void addGold(int gold) {
        character.addGold(gold);
    }

    public void addTotalExperience(int experience) {
        character.addTotalExperience(experience);
    }

    /**
     * A simple getter for the characters Armour
     * @return character's armour
     */
    public int getArmour() {
        return character.getArmour();
    }

    /**
     * A simple getter for the cards currently held by the player
     * @return list of cards
     */
    public List<Card> getCards() {
        return cardEntities;
    }

    /**
     * A simple getter for the enemies currently on the map
     * @return list of cards
     */
    public List<BasicEnemy> getEnemies() {
        return enemies;
    }

    /**
     * A simple setter for the enemies currently on the map
     * @return list of cards
     */
    public void setEnemies(List<BasicEnemy> incomingEnemies) {
        LoopManiaWorld.enemies = incomingEnemies;
    }

    /**
     * A simple setter for the doggieCoin currency on the map
     *
     */
    public void setDoggieCoin(DoggieCoin doggieCoin) {
        LoopManiaWorld.doggieCoin = doggieCoin;
    }

    public double getDoggieCoinPrice() {
        return doggieCoin.getPrice();
    }

    public void setDoggieCoinPrice(double price) {
        doggieCoin.setPrice(price);
    }

    public int getDoggieCoin() {
        return doggieCoin.getNumHeld();
    }

    public void addDoggieCoins(int amount) {
        doggieCoin.addDoggieCoins(amount);
    }

    /**
     * A simple getter for the buildings on the map
     * @return list of builings
     */
    public List<Building> getBuildings() {
        return buildingEntities;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     * @param character the character
     */
    public void setCharacter(Character character) {
        LoopManiaWorld.character = character;
    }

    /**
     * set the heroCastle. This is necessary because it is loaded as a special entity out of the file
     * @param heroCastle the heroCastle
     */
    public void setHeroCastle(HeroCastle heroCastle) {
        LoopManiaWorld.heroCastle = heroCastle;
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        // TODO = if more specialised types being added from main menu, add more methods like this with specific input types...
        nonSpecifiedEntities.add(entity);
    }

    /**
     * A method to update the number of times the character has made a full cycle and update both the 
     * vampire castle and zombie pit classe
     */
    public void updateCycles() {
        for(Building b: buildingEntities) {
            if(b instanceof VampireCastleBuilding) {
                VampireCastleBuilding vampireCastle = (VampireCastleBuilding) b;
                vampireCastle.updateCycle();
            } else if (b instanceof ZombiePitBuilding) {
                // spawns zombies
                ZombiePitBuilding zombiePit = (ZombiePitBuilding) b;
                zombiePit.updateCycle();
            }
        }
    }
    
    public int getNumUpgrades() {
        return character.getNumUpgrades();
    }

    /**
     * Tries to upgrade the characters base armour if they have upgrades left
     */
    public void upgradeMaxHealth() {
        character.upgradeMaxHealth();
    }

    /**
     * Tries to upgrade the characters base damage if they have upgrades left
     */
    public void upgradeBaseDamage() {
        character.upgradeBaseDamage();
    }


    public int getCurrentExp() {
        return character.getCurrentExp();
    }

    public int getLevel() {
        return character.getLevel();
    }

    public int getExperienceToLevel() {
        return character.getExperienceToLevel();
    }

    public void gainExp(int exp) {
        character.gainExp(exp);
    }


    /**
     * spawns enemies if the conditions warrant it, adds to world
     * @return list of the enemies to be displayed on screen
     */
    public List<BasicEnemy> possiblySpawnEnemies(){

        List<BasicEnemy> spawningEnemies = new ArrayList<>();

        // Spawn from buildings
        for(Building b : buildingEntities) {
            if(b instanceof VampireCastleBuilding) {
                // spawns vampires
                VampireCastleBuilding vampireCastle = (VampireCastleBuilding) b;
                BasicEnemy enemy = vampireCastle.spawnEnemy(orderedPath);
                if(enemy != null) {
                    enemies.add(enemy);
                    spawningEnemies.add(enemy);
                }
            } else if (b instanceof ZombiePitBuilding) {
                // spawns zombies
                ZombiePitBuilding zombiePit = (ZombiePitBuilding) b;
                BasicEnemy enemy = zombiePit.spawnEnemy(orderedPath);
                if(enemy != null) {
                    enemies.add(enemy);
                    spawningEnemies.add(enemy);
                }
            }
        }

        // Spawn Doggie at a random postion once conditions are met
        Pair<Integer, Integer> dogPos = randomSpawn();
        if (dogPos != null && getDoggieSpawn() == 1){
            setDoggieSpawn(2);
            int indexInPath = orderedPath.indexOf(dogPos);
            BasicEnemy enemy = new BasicEnemyDoggie(new PathPosition(indexInPath, orderedPath));
            enemies.add(enemy);
            spawningEnemies.add(enemy);

        }

        // Spawn Elan at a random postion once conditions are met
        Pair<Integer, Integer> elanPos = randomSpawn();
        if (elanPos != null && getElanSpawn() == 1){
            setElanSpawn(2);
            int indexInPath = orderedPath.indexOf(elanPos);
            BasicEnemy enemy = new BasicEnemyElanMuske(new PathPosition(indexInPath, orderedPath));
            enemies.add(enemy);
            spawningEnemies.add(enemy);

            // sets the wildly fluctuating price of doggiecoin;
            doggieCoin.setPrice(500.0);
            doggieCoin.setStdDev(BasicEnemyElanMuske.getStdDev());
            doggieCoin.setMean(BasicEnemyElanMuske.getMean());
        }

        // Spawn randomly
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
        if (pos != null){
            int indexInPath = orderedPath.indexOf(pos);
            BasicEnemy enemy = new BasicEnemySlug(new PathPosition(indexInPath, orderedPath));
            enemies.add(enemy);
            spawningEnemies.add(enemy);
        }
        return spawningEnemies;
    }

    /**
     * kill an enemy
     * @param enemy enemy to be killed
     */
    private static void killEntity(Entity enemy){
        enemy.destroy();
        enemies.remove(enemy);
    }

    /**
     * Find battles in the world by looking around the character
     * @return list of enemies which have been killed
     */

    public static List<BasicEnemy> findBattle() {
        Boolean fightStarted = false;
        List<MovingEntity> battleSoldiers = new ArrayList<MovingEntity>();
        List<MovingEntity> battleTowers = new ArrayList<MovingEntity>();
        List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
        Boolean charDoubleDamage = false;
        for (BasicEnemy e: enemies){
            if (fightStarted == false) {
                if (Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2) < e.getBattleRadius()){
                    fightStarted = true;
                }
            }
            if (fightStarted == true) {
                if (Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2) < e.getSupportRadius()){
                    battleEnemies.add(e);
                }
            }
        }
        for (MovingEntity s: soldiers){
            battleSoldiers.add(s);
        }
        for (MovingEntity t: towers){
            if (Math.pow((character.getX()-t.getX()), 2) +  Math.pow((character.getY()-t.getY()), 2) < t.getSupportRadius()){
                battleTowers.add(t);
            }
        }
        for (MovingEntity camp: campfires){
            if (Math.pow((character.getX()-camp.getX()), 2) +  Math.pow((character.getY()-camp.getY()), 2) < camp.getBattleRadius()){
                charDoubleDamage = true;
            }
        }
        if (fightStarted == true) {
            return runBattles(character, battleSoldiers, battleTowers, battleEnemies, charDoubleDamage);
        }
        return battleEnemies;
    } 

    /**
     * Executes the battles, attacking back and forth between allies and enemies until one side is dead
     * @param battleCharacter - Player Character, battleSoldiers - list of soldiers, battleTowers - list of towers, battleEnemies - list of enemies, charDoubleDamage - boolean if the character is in a campfire
     * @return list of enemies which have been killed
     */
    public static List<BasicEnemy> runBattles(Character battleCharacter, List<MovingEntity> battleSoldiers, List<MovingEntity> battleTowers, List<BasicEnemy> battleEnemies, Boolean charDoubleDamage) {

        String log = "Battle " + Integer.toString(battleLogs.size() + 1) + "- "; 

        List<BasicEnemy> defeatedEntities = new ArrayList<BasicEnemy>();
        Boolean enemiesAlive = true;
        //hold the starting damage
        int startingDamage = battleCharacter.getDamage();
        //double the character damage if they are in a campfire
        if (charDoubleDamage == true) {
            //set character damage to double
            battleCharacter.setDamage(startingDamage*2);
        }
        //Loop until either character is dead or all enemies in the battle are dead
        while (battleCharacter.getHealth() > 0 && enemiesAlive) {
            //Character attacks first enemy in battle
            if (battleCharacter.getStunned() == 0) {
                characterAttackEnemy(battleCharacter, battleEnemies, battleSoldiers);
            } else {
                //If character is stunned, decrement stunned counter
                battleCharacter.setStunned(battleCharacter.getStunned() - 1);
            }
            //Soldier attacks first enemy in battle
            for (MovingEntity s: battleSoldiers){
                //If the soldier is tranced, ie duration above 0
                if (s.getDuration() > 0) {
                    //Remove 1 turn of trance
                    s.addDuration(-1);
                    //If the enemy comes out of trance
                    if (s.getDuration() == 0) {
                        //Kill the soldier
                        s.setHealth(0);
                        //Restore the first tranced enemy
                        for (BasicEnemy e : battleEnemies) {
                            if (e.getHealth() == -100) {
                                //restore them to 1hp.
                                e.setHealth(1);
                                break;
                            }
                        }
                    }
                }
                attackEnemy(s, battleEnemies);
            }
            //Towers attack first enemy in battle
            for (MovingEntity t: battleTowers){
                attackEnemy(t, battleEnemies);
            }
            //Enemies attack first soldier in battle, then character if there are no soldiers
            enemiesAlive = false;
            //Use oldschool iterator incase we need to add a zombie during iteration
            for (int i = 0; i < battleEnemies.size(); i++){
                BasicEnemy e = battleEnemies.get(i); 
                if (e.getHealth() > 0) {
                    //Check to see if there is atleast one enemy alive
                    enemiesAlive = true;
                    //If enemy is Elan
                    if (e instanceof BasicEnemyElanMuske) {
                        //Heal all living enemies in the current combat for 2
                        for (BasicEnemy healTarget: battleEnemies) {
                            if (healTarget.getHealth() > 0) {
                                healTarget.changeHealth(elanHealAmount);
                            }
                        }
                    }
                    attackAlly(e, battleSoldiers, battleCharacter, battleEnemies);
                }
            }
        }

        if (battleCharacter.getHealth() <= 0) {
            if(character.getLives() > 0) {
                character.resurrect();
            } else {
                gameLost();
            }
        }

        //Kill all enemies and soldiers with hp <= 0
        int numKilled = 0;
        for (BasicEnemy e: battleEnemies){
            // IMPORTANT = we kill enemies here, because killEntity removes the enemy from the enemies list
            // if we killEntity in prior loop, we get java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            if (e.getHealth() <= 0) {
                numKilled++;
                defeatedEntities.add(e);
                killEntity(e);
            }
        }
        if(numKilled != 0) {
            log += "\n\tKilled " + Integer.toString(numKilled) + ((numKilled==1) ? " enemy." : " enemies.");
        }

        int soldiersDied = 0;
        for (MovingEntity s: battleSoldiers){
            //kill any soldiers with health <= 0 as well as any that were tranced
            if (s.getHealth() <= 0 || s.getDuration() > 0) {
                soldiersDied++;
                soldiers.remove(s);
                if(character.getSoldiers() > 0) { 
                    character.addSoldier(-1);
                }
            }
        }
        if(soldiersDied != 0) {
            log += "\n\t" + Integer.toString(soldiersDied) + ((soldiersDied==1) ? " soldier died." : " soldiers died.");
        }
        //reset the character damage if they were in a campfire
        if (charDoubleDamage == true) {
            //set character damage to normal
            battleCharacter.setDamage(startingDamage);
        }
        if (soldiersDied != 0 || numKilled != 0) {
            battleLogs.add(log);
        }
        return defeatedEntities;
    }


    /**
     * Lose the game
     */
    static void gameLost() {
        if (!testing) {
            LoopManiaWorldController.openGameLost();
        }
    }

    /**
     * Attack the enemy
     * @param MovingEntity ST - the Soldier or Tower Attacking
     * @param List<BasicEnemy> battleEnemies - list of enemies being attacked
     */
    public static void attackEnemy(MovingEntity ST, List<BasicEnemy> battleEnemies){
        boolean attacked = false;
        if (ST.getHealth() > 0) {
            for (BasicEnemy e: battleEnemies){
                if (e.getHealth() > 0) {
                    if (!attacked) {
                        ST.attack(e);
                        attacked = true;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Attack the enemy with the Character
     * @param MovingEntity c - Player Character,
     * @param List<BasicEnemy> battleEnemies - list of enemies being attacked
     * @param List<MovingEntity> battleSoldiers - list of soldiers currently involved in the battle
     */
    public static void characterAttackEnemy(Character c, List<BasicEnemy> battleEnemies, List<MovingEntity> battleSoldiers){
        boolean attacked = false;
        if (c.getHealth() > 0) {
            for (BasicEnemy e: battleEnemies){
                if (e.getHealth() > 0) {
                    if (!attacked) {
                        int temp = character.getDamage();
                        //Check if the weapon is a stake and enemy is a vampire
                        if (character.getAttackBonuses().contains("Vampire") && e instanceof BasicEnemyVampire) {
                            //Double the damage
                            temp = temp*2;
                            character.setDamage(temp);
                            c.attack(e);
                            character.setDamage(temp/2);
                        //Check if weapon is a staff
                        } else if (character.getEquippedWeapon() instanceof Staff) {
                            int crit = (new Random()).nextInt(100);
                            //If random 0-99 is below crit chance, Staff Crits
                            if (crit < Staff.getCritChance()){
                                //Set enemy HP to -100
                                e.setHealth(-100);
                                //Create a new Soldier
                                MovingEntity s = new Soldier(null);
                                s.setDuration(5);
                                battleSoldiers.add(s);
                            }
                        //Check if weapon is special and enemy is a boss
                        } else if (character.getAttackBonuses().contains("Boss") && e.getType() == "Boss") {
                            //Triple the damage
                            temp = temp*3;
                            character.setDamage(temp);
                            c.attack(e);
                            character.setDamage(temp/3);
                        } else {
                            //Attack normally
                            c.attack(e);
                        }
                        attacked = true;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Attack an ally with the enemy
     * 
     * @param BasicEnemy e - Enemy that is attacking,
     * @param List<MovingEntity> battleSoldiers - List of soldiers in the battle
     * @param Character battleCharacter - Player Character,
     * @param List<BasicEnemy> battleEnemies - list of enemies in the battle
     */
    public static void attackAlly(BasicEnemy e, List<MovingEntity> battleSoldiers, Character battleCharacter,List<BasicEnemy> battleEnemies){
        Boolean attacked = false;
        for (MovingEntity s: battleSoldiers){
            if (attacked == false){
                //if enemy is alive
                if (s.getHealth() > 0) {
                    e.attack(s);
                    //check if a soldier was crit by a zombie
                    if (s.getHealth() == -100) {
                        //Create a zombie
                        BasicEnemy zombie = new BasicEnemyZombie(null);
                        battleEnemies.add(zombie);
                    }
                    attacked = true;
                    break;
                }
            }
        }
        if (attacked == false){
            //Check Character is alive
            if (battleCharacter.getHealth() > 0) {
                //Check if enemy is a boss and Special Shield is eqipped
                if (e.getType() == "Boss" && battleCharacter.getDefenceBonuses().contains("Boss")) {
                    //Double the Armour Contribution
                    battleCharacter.addEquipmentArmour(TreeStump.getArmour());
                    e.attack(battleCharacter);
                    battleCharacter.addEquipmentArmour(-TreeStump.getArmour());
                //Check if enemy is Vampire and Character has a shield
                } else if (e instanceof BasicEnemyVampire && battleCharacter.isHoldingShield()) {
                    //Reduce crit chance by 60%
                    int originalCrit = e.getCritChance();
                    double vampCrit = e.getCritChance();
                    vampCrit = vampCrit*(1-Shield.getShieldCritModifier());
                    e.setCritChance( (int) vampCrit);
                    e.attack(battleCharacter);
                    //Set crit chance back to normal
                    if (!testing) {
                        e.setCritChance(originalCrit);
                    }
                } else {
                    //Attack Normally
                    e.attack(battleCharacter);
                }
                attacked = true;
            }
        }
    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public VampireCastleCard loadVampireCard(){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            // if you get more cards than you can hold, you get 100 gold.
            addGold(20);
            removeCard(0);
        }
        VampireCastleCard vampireCastleCard = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(vampireCastleCard);
        return vampireCastleCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public ZombiePitCard loadZombieCard(){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            // if you get more cards than you can hold, you get 100 gold.
            addGold(20);
            removeCard(0);
        }
        ZombiePitCard zombiePitCard = new ZombiePitCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(zombiePitCard);
        return zombiePitCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public TowerCard loadTowerCard(){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            // if you get more cards than you can hold, you get 100 gold.
            addGold(20);
            removeCard(0);
        }
        TowerCard towerCard = new TowerCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(towerCard);
        return towerCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public VillageCard loadVillageCard(){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            // if you get more cards than you can hold, you get 100 gold.
            addGold(20);
            removeCard(0);
        }
        VillageCard villageCard = new VillageCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(villageCard);
        return villageCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public BarracksCard loadBarracksCard(){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            // if you get more cards than you can hold, you get 100 gold.
            addGold(20);
            removeCard(0);
        }
        BarracksCard barracksCard = new BarracksCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(barracksCard);
        return barracksCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public TrapCard loadTrapCard(){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            // if you get more cards than you can hold, you get 100 gold.
            addGold(20);
            removeCard(0);
        }
        TrapCard trapCard = new TrapCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(trapCard);
        return trapCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public CampfireCard loadCampfireCard(){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            // if you get more cards than you can hold, you get 100 gold.
            addGold(20);
            removeCard(0);
        }
        CampfireCard campfireCard = new CampfireCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(campfireCard);
        return campfireCard;
    }


    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index){
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }

    /**
     * spawn a sword in the world and return the sword entity
     * @return a sword to be spawned in the controller as a JavaFX node
     */
    public Sword addUnequippedSword(){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new sword, as we know we have at least made a slot available...
        Sword sword = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(sword);
        return sword;
    }

    /**
     * spawn a sword in the world and return the sword entity
     * @return a sword to be spawned in the controller as a JavaFX node
     */
    public Anduril addUnequippedAnduril(){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from Andurils
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest Anduril
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new Anduril, as we know we have at least made a slot available...
        Anduril anduril = new Anduril(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(anduril);
        return anduril;
    }

    /**
     * spawn a staff in the world and return the staff entity
     * @return a staff to be spawned in the controller as a JavaFX node
     */
    public Staff addUnequippedStaff(){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from Staffs
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest Staff
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new Staff, as we know we have at least made a slot available...
        Staff staff = new Staff(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(staff);
        return staff;
    }

    /**
     * spawn a stake in the world and return the stake entity
     * @return a stake to be spawned in the controller as a JavaFX node
     */
    public Stake addUnequippedStake(){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from Stakes
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest Stake
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new Stake, as we know we have at least made a slot available...
        Stake stake = new Stake(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(stake);
        return stake;
    }

    /**
     * spawn a shield in the world and return the shield entity
     * @return a shield to be spawned in the controller as a JavaFX node
     */
    public Shield addUnequippedShield(){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from Shields
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest Shield
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new Shield, as we know we have at least made a slot available...
        Shield shield = new Shield(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(shield);
        return shield;
    }

    /**
     * spawn a shield in the world and return the shield entity
     * @return a shield to be spawned in the controller as a JavaFX node
     */
    public TreeStump addUnequippedTreeStump(){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from TreeStumps
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest TreeStump
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new TreeStump, as we know we have at least made a slot available...
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(treeStump);
        return treeStump;
    }


    /**
     * spawn a shield in the world and return the shield entity
     * @return a shield to be spawned in the controller as a JavaFX node
     */
    public Chest addUnequippedChest(){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from Chests
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest Chest
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new Chest, as we know we have at least made a slot available...
        Chest chest = new Chest(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(chest);
        return chest;
    }

    /**
     * spawn a shield in the world and return the shield entity
     * @return a shield to be spawned in the controller as a JavaFX node
     */
    public Helmet addUnequippedHelmet(){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from Helmets
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest Helmet
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new Helmet, as we know we have at least made a slot available...
        Helmet helmet = new Helmet(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(helmet);
        return helmet;
    }

    /**
     * spawn a shield in the world and return the shield entity
     * @return a shield to be spawned in the controller as a JavaFX node
     */
    public OneRing addUnequippedRing(){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from Rings
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest Ring
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new Ring, as we know we have at least made a slot available...
        OneRing ring = new OneRing(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(ring);
        return ring;
    }

    /**
     * Drink a Potion
     */
    public void drinkPotion() {
        character.drinkPotion();
    }

    /**
     * Add a Potion
     */
    public void addPotion() {
        character.addPotion();
    }

    /**
     * Get number of Potions
     */
    public static int getPotions() {
        return character.getPotions();
    }

    /**
     * Get number of Soldiers
     */
    public int getSoldiers() {
        return character.getSoldiers();
    }

    /**
     * remove an item by x,y coordinates
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y){
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }


    /**
     * Applies any building effects to their nearby units
     * 
     */
    public void applyBuildingRadiusEffects() {
        for (Building b : buildingEntities) {
            if (b instanceof CampfireBuilding || b instanceof VillageBuilding || b instanceof BarracksBuilding) {
            
                b.radiusCharacterEffects(character);
                b.radiusEnemyEffects(enemies);
                if (b instanceof BarracksBuilding) {
                    Soldier s = new Soldier(new PathPosition(0, orderedPath));
                    soldiers.add(s);
                }
            } else if (b instanceof TrapBuilding) { 
                // checks whether out of uses
                TrapBuilding trapBuilding = (TrapBuilding) b;
                trapBuilding.updateStatus();
                b.radiusCharacterEffects(character);
                b.radiusEnemyEffects(enemies);
            } 
        }
    }

    /**
     * run moves which occur with every tick without needing to spawn anything immediately
     */
    public void runTickMoves(){
        character.moveDownPath();
        if (character.getX() == heroCastle.getX() && character.getY() == heroCastle.getY()) {
            currentLoop += 1;
            updateCycles();
            setShopOpen(true);
            if (currentLoop > doggieSpawnLoops && getDoggieSpawn() == 0) {
                setDoggieSpawn(1);
            }
            if (currentLoop > elanSpawnLoops && getTotalExperience() > elanSpawnExperience && getElanSpawn() == 0) {
                setElanSpawn(1);
            } else {
                // if there is no Elan, resets the wildly fluctuating price of the coin.
                if(doggieCoin != null) {
                    doggieCoin.setDefault();
                }
            }

        }
        moveBasicEnemies();

        // Building effects
        applyBuildingRadiusEffects();

        killDead();

        goalCheck();

        if(doggieCoin != null) {
            doggieCoin.priceFluctuate();
        }

        // check if hero died here incase other effects come into place
        if (character.getHealth() <= 0) {
            if(character.getLives() > 0) {
                character.resurrect();
            } else {
                gameLost();
            }
        }
    }

    /**
     * Check if any victory conditions have been met
     * if so, advance the goal state, if all have been met
     * advance goal state to the end and win the game.
     */
    public void goalCheck() {
        //Check to see if continue has been pressed
        if (getGameWon()) {
            return;
        }
        //Currently only goal is experience but can expand to a list of conditions if needed
        for (int i = 0; i < victoryConditions.size(); i++) {
            //if i is a string
            if (victoryConditions.get(i) instanceof String) {
                //check what attribute to compare
                if (victoryConditions.get(i) == "Experience") {
                    //Checks the the experience is >= victory and hasnt been set to -1, ie completed yet
                    if (character.getTotalExperience() >= (Integer) victoryConditions.get(i+1) && (Integer) victoryConditions.get(i+1) >= 0) {
                        //Advance the goal state
                        endGoal.nextState();
                        //Set the condition to completed
                        victoryConditions.set(i+1, -1);
                    }
                }
            }
        }
        //if all victory conditions are completed, at the moment only 1, advance the state to win the game
        for (int i = 0; i < victoryConditions.size(); i++) {
            //if i is a int
            if (victoryConditions.get(i) instanceof Integer) {
                //check what attribute to compare
                if (i == victoryConditions.size()-1 && (Integer) victoryConditions.get(i) == -1) {
                    endGoal.nextState();
                    endGoal.nextState();
                    endGoal.nextState();
                }
            }
        }
        
    }
    /**
     * Win the game
     */
    static void winGame() {
        setGameWon(true);
        if (!testing) {
            LoopManiaWorldController.openGameWon();
        }
    }

    /**
     * kills all the enemies who have a hp less than zero
     */
    private void killDead() {
        ArrayList<BasicEnemy> listOfDead = new ArrayList<>();
        for(BasicEnemy e : enemies) {
            if(e.getHealth() <= 0) {
                listOfDead.add(e);
            }
        }
        
        for(BasicEnemy dead: listOfDead) {
            killEntity(dead);
        }

        return;
    }

    /**
     * remove an item from the unequipped inventory
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item){
        item.destroy();
        unequippedInventoryItems.remove(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    public Entity getUnequippedInventoryItemEntityByCoordinates(int x, int y){
        for (Entity e: unequippedInventoryItems){
            if ((e.getX() == x) && (e.getY() == y)){
                return e;
            }
        }
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list (this is ordered based on age in the starter code)
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index){
        Entity item = unequippedInventoryItems.get(index);
        item.destroy();
        unequippedInventoryItems.remove(index);
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the unequipped inventory
     * @return x,y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem(){
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available slot defined by looking row by row
        for (int y=0; y<unequippedInventoryHeight; y++){
            for (int x=0; x<unequippedInventoryWidth; x++){
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null){
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x){
        for (Card c: cardEntities){
            if (c.getX() >= x){
                c.x().set(c.getX()-1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void moveBasicEnemies() {
        // TODO = expand to more types of enemy
        for (BasicEnemy e: enemies){
            e.move();
        }
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     * @return null if random choice is that wont be spawning an enemy or it isn't possible, or random coordinate pair if should go ahead
     */
    private Pair<Integer, Integer> possiblyGetBasicEnemySpawnPosition(){
        
        // has a chance spawning a basic enemy on a tile the character isn't on or immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
        // TODO = change based on spec
        if ((choice == 0)){
            int numSlugs = 0;
            for (BasicEnemy e : enemies) {
                if (e instanceof BasicEnemySlug) {
                    numSlugs++;
                }
            }
            //Maximum of 3 slugs allowed
            if (numSlugs < 3) {
                return randomSpawn();
            }
        }
        return null;
    }

    /**
     * Extracted code to randomly get a spawn position
     * @return Pair<Integer, Integer>, Position to spawn the enemy
     */
    private Pair<Integer, Integer> randomSpawn(){
        Random rand = new Random();
        List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
        int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
        // inclusive start and exclusive end of range of positions not allowed
        int startNotAllowed = (indexPosition - 2 + orderedPath.size())%orderedPath.size();
        int endNotAllowed = (indexPosition + 3)%orderedPath.size();
        // note terminating condition has to be != rather than < since wrap around...
        for (int i=endNotAllowed; i!=startNotAllowed; i=(i+1)%orderedPath.size()){
            orderedPathSpawnCandidates.add(orderedPath.get(i));
        }
    
        // choose random choice
        Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));

        return spawnPosition;
    }



    /**
     * Gets the card by the coordinates
     * @param cardNodeX
     * @param cardNodeY
     * @return the card at the coordinates
     */
    public Card getCardbyCoordinates(int cardNodeX, int cardNodeY) {
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                card = c;
                break;
            }
        }
        return card;
    }

    /**
     * remove a card by its x, y coordinates
     * @param cardNodeX x index from 0 to width-1 of card to be removed
     * @param cardNodeY y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                card = c;
                break;
            }
        }

        // get card type
        if(card == null) {
            return null;
        }

        for(Building b : buildingEntities) {
            if(b.getX() == (buildingNodeX) && b.getY() == (buildingNodeY)) {
                // there is currently a building at these coordinates
                return null;
            }
        }

        if(card.checkPlacement(orderedPath, buildingNodeX, buildingNodeY)) {
            Building newBuilding = card.placeBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY)); 
            buildingEntities.add(newBuilding);
            //Add any new towers to the tower list
             if (newBuilding instanceof TowerBuilding) {
                Tower t = new Tower(new PathPosition(0, orderedPath));
                towers.add(t);
            }
 
             // destroy the card
            card.destroy();
            cardEntities.remove(card);
            shiftCardsDownFromXCoordinate(cardNodeX);

            return newBuilding;
        } else {
            return null;
        }
    }
}

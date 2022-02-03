package unsw.loopmania;


import unsw.loopmania.InventoryItems.Armour.Chest;
import unsw.loopmania.InventoryItems.Armour.Helmet;
import unsw.loopmania.InventoryItems.Armour.Shield;
import unsw.loopmania.InventoryItems.Rings.OneRing;
import unsw.loopmania.InventoryItems.Weapons.Weapon;

import java.util.List;
import java.util.ArrayList;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private int baseDamage = 2;
    private int weaponDamageModifier = 0;

    private int currentExperience = 0;
    private int level = 1;
    private int experienceToLevel = level^2; // quadratic increase in the amount of experience between levels
    private int freeUpgrades = 0;

    private int baseArmour = 0; // this will include modifiers such as chest/helmet/shield
    private int equipmentArmourModifier = 0;
    private int totalArmour = baseArmour + equipmentArmourModifier;

    private int lives = 0;
    private int maxHealth = 100;
    private int gold = 0;

    private int totalExperience = 0;

    private int numSoldiers = 0;

    private ArrayList<Potion> potions;

    private List<String> attackBonuses = new ArrayList<>();
    private List<String> defenceBonuses = new ArrayList<>();

    private Weapon equippedWeapon = null;

    private Chest equippedChest = null;
    private Helmet equippedHelmet = null;
    private Shield equippedShield = null;
    private OneRing equippedRing = null;

    public int getCurrentExperience() {
        return this.currentExperience;
    }

    public void setCurrentExperience(int currentExperience) {
        this.currentExperience = currentExperience;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setExperienceToLevel(int experienceToLevel) {
        this.experienceToLevel = experienceToLevel;
    }

    public int getFreeUpgrades() {
        return this.freeUpgrades;
    }

    public void setFreeUpgrades(int freeUpgrades) {
        this.freeUpgrades = freeUpgrades;
    }
    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    public List<String> getDefenceBonuses() {
        return this.defenceBonuses;
    }

    public void setDefenceBonuses(List<String> defenceBonuses) {
        this.defenceBonuses = defenceBonuses;
    }

    public int getTotalExperience() {
        return this.totalExperience;
    }

    public void setExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    public int getBaseDamage() {
        return this.baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getWeaponDamageModifier() {
        return this.weaponDamageModifier;
    }

    public void setWeaponDamageModifier(int weaponDamageModifier) {
        this.weaponDamageModifier = weaponDamageModifier;
    }

    public int getBaseArmour() {
        return this.baseArmour;
    }

    public void setBaseArmour(int baseArmour) {
        this.baseArmour = baseArmour;
    }

    public int getEquipmentArmourModifier() {
        return this.equipmentArmourModifier;
    }

    public void setEquipmentArmourModifier(int equipmentArmourModifier) {
        this.equipmentArmourModifier = equipmentArmourModifier;
    }

    public int getTotalArmour() {
        return this.totalArmour;
    }

    public void setTotalArmour(int totalArmour) {
        this.totalArmour = totalArmour;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getNumSoldiers() {
        return this.numSoldiers;
    }

    public void setNumSoldiers(int numSoldiers) {
        this.numSoldiers = numSoldiers;
    }
    public void setAttackBonuses(List<String> attackBonuses) {
        this.attackBonuses = attackBonuses;
    }

    public Weapon getEquippedWeapon() {
        return this.equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Chest getEquippedChest() {
        return this.equippedChest;
    }

    public void setEquippedChest(Chest equippedChest) {
        this.equippedChest = equippedChest;
    }

    public Helmet getEquippedHelmet() {
        return this.equippedHelmet;
    }

    public void setEquippedHelmet(Helmet equippedHelmet) {
        this.equippedHelmet = equippedHelmet;
    }

    public Shield getEquippedShield() {
        return this.equippedShield;
    }

    public void setEquippedShield(Shield equippedShield) {
        this.equippedShield = equippedShield;
    }

    public OneRing getEquippedRing() {
        return this.equippedRing;
    }

    public void setEquippedRing(OneRing equippedRing) {
        this.equippedRing = equippedRing;
    }

    public int getPotions() {
        return this.potions.size();
    }

    public void setPotions(ArrayList<Potion> potionsList) {
        this.potions = potionsList;
    }

    public int getCurrentExp() {
        return currentExperience;
    }
    
    public int getLevel() {
        return level;
    }

    public int getExperienceToLevel() {
        return experienceToLevel;
    }

    /**
     * Gives experience and checks level up.
     * @param exp
     */
    public void gainExp(int exp) {
        currentExperience += exp;
        checkLevelUp();
    }

    /**
     * Adds experience
     * @param exp
     */
    public void addTotalExperience(int exp) {
        totalExperience += exp;
    }

    // TODO = potentially implement relationships between this class and other classes

    public Character(PathPosition position) {
        super(position);
        super.setHealth(100);
        super.setBattleRadius(0);
        super.setSupportRadius(0);
        super.setCritChance(0);
        super.setType("Character");
        super.setDamage(baseDamage + weaponDamageModifier);
        currentExperience = 0;
        potions = new ArrayList<>();
    }
    
    /**
     * An updater method which is called whenever the baseDamage, attack bonuses or weaponDamage Modifier are changed
     * 
     */
    private void updateTotalDamage() {
        super.setDamage(baseDamage + weaponDamageModifier);

    }

    /**
     * Mostly called by equipping weapons, this method changes the weapon damage and updates total damage
     * @param weaponDamage
     */
    public void addWeaponDamage(int weaponDamage) {
        weaponDamageModifier += weaponDamage;
        updateTotalDamage();
    }
    
    /**
     * An updater method which is called to change the total armour
     */
    public void updateTotalArmour() {
        totalArmour = baseArmour + equipmentArmourModifier;
    }

    /**
     * Mostly called by equipping armour, this method changes the character's armour
     * @param armourUpgrade
     */
    public void addEquipmentArmour(int armourUpgrade) {
        equipmentArmourModifier += armourUpgrade;
        updateTotalArmour();
    }

    /**
     * Adds more lives to the character
     * @param moreLives
     */
    public void addLives(int moreLives) {
        lives += moreLives;
    }

    /**
     * Gives an attack bonus to the character
     * @param attackBonus
     */
    public void addAttackBonus(String attackBonus) {
        attackBonuses.add(attackBonus);
        updateTotalDamage();
    }

    /**
     * Removes an attack bonus from the character
     * @param attackBonus
     */
    public void removeAttackBonus(String attackBonus) {
        attackBonuses.remove(attackBonus);
        updateTotalDamage();
    }

    /**
     * Gives an attack bonus to the character
     * @param defenceBonus
     */
    public void addDefenceBonus(String defenceBonus) {
        defenceBonuses.add(defenceBonus);
        updateTotalArmour();
    }

    /**
     * Removes an attack bonus from the character
     * @param attackBonus
     */
    public void removeDefenceBonus(String defenceBonus) {
        defenceBonuses.remove(defenceBonus);
        updateTotalArmour();
    }


    // WEAPON FUNCTIONS
    /**
     * Checks to see if the character is holding a weapon
     * @return  Returns true if holding a weapon, false otherwise
     */
    public boolean isHoldingWeapon() {
        if(equippedWeapon == null) {
            return false;
        }
        return true;
    }

    /**
     * UnEquips a weapon. If not holding a weapon, doesnt do anything
     */
    public void unEquipWeapon() {
        if(isHoldingWeapon()) {
            equippedWeapon.unEquip(this);
            equippedWeapon = null;
        }
        return;
    }

    /**
     * Equips a weapon. This weapon can be of any type.
     * @param weapon
     */
    public void equipWeapon(Weapon weapon) {
        unEquipWeapon(); // automatically dequips any weapons currently being held
        weapon.equip(this);
        equippedWeapon = weapon;
    }

    // SHIELD FUNCTIONS
    /**
     * Unequips a shield. If not holding a shield doesnt do anything.
     */
    public void unEquipShield() {
        if(isHoldingShield()) {
            equippedShield.unEquip(this);
            equippedShield = null;
        }
        return;
    }

    /**
     * Equips a shield which updates the armour of the character
     * @param shield
     */
    public void equipShield(Shield shield) {
        unEquipShield(); // automatically dequiped Shield if currently holding one
        shield.equip(this);
        equippedShield = shield;
        return;
    }

    /**
     * Boolean whether currently equipping a shield or not
     */
    public boolean isHoldingShield() {
        if(equippedShield == null) {
            return false;
        }
        return true;
    }

    // CHEST FUNCTIONS
    /**
     * boolean whether currently equipped a chest or not
     * @return
     */
    public boolean isHoldingChest() {
        if(equippedChest == null) {
            return false;
        }
        return true;
    }

    /**
     * Unequips a chest. If not holding a chest doesnt do anything.
     */
    public void unEquipChest() {
        if(isHoldingChest()) {
            equippedChest.unEquip(this);
            equippedChest = null;
        }
        return;
    }

    /**
     * Equips a chest plate and updates character armour
     * @param chest
     */
    public void equipChest(Chest chest) {
        unEquipChest(); // automatically dequiped chest if currently holding one
        chest.equip(this);
        equippedChest = chest;
        return;
    }

    // Helmet Functions

    /**
     * a boolean whether a helmet is equipped or not
     */
    public boolean isHoldingHelmet() {
        if(equippedHelmet == null) {
            return false;
        }
        return true;
    }

    /**
     * Unequips a helmet. If not holding a helmet doesnt do anything.
     */
    public void unEquipHelmet() {
        if(isHoldingHelmet()) {
            equippedHelmet.unEquip(this);
            equippedHelmet = null;
        }
        return;
    }

    /**
     * Equips a helmet and updates the character's armour stats
     * @param helmet
     */
    public void equipHelmet(Helmet helmet) {
        unEquipHelmet(); // automatically dequiped Helmet if currently holding one
        helmet.equip(this);
        equippedHelmet = helmet;
        return;
    }

    // RING FUNCTIONS
    /**
     * A boolean showing wether a ring is currently equipped
     * @return
     */
    public boolean isHoldingRing() {
        if(equippedRing == null) {
            return false;
        }
        return true;
    }

    /**
     * Unequips a ring. If not holding a ring doesnt do anything.
     */
    public void unEquipRing() {
        if(isHoldingRing()) {
            equippedRing.unEquip(this);
            equippedRing = null;
        }
        return;
    }

    /**
     * Equips a ring and adds an extra life.
     * @param ring
     */
    public void equipRing(OneRing ring) {
        unEquipRing(); // automatically dequiped Helmet if currently holding one
        ring.equip(this);
        equippedRing = ring;
        return;
    }

    
    /**
     * Simple getter for the attack bonuses of the character
     * @return
     */
    public List<String> getAttackBonuses() {
        return attackBonuses;
    }

    /**
     * The character gets a potion in their inventory
     * @param potion
     */
    public void addPotion() {
        Potion potion = new Potion(null, null);
        potions.add(potion);
    }

    /**
     * The character drinks a poition and restores some health
     */
    public void drinkPotion() {
        if (potions.size() > 0) {
            potions.get(potions.size()-1).drink(potions, this);
        }
    }

    /**
     * A setter which adds some soldiers
     * @param numSoldiers
     */
    public void addSoldier(int numSoldiers) {
        this.numSoldiers += numSoldiers;
    }

    /**
     * A getter which gets the number of soldiers
     * @return
     */
    public int getSoldiers() {
        return numSoldiers;
    }

    /**
     * Gets the amount of health the player currently has
     * @return
     */
    public int getHealth() {
        return super.getHealth();
    }

    /**
     * Gets the total amount of armour the character currently has
     * @return
     */
    public int getArmour() {
        return totalArmour;
    }

    /**
     * Gets the number of lives the character has
     * @return int lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Gets the amount of gold the character currently has
     * @return int gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * Gives the character some gold
     * @param goldGiven
     */
    public void addGold(int goldGiven) {
        gold += goldGiven;
    }

    /**
     * Called by the gain experience function to check if the character can level up. If it can, levels up
     */
    private void checkLevelUp() {
        if(currentExperience >= experienceToLevel) {
            // need to level up
            while(currentExperience >= experienceToLevel) {
                currentExperience = currentExperience - experienceToLevel;
                levelUp();
            }
        }
    }

    
    /**
     * A private function which levels up the character. Controls the expereience to level and gives upgrades.
     */
    private void levelUp() {
        experienceToLevel = level^2;
        level++;
        if(level % 5 == 0) {
            freeUpgrades++;
        }
    }

    /**
     * Getter for number of upgrades
     * @return int freeUpgrades
     */
    public int getNumUpgrades() {
        return freeUpgrades;
    }

    /**
     * Doubles the Characters Base Damage
     */
    public void upgradeBaseDamage() {
        if(freeUpgrades >= 0) {
            baseDamage *= 2;
            updateTotalDamage();
            freeUpgrades--;
        }
    }

    /**
     * Gives 1.5 times max HP and heals for that amount
     */
    public void upgradeMaxHealth() {
        if(freeUpgrades >= 0) {
            maxHealth += (maxHealth/2);
            super.changeHealth(maxHealth/2);
            freeUpgrades--;
        }
    }

    /**
     * Changes HP by the amount, heal for positive amounts and take damage for negative
     * Damage is also reduced by armour at a rate of 1 armour to 1 % reduction
     * @param int change
     */
    @Override
    public void changeHealth(int change) {
        if (change < 0 && getArmour() != 0) {
            double doubleChange = (double) change;
            doubleChange = doubleChange*(1-((double)getArmour()/100));
            change = (int) Math.round(doubleChange);
        }
        super.changeHealth(change);
    }

    /**
     * Brings character back to life with full health if it dies.
     */
    public void resurrect() {
        super.setHealth(maxHealth);
        loseLife();
    }

    /**
     * Lose a life
     */
    private void loseLife() {
        lives--;
    }
}

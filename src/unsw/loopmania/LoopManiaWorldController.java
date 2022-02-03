package unsw.loopmania;
import unsw.loopmania.InventoryItems.Weapons.Weapon;
import unsw.loopmania.InventoryItems.Weapons.Staff;
import unsw.loopmania.InventoryItems.Weapons.Stake;
import unsw.loopmania.InventoryItems.Weapons.Anduril;
import unsw.loopmania.InventoryItems.Armour.Chest;
import unsw.loopmania.InventoryItems.Armour.Helmet;
import unsw.loopmania.InventoryItems.Armour.Shield;
import unsw.loopmania.InventoryItems.Armour.TreeStump;
import unsw.loopmania.InventoryItems.Rings.OneRing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.codefx.libfx.listener.handle.ListenerHandle;
import org.codefx.libfx.listener.handle.ListenerHandles;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import unsw.loopmania.InventoryItems.Weapons.Sword;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;  
import javafx.scene.layout.VBox;

import java.util.EnumMap;

import java.io.File;
import java.io.IOException;


/**
 * the draggable types.
 * If you add more draggable types, add an enum value here.
 * This is so we can see what type is being dragged.
 */
enum DRAGGABLE_TYPE{
    CARD,
    ITEM
}

/**
 * A JavaFX controller for the world.
 * 
 * All event handlers and the timeline in JavaFX run on the JavaFX application thread:
 *     https://examples.javacodegeeks.com/desktop-java/javafx/javafx-concurrency-example/
 *     Note in https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Application.html under heading "Threading", it specifies animation timelines are run in the application thread.
 * This means that the starter code does not need locks (mutexes) for resources shared between the timeline KeyFrame, and all of the  event handlers (including between different event handlers).
 * This will make the game easier for you to implement. However, if you add time-consuming processes to this, the game may lag or become choppy.
 * 
 * If you need to implement time-consuming processes, we recommend:
 *     using Task https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Task.html by itself or within a Service https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Service.html
 * 
 *     Tasks ensure that any changes to public properties, change notifications for errors or cancellation, event handlers, and states occur on the JavaFX Application thread,
 *         so is a better alternative to using a basic Java Thread: https://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
 *     The Service class is used for executing/reusing tasks. You can run tasks without Service, however, if you don't need to reuse it.
 *
 * If you implement time-consuming processes in a Task or thread, you may need to implement locks on resources shared with the application thread (i.e. Timeline KeyFrame and drag Event handlers).
 * You can check whether code is running on the JavaFX application thread by running the helper method printThreadingNotes in this class.
 * 
 * NOTE: http://tutorials.jenkov.com/javafx/concurrency.html and https://www.developer.com/design/multithreading-in-javafx/#:~:text=JavaFX%20has%20a%20unique%20set,in%20the%20JavaFX%20Application%20Thread.
 * 
 * If you need to delay some code but it is not long-running, consider using Platform.runLater https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Platform.html#runLater(java.lang.Runnable)
 *     This is run on the JavaFX application thread when it has enough time.
 */
public class LoopManiaWorldController {

    /**
     * squares gridpane includes path images, enemies, character, empty grass, buildings
     */
    @FXML
    private GridPane squares;

    /**
     * cards gridpane includes cards and the ground underneath the cards
     */
    @FXML
    private GridPane cards;

    /**
     * anchorPaneRoot is the "background". It is useful since anchorPaneRoot stretches over the entire game world,
     * so we can detect dragging of cards/items over this and accordingly update DragIcon coordinates
     */
    @FXML
    private AnchorPane anchorPaneRoot;

    @FXML
    private GridPane shopPanel;

    @FXML
    private GridPane logPanel;

    @FXML
    private Button buySword;

    public Button getBuySword() {
        return buySword;
    }

    @FXML
    private Button buyStake;

    @FXML
    private Button buyStaff;

    @FXML
    private Button buyHelmet;

    @FXML
    private Button buyChest;

    @FXML
    private Button buyShield;

    @FXML
    private Button buyPotion;

    @FXML
    private Button sellDoggieCoin;

    @FXML
    private Button returnButton;

    /**
     * equippedItems gridpane is for equipped items (e.g. swords, shield, axe)
     */
    @FXML
    private GridPane equippedItems;

    @FXML
    private GridPane unequippedInventory;

    @FXML
    private Label currentGold;

    @FXML
    private Label currentHealth;

    @FXML
    private Label currentDamage;

    @FXML
    private Label currentArmour;

    @FXML
    private Label currentPotions;

    @FXML
    private Label currentSoldiers;

    @FXML
    private Label currentDoggieCoin;

    @FXML
    private Label currentDoggiePrice;

    @FXML
    private Label currentLoop;

    @FXML
    private Label currentGoals;

    @FXML
    private Label currentXP;

    @FXML
    private Label currentLevel;

    @FXML
    private Label gameMode;

    @FXML
    private Button upHealth;

    @FXML
    private Button upDamage;

    @FXML
    private Rectangle currentExperience;

    @FXML
    private Rectangle experienceToLevel;

    @FXML
    private Label logHeader;

    @FXML
    private VBox battleLog;

    @FXML
    private ScrollPane battleScroll;

    // all image views including tiles, character, enemies, cards... even though cards in separate gridpane...
    private List<ImageView> entityImages;

    /**
     * when we drag a card/item, the picture for whatever we're dragging is set here and we actually drag this node
     */
    private DragIcon draggedEntity;

    private static boolean isPaused;
    private LoopManiaWorld world;

    /**
     * runs the periodic game logic - second-by-second moving of character through maze, as well as enemies, and running of battles
     */
    private static Timeline timeline;


    private Image vampireCastleCardImage;
    private Image villageCardImage;
    private Image zombiePitCardImage;
    private Image barracksCardImage;
    private Image trapCardImage;
    private Image towerCardImage;
    private Image campfireCardImage;


    private Image basicEnemyImage;
    private Image zombieImage;
    private Image vampireImage;
    private Image doggieImage;
    private Image elanImage;

    private Image swordImage;
    private Image stakeImage;
    private Image staffImage;
    private Image andurilImage;

    private Image shieldImage;
    private Image helmetImage;
    private Image chestImage;
    private Image treeStumpImage;

    private Image ringImage;

    private Image vampireCastleImage;
    private Image villageImage;
    private Image zombiePitImage;
    private Image barracksImage;
    private Image trapImage;
    private Image towerImage;
    private Image campfireImage;

    private Image potionImage;
    private Image doggieCoinImage;

    private int armourBuyable = 1;

    /**
     * the image currently being dragged, if there is one, otherwise null.
     * Holding the ImageView being dragged allows us to spawn it again in the drop location if appropriate.
     */
    // TODO = it would be a good idea for you to instead replace this with the building/item which should be dropped
    private ImageView currentlyDraggedImage;
    
    /**
     * null if nothing being dragged, or the type of item being dragged
     */
    private DRAGGABLE_TYPE currentlyDraggedType;

    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped over its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged over the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragOver;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped in the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged into the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragEntered;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged outside of the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragExited;

    /**
     * object handling switching to the main menu
     */
    private static MenuSwitcher mainMenuSwitcher;

    /**
     * object handling switching to the Game Won
     */
    private static MenuSwitcher gameWonSwitcher;

    /**
     * object handling switching to the Game Lost
     */
    private static MenuSwitcher gameLostSwitcher;

    /**
     * @param world world object loaded from file
     * @param initialEntities the initial JavaFX nodes (ImageViews) which should be loaded into the GUI
     */
    public LoopManiaWorldController(LoopManiaWorld world, List<ImageView> initialEntities) {
        this.world = world;
        entityImages = new ArrayList<>(initialEntities);

        // card images
        vampireCastleCardImage = new Image((new File("src/images/vampire_castle_card.png")).toURI().toString());
        villageCardImage = new Image((new File("src/images/village_card.png")).toURI().toString());
        zombiePitCardImage = new Image((new File("src/images/zombie_pit_card.png")).toURI().toString());
        barracksCardImage = new Image((new File("src/images/barracks_card.png")).toURI().toString());
        trapCardImage = new Image((new File("src/images/trap_card.png")).toURI().toString());
        towerCardImage = new Image((new File("src/images/tower_card.png")).toURI().toString());
        campfireCardImage = new Image((new File("src/images/campfire_card.png")).toURI().toString());

        // enemy images
        basicEnemyImage = new Image((new File("src/images/slug.png")).toURI().toString());
        zombieImage = new Image((new File("src/images/zombie.png")).toURI().toString());
        vampireImage = new Image((new File("src/images/vampire.png")).toURI().toString());
        doggieImage = new Image((new File("src/images/doggie.png")).toURI().toString());
        elanImage = new Image((new File("src/images/ElanMuske.png")).toURI().toString());

        // weapon images
        swordImage = new Image((new File("src/images/basic_sword.png")).toURI().toString());
        stakeImage = new Image((new File("src/images/stake.png")).toURI().toString());
        staffImage = new Image((new File("src/images/staff.png")).toURI().toString());
        andurilImage = new Image((new File("src/images/anduril_flame_of_the_west.png")).toURI().toString());

        // armour images
        shieldImage  = new Image((new File("src/images/shield.png")).toURI().toString());
        chestImage  = new Image((new File("src/images/armour.png")).toURI().toString());
        helmetImage  = new Image((new File("src/images/helmet.png")).toURI().toString());
        treeStumpImage = new Image((new File("src/images/tree_stump.png")).toURI().toString());

        ringImage = new Image((new File("src/images/the_one_ring.png")).toURI().toString());

        potionImage = new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString());
        doggieCoinImage = new Image((new File("src/images/doggiecoin.png")).toURI().toString());



        // building images
        vampireCastleImage = new Image((new File("src/images/vampire_castle_building_purple_background.png")).toURI().toString());
        villageImage = new Image((new File("src/images/village.png")).toURI().toString());
        zombiePitImage = new Image((new File("src/images/zombie_pit.png")).toURI().toString());
        barracksImage = new Image((new File("src/images/barracks.png")).toURI().toString());
        trapImage = new Image((new File("src/images/trap.png")).toURI().toString());
        towerImage = new Image((new File("src/images/tower.png")).toURI().toString());
        campfireImage = new Image((new File("src/images/campfire.png")).toURI().toString());
        

        currentlyDraggedImage = null;
        currentlyDraggedType = null;

        // initialize them all...
        gridPaneSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragOver = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragEntered = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragExited = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
    }

    @FXML
    public void initialize() {
        // TODO = load more images/entities during initialization
        
        Image pathTilesImage = new Image((new File("src/images/32x32GrassAndDirtPath.png")).toURI().toString());
        Image inventorySlotImage = new Image((new File("src/images/empty_slot.png")).toURI().toString());
        Rectangle2D imagePart = new Rectangle2D(0, 0, 32, 32);


        currentGold.setText(String.valueOf(LoopManiaWorld.getGold()));
        currentHealth.setText(String.valueOf(world.getHealth()));
        currentDamage.setText(String.valueOf(world.getDamage()));
        currentArmour.setText(String.valueOf(world.getArmour()));

        if(world.getNumUpgrades() > 0 ) {
            upHealth.setStyle(null);
            upDamage.setStyle(null);
            upHealth.setText("Up Health!");
            upDamage.setText("Up Damage!");
        } else {
            upHealth.setStyle("-fx-background-color: #ffffff");
            upHealth.setText("No Upgrades");
            upDamage.setStyle("-fx-background-color: #ffffff");
            upDamage.setText("");
        }
        upHealth.setOnAction(value -> {
            upgradeMaxHealth();
        });
        upDamage.setOnAction(value -> {
            upgradeBaseDamage();
        });

        // set up shop
        buySword = new Button();
        buySword.setId("buySword");
        buySword.setGraphic(null);
        buySword.setMinWidth(80);
        buySword.setMinHeight(50);

        buyStaff = new Button();
        buyStaff.setId("buyStaff");
        buyStaff.setGraphic(null);
        buyStaff.setMinWidth(80);
        buyStaff.setMinHeight(50);

        buyStake = new Button();
        buyStake.setId("buyStake");
        buyStake.setGraphic(null);
        buyStake.setMinWidth(80);
        buyStake.setMinHeight(50);

        buyHelmet = new Button();
        buyHelmet.setId("buyHelmet");
        buyHelmet.setGraphic(null);
        buyHelmet.setMinWidth(80);
        buyHelmet.setMinHeight(50);

        buyChest = new Button();
        buyChest.setId("buyChest");
        buyChest.setGraphic(null);
        buyChest.setMinWidth(80);
        buyChest.setMinHeight(50);

        buyShield = new Button();
        buyShield.setId("buyShield");
        buyShield.setGraphic(null);
        buyShield.setMinWidth(80);
        buyShield.setMinHeight(50);

        buyPotion = new Button();
        buyPotion.setId("buyPotion");
        buyPotion.setGraphic(null);
        buyPotion.setMinWidth(80);
        buyPotion.setMinHeight(50);

        sellDoggieCoin = new Button();
        sellDoggieCoin.setId("sellDoggieCoin");
        sellDoggieCoin.setGraphic(null);
        sellDoggieCoin.setMinWidth(80);
        sellDoggieCoin.setMinHeight(50);

        returnButton = new Button();
        returnButton.setId("returnButton");
        returnButton.setGraphic(null);
        returnButton.setMinWidth(80);
        returnButton.setMinHeight(50);

        shopPanel.add(buySword, 0, 1);
        shopPanel.add(buyStaff, 1, 1);
        shopPanel.add(buyStake, 2, 1);
        shopPanel.add(buyHelmet, 0, 2);
        shopPanel.add(buyChest, 1, 2);
        shopPanel.add(buyShield, 2, 2);
        shopPanel.add(buyPotion, 0, 4);
        shopPanel.add(sellDoggieCoin, 1, 4);
        shopPanel.add(returnButton, 2, 4);

    
        buySword.setStyle("-fx-background-color: #ffffff");
        buyStaff.setStyle("-fx-background-color: #ffffff");
        buyStake.setStyle("-fx-background-color: #ffffff");
        buyHelmet.setStyle("-fx-background-color: #ffffff");
        buyChest.setStyle("-fx-background-color: #ffffff");
        buyShield.setStyle("-fx-background-color: #ffffff");
        buyPotion.setStyle("-fx-background-color: #ffffff");
        sellDoggieCoin.setStyle("-fx-background-color: #ffffff");
        returnButton.setStyle("-fx-background-color: #ffffff");

        currentDoggiePrice.setText("Price:\t" + String.valueOf(Math.round(world.getDoggieCoinPrice())));
        currentDoggieCoin.setText("Owned:\t" + String.valueOf(Math.round(world.getDoggieCoin())));
        currentLoop.setText("Lap Number: " + String.valueOf(world.getcurrentLoop()));
        currentXP.setText("Experience: " + String.valueOf(world.getTotalExperience()));
        currentGoals.setText("Goal: Achieve " + String.valueOf(world.getVictoryConditions()));
        currentLevel.setText("Level: " + String.valueOf(world.getLevel()) + "\t");
        logHeader.setText("Battle Log- \n");
        gameMode.setText("Game Mode: " + LoopManiaWorld.getGameMode());

        // Add the ground first so it is below all other entities (inculding all the twists and turns)
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                ImageView groundView = new ImageView(pathTilesImage);
                groundView.setViewport(imagePart);
                squares.add(groundView, x, y);
            }
        }

        // load entities loaded from the file in the loader into the squares gridpane
        for (ImageView entity : entityImages){
            squares.getChildren().add(entity);
        }
        
        // add the ground underneath the cards
        for (int x=0; x<world.getWidth(); x++){
            ImageView groundView = new ImageView(pathTilesImage);
            groundView.setViewport(imagePart);
            cards.add(groundView, x, 0);
        }

        // add the empty slot images for the unequipped inventory
        for (int x=0; x<LoopManiaWorld.unequippedInventoryWidth; x++){
            for (int y=0; y<LoopManiaWorld.unequippedInventoryHeight; y++){
                ImageView emptySlotView = new ImageView(inventorySlotImage);
                unequippedInventory.add(emptySlotView, x, y);
            }
        }

        // create the draggable icon
        draggedEntity = new DragIcon();
        draggedEntity.setVisible(false);
        draggedEntity.setOpacity(0.7);
        anchorPaneRoot.getChildren().add(draggedEntity);

        //Give one of every item and card on load
        if (LoopManiaWorld.getGameMode() == "Testing") {
            giveOneofEverything();
        }
    }

    /**
     * Give one of each item and card
     */
    private void giveOneofEverything() {
        loadBarracksCard();
        loadCampfireCard();
        loadTowerCard();
        loadVillageCard();
        loadTrapCard();
        loadZombieCard();
        loadVampireCard();

        loadSword();
        loadStaff();
        loadStake();

        loadShield();
        loadHelmet();
        loadChest();

        loadRing();
        loadAnduril();
        loadTreeStump();

        world.addPotion();
    }

    /**
     * create and run the timer
     */
    public void startTimer(){
        // TODO = handle more aspects of the behaviour required by the specification
        System.out.println("starting timer");
        isPaused = false;
        // trigger adding code to process main game logic to queue. JavaFX will target framerate of 0.3 seconds
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), event -> {
            world.runTickMoves();
            List<BasicEnemy> defeatedEnemies = LoopManiaWorld.findBattle();
            for (BasicEnemy e: defeatedEnemies){
                reactToEnemyDefeat(e);
            }
            List<BasicEnemy> newEnemies = world.possiblySpawnEnemies();
            for (BasicEnemy newEnemy: newEnemies){
                onLoad(newEnemy);
            }

            currentGold.setText(String.valueOf(LoopManiaWorld.getGold()));
            currentHealth.setText(String.valueOf(world.getHealth()));
            currentDamage.setText(String.valueOf(world.getDamage()));
            currentArmour.setText(String.valueOf(world.getArmour()));
            currentPotions.setText(String.valueOf(LoopManiaWorld.getPotions()));
            currentSoldiers.setText(String.valueOf(world.getSoldiers()));
            currentDoggiePrice.setText("Price:\t" + String.valueOf(Math.round(world.getDoggieCoinPrice())));
            currentDoggieCoin.setText("Owned:\t" + String.valueOf(Math.round(world.getDoggieCoin())));

            if(world.getNumUpgrades() > 0 ) {
                upHealth.setStyle(null);
                upDamage.setStyle(null);
                upHealth.setText("Up Health!");
                upDamage.setText("Up Damage!");
            } else {
                upHealth.setStyle("-fx-background-color: #ffffff");
                upHealth.setText("No Upgrades");
                upDamage.setStyle("-fx-background-color: #ffffff");
                upDamage.setText("");
            }

            checkShop();

            currentLoop.setText("Lap Number: " + String.valueOf(world.getcurrentLoop()));
            currentXP.setText("Experience: " + String.valueOf(world.getTotalExperience()));
            currentGoals.setText("Goal: Achieve " + String.valueOf(world.getVictoryConditions()));
            currentLevel.setText("Level: " + String.valueOf(world.getLevel()) + "\t");
            currentExperience.setWidth((double) world.getCurrentExp()/world.getExperienceToLevel() * experienceToLevel.getWidth());
            gameMode.setText("Game Mode: " + LoopManiaWorld.getGameMode());

            // create scroll bar for the battle log
            logHeader.setText("Battle Log-");

            battleLog.getChildren().clear();
            for(String log : world.getBattleLogs()) {
                battleLog.getChildren().add(new Label(log));
            }

            battleScroll.setMinHeight(250);
            battleScroll.setMaxHeight(250);


            printThreadingNotes("HANDLED TIMER");
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Check if the Character is on the Hero's Castle and has Opened the Shop
     * Close the shop if they hit resume from the shop
     */
    public void checkShop() {
        if(world.getShopOpen()) {
            // set the display
            buySword.setGraphic(new ImageView(swordImage));
            buySword.setStyle(null);
            buySword.setText(String.valueOf(Sword.getPrice()));
            buySword.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    purchaseSword();
                }
            });

            buyStaff.setGraphic(new ImageView(staffImage));
            buyStaff.setStyle(null);
            buyStaff.setText(String.valueOf(Staff.getPrice()));
            buyStaff.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    purchaseStaff();
                }
            });

            buyStake.setGraphic(new ImageView(stakeImage));
            buyStake.setStyle(null);
            buyStake.setText(String.valueOf(Stake.getPrice()));
            buyStake.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    purchaseStake();
                }
            });

            buyHelmet.setGraphic(new ImageView(helmetImage));
            buyHelmet.setStyle(null);
            buyHelmet.setText(String.valueOf(Helmet.getPrice()));
            buyHelmet.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    purchaseHelmet();
                }
            });

            buyChest.setGraphic(new ImageView(chestImage));
            buyChest.setStyle(null);
            buyChest.setText(String.valueOf(Chest.getPrice()));
            buyChest.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    purchaseChest();
                }
            });

            buyShield.setGraphic(new ImageView(shieldImage));
            buyShield.setStyle(null);
            buyShield.setText(String.valueOf(Shield.getPrice()));
            buyShield.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    purchaseShield();
                }
            });

            buyPotion.setGraphic(new ImageView(potionImage));
            buyPotion.setStyle(null);
            buyPotion.setText(String.valueOf(Potion.getPrice()));
            buyPotion.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    purchasePotion();
                }
            });

            sellDoggieCoin.setGraphic(new ImageView(doggieCoinImage));
            sellDoggieCoin.setStyle(null);
            sellDoggieCoin.setText("Sell");
            sellDoggieCoin.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    sellDoggie();
                }
            });

            returnButton.setText("Resume");
            returnButton.setStyle(null);
            returnButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    closeShop();
                }
            });

            pause();
        } else {    
            buySword.setGraphic(null);
            buySword.setStyle("-fx-background-color: #ffffff");
            buySword.setText("");
            buySword.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    doNothing();
                }
            });

            buyStaff.setGraphic(null);
            buyStaff.setStyle("-fx-background-color: #ffffff");
            buyStaff.setText("");
            buyStaff.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    doNothing();
                }
            });

            buyStake.setGraphic(null);
            buyStake.setStyle("-fx-background-color: #ffffff");
            buyStake.setText("");
            buyStake.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    doNothing();
                }
            });

            blankArmours();

            buyPotion.setGraphic(null);
            buyPotion.setStyle("-fx-background-color: #ffffff");
            buyPotion.setText("");
            buyPotion.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    doNothing();
                }
            });

            sellDoggieCoin.setGraphic(null);
            sellDoggieCoin.setStyle("-fx-background-color: #ffffff");
            sellDoggieCoin.setText("");
            sellDoggieCoin.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    doNothing();
                }
            });

            returnButton.setGraphic(null);
            returnButton.setStyle("-fx-background-color: #ffffff");
            returnButton.setText("");
            returnButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    doNothing();
                }
            });
        }
    }


    /**
     * Set the buttons to do nothing
     */
    public void doNothing() {

    }
 
    /**
     * Purchase a Sword if the character has enough money
     */
    public void purchaseSword() {
        if (LoopManiaWorld.getGold() >= Sword.getPrice()) {
            LoopManiaWorld.addGold(-Sword.getPrice());
            loadSword();
            currentGold.setText(String.valueOf(LoopManiaWorld.getGold()));
        }
    }

    /**
     * Purchase a Stake if the character has enough money
     */
    public void purchaseStake() {
        if (LoopManiaWorld.getGold() >= Stake.getPrice()) {
            LoopManiaWorld.addGold(-Stake.getPrice());
            loadStake();
            currentGold.setText(String.valueOf(LoopManiaWorld.getGold()));
        }
    }

    /**
     * Purchase a Staff if the character has enough money
     */
    public void purchaseStaff() {
        if (LoopManiaWorld.getGold() >= Staff.getPrice()) {
            LoopManiaWorld.addGold(-Staff.getPrice());
            loadStaff();
            currentGold.setText(String.valueOf(LoopManiaWorld.getGold()));
        }
    }

    /**
     * Purchase a Helmet if the character has enough money
     * If beserker mode is active, only let them buy 1 armour piece
     */
    public void purchaseHelmet() {
        if (LoopManiaWorld.getGold() >= Helmet.getPrice()) {
            LoopManiaWorld.addGold(-Helmet.getPrice());
            loadHelmet();
            currentGold.setText(String.valueOf(LoopManiaWorld.getGold()));
            berserkerShop();
        }
    }

    /**
     * Purchase a Chest if the character has enough money
     * If beserker mode is active, only let them buy 1 armour piece
     */
    public void purchaseChest() {
        if (LoopManiaWorld.getGold() >= Chest.getPrice()) {
            LoopManiaWorld.addGold(-Chest.getPrice());
            loadChest();
            currentGold.setText(String.valueOf(LoopManiaWorld.getGold()));
            berserkerShop();
        }
    }

    /**
     * Purchase a Shield if the character has enough money
     * If beserker mode is active, only let them buy 1 armour piece
     */
    public void purchaseShield() {
        if (LoopManiaWorld.getGold() >= Shield.getPrice()) {
            LoopManiaWorld.addGold(-Shield.getPrice());
            loadShield();
            currentGold.setText(String.valueOf(LoopManiaWorld.getGold()));
            berserkerShop();
        }
    }

    /**
     * Purchase a Potion if the character has enough money
     * If Survival mode is active, only let them buy 1 armour piece
     */
    public void purchasePotion() {
        if (LoopManiaWorld.getGold() >= Potion.getPrice()) {
            LoopManiaWorld.addGold(-Potion.getPrice());
            world.addPotion();
            currentGold.setText(String.valueOf(LoopManiaWorld.getGold()));
            currentPotions.setText(String.valueOf(LoopManiaWorld.getPotions()));
            if (LoopManiaWorld.getGameMode() == "Survival") {
                buyPotion.setGraphic(null);
                buyPotion.setStyle("-fx-background-color: #ffffff");
                buyPotion.setText("");
            }
        }
    }

    /**
     * Sell a Doggie Coin
     */
    public void sellDoggie() {
        if (world.getDoggieCoin() > 0) {
            LoopManiaWorld.addGold((int) world.getDoggieCoinPrice());
            world.addDoggieCoins(-1);
            currentGold.setText(String.valueOf(LoopManiaWorld.getGold()));
        }
    }

    /**
     * Check if berserker Mode is active and if character has bought an armour yet.
     */
    public void berserkerShop() {
        if (LoopManiaWorld.getGameMode() == "Berserker" && armourBuyable == 1) {
            armourBuyable = 0;
            blankArmours();
        }
    }

    /**
     * Blank the Armour buttons in the shop
     */
    public void blankArmours() {
        buyHelmet.setGraphic(null);
        buyHelmet.setStyle("-fx-background-color: #ffffff");
        buyHelmet.setText("");
        buyHelmet.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                doNothing();
            }
        });
    
        buyChest.setGraphic(null);
        buyChest.setStyle("-fx-background-color: #ffffff");
        buyChest.setText("");
        buyChest.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                doNothing();
            }
        });
    
        buyShield.setGraphic(null);
        buyShield.setStyle("-fx-background-color: #ffffff");
        buyShield.setText("");
        buyShield.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                doNothing();
            }
        });
    }

    /**
     * pause the execution of the game loop
     * the human player can still drag and drop items during the game pause
     */
    public static void pause(){
        isPaused = true;
        System.out.println("pausing");
        timeline.stop();
    }

    public void terminate(){
        pause();
    }

    /**
     * pair the entity an view so that the view copies the movements of the entity.
     * add view to list of entity images
     * @param entity backend entity to be paired with view
     * @param view frontend imageview to be paired with backend entity
     */
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entityImages.add(view);
    }

    /**
     * load a vampire card from the world, and pair it with an image in the GUI
     */
    private void loadVampireCard() {
        // TODO = load more types of card
        VampireCastleCard vampireCastleCard = world.loadVampireCard();
        onLoad(vampireCastleCard);
    }

    /**
     * load a barracks card from the world, and pair it with an image in the GUI
     */
    private void loadBarracksCard() {
        BarracksCard barracksCard = world.loadBarracksCard();
        onLoad(barracksCard);
    }

    /**
     * load a village card from the world, and pair it with an image in the GUI
     */
    private void loadVillageCard() {
        VillageCard villageCard = world.loadVillageCard();
        onLoad(villageCard);
    }

    /**
     * load a zombie card from the world, and pair it with an image in the GUI
     */
    private void loadZombieCard() {
        ZombiePitCard zombiePit = world.loadZombieCard();
        onLoad(zombiePit);
    }

    /**
     * load a tower card from the world, and pair it with an image in the GUI
     */
    private void loadTowerCard() {
        TowerCard tower = world.loadTowerCard();
        onLoad(tower);
    }

    /**
     * load a campfire card from the world, and pair it with an image in the GUI
     */
    private void loadCampfireCard() {
        CampfireCard campfire = world.loadCampfireCard();
        onLoad(campfire);
    }

    /**
     * load a trap card from the world, and pair it with an image in the GUI
     */
    private void loadTrapCard() {
        TrapCard trap = world.loadTrapCard();
        onLoad(trap);
    }

    /**
     * load a sword from the world, and pair it with an image in the GUI
     */
    private void loadSword(){
        // start by getting first available coordinates
        Sword sword = world.addUnequippedSword();
        onLoad(sword);
    }

    /**
     * load a staff from the world, and pair it with an image in the GUI
     */
    private void loadStaff() {
        Staff staff = world.addUnequippedStaff();
        onLoad(staff);
    }

    /**
     * load a stake from the world, and pair it with an image in the GUI
     */
    private void loadStake() {
        Stake stake = world.addUnequippedStake();
        onLoad(stake);
    }

    /**
     * load an anduril from the world, and pair it with an image in the GUI
     */
    private void loadAnduril() {
        Anduril anduril = world.addUnequippedAnduril();
        onLoad(anduril);
    }

    /**
     * load a shield from the world, and pair it with an image in the GUI
     */
    private void loadShield(){
        // start by getting first available coordinates
        Shield shield = world.addUnequippedShield();
        onLoad(shield);
    }

    /**
     * load a shield from the world, and pair it with an image in the GUI
     */
    private void loadTreeStump(){
        // start by getting first available coordinates
        TreeStump treeStump = world.addUnequippedTreeStump();
        onLoad(treeStump);
    }



    /**
     * load a helmet from the world, and pair it with an image in the GUI
     */
    private void loadHelmet(){
        // start by getting first available coordinates
        Helmet helmet = world.addUnequippedHelmet();
        onLoad(helmet);
    }

    /**
     * load a chest from the world, and pair it with an image in the GUI
     */
    private void loadChest(){
        // start by getting first available coordinates
        Chest chest = world.addUnequippedChest();
        onLoad(chest);
    }

    /**
     * load a ring from the world, and pair it with an image in the GUI
     */
    private void loadRing(){
        OneRing ring = world.addUnequippedRing();
        onLoad(ring);
    }


    /**
     * run GUI events after an enemy is defeated, such as spawning items/experience/gold
     * @param enemy defeated enemy for which we should react to the death of
     */
    private void reactToEnemyDefeat(BasicEnemy enemy){
        // react to character defeating an enemy
        // in starter code, spawning extra card/weapon...

        // for basic slug
        Random rand = new Random();
        int outOf100 = rand.nextInt(100);
        if(enemy instanceof BasicEnemySlug) {
            world.gainExp(1);
            world.addTotalExperience(1);
            // 5% chance of a rare, Slugs are the only source of the rare Tree Stump
            if(outOf100 < 1) {
                loadTreeStump();
            } else if (outOf100 < 10) {
                // 10% chance of a basic sword
                loadSword();
            } else if (outOf100 < 20) {
                // 10% chance of 10 gold
                loadZombieCard();
            } else if (outOf100 < 40) {
                // 20% chance of a support building
                giveRandomCard();
            } else {
                // 60% chance of 10 gold
                LoopManiaWorld.addGold(10);
            }
        // for basic Zombie
        } else if (enemy instanceof BasicEnemyZombie) {
            world.gainExp(5);
            world.addTotalExperience(5);
            // 1% chance of a rare, zombies are the only source of the One Ring
            if(outOf100 < 1) {
                loadRing();
            } else if (outOf100 < 10) {
                // 10% chance of a random item
                giveRandomItem();
            } else if (outOf100 < 20) {
                // 10% chance of random support card
                giveRandomCard();
            } else if (outOf100 < 30) {
                loadZombieCard();
            } else if (outOf100 < 40) {
                loadVampireCard();
            } else if (outOf100 < 95) {
                // 50% chance of 30 gold
                LoopManiaWorld.addGold(30);
            } else {
                // 5% chance of item card and gold
                giveRandomItem();
                giveRandomCard();
                LoopManiaWorld.addGold(30);
            }
        // for basic Vampire
        } else if (enemy instanceof BasicEnemyVampire) {
            world.gainExp(20);
            world.addTotalExperience(20);
            // 5% chance of a rare, Vampires are the only source of the Anduril
            if(outOf100 < 1) {
                loadAnduril();
            } else if (outOf100 < 35) {
                // 30% chance of a random item
                giveRandomItem();
            } else if (outOf100 < 50) {
                // 15% chance of random support card
                giveRandomCard();
            } else if (outOf100 < 65) {
                // 15% chance of vampire card
                loadVampireCard();
            } else if (outOf100 < 95) {
                // 30% chance of 100 gold
                LoopManiaWorld.addGold(100);
            } else {
                // 5% chance of item card and gold
                giveRandomItem();
                giveRandomCard();
                LoopManiaWorld.addGold(100);
            }
        } else if (enemy instanceof BasicEnemyDoggie) {
            world.gainExp(1000);
            world.addTotalExperience(1000);
            world.addDoggieCoins(1);
        } else if (enemy instanceof BasicEnemyElanMuske) {
            world.gainExp(10000);
            world.addTotalExperience(10000);
            world.setDoggieCoinPrice(10.0);
        }
    }

    /**
     * Give a random card
     */
    private void giveRandomCard() {
        Random rand = new Random();
        int randomCard = rand.nextInt(100);
        if (randomCard < 20){
            loadBarracksCard();
        } else if (randomCard < 40) {
            loadTowerCard();
        } else if (randomCard < 60) {
            loadVillageCard();
        } else if (randomCard < 80) {
            loadTrapCard();
        } else if (randomCard < 100) {
            loadCampfireCard();
        } 
    }

    /**
     * Give a random non-rare item
     */
    private void giveRandomItem() {
        Random rand = new Random();
        int randomItem = rand.nextInt(6);
        if (randomItem < 1){
            loadSword();
        } else if (randomItem < 2) {
            loadStake();
        } else if (randomItem < 3) {
            loadStaff();
        } else if (randomItem < 4) {
            loadChest();
        } else if (randomItem < 5) {
            loadHelmet();
        } else if (randomItem < 6) {
            loadShield();
        } else if (randomItem < 7) {
            world.addPotion();
        } 
    }

    /**
     * load a card into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the cards GridPane.
     * @param card
     */
    private void onLoad(Card card) {
        ImageView view;
        if(card instanceof VampireCastleCard) {
            view = new ImageView(vampireCastleCardImage);
        } else if (card instanceof VillageCard) {
            view = new ImageView(villageCardImage);
        } else if (card instanceof ZombiePitCard) {
            view = new ImageView(zombiePitCardImage);
        } else if (card instanceof BarracksCard) {
            view = new ImageView(barracksCardImage);
        } else if (card instanceof TrapCard) {
            view = new ImageView(trapCardImage);
        } else if (card instanceof TowerCard) {
            view = new ImageView(towerCardImage);
        } else {
            view = new ImageView(campfireCardImage);
        }

        // FROM https://stackoverflow.com/questions/41088095/javafx-drag-and-drop-to-gridpane
        // note target setOnDragOver and setOnDragEntered defined in initialize method
        addDragEventHandlers(view, DRAGGABLE_TYPE.CARD, cards, squares);

        addEntity(card, view);
        cards.getChildren().add(view);
    }

    /**
     * load a weapon into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the unequippedInventory GridPane.
     * @param weapon
     */
    private void onLoad(Weapon weapon) {
        ImageView view;
        if(weapon instanceof Sword) {
            view = new ImageView(swordImage);
        } else if (weapon instanceof Staff) {
            view = new ImageView(staffImage);
        } else if (weapon instanceof Stake) {
            view = new ImageView(stakeImage);
        } else {
            view = new ImageView(andurilImage);
        }
        view.setId("weapon");
        addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedItems);
        addEntity(weapon, view);
        unequippedInventory.getChildren().add(view);
    }

    /**
     * load a shield into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the unequippedInventory GridPane.
     * @param shield
     */
    private void onLoad(Shield shield) {
        ImageView view;
        if (shield instanceof TreeStump) {
            view = new ImageView(treeStumpImage);
        } else {
            view = new ImageView(shieldImage);
        }
        view.setId("shield");
        addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedItems);
        addEntity(shield, view);
        unequippedInventory.getChildren().add(view);
    }

    /**
     * load a chest into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the unequippedInventory GridPane.
     * @param chest
     */
    private void onLoad(Chest chest) {
        ImageView view = new ImageView(chestImage);
        view.setId("chest");
        addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedItems);
        addEntity(chest, view);
        unequippedInventory.getChildren().add(view);
    }

    /**
     * load a helmet into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the unequippedInventory GridPane.
     * @param helmet
     */
    private void onLoad(Helmet helmet) {
        ImageView view = new ImageView(helmetImage);
        view.setId("helmet");
        addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedItems);
        addEntity(helmet, view);
        unequippedInventory.getChildren().add(view);
    }

    /**
     * load a ring into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the unequippedInventory GridPane.
     * @param ring
     */
    private void onLoad(OneRing ring) {
        ImageView view = new ImageView(ringImage);
        view.setId("ring");
        addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedItems);
        addEntity(ring, view);
        unequippedInventory.getChildren().add(view);
    }

    /**
     * load an enemy into the GUI
     * @param enemy
     */
    private void onLoad(BasicEnemy enemy) {
        ImageView view = new ImageView(basicEnemyImage);

        if(enemy instanceof BasicEnemySlug) {
            view = new ImageView(basicEnemyImage);
        } else if(enemy instanceof BasicEnemyZombie) {
            view = new ImageView(zombieImage);
        } else if (enemy instanceof BasicEnemyVampire) {
            view = new ImageView(vampireImage);
        } else if (enemy instanceof BasicEnemyDoggie) {
            view = new ImageView(doggieImage);
        } else if (enemy instanceof BasicEnemyElanMuske) {
            view = new ImageView(elanImage);
        }

        addEntity(enemy, view);
        squares.getChildren().add(view);
    }

    /**
     * load a building into the GUI
     * @param building
     */
    private void onLoad(Building building){
        // this will need to change for the specific types of buildings
        ImageView view;
        if(building instanceof VampireCastleBuilding) {
            view = new ImageView(vampireCastleImage);
        } else if (building instanceof VillageBuilding) {
            view = new ImageView(villageImage);
        } else if (building instanceof ZombiePitBuilding) {
            view = new ImageView(zombiePitImage);
        } else if (building instanceof BarracksBuilding) {
            view = new ImageView(barracksImage);
        } else if (building instanceof TrapBuilding) {
            view = new ImageView(trapImage);
        } else if (building instanceof TowerBuilding) {
            view = new ImageView(towerImage);
        } else {
            view = new ImageView(campfireImage);
        }

        addEntity(building, view);
        squares.getChildren().add(view);
    }

    /**
     * add drag event handlers for dropping into gridpanes, dragging over the background, dropping over the background.
     * These are not attached to invidual items such as swords/cards.
     * @param draggableType the type being dragged - card or item
     * @param sourceGridPane the gridpane being dragged from
     * @param targetGridPane the gridpane the human player should be dragging to (but we of course cannot guarantee they will do so)
     */
    private void buildNonEntityDragHandlers(DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane){
        // TODO = be more selective about where something can be dropped
        // for example, in the specification, villages can only be dropped on path, whilst vampire castles cannot go on the path

        gridPaneSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // TODO = for being more selective about where something can be dropped, consider applying additional if-statement logic
                /*
                 *you might want to design the application so dropping at an invalid location drops at the most recent valid location hovered over,
                 * or simply allow the card/item to return to its slot (the latter is easier, as you won't have to store the last valid drop location!)
                 */
                if (currentlyDraggedType == draggableType){
                    // problem = event is drop completed is false when should be true...
                    // https://bugs.openjdk.java.net/browse/JDK-8117019
                    // putting drop completed at start not making complete on VLAB...

                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();

                    if(node != targetGridPane && db.hasImage()){

                        Integer cIndex = GridPane.getColumnIndex(node);
                        Integer rIndex = GridPane.getRowIndex(node);
                        int x = cIndex == null ? 0 : cIndex;
                        int y = rIndex == null ? 0 : rIndex;
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        ImageView image = new ImageView(db.getImage());

                        int nodeX = GridPane.getColumnIndex(currentlyDraggedImage);
                        int nodeY = GridPane.getRowIndex(currentlyDraggedImage);
                        switch (draggableType){
                            case CARD:
                                removeDraggableDragEventHandlers(draggableType, targetGridPane);
                                // TODO = spawn a building here of different types

                                // get the type of building to be placed
                                Card card = world.getCardbyCoordinates(nodeX, nodeY);

                                Building newBuilding = convertCardToBuildingByCoordinates(nodeX, nodeY, x, y);
                                if(newBuilding == null) {
                                    // Error in placement, load the card back
                                    onLoad(card);
                                } else {
                                    // success in placing, add the card
                                    onLoad(newBuilding);
                                }
                                break;
                            case ITEM:
                                removeDraggableDragEventHandlers(draggableType, targetGridPane);
                               
                                // trying to make it so that items can only go into slots they are designed for
                                if(node.getId().equals("swordCell") && currentlyDraggedImage.getId().equals("weapon")) {
                                    Entity oldItem = world.equipItemByCoordinates(nodeX, nodeY);
                                    image.setId("swordCell");
                                    targetGridPane.add(image, x, y, 1, 1);
                                    removeItemByCoordinates(nodeX, nodeY);
                                    if(oldItem instanceof Sword) {
                                        loadSword();
                                    } else if (oldItem instanceof Staff) {
                                        loadStaff();
                                    } else if(oldItem instanceof Stake) {
                                        loadStake();
                                    } else if(oldItem instanceof Anduril) {
                                        loadAnduril();
                                    }


                                } else if (node.getId().equals("helmetCell") && (currentlyDraggedImage.getId().equals("helmet"))) {
                                    // Equip a helmet
                                    Entity oldItem = world.equipItemByCoordinates(nodeX, nodeY);
                                    image.setId("helmetCell");
                                    targetGridPane.add(image, x, y, 1, 1);
                                    removeItemByCoordinates(nodeX, nodeY);
                                    if(oldItem instanceof Helmet) {
                                        loadHelmet();
                                    }
                                } else if (node.getId().equals("chestCell") && (currentlyDraggedImage.getId().equals("chest"))) {
                                    // Equip a chest
                                    Entity oldItem = world.equipItemByCoordinates(nodeX, nodeY);
                                    image.setId("chestCell");
                                    targetGridPane.add(image, x, y, 1, 1);
                                    if(oldItem instanceof Chest) {
                                        loadChest();
                                    }
                                } else if (node.getId().equals("shieldCell") && (currentlyDraggedImage.getId().equals("shield"))) {
                                    // TODO: Equip a shield
                                    Entity oldItem = world.equipItemByCoordinates(nodeX, nodeY);
                                    image.setId("shieldCell");
                                    targetGridPane.add(image, x, y, 1, 1);
                                    removeItemByCoordinates(nodeX, nodeY);
                                    if (oldItem instanceof TreeStump) {
                                        loadTreeStump();
                                    } else if(oldItem instanceof Shield) {
                                        loadShield();
                                    }
                                } else if (node.getId().equals("ringCell") && (currentlyDraggedImage.getId().equals("ring"))) {
                                    // Equip a ring
                                    Entity oldItem = world.equipItemByCoordinates(nodeX, nodeY);
                                    image.setId("shieldCell");
                                    targetGridPane.add(image, x, y, 1, 1);
                                    removeItemByCoordinates(nodeX, nodeY);
                                    if (oldItem instanceof OneRing) {
                                        loadRing();
                                    }
                                } else {
                                    Entity item = world.getUnequippedInventoryItemEntityByCoordinates(nodeX, nodeY);
                                    removeItemByCoordinates(nodeX, nodeY);

                                    // for future refactoring - could just call loadItem() and then change this logic elsewhere.
                                    if(item instanceof Sword) {
                                        loadSword();
                                    } else if (item instanceof Staff) {
                                        loadStaff();
                                    } else if (item instanceof Stake) {
                                        loadStake();
                                    } else if (item instanceof Anduril) {
                                        loadAnduril();
                                    } else if (item instanceof Chest) {
                                        loadChest();
                                    } else if (item instanceof Helmet) {
                                        loadHelmet();
                                    } else if (item instanceof Shield) {
                                        loadShield();
                                    } else if (item instanceof OneRing) {
                                        loadRing();
                                    }

                                    // Return the image to where it was dragged from
                                    
                                    // could do this to just make a copy of whatever is held and delete the one that is held... but feels dumb
                                    // Entity itemHeld = world.getUnequippedInventoryItemEntityByCoordinates(nodeX, nodeY);
                                    // if(itemHeld instanceof Sword) {

                                    // }
                                    // ...
                                    // removeItemByCoordinates(nodeX, nodeY);


                                    event.setDropCompleted(false);
                                    event.consume();
                                }    
                                break;
                            default:
                                break;
                        }
                        
                        draggedEntity.setVisible(false);
                        draggedEntity.setMouseTransparent(false);
                        // remove drag event handlers before setting currently dragged image to null
                        currentlyDraggedImage = null;
                        currentlyDraggedType = null;
                        printThreadingNotes("DRAG DROPPED ON GRIDPANE HANDLED");
                    }
                }

                event.setDropCompleted(true);
                // consuming prevents the propagation of the event to the anchorPaneRoot (as a sub-node of anchorPaneRoot, GridPane is prioritized)
                // https://openjfx.io/javadoc/11/javafx.base/javafx/event/Event.html#consume()
                // to understand this in full detail, ask your tutor or read https://docs.oracle.com/javase/8/javafx/events-tutorial/processing.htm
                event.consume();
            }
        });

        // this doesn't fire when we drag over GridPane because in the event handler for dragging over GridPanes, we consume the event
        anchorPaneRootSetOnDragOver.put(draggableType, new EventHandler<DragEvent>(){
            // https://github.com/joelgraff/java_fx_node_link_demo/blob/master/Draggable_Node/DraggableNodeDemo/src/application/RootLayout.java#L110
            @Override
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType){
                    if(event.getGestureSource() != anchorPaneRoot && event.getDragboard().hasImage()){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                }
                if (currentlyDraggedType != null){
                    draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                }
                event.consume();
            }
        });

        // this doesn't fire when we drop over GridPane because in the event handler for dropping over GridPanes, we consume the event
        anchorPaneRootSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType){
                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();
                    if(node != anchorPaneRoot && db.hasImage()){
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        currentlyDraggedImage.setVisible(true);
                        draggedEntity.setVisible(false);
                        draggedEntity.setMouseTransparent(false);
                        // remove drag event handlers before setting currently dragged image to null
                        removeDraggableDragEventHandlers(draggableType, targetGridPane);
                        
                        currentlyDraggedImage = null;
                        currentlyDraggedType = null;
                    }
                }
                //let the source know whether the image was successfully transferred and used
                event.setDropCompleted(true);
                event.consume();
            }
        });
    }

    /**
     * remove the card from the world, and spawn and return a building instead where the card was dropped
     * @param cardNodeX the x coordinate of the card which was dragged, from 0 to width-1
     * @param cardNodeY the y coordinate of the card which was dragged (in starter code this is 0 as only 1 row of cards)
     * @param buildingNodeX the x coordinate of the drop location for the card, where the building will spawn, from 0 to width-1
     * @param buildingNodeY the y coordinate of the drop location for the card, where the building will spawn, from 0 to height-1
     * @return building entity returned from the world
     */
    private Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        return world.convertCardToBuildingByCoordinates(cardNodeX, cardNodeY, buildingNodeX, buildingNodeY);
    }

    /**
     * remove an item from the unequipped inventory by its x and y coordinates in the unequipped inventory gridpane
     * @param nodeX x coordinate from 0 to unequippedInventoryWidth-1
     * @param nodeY y coordinate from 0 to unequippedInventoryHeight-1
     */
    private void removeItemByCoordinates(int nodeX, int nodeY) {
        world.removeUnequippedInventoryItemByCoordinates(nodeX, nodeY);
    }

    /**
     * add drag event handlers to an ImageView
     * @param view the view to attach drag event handlers to
     * @param draggableType the type of item being dragged - card or item
     * @param sourceGridPane the relevant gridpane from which the entity would be dragged
     * @param targetGridPane the relevant gridpane to which the entity would be dragged to
     */
    private void addDragEventHandlers(ImageView view, DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane){
        //System.out.print(view.getId());
        view.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                currentlyDraggedImage = view; // set image currently being dragged, so squares setOnDragEntered can detect it...
                currentlyDraggedType = draggableType;
                //Drag was detected, start drap-and-drop gesture
                //Allow any transfer node
                Dragboard db = view.startDragAndDrop(TransferMode.MOVE);
    
                //Put ImageView on dragboard
                ClipboardContent cbContent = new ClipboardContent();
                cbContent.putImage(view.getImage());
                db.setContent(cbContent);
                view.setVisible(false);


                buildNonEntityDragHandlers(draggableType, sourceGridPane, targetGridPane);

                draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                switch (draggableType){
                    case CARD:
                        draggedEntity.setImage(view.getImage());
                        break;
                    case ITEM:
                        draggedEntity.setImage(view.getImage());
                        break;
                    default:
                        break;
                }
                
                draggedEntity.setVisible(true);
                draggedEntity.setMouseTransparent(true);
                draggedEntity.toFront();

                // IMPORTANT!!!
                // to be able to remove event handlers, need to use addEventHandler
                // https://stackoverflow.com/a/67283792
                targetGridPane.addEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

                for (Node n: targetGridPane.getChildren()){
                    // events for entering and exiting are attached to squares children because that impacts opacity change
                    // these do not affect visibility of original image...
                    // https://stackoverflow.com/questions/41088095/javafx-drag-and-drop-to-gridpane
                    gridPaneNodeSetOnDragEntered.put(draggableType, new EventHandler<DragEvent>() {
                        // TODO = be more selective about whether highlighting changes - if it cannot be dropped in the location, the location shouldn't be highlighted!
                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType){
                            //The drag-and-drop gesture entered the target
                            //show the user that it is an actual gesture target
                                if(event.getGestureSource() != n && event.getDragboard().hasImage()){
                                    n.setOpacity(0.7);
                                }
                            }
                            event.consume();
                        }
                    });
                    gridPaneNodeSetOnDragExited.put(draggableType, new EventHandler<DragEvent>() {
                        // TODO = since being more selective about whether highlighting changes, you could program the game so if the new highlight location is invalid the highlighting doesn't change, or leave this as-is
                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType){
                                n.setOpacity(1);
                            }
                
                            event.consume();
                        }
                    });
                    n.addEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
                    n.addEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
                }
                event.consume();
            }
            
        });
    }

    /**
     * remove drag event handlers so that we don't process redundant events
     * this is particularly important for slower machines such as over VLAB.
     * @param draggableType either cards, or items in unequipped inventory
     * @param targetGridPane the gridpane to remove the drag event handlers from
     */
    private void removeDraggableDragEventHandlers(DRAGGABLE_TYPE draggableType, GridPane targetGridPane){
        // remove event handlers from nodes in children squares, from anchorPaneRoot, and squares
        targetGridPane.removeEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));

        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

        for (Node n: targetGridPane.getChildren()){
            n.removeEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
            n.removeEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
        }
    }

    /**
     * handle the pressing of keyboard keys.
     * Specifically, we should pause when pressing SPACE
     * @param event some keyboard key press
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        // TODO = handle additional key presses, e.g. for consuming a health potion
        switch (event.getCode()) {
        case SPACE:
            if (isPaused){
                startTimer();
            }
            else{
                pause();
            }
            break;
        case P:
            world.drinkPotion();
            break;
        case F:
            timeline.setRate(4);
            // release is handled elsewhere
            break;
        default:
            break;
        }
    }
    

    /**
     * handle the releasing of keyboard keys.
     * @param event some keyboard key press
     */
    @FXML
    public void handleKeyRelease(KeyEvent event) {
        // TODO = handle additional key presses, e.g. for consuming a health potion
        switch (event.getCode()) {
        case F:
            timeline.setRate(1);
            // release is handled elsewhere
            break;
        default:
            break;
        }
    }

    /**
     * handles the upgrade armour button
     * @param event some keyboard key press
     */
    public void upgradeMaxHealth() {
        world.upgradeMaxHealth();
    }

    /**
     * handles the upgrade damage button
     * @param event some keyboard key press
     */
    public void upgradeBaseDamage() {
        world.upgradeBaseDamage();
    }

    /**
     * Switch the window to the main menu
     * @param MenuSwitcher mainMenuSwitcher - menu to be switched to
     */
    public void setMainMenuSwitcher(MenuSwitcher mainMenuSwitcher){
        // TODO = possibly set other menu switchers
        LoopManiaWorldController.mainMenuSwitcher = mainMenuSwitcher;
    }

    /**
     * Switch the window to the game Won Screen
     * @param MenuSwitcher mainMenuSwitcher - menu to be switched to
     */
    public void setGameWonSwitcher(MenuSwitcher gameWonSwitcher){
        LoopManiaWorldController.gameWonSwitcher = gameWonSwitcher;
    }

    /**
     * Switch the window to the game Lost Screen
     * @param MenuSwitcher mainMenuSwitcher - menu to be switched to
     */
    public void setGameLostSwitcher(MenuSwitcher gameLostSwitcher){
        LoopManiaWorldController.gameLostSwitcher = gameLostSwitcher;
    }

    /**
     * this method is triggered when click button to go to main menu in FXML
     * @throws IOException
     */
    @FXML
    private void switchToMainMenu() throws IOException {
        // TODO = possibly set other menu switchers
        pause();
        mainMenuSwitcher.switchMenu();
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the world.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * 
     * note that this is put in the controller rather than the loader because we need to track positions of spawned entities such as enemy
     * or items which might need to be removed should be tracked here
     * 
     * NOTE teardown functions setup here also remove nodes from their GridPane. So it is vital this is handled in this Controller class
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        // TODO = tweak this slightly to remove items from the equipped inventory?
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());

        ChangeListener<Number> xListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        };
        ChangeListener<Number> yListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        };

        // if need to remove items from the equipped inventory, add code to remove from equipped inventory gridpane in the .onDetach part
        ListenerHandle handleX = ListenerHandles.createFor(entity.x(), node)
                                               .onAttach((o, l) -> o.addListener(xListener))
                                               .onDetach((o, l) -> {
                                                    o.removeListener(xListener);
                                                    entityImages.remove(node);
                                                    squares.getChildren().remove(node);
                                                    cards.getChildren().remove(node);
                                                    equippedItems.getChildren().remove(node);
                                                    unequippedInventory.getChildren().remove(node);
                                                })
                                               .buildAttached();
        ListenerHandle handleY = ListenerHandles.createFor(entity.y(), node)
                                               .onAttach((o, l) -> o.addListener(yListener))
                                               .onDetach((o, l) -> {
                                                   o.removeListener(yListener);
                                                   entityImages.remove(node);
                                                   squares.getChildren().remove(node);
                                                   cards.getChildren().remove(node);
                                                   equippedItems.getChildren().remove(node);
                                                   unequippedInventory.getChildren().remove(node);
                                                })
                                               .buildAttached();
        handleX.attach();
        handleY.attach();

        // this means that if we change boolean property in an entity tracked from here, position will stop being tracked
        // this wont work on character/path entities loaded from loader classes
        entity.shouldExist().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> obervable, Boolean oldValue, Boolean newValue) {
                handleX.detach();
                handleY.detach();
            }
        });
    }

    /**
     * we added this method to help with debugging so you could check your code is running on the application thread.
     * By running everything on the application thread, you will not need to worry about implementing locks, which is outside the scope of the course.
     * Always writing code running on the application thread will make the project easier, as long as you are not running time-consuming tasks.
     * We recommend only running code on the application thread, by using Timelines when you want to run multiple processes at once.
     * EventHandlers will run on the application thread.
     */
    private void printThreadingNotes(String currentMethodLabel){
        // System.out.println("\n###########################################");
        // System.out.println("current method = "+currentMethodLabel);
        // System.out.println("In application thread? = "+Platform.isFxApplicationThread());
        // System.out.println("Current system time = "+java.time.LocalDateTime.now().toString().replace('T', ' '));
    }

    /**
     * Open the ShopOpen in LoopManiaWorld to false
     * reset the amount of armour you can buy
     */
    public void closeShop() {
        world.setShopOpen(false);
        armourBuyable = 1;
        startTimer();
    }

    /**
     * Open the Game Won Menu
     */
    public static void openGameWon() {
        pause();
        gameWonSwitcher.switchMenu();
    }

    /**
     * Open the Game Lost Menu
     */
    public static void openGameLost() {
        pause();
        gameLostSwitcher.switchMenu();
    }
}

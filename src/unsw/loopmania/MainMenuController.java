package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * controller for the main menu.
 * TODO = you could extend this, for example with a settings menu, or a menu to load particular maps.
 */
public class MainMenuController {
    /**
     * facilitates switching to main game
     */
    private MenuSwitcher gameSwitcher;

    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToStandardGame() throws IOException {
        gameSwitcher.switchMenu();
        LoopManiaWorld.setGameMode("Standard");
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToBerserkerGame() throws IOException {
        gameSwitcher.switchMenu();
        LoopManiaWorld.setGameMode("Berserker");
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToSurvivalGame() throws IOException {
        gameSwitcher.switchMenu();
        LoopManiaWorld.setGameMode("Survival");
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToConfusingGame() throws IOException {
        gameSwitcher.switchMenu();
        LoopManiaWorld.setGameMode("Confusing");
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToResumeGame() throws IOException {
        gameSwitcher.switchMenu();
    }
}

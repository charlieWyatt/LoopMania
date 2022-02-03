package unsw.loopmania;

public class cState implements GoalState {

    @Override
    public void next(Goal goal) {
        System.out.println("You have Won");
        LoopManiaWorld.winGame();
    }

    @Override
    public void prev(Goal goal) {
        goal.setState(new bState());
    }

    @Override
    public void printStatus() {
        System.out.println("Third Stage of the Goal");   
    }
}

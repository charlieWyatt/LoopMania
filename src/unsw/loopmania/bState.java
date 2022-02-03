package unsw.loopmania;

public class bState implements GoalState {
    
    @Override
    public void next(Goal goal) {
        goal.setState(new cState());
    }

    @Override
    public void prev(Goal goal) {
        goal.setState(new aState());
    }

    @Override
    public void printStatus() {
        System.out.println("Second Stage of the Goal");
    }
}
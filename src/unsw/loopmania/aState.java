package unsw.loopmania;

public class aState implements GoalState {

    @Override
    public void next(Goal goal) {
        goal.setState(new bState());
    }

    @Override
    public void prev(Goal goal) {
        //Earliest state, nothing happens
    }

    @Override
    public void printStatus() {
        System.out.println("First stage of the goal");
    }
}

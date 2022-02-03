package unsw.loopmania;

public class Goal {

    //State based stratergy to complete the goal

    private GoalState state = (GoalState) new aState();

    // getter, setter

    public void previousState() {
        state.prev(this);
    }

    public void nextState() {
        state.next(this);
    }

    public void printStatus() {
        state.printStatus();
    }

    public void setState(GoalState goalState) {
        this.state = goalState;
    }

    public GoalState getState() {
        return this.state;
    }

}
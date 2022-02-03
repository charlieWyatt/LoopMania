package unsw.loopmania;

public interface GoalState {

    void next(Goal goal);
    void prev(Goal goal);
    void printStatus();
}

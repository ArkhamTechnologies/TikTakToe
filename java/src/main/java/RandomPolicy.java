import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomPolicy {

    private final static Random random = new Random();

    private final Set<Integer> actionsTaken = new HashSet<>();

    public RandomPolicy() {

    }

    public int getAction() {
        int newAction = getRandomAction();

        while(actionsTaken.contains(newAction)) {
            newAction =  getRandomAction();
        }

        addAction(newAction);
        return newAction;
    }

    private int getRandomAction() {
        return random.nextInt(Constants.N_FIELDS * Constants.N_FIELDS);
    }

    public void addAction(int a) {
        actionsTaken.add(a);
    }
}

package creatures;

import edu.princeton.cs.algs4.StdRandom;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.Map;
import java.util.Deque;
import java.util.ArrayDeque;

public class Clorus extends Creature {

    /**
     * red color
     */
    private final int r = 34;
    /**
     * green color
     */
    private final int g = 0;
    /**
     * blue color
     */
    private final int b = 231;

    /**
     * creates plip with energy equal to E.
     * Cloruses have no restrictions on their maximum energy.
     */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
//    public Clorus() {
//        this(1);
//    }

    /**
     * Cloruses should lose 0.03 units of energy
     * on a MOVE action.
     */
    public void move() {
        energy -= 0.03;
    }

    /**
     * Cloruses should lose 0.03 units of energy
     * on a MOVE action.
     */
    public void stay() {
        energy -= 0.01;
    }

    /**
     * The color() method for Cloruses should always
     * return the color red = 34, green = 0, blue = 231.
     */
    public Color color() {
        return color(r, g, b);
    }

    /**
     * If a Clorus attacks another creature, it should gain that creature’s
     * energy. This should happen in the attack() method, not in chooseAction().
     * You do not need to worry about making sure the attacked creature dies—
     * the simulator does that for you.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Like a Plip, when a Clorus replicates, it keeps 50% of its energy.
     * The other 50% goes to its offspring.
     * No energy is lost in the replication process.
     */
    public Clorus replicate() {
        energy = energy * 0.5;
        double babyEnergy = energy;
        return new Clorus(babyEnergy);
    }

    private static Direction randomEntry(Deque<Direction> creatureDir) {
        int index = StdRandom.uniform(creatureDir.size());
        Direction[] arrForm = new Direction[creatureDir.size()];
        arrForm = creatureDir.toArray(arrForm);
        Direction rdmElement = arrForm[index];
        return rdmElement;
    }

    /**
     * Cloruses should obey exactly the following behavioral rules:
     * 1. If there are no empty squares, the Clorus will STAY (even if
     * there are Plips nearby they could attack since plip squares do
     * not count as empty squares).
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of
     * them randomly.
     * 3. Otherwise, if the Clorus has energy greater than or equal to one,
     * it will REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        //Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipLocation = new ArrayDeque<>();
        boolean anyPlip = false;
        for(Direction key : neighbors.keySet()) {
            Occupant value = neighbors.get(key);
            if (value.name().equals("empty")) {
                emptyNeighbors.add(key);
            } else if (value.name().equals("plip")) {
                plipLocation.add(key);
                anyPlip = true;
            }
        }
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        // Rule 2
        if (anyPlip) {
            Direction targetPos = randomEntry(plipLocation);
            return new Action(Action.ActionType.ATTACK, targetPos);
        }
        // Rule 3
        if (energy >= 1) {
            Direction targetPos = randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, targetPos);
        }
        // Rule 4
        Direction targetPos = randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, targetPos);
    }
}


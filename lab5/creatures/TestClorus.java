package creatures;
import huglife.Direction;
import huglife.Impassible;
import huglife.Occupant;
import huglife.Action;
import huglife.Empty;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Deque;
import java.util.HashMap;
import java.awt.Color;

/** Tests the plip class
 *  @authr Yunhao Ji
 */
public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0 ,231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus expected = new Clorus(1);
        Clorus actual = c.replicate();
        assertEquals(expected.energy(), actual.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // Plip does not count as an empty square
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> noEmpty = new HashMap<Direction, Occupant>();
        noEmpty.put(Direction.TOP, new Impassible());
        noEmpty.put(Direction.BOTTOM, new Plip());
        noEmpty.put(Direction.LEFT, new Impassible());
        noEmpty.put(Direction.RIGHT, new Plip());

        actual = c.chooseAction(noEmpty);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // attack a plip
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> existPlip = new HashMap<Direction, Occupant>();
        existPlip.put(Direction.TOP, new Plip());
        existPlip.put(Direction.BOTTOM, new Impassible());
        existPlip.put(Direction.LEFT, new Empty());
        existPlip.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(existPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);

        // attack a randomly chosen plip
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> existTwoPlips = new HashMap<Direction, Occupant>();
        existTwoPlips.put(Direction.TOP, new Plip());
        existTwoPlips.put(Direction.BOTTOM, new Impassible());
        existTwoPlips.put(Direction.LEFT, new Empty());
        existTwoPlips.put(Direction.RIGHT, new Plip());

        actual = c.chooseAction(existTwoPlips);
        expected = new Action(Action.ActionType.STAY);

        assertNotEquals(expected, actual);

        // energy >= 1, replicate to the top empty square
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> replicateTop = new HashMap<Direction, Occupant>();
        replicateTop.put(Direction.TOP, new Empty());
        replicateTop.put(Direction.BOTTOM, new Impassible());
        replicateTop.put(Direction.LEFT, new Impassible());
        replicateTop.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(replicateTop);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);

        // energy >= 1, replicate to a random empty square
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> replicateRandomly = new HashMap<Direction, Occupant>();
        replicateRandomly.put(Direction.TOP, new Empty());
        replicateRandomly.put(Direction.BOTTOM, new Impassible());
        replicateRandomly.put(Direction.LEFT, new Impassible());
        replicateRandomly.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(replicateRandomly);
        expected = new Action(Action.ActionType.STAY);

        assertNotEquals(expected, actual);

        // energy < 1, move to TOP
        c = new Clorus(0.2);
        HashMap<Direction, Occupant> moveWithEmpty = new HashMap<Direction, Occupant>();
        moveWithEmpty.put(Direction.TOP, new Empty());
        moveWithEmpty.put(Direction.BOTTOM, new Impassible());
        moveWithEmpty.put(Direction.LEFT, new Impassible());
        moveWithEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(moveWithEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);

        // energy < 1, move randomly
        c = new Clorus(0.2);
        HashMap<Direction, Occupant> moveRandomly = new HashMap<Direction, Occupant>();
        moveRandomly.put(Direction.TOP, new Empty());
        moveRandomly.put(Direction.BOTTOM, new Impassible());
        moveRandomly.put(Direction.LEFT, new Impassible());
        moveRandomly.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(moveRandomly);
        expected = new Action(Action.ActionType.STAY);

        assertNotEquals(expected, actual);
    }

}

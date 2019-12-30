import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> stud = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> std = new ArrayDequeSolution<>();
        String msg = "";

        for (int i = 0; i < 1000; i++) {
            int flag = StdRandom.uniform(0, 4);
            Integer toAdd = Integer.valueOf(StdRandom.uniform(100));

            if (flag == 0) {
                msg += "addFirst(" + i + ")\n";
                stud.addFirst(toAdd);
                std.addFirst(toAdd);
            } else if (flag == 1) {
                msg += "addLast(" + i + ")\n";
                stud.addLast(toAdd);
                std.addLast(toAdd);
            } else if (flag == 2 && !stud.isEmpty()) {
                assertEquals(msg + "removeFirst()", stud.isEmpty(), std.isEmpty());
                Integer expected = std.removeFirst();
                Integer actual = stud.removeFirst();
                assertEquals(msg + "removeFirst()", expected, actual);
                msg += "removeFirst()\n";
            } else {
                if (!stud.isEmpty()) {
                    assertEquals(msg + "removeLast()", stud.isEmpty(), std.isEmpty());
                    Integer expected = std.removeLast();
                    Integer actual = stud.removeLast();
                    assertEquals(msg + "removeLast()", expected, actual);
                    msg += "removeLast()\n";
                }
            }
        }
    }
}

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
//               System.out.println("addFirst(" + toAdd + ")");
                stud.addFirst(toAdd);
                std.addFirst(toAdd);
            } else if (flag == 1) {
                msg += "addLast(" + i + ")\n";
//               System.out.println("addLast(" + toAdd + ")");
                stud.addLast(toAdd);
                std.addLast(toAdd);
            } else if (flag == 2 && !stud.isEmpty()) {
//               System.out.println("removeFirst()");
                assertEquals(msg + "removeFirst()", stud.isEmpty(), std.isEmpty());
                Integer expected = std.removeFirst();
                Integer actual = stud.removeFirst();
                assertEquals(msg + "removeFirst()", expected, actual);
//               System.out.println("removeFirst()");
                msg += "removeFirst()\n";
            } else {
                if (!stud.isEmpty()) {
                    assertEquals(msg + "removeLast()", stud.isEmpty(), std.isEmpty());
                    Integer expected = std.removeLast();
                    Integer actual = stud.removeLast();
                    assertEquals(msg + "removeLast()", expected, actual);
//                   System.out.println("removeLast()");
                    msg += "removeLast()\n";
                }
            }
        }
    }
}

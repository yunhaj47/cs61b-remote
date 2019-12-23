import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testFlik() {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            assertTrue(true == Flik.isSameNumber(i, j));
        }
    }
}

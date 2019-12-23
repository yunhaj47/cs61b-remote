/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        // Integer类仅当 -127到128时用等号进行比较不会出问题
//        return a == b;
        return a.equals(b);
    }
}

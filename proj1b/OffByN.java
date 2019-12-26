public class OffByN implements CharacterComparator {
    /** A class for off-by-N comparators. */
    /** Single argument consturctor which takes in an integer. */
    private int offset;
    public OffByN(int N) {
        offset = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == offset || diff == -offset;
    }
}

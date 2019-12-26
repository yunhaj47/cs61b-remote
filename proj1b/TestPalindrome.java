import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String s1 = "aacbb";
        assertFalse(palindrome.isPalindrome(s1));
        String s2 = "abcba";
        assertTrue(palindrome.isPalindrome(s2));
        String s3 = "a";
        assertTrue(palindrome.isPalindrome(s3));
        String s4 = " ";
        assertTrue(palindrome.isPalindrome(s4));
        String s5 = "ab";
        assertFalse(palindrome.isPalindrome(s5));
        String s6 = "abba";
        assertTrue(palindrome.isPalindrome(s6));
        String s7 = "Abba";
        assertFalse(palindrome.isPalindrome(s7));
        String s8 = "";
        assertTrue(palindrome.isPalindrome(s8));

    }

    @Test
    public void testIsPalindromeOffByOne() {
        CharacterComparator offbyOne = new OffByOne();
        String s1 = "%&";
        assertTrue(palindrome.isPalindrome(s1, offbyOne));
        String s2 = "% &";
        assertTrue(palindrome.isPalindrome(s2, offbyOne));
        String s3 = "&%";
        assertTrue(palindrome.isPalindrome(s3, offbyOne));
        String s4 = " ";
        assertTrue(palindrome.isPalindrome(s4, offbyOne));
        String s5 = "";
        assertTrue(palindrome.isPalindrome(s5, offbyOne));
        String s6 = "flake";
        assertTrue(palindrome.isPalindrome(s6, offbyOne));
        String s7 = "flke";
        assertTrue(palindrome.isPalindrome(s7, offbyOne));
        String s8 = "alke";
        assertFalse(palindrome.isPalindrome(s8, offbyOne));
    }

}

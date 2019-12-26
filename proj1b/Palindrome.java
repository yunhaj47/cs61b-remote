public class Palindrome {
    /** Given a String, wordToDeque should return a Deque
     * where the characters appear in the same order
     * as in the String. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> re = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            re.addLast(word.charAt(i));
        }
        return re;
    }
    /** Judge if a word is a Palindrome
     * using recursion. */
    public boolean isPalindrome(String word) {
        if (word.length() == 1 || word == null) {
            return true;
        }
        return isPalindromeHelper(wordToDeque(word));
    }

    private boolean isPalindromeHelper(Deque charq) {
        if (charq.size() <= 1) {
            return true;
        } else if (charq.removeFirst() == charq.removeLast()) {
            return isPalindromeHelper(charq);
        }
        return false;
    }

    /**  The method will return true if the word is a palindrome
     * according to the character comparison test
     * provided by the CharacterComparator passed in as argument cc. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 1 || word == null) {
            return true;
        }
        Deque charq = wordToDeque(word);
        for (int i = 0, j = word.length() - 1; i < j; i++, j--) {
            char first = (char) charq.removeFirst();
            char last = (char) charq.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }


}

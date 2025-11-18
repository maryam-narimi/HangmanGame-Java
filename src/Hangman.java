import java.util.*;

public class Hangman {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("computer", "programming", "java", "hangman", "university", "algorithm", "network");
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size())).toLowerCase();
        char[] board = new char[word.length()];
        Arrays.fill(board, '_');
        Set<Character> guessed = new HashSet<>();
        int attemptsLeft = 6;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        while (attemptsLeft > 0) {
            System.out.println();
            System.out.print("Word: ");
            for (char c : board) System.out.print(c + " ");
            System.out.println();
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Guessed letters: ");
            if (guessed.isEmpty()) System.out.print("none");
            else {
                List<Character> gl = new ArrayList<>(guessed);
                Collections.sort(gl);
                for (char c : gl) System.out.print(c + " ");
            }
            System.out.println();
            System.out.print("Enter a letter or guess the whole word: ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {
                System.out.println("Please enter something.");
                continue;
            }
            if (input.length() == 1) {
                char ch = input.charAt(0);
                if (!Character.isLetter(ch)) {
                    System.out.println("Please enter a letter (a-z).");
                    continue;
                }
                if (guessed.contains(ch)) {
                    System.out.println("You already guessed '" + ch + "'.");
                    continue;
                }
                guessed.add(ch);
                boolean found = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == ch) {
                        board[i] = ch;
                        found = true;
                    }
                }
                if (found) {
                    System.out.println("Good guess!");
                } else {
                    System.out.println("Wrong guess.");
                    attemptsLeft--;
                }
            } else {
                if (input.equals(word)) {
                    System.out.println("Congratulations! You guessed the word: " + word);
                    return;
                } else {
                    System.out.println("Wrong word guess.");
                    attemptsLeft--;
                }
            }
            boolean complete = true;
            for (char c : board) if (c == '_') { complete = false; break; }
            if (complete) {
                System.out.println();
                for (char c : board) System.out.print(c + " ");
                System.out.println();
                System.out.println("Well done! You found the word: " + word);
                return;
            }
        }
        System.out.println();
        System.out.println("Game over. The word was: " + word);
    }
}

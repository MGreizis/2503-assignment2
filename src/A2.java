import java.util.*;

public class A2 {
   private SLL<Token> wordList;
   private int totalStopWords = 0;
   private int totalWords = 0;

   public A2() {
      wordList = new SLL<>();
   }

   public static void main(String[] args) {
      A2 a2 = new A2();
      a2.processInput();
      a2.printStatistics();
   }

   /**
    * Process user input from the console.
    *
    */
   private void processInput() {
      Scanner scanner = new Scanner(System.in);
      while (scanner.hasNext()) {
         String word = scanner.next();
         processWord(word);
      }
      scanner.close();
   }

   /**
    * Process the given word by trimming, converting to lowercase, and removing
    * non-alphabetic characters.
    *
    * @param word the word to be processed
    */
   private void processWord(String word) {
      word = word.trim().toLowerCase();
      word = word.replaceAll("[^a-zA-Z]", "");

      if (word.isEmpty()) {
         return;
      }

      if (isStopWord(word)) {
         totalStopWords++;
      } else {
         Token token = new Token(word);
         int index = wordList.indexOf(token);

         if (index == -1) {
            wordList.addInOrder(token);
         } else {
            wordList.get(index).getData().incrementCount();
         }
      }
      totalWords++;
   }

   /**
    * Checks if the given word is a stop word.
    *
    * @param word the word to check
    * @return true if the word is a stop word, false otherwise
    */
   private boolean isStopWord(String word) {
      String[] stopWords = { "a", "about", "all", "am", "an", "and", "any", "are", "as", "at", "be", "been", "but",
            "by", "can", "cannot", "could", "did", "do", "does", "else", "for", "from", "get", "got", "had", "has",
            "have", "he", "her", "hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", "its", "like",
            "more", "me", "my", "no", "now", "not", "of", "on", "one", "or", "our", "out", "said", "say", "says",
            "she", "so", "some", "than", "that", "the", "their", "them", "then", "there", "these", "they", "this",
            "to", "too", "us", "upon", "was", "we", "were", "what", "with", "when", "where", "which", "while", "who",
            "whom", "why", "will", "you", "your" };

      for (String stopWord : stopWords) {
         if (stopWord.equals(word)) {
            return true;
         }
      }
      return false;
   }

   /**
    * Print statistics including total words, unique words, stop words,
    * most frequent words, least frequent words, and all words.
    */
   private void printStatistics() {
      wordList.sort();
      calculateTotalWords();

      System.out.println("Total Words: " + totalWords);
      System.out.println("Unique Words: " + wordList.size());
      System.out.println("Stop Words: " + totalStopWords);

      System.out.println("\n10 Most Frequent");
      printTopWords(10);

      System.out.println("\n10 Least Frequent");
      printBottomWords(10);

      System.out.println("\nAll");
      printAllWords();
   }

   /**
    * Calculate the total number of stop words in the word list.
    */
   private void calculateTotalWords() {
      Node<Token> current = wordList.getHead();
      while (current != null) {
         if (isStopWord(current.getData().getWord())) {
            totalStopWords += current.getData().getCount();
         }
         current = current.getNext();
      }
   }

   /**
    * A function to print the top N words in the word list along with their counts.
    *
    * @param n The number of top words to print
    */
   private void printTopWords(int n) {
      Node<Token> current = wordList.getHead();
      List<Token> topWordsList = new ArrayList<>();

      for (int i = 0; i < Math.min(n, wordList.size()); i++) {
         topWordsList.add(current.getData());
         current = current.getNext();
      }

      topWordsList.sort(Comparator.comparing(Token::getCount).reversed().thenComparing(Token::getWord));

      for (Token token : topWordsList) {
         System.out.println(token.getWord() + " : " + token.getCount());
      }
   }

   /**
    * Print the bottom n words along with their counts.
    *
    * @param n the number of bottom words to print
    * @return void
    */
   private void printBottomWords(int n) {
      Node<Token> current = wordList.getHead();
      List<Token> bottomWordsList = new ArrayList<>();

      int size = wordList.size();
      for (int i = 0; i < Math.min(n, size); i++) {
         bottomWordsList.add(current.getData());
         current = current.getNext();
      }

      bottomWordsList.sort(Comparator.comparing(Token::getCount).thenComparing(Token::getWord));

      for (Token token : bottomWordsList) {
         System.out.println(token.getWord() + " : " + token.getCount());
      }
   }

   /**
    * Print all words in the word list along with their counts.
    *
    * @param None
    * @return None
    */
   private void printAllWords() {
      Node<Token> current = wordList.getHead();
      while (current != null) {
         Token token = current.getData();
         System.out.println(token.getWord() + " : " + token.getCount());
         current = current.getNext();
      }
   }
}
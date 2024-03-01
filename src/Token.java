class Token implements Comparable<Token> {
   private String word;
   private int count;

   public Token(String word) {
      this.word = word;
      this.count = 1;
   }

   public String getWord() {
      return word;
   }

   public int getCount() {
      return count;
   }

   public void incrementCount() {
      count++;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null || getClass() != obj.getClass())
         return false;
      Token token = (Token) obj;
      return word.equals(token.word);
   }

   @Override
   public int compareTo(Token otherToken) {
      return this.word.compareTo(otherToken.word);
   }
}
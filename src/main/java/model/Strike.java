package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Strike implements Countable{
   private int firstScore;

   @Override
   public int countTotal() {
      return firstScore;
   }

   @Override
   public int getFirstScore() {
      return firstScore;
   }
}

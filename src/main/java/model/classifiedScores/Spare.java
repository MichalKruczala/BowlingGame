package model.classifiedScores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import model.Countable;

@AllArgsConstructor
@ToString
@Getter
public class Spare implements Countable {
    private int firstScore;
    private int secondScore;

    @Override
    public int countTotal() {
        return firstScore + secondScore;
    }

    @Override
    public int getFirstScore() {
        return firstScore;
    }

}

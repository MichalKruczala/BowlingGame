package model.classifiedScores;

import lombok.AllArgsConstructor;
import lombok.ToString;
import model.Countable;

@AllArgsConstructor
@ToString
public class Strike implements Countable {
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

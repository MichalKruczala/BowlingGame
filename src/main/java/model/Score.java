package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class Score implements Countable {
    private int firstScore;
    private int secondScore;
    private int thirdScore;
    private boolean extraScore;

    @Override
    public int countTotal() {
        return firstScore + secondScore;
    }

    public Score(int firstScore, int secondScore, boolean extraScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.extraScore = extraScore;

    }

    public boolean hasExtraScore() {
        return this.extraScore;


    }
}

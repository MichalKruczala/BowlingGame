package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class NonBonusScore implements Countable {
    private int firstScore;
    private int secondScore;
    private int thirdScore;


    public NonBonusScore(int firstScore, int secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    @Override
    public int countTotal() {
        return 0;
    }
}

 
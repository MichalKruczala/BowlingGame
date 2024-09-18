package model.classifiedScores;

import lombok.Getter;
import lombok.ToString;
import model.Countable;


@ToString
@Getter
public class NonBonusScore implements Countable {
    private final int firstScore;
    private final int secondScore;
    private int thirdScore;
    private final boolean extraRoll;


    public NonBonusScore(int firstScore, int secondScore, boolean extraRoll) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.extraRoll = extraRoll;
    }

    public NonBonusScore(int firstScore, int secondScore, int thirdScore, boolean extraRoll) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.thirdScore = thirdScore;
        this.extraRoll = extraRoll;
    }

    @Override
    public int countTotal() {
        return firstScore + secondScore;
    }

    @Override
    public int getFirstScore() {
        return firstScore;
    }

    public int countTotalWithExtraRoll(){
        return firstScore + secondScore + thirdScore;

    }
    public boolean hasExtraRoll(){
        return extraRoll;
    }
}


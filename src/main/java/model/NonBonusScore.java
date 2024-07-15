package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class NonBonusScore implements Countable {
    private int firstScore;
    private int secondScore;
    private int thirdScore;
    private boolean extraRoll;


    public NonBonusScore(int firstScore, int secondScore, boolean extraRoll) {
        this.firstScore = firstScore;
        this.seco
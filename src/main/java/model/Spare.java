package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Spare implements Countable {
    private int firstScore;
    private int secondScore;

    @Override
    public int countTotal() {
        return firstScore+ secondScore;
    }

    @Override
    public int getFirstScore() {
        return firstScore;
    }
}

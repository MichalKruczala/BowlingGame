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

    @Override
    public int countTotal() {
        return 0;
    }

    @Override
    public int getFirstScore() {
        return firstScore;
    }
}

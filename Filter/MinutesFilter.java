package Filter;

import Database.*;

public class MinutesFilter implements MovieFilter {
    private int minLength;
    private int maxLength;

    public MinutesFilter(int nminLength, int nmaxLength) {
        minLength = nminLength;
        maxLength = nmaxLength;
    }

    public boolean satisfy(String id) {
        int curLength = MovieDatabase.getMinutes(id);
        return curLength >= minLength && curLength <= maxLength;
    }
}

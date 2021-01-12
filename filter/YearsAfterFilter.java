package filter;

import database.*;

public class YearsAfterFilter implements MovieFilter{
    private int minYear;

    public YearsAfterFilter(int year) {
        minYear = year;
    }

    public boolean satisfy(String id) {
        return MovieDatabase.getYear(id) >= minYear;
    }
}

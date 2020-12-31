public class LengthFilter implements MovieFilterInterface{
    private int minLength;
    private int maxLength;

    public LengthFilter(int nminLength, int nmaxLength) {
        minLength = nminLength;
        maxLength = nmaxLength;
    }

    public boolean satisfy(Movie m) {
        return m.getMinutes() >= minLength && m.getMinutes() <= maxLength;
    }
}

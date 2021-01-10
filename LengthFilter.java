public class LengthFilter implements MovieFilterInterface{
    private int minLength;
    private int maxLength;

    public LengthFilter(int nminLength, int nmaxLength) {
        minLength = nminLength;
        maxLength = nmaxLength;
    }

    public boolean satisfy(String id) {
        int curLength = MovieDatabase.getMinutes(id);
        return curLength >= minLength && curLength <= maxLength;
    }
}

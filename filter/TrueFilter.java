package filter;

public class TrueFilter implements MovieFilter {
    public boolean satisfy(String id) {
        return true;
    }
}

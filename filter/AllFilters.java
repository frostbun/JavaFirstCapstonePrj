package filter;

import java.util.ArrayList;

public class AllFilters implements MovieFilter {
    private ArrayList<MovieFilter> all = new ArrayList<>();
    
    public void addFilter(MovieFilter f) {
        all.add(f);
    }

    public boolean satisfy(String id) {
        for(MovieFilter f: all) {
            if(!f.satisfy(id)) {
                return false;
            }
        }
        return true;
    }
}

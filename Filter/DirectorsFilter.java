package Filter;

import Database.*;

public class DirectorsFilter implements MovieFilter{
    private String[] director;

    public DirectorsFilter(String[] ndirector) {
        director = ndirector;
    }

    public boolean satisfy(String id) {
        String currDirector = MovieDatabase.getDirector(id);
        for(String eachDirector: director) {
            if(currDirector.contains(eachDirector)) {
                return true;
            }
        }
        return false;
    }
}

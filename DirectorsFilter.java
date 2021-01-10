public class DirectorsFilter implements MovieFilterInterface{
    private String[] director;

    public DirectorsFilter(String[] ndirector) {
        director = ndirector;
    }

    public boolean satisfy(String id) {
        String currDirector = MovieDatabase.getDirector(id);
        for(String eachDirector: director) {
            if(!currDirector.contains(eachDirector)) {
                return false;
            }
        }
        return true;
    }
}

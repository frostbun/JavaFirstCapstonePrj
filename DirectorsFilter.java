public class DirectorsFilter implements MovieFilter{
    private String[] director;

    public DirectorsFilter(String[] ndirector) {
        director = ndirector;
    }

    public boolean satisfy(Movie m) {
        String currDirector = m.getDirector();
        for(String eachDirector: director) {
            if(!currDirector.contains(eachDirector)) {
                return false;
            }
        }
        return true;
    }
}

import java.util.ArrayList;
import java.util.HashMap;

public class test {
 
    public ArrayList<Movie> filterBy(ArrayList<Movie> data, MovieFilter filter) {
        ArrayList<Movie> ret = new ArrayList<>();
        for(Movie m: data) {
            if(filter.satisfy(m)) {
                ret.add(m);
            }
        }
        return ret;
    }
    
    private HashMap<String, ArrayList<Movie>> parseDirectors(ArrayList<Movie> data) {
        HashMap<String, ArrayList<Movie>> ret = new HashMap<>();
        for(Movie m: data) {
            String[] directors = m.getDirector().split(", ");
            for(String currDirector: directors){
                ArrayList<Movie> movies = new ArrayList<>();
                if(ret.keySet().contains(currDirector)) {
                    movies = ret.get(currDirector);
                }
                movies.add(m);
                ret.put(currDirector, movies);
            }
        }
        return ret;
    }

    public static void main(String[] args){
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> data = fr.loadMovies("data/ratedmoviesfull.csv");
        // for(Movie m: data) {
        //     System.out.println(m);
        // }
        test _main = new test();
        HashMap<String, ArrayList<Movie>> directors = _main.parseDirectors(data);
        // for(String currDirector: directors.keySet()){
        //     System.out.println(currDirector + "\t" + directors.get(currDirector).size());
        // }
    }
}
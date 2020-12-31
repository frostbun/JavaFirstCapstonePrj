import java.util.ArrayList;
import java.util.HashMap;

public class test {
 
    private ArrayList<Movie> filterMovieBy(ArrayList<Movie> data, MovieFilterInterface filter) {
        ArrayList<Movie> ret = new ArrayList<>();
        for(Movie m: data) {
            if(filter.satisfy(m)) {
                ret.add(m);
            }
        }
        return ret;
    }
    
    private HashMap<String, ArrayList<Movie>> parseDataToDirectors(ArrayList<Movie> data) {
        HashMap<String, ArrayList<Movie>> ret = new HashMap<>();
        for(Movie m: data) {
            String[] directors = m.getDirector().split(", ");
            for(String curDirector: directors){
                ArrayList<Movie> movies = new ArrayList<>();
                if(ret.keySet().contains(curDirector)) {
                    movies = ret.get(curDirector);
                }
                movies.add(m);
                ret.put(curDirector, movies);
            }
        }
        return ret;
    }
    
    public static void main(String[] args){
        System.out.println("Testing");

        test _main = new test();
        FirstRatings fr = new FirstRatings();
        MovieRunnerAverage mra = new MovieRunnerAverage();

        mra.printAverageRatings(12);
        // mra.getAverageRatingOneMovie("Vacation");

    }
}
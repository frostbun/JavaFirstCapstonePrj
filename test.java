import java.util.ArrayList;
import java.util.HashMap;

public class test {
    
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

        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();

        mrwf.printAverageRatings();

    }
}
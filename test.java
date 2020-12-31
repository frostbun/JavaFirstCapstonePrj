import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class test {
 
    private ArrayList<Movie> filterMovieBy(ArrayList<Movie> data, MovieFilter filter) {
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

    private Rater getRaterById(ArrayList<Rater> data, String id) {
        for(Rater r: data) {
            if(r.getID().equals(id)) {
                return r;
            }
        }
        return null;
    }
    
    private int getRatingByMovie(ArrayList<Rater> data, String id) {
        int count = 0;
        for(Rater r: data) {
            if(r.hasRating(id)) {
                ++count;
            }
        }
        return count;
    }
    
    private int getMovieByRater(ArrayList<Rater> data) {
        HashSet<String> uniqueMovies = new HashSet<>();
        for(Rater r: data) {
            for(String movie: r.getItemsRated()) {
                uniqueMovies.add(movie);
            }
        }
        return uniqueMovies.size();
    }
    
    public static void main(String[] args){
        System.out.println("Testing");
        FirstRatings fr = new FirstRatings();
        test _main = new test();

        // ArrayList<Movie> data = fr.loadMovies("data/ratedmoviesfull.csv");
        // data = _main.filterMovieBy(data, new GenresFilter(new String[]{"Comedy", }));
        // data = _main.filterMovieBy(data, new LengthFilter(151, 9999999));
        // System.out.println(data.size());
        // for(Movie m: data) {
        //     System.out.println(m);
        // }

        // HashMap<String, ArrayList<Movie>> directors = _main.parseDataToDirectors(data);
        // String directorOfMaxMovie = "";
        // int maxMovie = 0;
        // for(String curDirector: directors.keySet()){
        //     // System.out.println(curDirector + "\t" + directors.get(curDirector).size());
        //     if(directors.get(curDirector).size() > maxMovie) {
        //         maxMovie = directors.get(curDirector).size();
        //         directorOfMaxMovie = curDirector;
        //     }
        // }
        // System.out.println(directorOfMaxMovie + "\t" + maxMovie);

        ArrayList<Rater> data = fr.loadRaters("data/ratings.csv");
        // String raterWithMaxRating = "";
        // int maxRating = 0;
        // for(Rater r: data) {
        //     // System.out.println(r.getID() + "\t" + r.numRatings());
        //     if(r.numRatings() > maxRating) {
        //         maxRating = r.numRatings();
        //         raterWithMaxRating = r.getID();
        //     }
        // }
        // System.out.println(raterWithMaxRating + "\t" + maxRating);

        // System.out.println(_main.getRaterById(data, "193").numRatings());
        // System.out.println(_main.getRatingByMovie(data, "1798709"));
        System.out.println(_main.getMovieByRater(data));
    }
}
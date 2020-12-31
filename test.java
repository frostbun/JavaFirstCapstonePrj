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
        FirstRatings fr = new FirstRatings();
        // ArrayList<Movie> data = fr.loadMovies("data/ratedmoviesfull.csv");
        // for(Movie m: data) {
        //     System.out.println(m);
        // }

        // test _main = new test();
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

        ArrayList<Rater> data = fr.loadRaters("data/ratings_short.csv");
        for(Rater r: data) {
            System.out.println(r.getID() + "\t" + r.getItemsRated());
        }
    }
}
import java.util.ArrayList;

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
    
    public static void main(String[] args){
        FirstRatings _main = new FirstRatings();
        ArrayList<Movie> arr = _main.loadMovies("data/ratedmovies_short.csv");
        for(Movie m: arr) {
            System.out.println(m);
        }
    }
}
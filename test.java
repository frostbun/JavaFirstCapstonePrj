import java.util.ArrayList;

public class test {
    public static void main(String[] args){
        FirstRatings _main = new FirstRatings();
        ArrayList<Movie> arr = _main.loadMovies("data/ratedmovies_short.csv");
        for(Movie m: arr) {
            System.out.println(m);
        }
    }
}
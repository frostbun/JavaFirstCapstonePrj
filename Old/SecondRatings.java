package Old;

import java.util.ArrayList;

import Assignment.FirstRatings;
import DataStructure.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings(String moviesFile, String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviesFile);
        myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public String getTitle(String id) {
        for(Movie m: myMovies) {
            if(m.getID().equals(id)) {
                return m.getTitle();
            }
        }
        return "Cant find a movie with that ID!";
    }

    public String getId(String title) {
        for(Movie m: myMovies) {
            if(m.getTitle().equals(title)) {
                return m.getID();
            }
        }
        return "Cant find a movie with that Title!";
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        double sumRating = 0;
        int countRaters = 0;
        for(Rater r: myRaters) {
            if(r.hasRating(id)) {
                ++countRaters;
                sumRating += r.getRating(id);
            }
        }
        if(countRaters >= minimalRaters) {
            return sumRating / countRaters;
        }
        return 0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ret = new ArrayList<>();
        for(Movie m: myMovies) {
            String id = m.getID();
            ret.add(new Rating(id, getAverageByID(id, minimalRaters)));
        }
        return ret;
    }
}

package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import DataStructure.*;

public class MovieRunnerAverage {

    public void getAverageRatingOneMovie(String title) {
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        // SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        // System.out.println(sr.getMovieSize());
        // System.out.println(sr.getRaterSize());

        ArrayList<Rating> averageRating = sr.getAverageRatings(2);
        String id = sr.getId(title);
        for(Rating curRating: averageRating) {
            if(curRating.getItem().equals(id)) {
                Double rank = curRating.getValue();
                System.out.printf("%.4f\t%s\n", rank, title);
            }
        }
    }

    public void printAverageRatings(int minimalRaters) {
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        // SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        // System.out.println(sr.getMovieSize());
        // System.out.println(sr.getRaterSize());

        ArrayList<Rating> averageRating = sr.getAverageRatings(minimalRaters);
        Collections.sort(averageRating);
        for(Rating curRating: averageRating) {
            double rank = curRating.getValue();
            String title = sr.getTitle(curRating.getItem());
            if(rank > 0) {
                System.out.printf("%.2f\t%s\n", rank, title);
            }
        }
    }
}

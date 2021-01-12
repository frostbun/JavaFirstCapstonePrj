package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import database.MovieDatabase;
import database.RaterDatabase;
import datastructure.Rater;
import datastructure.Rating;
import filter.AllFilters;
import filter.GenresFilter;
import filter.MovieFilter;
import sorter.BySimilarityDescending;

public class MovieRunnerSimilarRatings {

    private void print(ArrayList<Rating> ratings) {
        int count = 0;
        for(Rating curRating: ratings) {
            double rank = curRating.getValue();
            String title = MovieDatabase.getTitle(curRating.getItem());
            if(rank > 0) {
                ++count;
                System.out.printf("%.2f\t%s\n", rank, title);
            }
        }
        System.out.println("total " + count + " movies");
    }

    
    private double dotProduct(Rater me, Rater other) {
        double ret = 0;
        for (String item : me.getItemsRated()) {
            if (other.hasRating(item)) {
                ret += (me.getRating(item) - 5) * (other.getRating(item) - 5);
            }
        }
        return ret;
    }

    public ArrayList<Rating> getSimilarRatings(String id,
                                                int numSimilarRaters,
                                                int minimalRaters,
                                                MovieFilter filter) {
        HashMap<String, Double> rating = new HashMap<>();
        HashMap<String, Integer> count = new HashMap<>();

        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Collections.sort(raters, new BySimilarityDescending(id));

        for(int i=0; i<Math.min(numSimilarRaters, raters.size()); ++i) {
            Rater curRater = raters.get(i);
            // System.out.println(curRater.getID());
            for(String movie: curRater.getItemsRated()) {
                if(!filter.satisfy(movie)) {
                    continue;
                }
                double rank = curRater.getRating(movie);
                if(!rating.keySet().contains(movie)) {
                    rating.put(movie, rank);
                    count.put(movie, 1);
                }
                else {
                    rating.put(movie, rating.get(movie) + rank);
                    count.put(movie, count.get(movie) + 1);
                }
            }
        }

        ArrayList<Rating> ret = new ArrayList<>();
        for(String movie: rating.keySet()) {
            Double rank = rating.get(movie) / count.get(movie);
            if(count.get(movie) < minimalRaters) {
                rank = 0.0;
            }
            ret.add(new Rating(movie, rank));
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }

    public void printSimilarRatings() {
        // FourthRatings fRatings = new FourthRatings("ratedmovies_short.csv", "ratings_short.csv");
        FourthRatings fRatings = new FourthRatings();
        
        AllFilters all = new AllFilters();
        all.addFilter(new GenresFilter(new String[]{"Action"}));

        ArrayList<Rating> similarRating = getSimilarRatings("65", 20, 5, all);

        print(similarRating);
    }

    public void printAverageRatings() {
        FourthRatings fRatings = new FourthRatings();

        AllFilters all = new AllFilters();

        ArrayList<Rating> averageRating = fRatings.getAverageRatings(10, all);
        Collections.sort(averageRating);

        print(averageRating);
    }

    public static void main(String[] args){
        System.out.println("Testing");

        MovieRunnerSimilarRatings mRunnerSimilarRatings = new MovieRunnerSimilarRatings();

        mRunnerSimilarRatings.printSimilarRatings();
    }
}

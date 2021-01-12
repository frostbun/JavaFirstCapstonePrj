package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import database.MovieDatabase;
import database.RaterDatabase;
import datastructure.Rater;
import datastructure.Rating;
import filter.AllFilters;
import filter.DirectorsFilter;
import filter.GenresFilter;
import filter.MinutesFilter;
import filter.MovieFilter;
import filter.YearsAfterFilter;
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
                                                int minimalRaters,
                                                int numSimilarRaters,
                                                MovieFilter filter) {
        HashMap<String, Double> rating = new HashMap<>();
        HashMap<String, Integer> count = new HashMap<>();

        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Collections.sort(raters, new BySimilarityDescending(id));

        for(int i=0; i<Math.min(numSimilarRaters, raters.size()); ++i) {
            Rater curRater = raters.get(i);
            double weight = dotProduct(RaterDatabase.getRater(id), curRater);
            if(weight <= 0) {
                break;
            }
            for(String movie: curRater.getItemsRated()) {
                if(!filter.satisfy(movie)) {
                    continue;
                }
                double rank = curRater.getRating(movie) * weight;
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
        
        AllFilters allFilters = new AllFilters();
        // allFilters.addFilter(new DirectorsFilter(new String[]{"Clint Eastwood",
        //                                                 "J.J. Abrams",
        //                                                 "Alfred Hitchcock",
        //                                                 "Sydney Pollack",
        //                                                 "David Cronenberg",
        //                                                 "Oliver Stone",
        //                                                 "Mike Leigh"}));    
        allFilters.addFilter(new GenresFilter(new String[]{"Drama"}));
        allFilters.addFilter(new MinutesFilter(80, 160));
        // allFilters.addFilter(new YearsAfterFilter(1975));

        ArrayList<Rating> similarRating = getSimilarRatings("168", 3, 10, allFilters);

        double rank = similarRating.get(0).getValue();
        String title = MovieDatabase.getTitle(similarRating.get(0).getItem());
        System.out.printf("%.2f\t%s\n", rank, title);
        // print(similarRating);
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
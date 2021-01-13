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
import filter.TrueFilter;
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

        ArrayList<Rating> similarRating = fRatings.getSimilarRatings("168", 3, 10, allFilters);

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
}

package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import DataStructure.*;
import Database.*;
import Filter.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");

        System.out.println("read data for " + tr.getRaterSize() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        AllFilters all = new AllFilters();
        // all.addFilter(new YearsAfterFilter(1990));
        // all.addFilter(new GenresFilter(new String[]{"Drama"}));
        // all.addFilter(new MinutesFilter(105, 135));
        // all.addFilter(new DirectorsFilter(new String[]{"Clint Eastwood",
        //                                                 "Joel Coen",
        //                                                 "Tim Burton",
        //                                                 "Ron Howard",
        //                                                 "Nora Ephron",
        //                                                 "Sydney Pollack" }));
        ArrayList<String> movies = MovieDatabase.filterBy(all);

        ArrayList<Rating> averageRating = tr.getAverageRatings(movies, 10);
        Collections.sort(averageRating);

        int count = 0;
        for(Rating curRating: averageRating) {
            double rank = curRating.getValue();
            String title = MovieDatabase.getTitle(curRating.getItem());
            if(rank > 0) {
                ++count;
                System.out.printf("%.2f\t%s\n", rank, title);
            }
        }
        System.out.println("total " + count + " movies");
    }

    public static void main(String[] args){
        System.out.println("Testing");

        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();

        mrwf.printAverageRatings();
    }
}

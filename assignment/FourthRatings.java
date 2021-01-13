package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import database.*;
import datastructure.*;
import filter.*;
import sorter.BySimilarityDescending;

public class FourthRatings {
    
    public FourthRatings(String moviefile, String raterfile) {
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
    }

    public FourthRatings() {
        MovieDatabase.initialize();
        RaterDatabase.initialize();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        double sumRating = 0;
        int countRaters = 0;
        for(Rater r: RaterDatabase.getRaters()) {
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
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters, MovieFilter filter) {
        ArrayList<Rating> ret = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(filter);
        for(String id: movies) {
            ret.add(new Rating(id, getAverageByID(id, minimalRaters)));
        }
        return ret;
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

    public ArrayList<Rating> getSimiRatings(String id, int minimalRaters, int numSimilarRaters) {
        return getSimilarRatings(id, minimalRaters, numSimilarRaters, new TrueFilter());
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
}

package Assignment;

import java.util.ArrayList;
import DataStructure.*;
import Database.*;
import Filter.*;

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
}

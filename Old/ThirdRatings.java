package Old;

import java.util.ArrayList;

import Assignment.FirstRatings;
import DataStructure.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings(String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
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
    
    public ArrayList<Rating> getAverageRatings(ArrayList<String> movies, int minimalRaters) {
        ArrayList<Rating> ret = new ArrayList<>();
        for(String id: movies) {
            ret.add(new Rating(id, getAverageByID(id, minimalRaters)));
        }
        return ret;
    }
}

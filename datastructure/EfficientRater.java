package datastructure;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        return myRatings.keySet().contains(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        return myRatings.get(item).getValue();
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> ret = new ArrayList<>();
        for(Rating curRating: myRatings.values()){
            ret.add(curRating.getItem());
        }
        return ret;
    }
}

package sorter;

import java.util.Comparator;

import database.RaterDatabase;
import datastructure.Rater;

public class BySimilarityDescending implements Comparator<Rater> {
    private Rater me;

    public BySimilarityDescending(String id) {
        me = RaterDatabase.getRater(id);
    }

    private double dotProduct(Rater other) {
        double ret = 0;
        for (String item : me.getItemsRated()) {
            if (other.hasRating(item)) {
                ret += (me.getRating(item) - 5) * (other.getRating(item) - 5);
            }
        }
        return ret;
    }
    
    public int compare(Rater o1, Rater o2) {
        if(o1.getID() == me.getID()) {
            return 1;
        }
        if(o2.getID() == me.getID()) {
            return -1;
        }
        return Double.compare(dotProduct(o2), dotProduct(o1));
    }
}
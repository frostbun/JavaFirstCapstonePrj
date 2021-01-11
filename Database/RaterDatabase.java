package Database;

import java.util.ArrayList;
import java.util.HashMap;
import DataStructure.*;
import Assignment.*;

public class RaterDatabase {
    private static HashMap<String,Rater> ourRaters;
     
	public static void initialize(String raterfile) {
        if (ourRaters == null) {
            ourRaters = new HashMap<>();
            loadRaters("data/" + raterfile);
        }
    }

    public static void initialize() {
        if (ourRaters == null) {
            ourRaters = new HashMap<>();
            loadRaters("data/ratings.csv");
        }
    }	
	
    private static void loadRaters(String filename) {
        FirstRatings fr = new FirstRatings();
        ArrayList<Rater> list = fr.loadRaters(filename);
        for (Rater r : list) {
            ourRaters.put(r.getID(), r);
        }
        System.out.println("read data for " + size() + " raters");
    }
     
    public static Rater getRater(String id) {
    	initialize();
    	return ourRaters.get(id);
    }
    
    public static ArrayList<Rater> getRaters() {
    	initialize();
    	ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
    	return list;
    }
 
    public static int size() {
	    return ourRaters.size();
    }
}
import java.util.ArrayList;

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
    
    public ArrayList<Rating> getAverageRatings() {
        ArrayList<Rating> ret = new ArrayList<>();

        AllFilters all = new AllFilters();
        all.addFilter(new YearsAfterFilter(1990));
        all.addFilter(new GenresFilter(new String[]{"Drama"}));
        // all.addFilter(new MinutesFilter(105, 135));
        // all.addFilter(new DirectorsFilter(new String[]{"Clint Eastwood",
        //                                                 "Joel Coen",
        //                                                 "Tim Burton",
        //                                                 "Ron Howard",
        //                                                 "Nora Ephron",
        //                                                 "Sydney Pollack" }));
        ArrayList<String> movies = MovieDatabase.filterBy(all);

        for(String id: movies) {
            ret.add(new Rating(id, getAverageByID(id, 8)));
        }
        return ret;
    }
}

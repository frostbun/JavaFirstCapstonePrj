import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");

        System.out.println("read data for " + tr.getRaterSize() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        ArrayList<Rating> averageRating = tr.getAverageRatings();
        Collections.sort(averageRating, new ByRatingAccending());

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
}

package recommender;

import java.util.ArrayList;
import java.util.Collections;

import assignment.FourthRatings;
import database.MovieDatabase;
import datastructure.Rating;
import filter.TrueFilter;

public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate () {
        ArrayList<String> ret = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        Collections.shuffle(movies);
        for(int i=0; i<10; ++i) {
            ret.add(movies.get(i));
        }
        return ret;
    }

    public void printRecommendationsFor (String webRaterID) {
        FourthRatings fRatings = new FourthRatings();
        ArrayList<Rating> ratings = fRatings.getSimiRatings(webRaterID, 5, 50);
        System.out.println("<table style=\"border-collapse: collapse;margin: 25px 0;font-size: 0.9em;font-family: sans-serif;min-width: 400px;box-shadow: 0 0 20px rgba(0, 0, 0, 0.15)\">");
        System.out.println("<tr style=\"background-color: #009879;color: #ffffff;text-align: left;\">");
        System.out.println("<th style=\"padding: 12px 15px;\"></th>");
        System.out.println("<th style=\"padding: 12px 15px;\"></th>");
        System.out.println("<th style=\"padding: 12px 15px;\">Tilte</th>");
        System.out.println("<th style=\"padding: 12px 15px;\">Genres</th>");
        System.out.println("<th style=\"padding: 12px 15px;\">Time</th>");
        System.out.println("<th style=\"padding: 12px 15px;\">Directors</th>");
        System.out.println("<th style=\"padding: 12px 15px;\">Country</th>");
        System.out.println("<th style=\"padding: 12px 15px;\">Year</th>");
        System.out.println("</tr>");
        for(int i=0; i<20; ++i) {
            String id = ratings.get(i).getItem();
            if(i % 2 == 1) {
                System.out.println("<tr style=\"border-bottom: 1px solid #dddddd;\">");
            }
            else {
                System.out.println("<tr style=\"border-bottom: 1px solid #dddddd;background-color: #f3f3f3;\">");
            }
            System.out.println("<td style=\"padding: 12px 15px;\">" + (i+1) + "</td>");
            System.out.println("<td style=\"padding: 12px 15px;\"><img src=\"" + MovieDatabase.getPoster(id) + "\"/></td>");
            System.out.println("<td style=\"padding: 12px 15px;\">" + MovieDatabase.getTitle(id) + "</td>");
            System.out.println("<td style=\"padding: 12px 15px;\">" + MovieDatabase.getGenres(id) + "</td>");
            System.out.println("<td style=\"padding: 12px 15px;\">" + MovieDatabase.getMinutes(id) + " minutes</td>");
            System.out.println("<td style=\"padding: 12px 15px;\">" + MovieDatabase.getDirector(id) + "</td>");
            System.out.println("<td style=\"padding: 12px 15px;\">" + MovieDatabase.getCountry(id) + "</td>");
            System.out.println("<td style=\"padding: 12px 15px;\">" + MovieDatabase.getYear(id) + "</td>");
            System.out.println("</tr>");
        }
        System.out.println("</table>");
    }
}

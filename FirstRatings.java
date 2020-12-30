import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FirstRatings {

    private ArrayList<String> parseLine(String line) {
        ArrayList<String> data = new ArrayList<>();
        String current = "";
        int count = 0;
        for(int i=0; i<line.length(); ++i) {
            char c = line.charAt(i);
            if(c == '\"') {
                ++count;
            }
            else if(c == ',' && count % 2 == 0) {
                data.add(current);
                current = "";
            }
            else {
                current += c;
            }
            // System.out.println(current);
        }
        data.add(current);
        //System.out.println(data);
        return data;
    }
    
    private Movie createMovie(ArrayList<String> data) {
        String anID = data.get(0);
        String aTitle = data.get(1);
        String aYear = data.get(2);
        String aCountry = data.get(3);
        String theGenres = data.get(4);
        String aDirector = data.get(5);
        int theMinutes = Integer.parseInt(data.get(6));
        String aPoster = data.get(7);
        return new Movie(anID, aTitle, aYear, theGenres, aDirector, aCountry, aPoster, theMinutes);
    }

    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> ret = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            reader.readLine();
            String line;
            while((line = reader.readLine()) != null) {
                ret.add(createMovie(parseLine(line)));
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }
        catch (IOException e) {
            System.out.println("Failed To Read File!");
        }
        return ret;
    }
}
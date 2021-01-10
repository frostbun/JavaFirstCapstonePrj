package Filter;

import Database.*;

public class GenresFilter implements MovieFilter{
    private String[] genre;

    public GenresFilter(String[] ngenre) {
        genre = ngenre;
    }

    public boolean satisfy(String id) {
        String currGenre = MovieDatabase.getGenres(id);
        for(String eachGenre: genre) {
            if(!currGenre.contains(eachGenre)) {
                return false;
            }
        }
        return true;
    }
}

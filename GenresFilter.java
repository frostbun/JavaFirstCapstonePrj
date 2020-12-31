public class GenresFilter implements MovieFilterInterface{
    private String[] genre;

    public GenresFilter(String[] ngenre) {
        genre = ngenre;
    }

    public boolean satisfy(Movie m) {
        String currGenre = m.getGenres();
        for(String eachGenre: genre) {
            if(!currGenre.contains(eachGenre)) {
                return false;
            }
        }
        return true;
    }
}

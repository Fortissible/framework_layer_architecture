import java.util.HashMap;
import java.util.Map;

// PROXY ADALAH SEBUAH
public class MovieCacheProxy {
    // PROPS / ATTRIBUTES SEBUAH CLASS
    public MovieDatabase movieDatabase;
    private Map<String, String> movieCache;
    // PARAMS
    public MovieCacheProxy(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
        this.movieCache = new HashMap<>();
    }

    // METHOD/BEHAVIOUR SEBUAH CLASS
    public String getMovieDescription(String movieName) {
        String lowerMovieName = movieName.toLowerCase();

        if (movieCache.containsKey(lowerMovieName)) {
            System.out.println("Movie data found on cache...");
            System.out.println("Retrieving movie description from cache...");
            return movieCache.get(lowerMovieName);
        } else {
            System.out.println("Saving movie data to cache...");
            String description = movieDatabase.getMovieDescription(lowerMovieName);
            if (description != null) {
                movieCache.put(lowerMovieName, description);
            }
            return description;
        }
    }

    // public getter movieDatabase
    public MovieDatabase getterMovieDatabase(){
        return this.movieDatabase;
    }
}

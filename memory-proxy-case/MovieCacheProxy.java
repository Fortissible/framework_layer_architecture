import java.util.HashMap;
import java.util.Map;

public class MovieCacheProxy {
    private MovieDatabase movieDatabase;
    private Map<String, String> movieCache;

    public MovieCacheProxy(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
        this.movieCache = new HashMap<>();
    }

    public String getMovieDescription(String movieName) {
        String lowerMovieName = movieName.toLowerCase();
        if (movieCache.containsKey(lowerMovieName)) {
            System.out.println("Retrieving movie description from cache...");
            return movieCache.get(lowerMovieName);
        } else {
            String description = movieDatabase.getMovieDescription(lowerMovieName);
            if (description != null) {
                movieCache.put(lowerMovieName, description);
            }
            return description;
        }
    }
}

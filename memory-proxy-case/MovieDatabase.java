import java.util.HashMap;
import java.util.Map;

public class MovieDatabase {
    private Map<String, String> movies;

    public MovieDatabase() {
        movies = new HashMap<>();
        movies.put("avengars", "is an American superhero film");
        movies.put("tem & jorry", "is an American cartoon film about a cat and a mouse");
        movies.put("crayon shinchon", "is a popular cartoon from Japan also called anime, is about a 5 year old kid");
    }

    public String getMovieDescription(String movieName) {
        System.out.println("Retrieving movie description from database...");
        return movies.getOrDefault(movieName.toLowerCase(), null);
    }
}

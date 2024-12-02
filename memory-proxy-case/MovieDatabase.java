import java.util.HashMap;
import java.util.Map;

public class MovieDatabase {
    // Tipe data map
    private Map<String, String> movies;
    private int accessCount = 0;

    public MovieDatabase() {
        movies = new HashMap<>();
        movies.put("avengers", "is an American superhero film");
        movies.put("tomjerry", "is an American cartoon film about a cat and a mouse");
        movies.put("crayonshinchan", "is a popular cartoon from Japan also called anime, is about a 5 year old kid");
    }

    public String getMovieDescription(String movieName) {
        System.out.println("Retrieving movie description from database...");
        accessCount++;
        return movies.getOrDefault(movieName.toLowerCase(), null);
    }

    public void getTotalDatabaseAccessCount(){
        System.out.println("Database accessed");
        System.out.println(accessCount + " Times");
    }
}

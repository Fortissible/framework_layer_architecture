import java.util.HashMap;
import java.util.Map;

public class MovieAuthProxy {
    private MovieCacheProxy movieCacheProxy;
    private Map<String, String> userCredentials;

    public MovieAuthProxy(MovieCacheProxy movieCacheProxy) {
        this.movieCacheProxy = movieCacheProxy;
        this.userCredentials = new HashMap<>();

        userCredentials.put("guest123", "guest123");
    }

    public String getMovieDescription(String movieName, String username, String password) {
        if (isAuthenticated(username, password)) {
            System.out.println("Authentication successful!");
            return movieCacheProxy.getMovieDescription(movieName);
        } else {
            System.out.println("Error: Invalid credentials. Access denied.");
            return null;
        }
    }

    // Function for credentials checking
    private boolean isAuthenticated(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }
}


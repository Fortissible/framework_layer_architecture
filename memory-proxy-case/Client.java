import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        MovieDatabase movieDatabase = new MovieDatabase();
        MovieCacheProxy movieCacheProxy = new MovieCacheProxy(movieDatabase);
        MovieAuthProxy movieAuthProxy = new MovieAuthProxy(movieCacheProxy);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Angel Flix");
            System.out.println("================");
            System.out.println("What movie do you want to know?");
            System.out.print("Input movie name >> ");
            String movieName = scanner.nextLine();

            // User need to input the crendentials
            System.out.println("Please provide your credentials.");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            String description = movieAuthProxy.getMovieDescription(movieName, username, password);

            if (description != null) {
                System.out.println("Movie Name: " + movieName);
                System.out.println("Movie Description: " + description);
            } else {
                System.out.println("Failed to fetch movie description. Please try again.");
            }

            movieDatabase.getTotalDatabaseAccessCount();

            System.out.println("Would you like to search for another movie? (yes/no)");
            String continueOption = scanner.nextLine();
            if (!continueOption.equalsIgnoreCase("yes")) {
                break;
            }
        }

        scanner.close();
    }
}

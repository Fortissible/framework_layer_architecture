import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        MovieDatabase movieDatabase = new MovieDatabase();
        MovieCacheProxy movieCacheProxy = new MovieCacheProxy(movieDatabase);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Angel Flix");
            System.out.println("================");
            System.out.println("What movie do you want to know?");
            System.out.print("Input movie name >> ");
            String movieName = scanner.nextLine();

            String description = movieCacheProxy.getMovieDescription(movieName);

            if (description != null) {
                System.out.println("Movie Name: " + movieName);
                System.out.println("Movie Description: " + description);
            } else {
                System.out.println("Movie not found!");
            }

            System.out.println("Would you like to search for another movie? (yes/no)");
            String continueOption = scanner.nextLine();
            if (!continueOption.equalsIgnoreCase("yes")) {
                break;
            }
        }

        scanner.close();
    }
}

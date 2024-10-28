import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ------------------------------- OBSERVER REGION -------------------------------
// Observer Interface
interface Subscriber {
    // Method update untuk melakukan siaran lagu yg sedang diputar
    void update(String track);

    // Method getter untuk mendapatkan tipe company
    String getCompanyType();
}


// Concrete Observer for PT Gogo Company (Radio)
class RadioGogoCompany implements Subscriber {
    @Override
    public void update(String track) {
        System.out.println("PT Radio Gogo is now playing '" + track + "'");
    }
    
    @Override
    public String getCompanyType() {
        return "Radio";
    }
}

// Concrete Observer for PT Loga Company (Hotel)
class HotelLogaCompany implements Subscriber {
    @Override
    public void update(String track) {
        System.out.println("Hotel Loga is currently live streaming a music named '" + track + "'");
    }
    
    @Override
    public String getCompanyType() {
        return "Hotel";
    }
}

// Concrete Observer for PT Lolo Company (Radio)
class MediaSocialLoloCompany implements Subscriber {
    @Override
    public void update(String track) {
        System.out.println("Lolo Media Social right now is streaming '" + track + "' songs");
    }
    
    @Override
    public String getCompanyType() {
        return "Media Social";
    }
}
// ------------------------------- OBSERVER REGION -------------------------------

// ------------------------------- SUBJECT/PUBLISHER/PROVIDER REGION -------------------------------
// Subject or Publisher class
class AngelMusicService {
    private List<Subscriber> subscribers = new ArrayList<>(); // COMPANY SUBSCRIBER

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(String track) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(track);
        }
    }
}
// ------------------------------- SUBJECT/PUBLISHER/PROVIDER REGION -------------------------------

// Main class to run the program
public class AngelMusic {
    public static void main(String[] args) {
        AngelMusicService musicService = new AngelMusicService(); // INITIALISASI ANGEL MUSIC SERVICE AS A SUBJECT/PUBLISHER/PROVIDER
        HotelLogaCompany hotelCompany = new HotelLogaCompany();
        // Adding subscribers
        musicService.addSubscriber(new RadioGogoCompany());
        musicService.addSubscriber(hotelCompany);
        musicService.addSubscriber(new MediaSocialLoloCompany());

        // User input for current track
        Scanner scanner = new Scanner(System.in);
        System.out.println("Angel Music");
        System.out.println("===============");
        System.out.print("Input current track >> ");
        String track = scanner.nextLine();
        
        // Notify all subscribers
        musicService.notifySubscribers(track);

        // REMOVE A SUBSCRIBER IF NEEDED
        musicService.removeSubscriber(hotelCompany);

        // User input for current track
        System.out.println("Angel Music");
        System.out.println("===============");
        System.out.print("Input current track >> ");
        String track2 = scanner.nextLine();
        
        // Notify all subscribers
        musicService.notifySubscribers(track2);

        scanner.close();
    }
}

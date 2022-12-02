import entities.Bathroom;
import entities.PersonBathroom;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static private final int interval = 1000;

    public static void main(String[] args) {
        Bathroom bathroom = new Bathroom();
        Timer timer = new Timer();
        ExecutorService executor = Executors.newCachedThreadPool();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                PersonBathroom personBathroom = new PersonBathroom(bathroom);
                executor.execute(personBathroom);
            }
        }, 200, interval);

    }
}

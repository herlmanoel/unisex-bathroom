import entities.Bathroom;
import entities.PersonBathroom;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    static private final int interval = 2000;

    public static void main(String[] args) {
        Bathroom bathroom = new Bathroom();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                PersonBathroom personBathroom = new PersonBathroom(bathroom);
                personBathroom.use();
            }
        }, 200, interval);
    }
}

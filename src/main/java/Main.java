import entities.Bathroom;
import entities.PersonBathroom;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static private final int interval = 2000;

    public static void main(String[] args) {
        Bathroom bathroom = new Bathroom();
        Timer timer = new Timer();
        ExecutorService executor = Executors.newCachedThreadPool();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("run");
//                personBathroom.use();
                for (int i = 0; i < 4; i++) {
                    PersonBathroom personBathroom = new PersonBathroom(bathroom);
                    executor.execute(personBathroom);
                }
            }
        }, 200, interval);

    }
}

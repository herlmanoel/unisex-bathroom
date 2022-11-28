package entities;

import java.util.Timer;
import java.util.TimerTask;

public class PersonBathroom {
    Bathroom bathroom;
    Person person;

    private int interval = 2000;

    public synchronized void leave() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                bathroom.getPeople().remove(bathroom.getPeople().get(0));
            }
        }, getRandomDelay(200, 2000), interval);
    }

    private int getRandomDelay(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void use(int min, int max) {
        tryEnter();
        leave();
    }

    private synchronized void tryEnter() {
        waitIfExceededMaximumCapacity();
        waitIfSexNotMatch(person);

        if (bathroom.bathroomIsEmpty()) {
            bathroom.setSex(person.getSex());
        }
        bathroom.setPerson(person);
        notify();
    }

    private void waitIfExceededMaximumCapacity() {
        while (bathroom.isExceededMaximumCapacity()) {
            System.out.println("Bathroom is full.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void waitIfSexNotMatch(Person person) {
        while (!bathroom.isSexEquals(person.getSex())) {
            System.out.println("Bathroom is sex opposite.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

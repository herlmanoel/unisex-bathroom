package entities;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Bathroom {
    private List<Person> people;
    private int maximumCapacity;
    private Sex sex;

    private int interval = 2000;

    public synchronized void leave() {
        int DELAY = 500;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                getPeople().remove(getPeople().get(0));
            }
        }, DELAY, interval);
    }


    public Bathroom() {}

    public List<Person> getPeople() {
        return people;
    }

    public void setPerson(Person person) {
        this.people.add(person);
    }

    public synchronized void tryEnter(Person person) {
        waitIfExceededMaximumCapacity();
        waitIfSexNotMatch(person);

        if(bathroomIsEmpty()) {
            setSex(person.getSex());
        }
        this.setPerson(person);
        notify();
    }

    private void waitIfExceededMaximumCapacity() {
        while (isExceededMaximumCapacity()) {
            System.out.println("Bathroom is full.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void waitIfSexNotMatch(Person person) {
        while (!isSexEquals(person.getSex())) {
            System.out.println("Bathroom is sex opposite.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isSexEquals(Sex sex) {
        return this.sex == sex;
    }

    private boolean isExceededMaximumCapacity() {
        return this.getPeople().size() > maximumCapacity;
    }

    private boolean bathroomIsEmpty() {
        return getPeople().size() == 0;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}

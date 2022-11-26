package entities;

import java.util.Set;

public class Bathroom {
    private Set<Person> people;
    private int maximumCapacity;
    private Sex sex;

    public Bathroom() {}

    public Set<Person> getPeople() {
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

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}

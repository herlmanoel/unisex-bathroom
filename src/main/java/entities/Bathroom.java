package entities;

import java.util.List;
import java.util.ArrayList;

public class Bathroom {
    private List<Person> people;
    private final int maximumCapacity = 5;
    private Sex sex;

    public Bathroom() {
        people = new ArrayList<Person>();
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPerson(Person person) {
        this.people.add(person);
    }

    public boolean isSexEquals(Sex sex) {
        return this.getPeople().size() == 0 || this.sex == sex;
    }

    public boolean isExceededMaximumCapacity() {
        return this.getPeople().size() > maximumCapacity;
    }

    public boolean bathroomIsEmpty() {
        return getPeople().size() == 0;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
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

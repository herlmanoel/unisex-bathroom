package entities;

import java.util.List;
import java.util.ArrayList;

public class Bathroom {
    private List<Person> people;
    private final int maximumCapacity = 5;

    public Bathroom() {
        people = new ArrayList<>();
    }

    public List<Person> getPeople() {
        return people;
    }

    public synchronized void setPerson(Person person) {
        try {
            while (true) {
                System.out.println("dentro do banheiro: " + getPeople());
                if (this.isExceededMaximumCapacity()) {
                    System.out.println("Banheiro está cheio");
                } else if (!this.isSexEquals(person.getSex())) {
                    System.out.println("Banheiro está ocupado pelo sexo oposto");
                } else {
                    break;
                }
                System.out.println("fila     >   " + person.toString());
                wait();
                System.out.println("acordou  >   " + person.toString());
            }

            this.people.add(person);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void removePerson(Person person) {
        this.people.remove(person);
        notifyAll();
    }

    public boolean isSexEquals(Sex sex) {
        Sex current_sex = this.getSex();
        return current_sex == sex || current_sex == null;
    }

    public boolean isExceededMaximumCapacity() {
        return this.people.size() > maximumCapacity;
    }

    public Sex getSex() {
        if (this.people.size() > 0) {
            return this.people.get(0).getSex();
        }
        return null;
    }
}

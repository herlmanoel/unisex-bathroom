package entities;

import java.util.Objects;

public class Person {
    private static int count = 0;
    private int id;

    private Sex sex;

    public Person() {
        sex = Sex.randomSex();
        this.id = ++count;
    }


    public int getId() {
        return id;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Person))
            return false;
        Person person = (Person) o;
        return getId() == person.getId() && sex == person.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), sex);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                // ", createdDate=" + getCreatedDate() +
                ", sex=" + sex +
                '}';
    }
}

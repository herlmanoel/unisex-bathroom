package entities;

import java.util.Objects;

public class Person {
    private int id;
    private Sex sex;

    public Person() {
        sex = Sex.randomSex();
    }

    public Person(int id, Sex sex) {
        this.id = id;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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
}

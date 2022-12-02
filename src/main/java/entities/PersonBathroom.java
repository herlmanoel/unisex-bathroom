package entities;

public class PersonBathroom extends Thread {
    Bathroom bathroom;
    Person person;

    public PersonBathroom(Bathroom bathroom) {
        this.bathroom = bathroom;
        this.person = new Person();
    }

    private int getRandomDelay(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void use() {
        tryEnter();
        try {
            Thread.sleep(getRandomDelay(1000, 4000));

            removePerson();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void removePerson() {
        bathroom.removePerson(this.person);
        System.out.println("saiu     >   " + this.person.toString());
        System.out.println("restante: " + bathroom.getPeople());
    }

    @Override
    public void run() {
        this.use();
    }

    private void tryEnter() {
        System.out.println("tryEnter >   " + this.person.toString());
        // waitIfExceededMaximumCapacity();
        // waitIfSexNotMatch();

        bathroom.setPerson(person);
        System.out.println("entrou   >   " + this.person.toString());
        // notify();
    }

    // private void waitIfExceededMaximumCapacity() {
    // while (bathroom.isExceededMaximumCapacity()) {
    // System.out.println("Bathroom is full.");
    // try {
    // wait();
    // System.out.println("acordou > " + this.person.toString());
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // }

    // private void waitIfSexNotMatch() {
    // while (!bathroom.isSexEquals(person.getSex())) {
    // System.out.println("Bathroom is sex opposite.");
    // try {
    // wait();
    // System.out.println("acordou > " + this.person.toString());
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // }
}

package entities;

public class PersonBathroom {
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
            bathroom.getPeople().remove(person);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void tryEnter() {
        waitIfExceededMaximumCapacity();
        waitIfSexNotMatch();

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

    private void waitIfSexNotMatch() {
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

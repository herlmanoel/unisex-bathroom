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

    private synchronized void enterBathroom() {
        System.out.println("tryenter >   " + person);
        bathroom.setPerson(person);
        System.out.println("entrou   >   " + person.toString());
    }

    private synchronized void exitBathroom() {
        bathroom.removePerson(person);
        System.out.println("saiu     >   " + person);
        System.out.println("dentro do banheiro: " + bathroom.getPeople());
    }

    @Override
    public void run() {
        enterBathroom();
        try {
            Thread.sleep(getRandomDelay(1000, 4000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            exitBathroom();
        }
    }
}

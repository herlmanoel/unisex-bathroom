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
        System.out.println("dentro do banheiro: " + bathroom.getPeople());
    }

    @Override
    public void run() {
        this.use();
    }

    private void tryEnter() {

        System.out.println("tryEnter >   " + this.person.toString());
        System.out.println("dentro do banheiro: " + bathroom.getPeople());
        bathroom.setPerson(person);
        System.out.println("entrou   >   " + this.person.toString());

    }
}

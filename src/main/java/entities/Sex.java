package entities;

import java.util.Random;

public enum Sex {
    MALE,
    FEMALE;

    private static final Random PRNG = new Random();

    public static Sex randomSex() {
        Sex[] sex = values();
        return sex[PRNG.nextInt(sex.length)];
    }
}

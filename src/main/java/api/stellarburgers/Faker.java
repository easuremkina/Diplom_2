package api.stellarburgers;

import java.util.ArrayList;

public class Faker {
    private static final com.github.javafaker.Faker faker = new com.github.javafaker.Faker();
    public static String getFakeEmail() {
        return faker.letterify("?????-???@????????.ru");
    }

    public static String getFakeName() {
        return faker.name().username();
    }

    public static String getFakePassword() {
        return faker.letterify("?????????");
    }

    public static ArrayList<String> getFakeIngredients() {
        ArrayList<String> fakeIngredients = new ArrayList<>();
        fakeIngredients.add(faker.bothify("????????????????????????"));
        fakeIngredients.add(faker.bothify("????????????????????????"));
        return fakeIngredients;
    }
}

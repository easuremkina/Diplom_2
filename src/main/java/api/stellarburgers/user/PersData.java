package api.stellarburgers.user;

import api.stellarburgers.Faker;
import lombok.Data;

@Data
public class PersData {
    private String email;
    private String password;
    private String name;

    public PersData() {}
    private PersData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private PersData(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static PersData createRandom() {
        return new PersData(Faker.getFakeEmail(),
                Faker.getFakePassword(),
                Faker.getFakeName());
    }

    public static PersData createUserWithoutFieldName() {
        return new PersData(Faker.getFakeEmail(), Faker.getFakePassword());
    }

    public void updateField(UserField field, String newFieldValue) throws Exception {
        if (UserField.PASSWORD.equals(field)) {
            setPassword(newFieldValue);
        } else if (UserField.EMAIL.equals(field)) {
            setEmail(newFieldValue);
        } else if (UserField.NAME.equals(field)) {
            setName(newFieldValue);
        } else {
            throw new Exception("Неверное поле");
        }
    }
}

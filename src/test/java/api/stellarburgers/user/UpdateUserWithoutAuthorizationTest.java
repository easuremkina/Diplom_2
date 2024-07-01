package api.stellarburgers.user;

import api.stellarburgers.Faker;
import api.stellarburgers.UserHelper;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class UpdateUserWithoutAuthorizationTest extends AbstractUserTest{
    private final UserField fieldNameToUpdate;
    private final String newFieldValue;
    public UpdateUserWithoutAuthorizationTest(UserField fieldNameToUpdate, String newFieldValue) {
        this.fieldNameToUpdate = fieldNameToUpdate;
        this.newFieldValue = newFieldValue;
    }

    @Parameterized.Parameters(name  = "Поле {0}, новое значение {1}")
    public static  Object[][] getField() {
        return new Object[][] {
                {UserField.PASSWORD, Faker.getFakePassword()},
                {UserField.EMAIL, Faker.getFakeEmail()},
                {UserField.NAME, Faker.getFakeName()}
        };
    }

    @DisplayName("Обновление данных пользователя с отсутствующим accessToken")
    @Test
    public void updateTest() throws Exception {
        User user = UserHelper.createNewUser();
        String oldPassword = user.getPersData().getPassword();
        String oldEmail = user.getPersData().getEmail();
        user.getPersData().updateField(fieldNameToUpdate, newFieldValue);
        ValidatableResponse response = client.updateUserWithoutTokenRequest(user);

        //вернем объекту исходный password для возможности удаления юзера после выполнения теста
        user.getPersData().setPassword(oldPassword);
        user.getPersData().setEmail(oldEmail);

        check.bodyContainsSuccessFalse(response);
        check.responseCode401Unauthorized(response);
        check.bodeContainsErrorMessageAboutNonAuthourised(response);
    }
}

package api.stellarburgers.user;

import api.stellarburgers.Faker;
import api.stellarburgers.UserHelper;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UpdateUserTest extends AbstractUserTest{
    private final UserField fieldNameToUpdate;
    private final String newFieldValue;
    public UpdateUserTest(UserField fieldNameToUpdate, String newFieldValue) {
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

    @DisplayName("Обновление данных пользователя с верным accessToken")
    @Test
    public void updateTest() throws Exception {
        User user = UserHelper.createNewUser();
        UserHelper.loginUser(user);
        String accessToken = UserHelper.getAccessToken(user);
        user.setAccessToken(accessToken);

        user.getPersData().updateField(fieldNameToUpdate, newFieldValue);

        ValidatableResponse response = client.updateUserRequest(user);
        check.bodyContainsSuccessTrue(response);
        check.responseCode20OHttpOk(response);
        updateUserCheck.fieldEmailContainsUsersEmail(response, user.getPersData().getEmail());
    }
}

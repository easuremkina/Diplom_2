package api.stellarburgers;

import api.stellarburgers.user.AbstractUserTest;
import api.stellarburgers.user.Credentials;
import api.stellarburgers.user.PersData;
import api.stellarburgers.user.User;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class UserHelper extends AbstractUserTest {
    @Step("Создание нового пользователя с проверкой на success: true в теле ответа")
    public static User createNewUser() {
        User user = new User(PersData.createRandom());
        ValidatableResponse response = client.createUserRequest(user.getPersData());
        user_list_for_disposal.add(user);
        check.bodyContainsSuccessTrue(response);
        return user;
    }

    @Step("Авторизация пользователя")
    public static ValidatableResponse loginUser(User user) {
        return client.loginRequest(Credentials.fromUser(user.getPersData()));
    }

    @Step("Получение токена пользователя")
    public static String getAccessToken(User user) {
        Credentials creds = Credentials.fromUser(user.getPersData());
        ValidatableResponse responseLogin =  client.loginRequest(creds);
        return loginCheck.bodyLoggedContainsAccessToken(responseLogin);
    }

    @Step("Получение токена нового созданного пользователя")
    public static String getAccessTokenFromCreatedNewUser() {
        User user = createNewUser();
        loginUser(user);
        return getAccessToken(user);
    }
}

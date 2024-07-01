package api.stellarburgers.user;

import api.stellarburgers.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class UserClientImpl extends Client implements UserClient {
    private static final String CREATE_USER_ENDPOINT = "/auth/register";
    private static final String LOGIN_ENDPOINT = "/auth/login";
    private static final String USER_ENDPOINT  = "/auth/user";

    @Step("Отправка запроса на создание пользователя")
    @Override
    public ValidatableResponse createUserRequest(PersData userPersData) {
        return requestSpecification()
                .body(userPersData)
                .when()
                .post(CREATE_USER_ENDPOINT)
                .then().log().all();
    }

    @Step("Отправка запроса  на авторизацию")
    @Override
    public ValidatableResponse loginRequest(Credentials credentials) {
        return requestSpecification()
                .body(credentials)
                .when()
                .post(LOGIN_ENDPOINT)
                .then().log().all();
    }

    @Step("Отправка запроса на обновление всех полей пользователя")
    @Override
    public ValidatableResponse updateUserRequest(User user) {
        return requestSpecification()
                .header("Authorization", user.getAccessToken())
                .body(user.getPersData())
                .when()
                .patch(USER_ENDPOINT)
                .then().log().all();
    }

    @Step("Отправка запроса на обновление всех полей пользователя  - без токена")
    public ValidatableResponse updateUserWithoutTokenRequest(User user) {
        return requestSpecification()
                .body(user.getPersData())
                .when()
                .patch(USER_ENDPOINT)
                .then().log().all();
    }

    @Step("Отправка запроса на удаление пользователя")
    @Override
    public void deleteUserRequest(String accessToken) {
        requestSpecification()
                .header("Authorization", accessToken)
                .when()
                .delete(USER_ENDPOINT)
                .then().log().all();
    }
}

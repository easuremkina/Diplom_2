package api.stellarburgers.user;

import api.stellarburgers.UserHelper;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;


public class LoginUserTest extends AbstractUserTest{

    User user;
    @Before
    public void createUser() {
        user = UserHelper.createNewUser();
    }

    @DisplayName("Успешная авторизация пользователя - accessToken")
    @Test
    public void loginUserCheckBodyFieldAccessToken(){
        ValidatableResponse response = UserHelper.loginUser(user);
        loginCheck.bodyLoggedContainsAccessToken(response);
    }

    @DisplayName("Успешная авторизация пользователя - success: true")
    @Test
    public void loginUserCheckBodyFieldSuccess(){
        ValidatableResponse response = UserHelper.loginUser(user);
        check.bodyContainsSuccessTrue(response);
    }

    @DisplayName("Успешная авторизация пользователя - код ответа 200")
    @Test
    public void loginUserCheckResponseCode(){
        ValidatableResponse response = UserHelper.loginUser(user);
        check.responseCode20OHttpOk(response);
    }

    @DisplayName("Авторизация пользователя с неверным паролем - success: false")
    @Test
    public void loginWithIncorrectPasswordCheckBody(){
        Credentials creds = Credentials.fromUserWhitIncorrectPassword(user.getPersData());
        ValidatableResponse response = client.loginRequest(creds);
       check.bodyContainsSuccessFalse(response);
    }

    @DisplayName("Авторизация пользователя с неверным паролем - код ответа 401")
    @Test
    public void loginWithIncorrectPasswordCheckResponseCode(){
        Credentials creds = Credentials.fromUserWhitIncorrectPassword(user.getPersData());
        ValidatableResponse response = client.loginRequest(creds);
        check.responseCode401Unauthorized(response);
    }

    @DisplayName("Авторизация пользователя с неверным email и паролем - success: false")
    @Test
    public void loginWithIncorrectEmailAndPasswordCheckBody(){
        Credentials creds = Credentials.fromUserWhitIncorrectEmailAndPassword(user.getPersData());
        ValidatableResponse response = client.loginRequest(creds);
        check.bodyContainsSuccessFalse(response);
    }
}

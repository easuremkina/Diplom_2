package api.stellarburgers.user.checks;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class LoginChecks {
    @Step("Проверка успешной авторизации пользователя - accessToken заполнен")
    public String bodyLoggedContainsAccessToken(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .body("accessToken", is(notNullValue()))
                .extract()
                .path("accessToken");
    }
}

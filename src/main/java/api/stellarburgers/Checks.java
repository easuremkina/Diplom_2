package api.stellarburgers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.net.HttpURLConnection;
import static org.hamcrest.CoreMatchers.equalTo;

public class Checks {
    @Step("Проверка - тело содержит поле success: true")
    public void bodyContainsSuccessTrue(ValidatableResponse response) {
        response
                .assertThat()
                .body("success", equalTo(true));
    }

    @Step("Проверка - тело ответа  содержит поле success: false")
    public void bodyContainsSuccessFalse(ValidatableResponse response) {
        response
                .assertThat()
                .body("success", equalTo(false));
    }

    @Step("Проверка - код ответа 200")
    public void responseCode20OHttpOk(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK);
    }

    @Step("Проверка - код ответа 400")
    public void responseCode400BadRequest(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Step("Проверка - код ответа 401")
    public void responseCode401Unauthorized(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED);
    }

    @Step("Проверка - код ответа 403")
    public void responseCode403UForbidden(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN);
    }

    @Step("Проверка  сообщения об ошибке для неавторизованного пользователя")
    public void bodeContainsErrorMessageAboutNonAuthourised(ValidatableResponse response){
        response
                .assertThat()
                .body("message", equalTo("You should be authorised"));
    }
}

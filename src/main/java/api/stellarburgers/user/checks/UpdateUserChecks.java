package api.stellarburgers.user.checks;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class UpdateUserChecks {
    @Step("Проверка обновления данных авторизованным пользователем - содержит верное значение в поле email")
    public void fieldEmailContainsUsersEmail(ValidatableResponse response, String email){
        response
                .assertThat()
                .body("user.email", equalTo(email));
    }
}

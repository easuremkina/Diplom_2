package api.stellarburgers.user.checks;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserChecks {

    @Step("Проверка создания пользователя - в поле email прописан email созданного пользователя")
    public void fieldEmailContainsUsersEmail(ValidatableResponse response, String email){
        response
                .assertThat()
                .body("user.email", equalTo(email));
    }
}

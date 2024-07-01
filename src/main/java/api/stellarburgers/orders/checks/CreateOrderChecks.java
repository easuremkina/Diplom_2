package api.stellarburgers.orders.checks;

import api.stellarburgers.orders.ingredients.IngredientsResponse;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderChecks {
    @Step("Получение ингредиентов")
    public static IngredientsResponse extractIngredients(ValidatableResponse response) {
        return response
                .assertThat()
                .body("data", is(notNullValue()))
                .extract()
                .as(IngredientsResponse.class);
    }
    @Step("Проверка - номер заказа не пустой")
    public void fieldOrderNumberIsNotNull(ValidatableResponse response) {
        response
                .assertThat()
                .body("order.number", notNullValue());
    }

    @Step("Получение номера заказа")
    public Integer extractOrderNumber(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .body("order.number", is(notNullValue()))
                .extract()
                .path("order.number");
    }
}

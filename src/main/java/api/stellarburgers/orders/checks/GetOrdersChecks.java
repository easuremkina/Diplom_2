package api.stellarburgers.orders.checks;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;
public class GetOrdersChecks {
    @Step("Получение списка заказов - тело ответа содержит номер заказа")
    public void bodyContainOrderNumber(ValidatableResponse response, Integer orderNumber) {
        response
                .assertThat()
                .body("orders[0].number", equalTo(orderNumber));
    }
}

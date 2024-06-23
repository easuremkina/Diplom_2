package api.stellarburgers.orders;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static api.stellarburgers.UserHelper.getAccessTokenFromCreatedNewUser;

public class GetOrdersTest extends AbstractOrderTest{
    Order order;
    String token;
    Integer orderNumber;
    @Before
    public void createOrder() {
        token = getAccessTokenFromCreatedNewUser();
        order = OrderHelper.createOrderWithIngredients();
        ValidatableResponse response = client.createOrderRequest(order, token);
        orderNumber = orderCheck.extractOrderNumber(response);
    }

    @DisplayName("Получение списка заказов авторизованного пользователя - тело ответа")
    @Description("Ожидание: в ответе возвращается success: true")
    @Test
    public void getOrdersSuccessfullyCheckBody() {
        ValidatableResponse response = client.getOrdersRequest(token);
        check.bodyContainsSuccessTrue(response);
    }

    @DisplayName("Получение списка заказов авторизованного пользователя - тело ответа")
    @Description("Ожидание: в ответе возвращается номер заказа")
    @Test
    public void getOrdersSuccessfullyCheckBodyContainsOrderNumber() {
        ValidatableResponse response = client.getOrdersRequest(token);
        getOrdersCheck.bodyContainOrderNumber(response, orderNumber);
    }

    @DisplayName("Получение списка заказов авторизованного пользователя - код ответа")
    @Description("Ожидание: код ответа 200")
    @Test
    public void getOrdersSuccessfullyCheckResponseCode() {
        ValidatableResponse response = client.getOrdersRequest(token);
        check.responseCode20OHttpOk(response);
    }

    @DisplayName("Получение списка заказов авторизованного пользователя - тело ответа")
    @Description("Ожидание: в ответе возвращается success: false")
    @Test
    public void getOrdersWithoutAuthorizationCheckBody() {
        ValidatableResponse response = client.getOrdersRequestWithoutToken();
        check.bodyContainsSuccessFalse(response);
    }

    @DisplayName("Получение списка заказов авторизованного пользователя - код ответа")
    @Description("Ожидание: код ответа 200")
    @Test
    public void getOrdersWithoutAuthorizationCheckResponseCode() {
        ValidatableResponse response = client.getOrdersRequestWithoutToken();
        check.responseCode401Unauthorized(response);
    }
}

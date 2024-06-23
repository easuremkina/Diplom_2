package api.stellarburgers.orders;

import api.stellarburgers.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderClientImpl extends Client implements OrderClient{
    private static final String CREATE_ORDER_ENDPOINT = "/orders";
    private static final String INGREDIENT_ENDPOINT = "/ingredients";


    @Step("Отправка запроса на получение ингредиентов")
    @Override
    public ValidatableResponse getIngredientsRequest() {
        return requestSpecification()
                .when()
                .get(INGREDIENT_ENDPOINT)
                .then().log().all();
    }

    @Step("Отправка запроса на создание заказа без авторизации")
    @Override
    public ValidatableResponse createOrderRequestWithoutToken(Order order) {
        return requestSpecification()
                .body(order)
                .when()
                .post(CREATE_ORDER_ENDPOINT)
                .then().log().all();
    }

    @Step("Отправка запроса на cоздание заказа с авторизацией")
    @Override
    public ValidatableResponse createOrderRequest(Order order, String token) {
        return requestSpecification()
                .header("Authorization", token)
                .body(order)
                .when()
                .post(CREATE_ORDER_ENDPOINT)
                .then().log().all();
    }

    @Step("Отправка запроса на получение заказов пользователя")
    @Override
    public  ValidatableResponse getOrdersRequest(String token) {
        return requestSpecification()
                .header("Authorization", token)
                .when()
                .get(CREATE_ORDER_ENDPOINT)
                .then().log().all();
    }

    @Step("Отправка запроса на получение заказов пользователя без авторизационного токена")
    @Override
    public  ValidatableResponse getOrdersRequestWithoutToken() {
        return requestSpecification()
                .when()
                .get(CREATE_ORDER_ENDPOINT)
                .then().log().all();
    }
}

package api.stellarburgers.orders;

import api.stellarburgers.orders.ingredients.Ingredient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static api.stellarburgers.UserHelper.getAccessTokenFromCreatedNewUser;

public class CreateOrderTest extends AbstractOrderTest{
    String token;
    @Before
    public void getUserToken() {
        token = getAccessTokenFromCreatedNewUser();
    }

    @DisplayName("Создание заказа с авторизацией и ингериентами - тело ответа")
    @Description("Ожидание: в ответе возвращается success: true")
    @Test
    public void createOrderSuccessfullyCheckBody() {
        Order order = OrderHelper.createOrderWithIngredients();
        ValidatableResponse response = client.createOrderRequest(order, token);
        check.bodyContainsSuccessTrue(response);
    }

    @DisplayName("Создание заказа с авторизацией и ингериентами - поле номер заказа")
    @Description("Ожидание: в ответе возвращается не пустой номер заказа")
    @Test
    public void createOrderSuccessfullyCheckFieldOrderNumberIsNotNull() {
        Order order = OrderHelper.createOrderWithIngredients();
        ValidatableResponse response = client.createOrderRequest(order, token);
        orderCheck.fieldOrderNumberIsNotNull(response);
    }

    @DisplayName("Создание заказа c авторизацией и ингридиентами - код ответа")
    @Description("Ожидание: в ответе возвращается код 200")
    //в документации не указано как код ждем, согласовать 200 или 201
    @Test
    public void createOrderCheckResponseCode() {
        Order order = OrderHelper.createOrderWithIngredients();
        ValidatableResponse response = client.createOrderRequest(order, token);
        check.responseCode20OHttpOk(response);
    }

    @DisplayName("Создание заказа c авторизацией и без ингридиентов - тело ответа")
    @Description("Ожидание: в ответе возвращается success: false")
    @Test
    public void createOrderWithoutIngredientsCheckBody() {
        Order order = new Order(new ArrayList<>());
        ValidatableResponse response = client.createOrderRequest(order, token);
        check.bodyContainsSuccessFalse(response);
    }

    @DisplayName("Создание заказа c авторизацией и без ингридиентов - код ответа")
    @Description("Ожидание: в ответе возвращается код 400")
    @Test
    public void createOrderWithoutIngredientsCheckResponseCode() {
        Order order = new Order(new ArrayList<>());
        ValidatableResponse response = client.createOrderRequest(order, token);
        check.responseCode400BadRequest(response);
    }

    @DisplayName("Создание заказа c авторизацией и неверным хешем ингридиентов - тело ответа")
    @Description("Ожидание: в ответе возвращается код 400")
    @Test
    public void createOrderInvalidIngredientsCheckBody() {
        ArrayList<String> fakeIngredients =  OrderHelper.getFakeIngredientsId();
        Order order = new Order(fakeIngredients);
        ValidatableResponse response = client.createOrderRequest(order, token);
        check.bodyContainsSuccessFalse(response);
    }

    @DisplayName("Создание заказа c авторизацией и неверным хешем ингридиентов - код ответа")
    //по документации - ожидание - код 500, но это неправильно, ошибка критична, уточнить код, оставила пока 400
    @Description("Ожидание: в ответе возвращается код 400")
    @Test
    public void createOrderInvalidIngredientsCheckResponseCode() {
        ArrayList<String> fakeIngredients =  OrderHelper.getFakeIngredientsId();
        Order order = new Order(fakeIngredients);
        ValidatableResponse response = client.createOrderRequest(order, token);
        check.responseCode400BadRequest(response);
    }

    @DisplayName("Создание заказа без авторизационного токена - тело ответа")
    @Description("Ожидание: в ответе возвращается success: false")
    //предполагаю, что создание заказа без авторизации не допустимо, обсудить
    @Test
    public void createOrderWithoutAuthorizationCheckBody() {
        ArrayList<Ingredient> allIngredients =  OrderHelper.getIngredients();
        ArrayList<String> listIngredients =  OrderHelper.prepareListIngredient(allIngredients);
        Order order = new Order(listIngredients);
        ValidatableResponse response = client.createOrderRequestWithoutToken(order);
        check.bodyContainsSuccessFalse(response);
    }

    @DisplayName("Создание заказа без авторизационного токена - код ответа")
    @Description("Ожидание: в ответе возвращается код 401")
    @Test
    public void createOrderWithoutAuthorizationCheckResponseCode() {
        ArrayList<Ingredient> allIngredients =  OrderHelper.getIngredients();
        ArrayList<String> listIngredients =  OrderHelper.prepareListIngredient(allIngredients);
        Order order = new Order(listIngredients);
        ValidatableResponse response = client.createOrderRequestWithoutToken(order);
        check.responseCode401Unauthorized(response);
    }
}

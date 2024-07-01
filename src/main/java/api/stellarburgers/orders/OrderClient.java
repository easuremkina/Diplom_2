package api.stellarburgers.orders;

import io.restassured.response.ValidatableResponse;

public interface OrderClient {
    ValidatableResponse createOrderRequestWithoutToken(Order order);
    ValidatableResponse createOrderRequest(Order order, String token);
    ValidatableResponse getOrdersRequest(String token);
    ValidatableResponse getOrdersRequestWithoutToken();
    ValidatableResponse getIngredientsRequest();
}

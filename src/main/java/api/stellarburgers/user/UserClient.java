package api.stellarburgers.user;

import io.restassured.response.ValidatableResponse;

public interface UserClient {
    ValidatableResponse createUserRequest(PersData userPersData);
    ValidatableResponse loginRequest(Credentials credentials);
    ValidatableResponse updateUserRequest(User user);
    void deleteUserRequest(String accessToken);
}

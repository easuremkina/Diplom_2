package api.stellarburgers.user;

import io.restassured.response.ValidatableResponse;
import lombok.Data;

@Data
public class User {
    private PersData persData;
    private String accessToken;
    private ValidatableResponse loginResponse;
    public User(PersData userPersData){
        this.persData = userPersData;
    }

    public User(PersData userPersData, ValidatableResponse loginResponse){
        this.persData = userPersData;
        this.loginResponse = loginResponse;
    }
}

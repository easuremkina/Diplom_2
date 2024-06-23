package api.stellarburgers.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class CreateUserTest extends AbstractUserTest {
    @DisplayName("Успешное создание уникального пользователя - тело ответа")
    @Description("Ожидание: в ответе возвращается success: true")
    @Test
    public void createUserSuccessfullyCheckBody() {
        User user = new User(PersData.createRandom());
        ValidatableResponse response = client.createUserRequest(user.getPersData());
        user_list_for_disposal.add(user);
        check.bodyContainsSuccessTrue(response);
    }

    @DisplayName("Успешное создание пользователя - код ответа")
    @Description("Ожидание: код ответа - 200 ОК")
    @Test
    public void createUserCheckResponseCode() {
        User user = new User(PersData.createRandom());
        ValidatableResponse response = client.createUserRequest(user.getPersData());
        user_list_for_disposal.add(user);
        check.responseCode20OHttpOk(response);
        //реализован код ответа 200, можно уточнить, скорее всего и по хорошему, ожидание тут 201
    }

    @DisplayName("Успешное создание уникального пользователя - тело ответа содержит email пользователя")
    @Description("Ожидание: в ответе возвращается email , заданный при регистрации")
    @Test
    public void createUserSuccessfullyCheckFieldEmail() {
        User user = new User(PersData.createRandom());
        ValidatableResponse response = client.createUserRequest(user.getPersData());
        user_list_for_disposal.add(user);
        createUserCheck.fieldEmailContainsUsersEmail(response, user.getPersData().getEmail());
    }

    @DisplayName("Создание пользователя, который уже зарегистрирован - тело ответа")
    @Description("Ожидание: тело ответа содержит success: false")
    @Test
    public void createExistentUserCheckBody() {
        User user = new User(PersData.createRandom());
        client.createUserRequest(user.getPersData());
        user_list_for_disposal.add(user);
        ValidatableResponse response = client.createUserRequest(user.getPersData());
        check.bodyContainsSuccessFalse(response);
    }

    @DisplayName("Создание пользователя, который уже зарегистрирован - код ответа")
    @Description("Ожидание: код ответа - 403")
    @Test
    public void createExistentUserCheckResponseCode() {
        User user = new User(PersData.createRandom());
        client.createUserRequest(user.getPersData());
        user_list_for_disposal.add(user);
        ValidatableResponse response = client.createUserRequest(user.getPersData());
        check.responseCode403UForbidden(response);
    }

    @DisplayName("Создание пользователя, без заполенного поля имени - тело ответа")
    @Description("Ожидание: тело ответа содержит success: false")
    @Test
    public void createUserWithoutPasswordCheckBody() {
        User user = new User(PersData.createUserWithoutFieldName());
        ValidatableResponse response = client.createUserRequest(user.getPersData());
        check.bodyContainsSuccessFalse(response);
    }

    @DisplayName("Создание пользователя, без заполенного поля имени - код ответа")
    @Description("Ожидание: код ответа 403")
    @Test
    public void createUserWithoutPasswordCheckResponseCode() {
        User user = new User(PersData.createUserWithoutFieldName());
        ValidatableResponse response = client.createUserRequest(user.getPersData());
        check.responseCode403UForbidden(response);
    }
}

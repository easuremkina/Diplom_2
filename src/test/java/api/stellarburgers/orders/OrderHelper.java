package api.stellarburgers.orders;

import api.stellarburgers.Faker;
import api.stellarburgers.orders.checks.CreateOrderChecks;
import api.stellarburgers.orders.ingredients.Ingredient;
import api.stellarburgers.orders.ingredients.IngredientType;
import api.stellarburgers.orders.ingredients.IngredientsResponse;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.Objects;

public class OrderHelper extends AbstractOrderTest{
    @Step("Получение списка всех ингредиентов")
    public static ArrayList<Ingredient> getIngredients() {
        ValidatableResponse response = client.getIngredientsRequest();
        IngredientsResponse ingredientsResponse = CreateOrderChecks.extractIngredients(response);
        return ingredientsResponse.getData();
    }

    @Step("Создание набора ингредиентов для заказа")
    public static ArrayList<String> prepareListIngredient(ArrayList<Ingredient> ingredients){
        ArrayList<String> ingredientsId = new ArrayList<>();

        // собираем id разных ингредиентов в один список
        ingredientsId.add(getAnyIngredientIdFromIngredientType(ingredients, IngredientType.BUN));
        ingredientsId.add(getAnyIngredientIdFromIngredientType(ingredients, IngredientType.MAIN));
        ingredientsId.add(getAnyIngredientIdFromIngredientType(ingredients, IngredientType.SAUCE));

        return ingredientsId;
    }

    @Step("Получение списка ингридиентов с неверным хешем")
    public static ArrayList<String> getFakeIngredientsId(){
        return Faker.getFakeIngredients();
    }

    @Step("Получение id ингредиента по заданному типу")
    public static String getAnyIngredientIdFromIngredientType(ArrayList<Ingredient> ingredients, IngredientType ingredientType) {
        Ingredient ingredientBun = ingredients.stream()
                .filter(ingredient -> Objects.equals(ingredient.getType(), ingredientType.getMode()))
                .findFirst()
                .orElseThrow();
        return ingredientBun.get_id();
    }

    @Step("Создание заказа с ингредиентами")
    public static Order createOrderWithIngredients () {
        ArrayList<Ingredient> allIngredients =  OrderHelper.getIngredients();
        ArrayList<String> listIngredients =  OrderHelper.prepareListIngredient(allIngredients);
        return new Order(listIngredients);
    }

}

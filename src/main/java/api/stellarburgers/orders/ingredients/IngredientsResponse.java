package api.stellarburgers.orders.ingredients;

import lombok.Data;

import java.util.ArrayList;

@Data
public class IngredientsResponse {
    Boolean success;
    private ArrayList<Ingredient> data;
}

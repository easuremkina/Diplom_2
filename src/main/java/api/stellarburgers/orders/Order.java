package api.stellarburgers.orders;


import lombok.Data;

import java.util.ArrayList;

@Data
public class Order {
    private ArrayList<String> ingredients;
    public Order(ArrayList<String> ingredients){
        this.ingredients = ingredients;
    }
}

package api.stellarburgers.orders.ingredients;

public enum IngredientType {
    BUN("bun"),
    MAIN("main"),
    SAUCE("sauce");
    private final String ingredientType;
    IngredientType(String ingredientType) {
        this.ingredientType = ingredientType;}
    public String getMode() {return  ingredientType;}
}

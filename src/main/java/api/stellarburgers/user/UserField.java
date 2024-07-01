package api.stellarburgers.user;

public enum UserField {
    EMAIL("email"),
    PASSWORD("password"),
    NAME("name");
    private final String field;
    UserField(String field) {
        this.field = field;
    }
}

package api.stellarburgers.user;

import org.apache.commons.lang3.RandomStringUtils;

public class Credentials {
    private String email;
    private String password;
    public Credentials() {
    }

    private Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Credentials fromUser(PersData userPersData) {
        return new Credentials(userPersData.getEmail(), userPersData.getPassword());
    }

    public  static Credentials fromUserWhitIncorrectPassword(PersData userPersData) {
        return new Credentials(userPersData.getEmail(), RandomStringUtils.randomAlphabetic(10, 15) );
    }

    public  static Credentials fromUserWhitIncorrectEmailAndPassword(PersData userPersData) {
        return new Credentials("nonexist_" + userPersData.getEmail(),
                "_" + userPersData.getPassword() );
    }
}

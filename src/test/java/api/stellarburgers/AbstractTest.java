package api.stellarburgers;

import api.stellarburgers.user.User;
import api.stellarburgers.user.UserClientImpl;
import org.junit.AfterClass;

import java.util.ArrayList;

import static api.stellarburgers.UserHelper.getAccessToken;

public class AbstractTest {
    protected static final UserClientImpl client = new UserClientImpl();
    protected static final Checks check = new Checks();
    protected static final ArrayList<User> user_list_for_disposal = new ArrayList<>();
    @AfterClass
    public static void deleteUser() {
        System.out.println(user_list_for_disposal);
        for (User user : user_list_for_disposal) {
            String accessToken = getAccessToken(user);
            client.deleteUserRequest(accessToken);
        }
    }
}

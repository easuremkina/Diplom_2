package api.stellarburgers.user;

import api.stellarburgers.AbstractTest;
import api.stellarburgers.user.checks.CreateUserChecks;
import api.stellarburgers.user.checks.LoginChecks;
import api.stellarburgers.user.checks.UpdateUserChecks;

public  abstract class AbstractUserTest extends AbstractTest {

    protected static final CreateUserChecks createUserCheck = new CreateUserChecks();
    protected static final LoginChecks loginCheck = new LoginChecks();
    protected static final UpdateUserChecks updateUserCheck = new UpdateUserChecks();
}

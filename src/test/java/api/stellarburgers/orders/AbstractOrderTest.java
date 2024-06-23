package api.stellarburgers.orders;

import api.stellarburgers.AbstractTest;
import api.stellarburgers.orders.checks.GetOrdersChecks;
import api.stellarburgers.orders.checks.CreateOrderChecks;

public abstract class AbstractOrderTest extends AbstractTest {
    protected static final OrderClientImpl client = new OrderClientImpl();
    protected static final CreateOrderChecks orderCheck = new CreateOrderChecks();
    protected static final GetOrdersChecks getOrdersCheck = new GetOrdersChecks();
}

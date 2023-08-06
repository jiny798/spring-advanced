package jiny.proxy.pureproxy.concreteproxy;

import jiny.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import jiny.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {
    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }
}
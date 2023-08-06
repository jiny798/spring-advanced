package jiny.proxy.pureproxy.concreteproxy;

import jiny.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import jiny.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import jiny.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {
    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        // TimeProxy 는 원본 코드를 가지는 ConcreteLogic 을 상속 받고 다형성에 의해 전달 가능
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.execute();
    }
}
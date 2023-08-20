package jiny.proxy.jdkdynamic;

import jiny.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        // AInterface의 메서드 정보가 handler 의 invoke 메서드의 인자 Method에 들어간다
        // 메서드가 달라도 로직을 호출하는 메서드 하나만(invoke) 쓰고도 공통 로직을 적용할 수 있다고 했다
        // 자동으로 AIterface 가 가지는 모든 메서드에 공통 로직을 적용하고, 메서드를 호출하는 부분을 handler의 invoke 라는 메서드 하나로 통일했다.
        // newProxyInstance() 는 위와 같이 적용하면서 프록시 객체를 만들어준다
        proxy.call();
        log.info("-----------------------------");
        proxy.call2();
        
        log.info("target class = {}",target.getClass());
        log.info("proxy class = {}",proxy.getClass());

    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class},handler);

        proxy.call();
        log.info("target class = {}",target.getClass());
        log.info("proxy class = {}",proxy.getClass());
        //dynamicA 에서의 프록시와 다르다. 동적으로 만든다.

    }
}

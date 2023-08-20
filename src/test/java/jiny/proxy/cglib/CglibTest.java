package jiny.proxy.cglib;

import jiny.proxy.cglib.code.TimeMethodInterceptor;
import jiny.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {
    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);  // 타켓 클랙스 , 해당 클래스를 상속 받아서 프록시가 만들어진다
        enhancer.setCallback(new TimeMethodInterceptor(target)); // 공통로직+ (모든 실제 메서드호출) + 공통로직 -> 프록시에 적용할 로직
        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.call();
    }
}
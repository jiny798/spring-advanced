package jiny.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    void reflection0() {
        Target target = new Target();

        log.info("A 메서드 호출");
        String resultA = target.callA(); /// X()
        log.info("A 메서드 결과 = {}", resultA);

        log.info("B 메서드 호출");
        String resultB = target.callB(); /// X()
        log.info("B 메서드 결과 = {}", resultB);

    }

    @Slf4j
    static class Target {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }

    @Test
    void reflection1() throws Exception{
        Class classTarget = Class.forName("jiny.proxy.jdkdynamic.ReflectionTest$Target"); //클래스의 정보를 불러온다

        Target target = new Target();

        Method methodCallA = classTarget.getMethod("callA");
        Object result1 = methodCallA.invoke(target); // Method.invoke 로 통일
        log.info("result1={}",result1);

        Method methodCallB = classTarget.getMethod("callB");
        Object result2 = methodCallB.invoke(target); // Method.invoke 로 통일
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        Class classTarget = Class.forName("jiny.proxy.jdkdynamic.ReflectionTest$Target"); //클래스의 정보를 불러온다

        Target target = new Target();
        Method methodCallA = classTarget.getMethod("callA");
        Method methodCallB = classTarget.getMethod("callB");

        dynamicCall(methodCallA,target);
        dynamicCall(methodCallB,target);
    }

    private void dynamicCall(Method method, Object object) throws Exception {
        log.info("dynamicCall start");
        Object result = method.invoke(object);
        log.info("result = {}",result);
    }
}
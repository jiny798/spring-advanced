package jiny.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AImpl implements AInterface {
    @Override
    public String call() {
        log.info("A 호출");
        return "a";
    }

    @Override
    public String call2() {
        log.info("A2 호출");
        return "aa";
    }
}
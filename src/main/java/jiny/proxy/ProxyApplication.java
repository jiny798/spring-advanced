package jiny.proxy;

import jiny.advanced.trace.logtrace.LogTrace;
import jiny.advanced.trace.logtrace.ThreadLocalLogTrace;
import jiny.proxy.config.AppV1Config;
import jiny.proxy.config.AppV2Config;
import jiny.proxy.config.v1_proxy.InterfaceProxyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import({AppV1Config.class, AppV2Config.class})
@Import(InterfaceProxyConfig.class)
@SpringBootApplication(scanBasePackages = "jiny.proxy.app") //주의
public class ProxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }
    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
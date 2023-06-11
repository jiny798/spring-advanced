package jiny.advanced;

import jiny.advanced.trace.logtrace.FieldLogTrace;
import jiny.advanced.trace.logtrace.LogTrace;
import jiny.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }

}

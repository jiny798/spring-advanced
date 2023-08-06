package jiny.advanced.trace.callback;

import jiny.advanced.trace.TraceStatus;
import jiny.advanced.trace.logtrace.LogTrace;

public class TraceTemplate {
    private final LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    //callback을 필드로 두지않고 직접 구현해서 넘겨주어야 함. (템플릿 콜백 패턴)
    public <T> T execute(String message, TraceCallback<T> callback) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            T result = callback.call();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}

package jiny.advanced.trace;

public class TraceStatus {
    private TraceId traceId;
    private Long startTimeMs; //로그 종료시 이 시작 시간을 기준으로 전체 수행 시간을 구할 수 있다.
    private String message; // 시작시 사용한 메세지

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }
    public Long getStartTimeMs() {
        return startTimeMs;
    }
    public String getMessage() {
        return message;
    }
    public TraceId getTraceId() {
        return traceId;
    }
}

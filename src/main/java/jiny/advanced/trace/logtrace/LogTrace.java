package jiny.advanced.trace.logtrace;

import jiny.advanced.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status,Exception e);
}

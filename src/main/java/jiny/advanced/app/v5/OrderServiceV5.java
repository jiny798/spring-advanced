package jiny.advanced.app.v5;

import jiny.advanced.trace.callback.TraceTemplate;
import jiny.advanced.trace.logtrace.LogTrace;
import jiny.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;
    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        template.execute("OrderService.request()",()->{
            orderRepository.save(itemId);
            return null;
        });
    }
}

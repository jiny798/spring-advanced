### [인프런] 스프링 핵심 원리 - 고급편 정리

1. [스레드 로컬](https://velog.io/@jiny798/ThreadLocal-In-Java)
2. 템플릿 메서드 패턴
    - 좋은 설계는 변하는 것과 변하지 않는 것을 분리하는 것

```java
//OrderServiceV0 코드
public void orderItem(String itemId){
    orderRepository.save(itemId);
}
```
[V0 코드]
- 로그 추적 코드가 없는 코드

<br/>

```java
// OrderService V3 코드
public void orderItem(String itemId) {
    TraceStatus status = null;
    try {
        status = trace.begin("OrderService.orderItem()");
        orderRepository.save(itemId); //핵심 기능
        trace.end(status);
    } catch (Exception e) {
        trace.exception(status, e);
        throw e;
    }
}
```
[V3 코드] Service -> Repository 호출
- trace가 begin() 호출시, 내부에서 traceId 필드를 통해 로그와 깊이를 표현하고,
- status를 반환한다. 그리고 서비스 로직이 끝나면 status를 end() 메서드에 넘겨서 시간을 출력한다.
- Service에서 Repository를 호출시, traceId를 인자로 넘겨서 깊이가 증가된 값으로 trace를 생성(동기화)하는 방식에서 스레드로컬 사용하는 방식이므로 인자는 사용자에게 받은 itemId만 사용

<br/>

```java
//OrderService V4 코드
AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
    @Override
    protected Void call() {
        orderRepository.save(itemId);
        return null;
    }
};
template.execute("OrderService.orderItem()");
```

[V4 코드]
- 공통 코드는 AbstraceTemplate의 execute에 정의하고
- call 추상 메서드만 구현하는 형태
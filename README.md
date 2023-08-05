## [인프런] 스프링 핵심 원리 - 고급편 정리

### 1. [스레드 로컬](https://velog.io/@jiny798/ThreadLocal-In-Java)
### 2. 템플릿 메서드 패턴
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
call 추상 메서드만 구현하는 형태
- V3 의 경우는 모두 찾아서 수정해야한다.
로그만 변경해야 하면 AbstractTemplate 코드를 수정하면된다.

- 단일 책임 원칙(SRP)을 지킬 수 있게된 구조로 변경되었음

#### 여기서 템플릿 메서드 패턴은 상속을 사용하는 것을 확인
- 하지만 자식 클래스 입장에서는 부모 클래스의 기능을 전혀 사용하지 않는다
- 또한 상속은 특정 부모 클래스를 강하게 의존하게 된다.
- 이러한 의존이라는 단점과 부모 클래스의 기능을 사용하지 않는데, 부모 클래스를 받도록 
설계하는 것은 잘못된 의존 관계라고 볼 수 있고,
- 잘못된 의존 관계는 추후에 부모 클래스를 수정하면, 자식 클래스에 영향을 줄 수 있다
- 상속의 단점을 커버할 수 있는 전략 패턴이 있다.

<br/>

### 3. 전략패턴
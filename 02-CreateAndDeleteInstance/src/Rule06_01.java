import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by gunny on 2017. 7. 24..
 *
 * 6. 유효기간이 지난 객체 참조는 폐기하라.
 *
 * C나 C++처럼 손수 메모리 관리를 해야 하는 언어를 쓰다가 쓰레기 수집가가 포함된 언어를 사용하기
 *
 * 시작하면 프로그래밍이 아주 쉬워진다. 그래서 결국 메모리 관리가 필요하다는 사실을 망각하게 되기도
 *
 * 하는데 그러면, 곤란하다.
 */
public class Rule06_01 {
}
// 메모리 누수(memory leak)가 어디서 생기는지 보이는가?
class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if(size == 0)
            throw new EmptyStackException();

        // 객체 참조를 null 처리 하는 것은 규범(norm)이라기 보단 예외 적인 조치가 되어야 한다.
        /*
            Stack이 자체적으로 메모리를 관리한 다는 것이 문제다.

            이 저장공간 풀(storage pool)은 elements 배열의 원소들이다.
            이 배열에서 실제로 사용되는 부분에 있는 원소가 참조하는 객체는 할당된 객체지만
            나머지 원소가 참조하는 개체는 반환 가능한 객체이다.

            하지만 GC는 그걸 알 도리가 없다.
            사용하지 않는 참조들을 즉시 null로 만들어 버리면 GC는 반화해도 좋은 객체가
            어떤 것인지 바로 알 수 있다.

            일반적으로, 자체적으로 관리하는 메모리가 있는 클래스를 만들 때는 메모리 누수가 발생하지 않도록
            주의 해야한다.
         */
        Object result = elements[--size];
        elements[size] = null; // 쓸모없는 참조 제거.
        return result;


        /*
            Stack이 커졌다가 줄어 드면서 제거한 객체들을 쓰레기 수집기가 처리하지 못해서 생긴다.
            Stack을 사용한는 프로그램이 그 객체들을 더 이상 참조하지 않는데도 말이다.
            Stack이 그런 객체에 대한 만기참조를 제거하지 않기 때문이다.
            만기참조(obsolete reference) : 다시 이용되지 않을 참조를 말한다.
        */
        // return elements[--size];
    }

    /**
     * 적어도 하나 이상의 원소를 담을 공간을 보장한다.
     * 배열의 길이를 늘려야 할 때마다 대략 두 배씩 늘인다.
     */
    private void ensureCapacity() {

        if(elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}

/*
 캐시(cache)도 메모리 누수가 흔히 발생하는 장소다. 객체 참조를 캐시 안에 넣어 놓고 잊어버리는 일이 많기 때문.
 1. WeakHashMap을 가지고 캐시를 구현하는 것이다.
    캐시 바깥에서 키(key)를 참조하고 있을 떄만 값(value)을 보관하면 될 때 쓸 수 있는 전략이다.
    키에 대한 참조가 만기 참조가 되는 순간 캐시 안에 보관된 키-값 쌍은 자동으로 삭제되기 때문.
    WeakHashMap은 캐시 안에 보관되는 항목의 수명이 키에 대한 외부 참조의 수명에 따라 결정되는 상황에만
    적용가능하다는 것을 기억하자.
 2. 하지만 일반적으로 캐시에 보관되는 항목의 수명은 캐시에 보관된 기간에 따라 결정되는 것이 보통이다.
    그런 캐시는 이따금씩 사용하지 않는 항목들을 비워야한다. 이런 작업은 후면 스레드(backgraound thread)를
    사용해 처리할 수 도 있고(Timer나 ScheduledThreadPoolExecutor 사용), 캐시에 새로운 항목을
    추가할 때 처리할 수도 있다. LinkedHashMap 클래스를 사용하면 두 번째 접근법을 구현하기 좋은데,
    removedEldestEntry 메소드가 제공되기 때문이다.
*/

/*
    메모리 누수가 흔히 발견되는 또 한 곳은 리스너(listener) 등의 역호출자(callback)다.
 */
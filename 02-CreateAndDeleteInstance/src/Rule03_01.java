/**
 * Created by gunny on 2017. 7. 17..
 *
 * 싱글턴은 객체를 하나만 만들 수 있는 클래스이다
 *
 * 싱글턴은 보통 유일할 수 밖에 없는 시스템 컴포넌트를 나타낸다.
 *
 * 그런데 클래스를 싱글턴으로 만들면 클라이언트를 테스트하기가 어려워질 수 가 있다.
 *
 * JDK 1.5 이전에는 싱글턴을 구현하는 방법이 두가지 였다.
 *
 * 두 방법 다 생성자는 private로 선언하고, 싱글턴 객체는 정적(static) 멤버를 통해 이용한다.
 *
 * 첫번 째 방법의 경우, 정적 멤버는 final로 선언한다.
 */
public class Rule03_01 {
    public static void main(String args[]) {
        ElvisFinal eF = ElvisFinal.INSTANCE;
    }
}
// public final 필드를 이용한 싱글턴
class ElvisFinal{
    /*
        private 생성자는 public static final 필드인 Elvis.Instance를 초기화 할때 한번만 호출된다.

        public이나 protected로 선언된 생성자가 없으므로, Elvis객체는 일단 Elvis 클래스가 초기화 되고 나면
        하나만 존재하게 된다.

        클라이언트가 이 상태를 변경할 방법은 없지만 주의할 것이 하나 있다.

        AccessibleObject.setAccessible 메소드의 도움을 받아 권한을 획득한 클라이언트는 리플렉션 기능을 통해

        private 생성자를 호출할 수 있다는 것이다. 이런 종류의 공격을 방어하고 싶다면, 두 번째 객체를 생성하라는 요청

        을 받으면 예외를 던지도록 생성자를 고쳐야 한다.

      */

    public static final ElvisFinal INSTANCE = new ElvisFinal();

    private ElvisFinal() { }

    public void leaveTheBuilding() {}
}

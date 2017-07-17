/**
 * Created by gunny on 2017. 7. 17..
 *
 * Utility class 들은 객체를 만들 목적의 클래스가 아니다.
 * 객체를 만들면 오히려 이상하다. 하지만 생성자를 생략하면 컴파일러 자동으로 인자 없는 public 기본 생성자를 만들어 버린다.
 *
 * 사용자는 이 생성자를 일반 생성자와 구별할 수 없다.
 *
 * 객체를 만들수 없도록 하려고 클래스를 abstract로 선언해 봤자 소용없다. 하위클래스를 정의하는 순간 객체 생성이 가능해지기 때문이다.
 * 게다가 abstract 클래스니깐 계승해서 사용하는 것이 맞다고 착각하는 사용자도 있을 수 있다.
 *
 * private 생성자를 넣어 객체 생성을 방지하자는 것이다.
 */
public class Rule04 {

}
// 명시적으로 정의된 생성자가 private이므로 클래스 외부에서는 사용할 수 없다.
class UtilityClass {
    private UtilityClass() {
        throw new AssertionError();
    }
}
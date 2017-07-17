/**
 * Created by gunny on 2017. 7. 17..
 *
 * enum을 이용한 싱글턴
 *
 * 이 접근법은 기능적으로는 public 필드를 사용하는 구현법과 동등하다.
 *
 * 한가지 차이는 좀더 간결하다는 것과, 직렬화가 자동으로 처리된다는 것이다.
 *
 * 직렬화가 아무리 복잡하게 이루어져도 여러 객체가 생길일이 없으며, 리플렉션을
 *
 * 통한 공격에도 안전하다.
 *
 * 원소가 하나뿐인 enum 자료형이야말로 싱글턴을 구현하는 가장 좋은 방법이다.
 */
public class Rule03_03 {
    public static void main(String[] args) {
        ElvisEnum eE = ElvisEnum.INSTANCE;
    }
}

enum ElvisEnum {
    INSTANCE;

    public void leaveTheBuilding() {}
}

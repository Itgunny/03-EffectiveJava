import java.util.IllegalFormatCodePointException;

/**
 * Created by gunny on 2017. 7. 13..
 *
 * 3. static factory method를 사용하면 return 할 객체의 타입을 유연하게 선택할 수 있다.
 *
 *    - 스태틱 팩토리 메소드가 인터페이스 타입을 리턴하는 인터페이스 기반의 프레임워크에 들어 맞는다.
 *    - 스태틱 메소드를 사용하면 리턴받는 객체를 실제 구현 클래스 타입이 아닌 인터페이스 타입만 참조하도록 강제하는 장점
 *      도 있다.
 *    - 전달받은 인자에 따라 어떤 타입의 객체를 리턴할 지 선택할 수 있다.
 *
 *    단점 : 메소드를 정의한 클래스가 public이나 protected 생성자를 제공하지 않으면, 다른 클래스가 이 클래스를
 *          상속받을 수 없다.
 *          다른 스태틱 메소드와의 차이를 명시할 수 없다.
 */
public class Rule01_03 {
    public static void main(String[] args) {
        Employee emp = Employee.create("Engineer");
        Employee emp2 = Employee.create(2);

    }
}

class Employee {
    public static final int ENGINEER    = 1;
    public static final int SALESMAN    = 2;
    public static final int MANAGER     = 3;

    private Employee() {}

    static Employee create(int type) {
        switch(type) {
            case ENGINEER:
                return create("Engineer");
            case SALESMAN:
                return create("Salesman");
            case MANAGER:
                return create("Manager");
            default:
                throw new IllegalArgumentException("Incorrect type code value");
        }
    }

    static Employee create(String name) {
        try {
            return (Employee) Class.forName(name).newInstance();
        } catch(Exception e) {
            throw new IllegalArgumentException("Incorrect type cond value");
        }
    }


}

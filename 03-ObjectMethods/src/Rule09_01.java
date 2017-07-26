import java.util.HashMap;
import java.util.Map;

/**
 * Created by gunny on 2017. 7. 25..
 *
 * Rule 09. equals를 재정의할 때는 반드시 hashCode도 재정의하라.
 *
 * equals 메소드를 재정의하는 클래스는 반드시 hashCode 메소드도 재정의 해야한다.
 * 그렇지 않으면 Object.hashCode의 일반 규약을 어기게 되므로, HashMap, HashSet, Hashtable
 * 같은 해시(hash) 기반 컬렉션과 함께 사용하면 오동작하게 된다.
 *
 * 1. 응용프로그램 실행 중에 같은 객체의 hashCode를 여러번 호출하는 경우, equals가 사용하는 정보들이 변경되지
 *    않았다면, 언제나 동일한 정수가 반환되어야 한다.
 * 2. equals(Object) 메소드가 같다고 판정한 두 객체의 hashCode 값은 같아야 한다.
 * 3. equals(Object) 메소드가 다르다고 판정한 두 객체의 hashCode 값은 꼭 다를 필요는 없다.
 *    그러나 서로 다른 hashCode 값이 나오면 해시 테이블의 성능이 향상될 수 있다는 점은 이해하고 있어야한다.
 */

/*
    hashCode를 재정의하지 않으면 위반되는 핵심 규약은 두번째다.
    같은 객체는 같은 해시코드 값을 가져야 한다는 규약이 위반되는 것이다.
 */
public class Rule09_01 {
    public static void main(String[] args) {
        /*
            기존에 들어갔던 jenny가 반환되지 않고 null이 반환된다.
            왜냐하면 hashCode 메소드를 재정의 하지 않아서 생긴 문제이다.
         */
        Map<PhoneNumber, String> m = new HashMap<PhoneNumber, String>();
        m.put(new PhoneNumber(707, 806, 5309), "Jenny");
        m.get(new PhoneNumber(707, 867, 5309)); // null 반환



    }
}

final class PhoneNumber {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        this.areaCode = (short)areaCode;
        this.prefix = (short)prefix;
        this.lineNumber = (short)lineNumber;
    }

    private static void rangeCheck(int arg, int max, String name) {
        if(arg < 0 || arg > max) {
            throw new IllegalArgumentException(name + ": " + arg);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(!(obj instanceof PhoneNumber))
            return false;
        PhoneNumber pn = (PhoneNumber) obj;
        return pn.lineNumber == lineNumber
                && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    // hashCode 메소드가 없으므로 문제가 발생한다.

    // 가장 끔직한 형태의 해시 함수, 절대로 이렇게 구현하지 말 것.
    // 모든 객체가 같은 해시코드를 가지게 되니 끔직하다.
    @Override
    public int hashCode() {
        return 42;
    }
}
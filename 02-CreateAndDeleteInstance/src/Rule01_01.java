import java.math.BigInteger;
import java.util.Random;

/**
 * Created by gunny on 2017. 7. 12..
 *
 * 생성자 대신 정적 팩터리 메소드(static factory method)를 사용할 수 없는지 생각해보라.
 * 1. static factory method는 알맞은 이름을 줄 수 있다.
 *
 */
public class Rule01_01 {
    boolean b;

    // 클래스를 통해 객체를 만드는 일반적인 방법
    // 1. Constructor를 이용한 생성방법
    Rule01_01(boolean b) {
        this.b = b ? Boolean.TRUE: Boolean.FALSE;
    }
    // 2. public으로 선언된 정적 팩터리 메소드
    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE: Boolean.FALSE;
    }

    public static void main(String[] args) {
        // 인자들만 객체의 특징을 알기 어렵다.
        Rule01_01 r = new Rule01_01(true);
        BigInteger bi = new BigInteger(5, new Random());

        // 메소드 이름을 통하여 자신이 리턴하는 객체의 특징을 설명할 수 있다.
        Rule01_01.valueOf(true);
        BigInteger bi1 = BigInteger.probablePrime(5, new Random());
    }
}

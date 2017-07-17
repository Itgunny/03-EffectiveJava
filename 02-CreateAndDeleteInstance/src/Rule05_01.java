/**
 * Created by gunny on 2017. 7. 17..
 *
 * Rule 05 : 불필요한 객체를 만들지 마라.
 *
 * 객체를 재사용하는 프로그램은 더 빠르고 우아하다.
 *
 * 변경 불가능(immutable) 객체는 언제나 재사용할 수 있다.
 */
public class Rule05_01 {
    public static void main(String[] args) {
        String s = new String("stringgette");
        // 위의 문장이 순환문이나 자주 호출되는 메서드 안에 있다면, 수백만 개의 String객체가 쓸데 없이 만들어질 것이다.
        String s1 = "stringette"; // 이렇게 하면 객체를 만드는 대신, 동일한 String 객체를 사용한다.

        // 생성자와 정적 팩토리 메서드를 함께 제공하는 immutable class의 경우, 생성자 대신
        // 정적 팩터리 메서드를 이용하면 불필요한 객체 생성을 피할 수 있을 때가 많다.

        Boolean boolean_constructor = new Boolean(true);

        Boolean boolean_static = Boolean.valueOf(true);


    }
}

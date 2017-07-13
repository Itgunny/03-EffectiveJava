/**
 * Created by gunny on 2017. 7. 12..
 *
 * 2. static factory method는 생성자와 달리 호출될 때마다 새로운 객체를 생성하지 않아도 된다.
 *    ex) immutable class -> String
 */
public class Rule01_02 {
    public static void main(String[] args) {

        /*
         * 대표적인 Immutable class로는 String클래스가 있다.
         *
         * immutable class 설계는 내부 프로퍼티를 변경할 수 있도록 하는 public 인터페이스를 구현하지 않으면된다.
         * 즉, 객체의 어떤 메소드를 쓰더라도 한번 만들어진 내부 속성이 변하지 않게 된다.
         */
        String s = "Hello";
        String t = " " + "there";

        s.concat(t);
        s.toLowerCase();
        s += "myFriend";





    }

}

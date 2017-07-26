import java.util.HashMap;
import java.util.Map;

/**
 * Created by gunny on 2017. 7. 24..
 *
 * 1. Equals를 재정의 할때는 일반 규약을 따르라.
 *
 * eqauls 메서드는 재정의하기 쉬워 보이지만 실수할 여지도 많고, 그 결과는 끔직하다.
 *
 * 그런 문제를 피하는 가장 간단한 방법은 equals 메소드를 재정의하지 않는 것이다.
 *
 * 그 경우 모든 객체는 오직 자기 자신하고만 같다. 아래의 조건 가운데 하나라도 만족되면 그래도된다.
 */

/* equals를 재정의하지 않아도 되는 클래스
   1. 각각의 객체가 고유하다. 값(value) 대신 활성 개체(active entity)를 나타내는 Thread 같은 클래스가
      이 조건에 부합한다. 이런 클래스는 Object의 equals method를 그대로 사용해도 된다.
   2. 클래스에 "논리적 동일성(logical equality)" 검사 방법이 있건 없건 상관없다.
      일례로 java.util.Random 클래스는 두 Random 객체가 같은 난수열을 만드는지 검사하는 equals 메소드를
      재정의할 수도 있었지만, 이 클래스를 설계한 사람들은 클라이언트가 그런 기능을 원할 거라 생각하지 않았다.
   3. 상위 클래스에서 재정의한 equals가 하위 클래스에서 사용하기에도 적당하다.
   4. 클래스가 private 또는 package-private로 선언되었고, equals 메소드를 호출할 일이 없다.
 */

/*

 */
public class Rule08_01 {
    public static void main(String[] args) {
        Wallet a = new Wallet(1000, "BC_CARD");
        Wallet b = new Wallet(1000, "BC_CARD");
        Wallet c = new Wallet(2000, "BC_CARD");

        System.out.println(a==b);

        System.out.println(a.equals(b));
        System.out.println(b.equals(c));

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        //hashcode Test
        Map m = new HashMap();
        m.put(new Wallet(1000, "BC_CARD"), "My");

        System.out.println(m.get(new Wallet(1000, "BC_CARD")));

        m.put(a, "My2");
        System.out.println(m.get(a));

        System.out.println(a.equals(null));

        System.out.println(a.toString());

        System.out.println(a.getCard());

    }

}


class Wallet {
    private int money;
    private String card;

    public Wallet(int money, String card) {
        this.money = money;
        this.card = card;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Wallet)) {
            return false;
        }
        Wallet a = (Wallet) obj;
        return a.money == money && a.card == card;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + money;
        return result;
    }

    @Override
    public String toString() {
        return "card is " + card;
    }

    public int getMoney() {
        return money;
    }

    public String getCard() {
        return card;
    }
}

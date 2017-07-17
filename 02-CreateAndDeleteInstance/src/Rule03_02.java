/**
 * Created by gunny on 2017. 7. 17..
 *
 * Elvis.getInstance는 항상 같은 객체에 대한 참조를 반환한다. 이것 외의 Elvis 객체는 만들 수 없다.
 *
 * public 필드를 사용하면 클래스가 싱글턴인지는 선언만 보면 금방 알수 있어서 좋다.
 *
 */
public class Rule03_02 {
    public static void main(String[] args) {

    }
}
// 정적 팩터리를 이용한 싱글턴.
class ElvisStatic {
    private static final ElvisStatic INSTANCE = new ElvisStatic();

    private ElvisStatic() { }

    public static ElvisStatic getInstance() { return INSTANCE; }

    public void leaveTheBuilding() { }
}

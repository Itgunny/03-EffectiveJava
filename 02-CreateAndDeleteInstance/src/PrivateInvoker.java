import java.lang.reflect.Constructor;

/**
 * Created by gunny on 2017. 7. 17..
 */
public class PrivateInvoker {
    public static void main(String[] args) throws Exception {
        // 리플렉션과 setAccessible 메소드를 통해 private로 선언된 생성자의 호출 권한을 획득한다.
        Constructor<?> con = Private.class.getDeclaredConstructors()[0];
        con.setAccessible(true);
        Private p = (Private) con.newInstance();
    }

}

class Private {
    private Private() {
        System.out.println("Private Constructor");
    }
}

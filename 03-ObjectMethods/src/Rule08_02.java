import java.awt.*;

/**
 * Created by gunny on 2017. 7. 25..
 *
 * equals 메소드 재정의 표준 구현 계약사항
 *
 * 1. 반사성(Reflexivity) : 모든 객체는 자기 자신과 동등해야한다.
 *
 * 2. 대칭성(Symmetry) : 어떤 두 객체가 서로에 대해 동등성을 검사하면 그 결과는 같아야 한다.
 *    ex) equals가 따라야 할 규약을 어기면, 그 객체를 만난 다른 객체들이 어떻게 행동할지 예측하지 못하게 된다.
 *
 *
 * 3. 추이성(Transitivity) : 첫번째 객체가 두번째 객체와 같고 두번째 객체가 세번째 객체와 같다면,
 *    첫번째 객체는 세번째 객체와 같아야한다.
 *
 * 4. 일관성 : 일단 같다고 판정된 객체들은 추후 변경되지 않는 한 계속 같아야 한다는 것이다.
 *    신뢰성이 보장되지 않는 자원(unreliable resource)들을 비교하는 equals를 구현하는 것을 삼가라.
 *    ex) java.net.URL의 equals 메소드는 URL에 대응되는 호스트의 IP 주소를 비교하여 equals의 반환
 *    값을 결정한다. 문제는 호스트 명을 IP주소로 변환하려면 네트워크에 접속해야하므로 언제나 같은 결과가 나온다는
 *    보장이 없다.
 *
 * 5. 널(Null)에 대한 비 동치성(Non-nullity) : 모든 객체는 null과 동치 관계에 있지 아니한다.
 */
public class Rule08_02 {
    public static void main(String[] args) {
        // 1. 반사성
        CaseInsensitiveString cis = new CaseInsensitiveString("Hello");
        cis.equals(cis);

        // 2. 대칭성
        String s = new String("Hello");
        System.out.println(cis.equals(s));
        System.out.println(s.equals(cis));

        // 3. 추이성 - 대칭성 위반!
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1, 2, Color.RED);

        p.equals(cp);
        cp.equals(p);

        // 3. 추이성 - 추이성 위반!
        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);

        System.out.println(p1.equals(p2)); // false
        System.out.println(p2.equals(p3)); // false
        System.out.println(p1.equals(p3)); // false
    }
}

final class CaseInsensitiveString {
    private String s;

    public CaseInsensitiveString(String s) {
        if(s == null) {
            throw new NullPointerException();
        }
        this.s = s;
    }

    // 틀린 구현 - equals의 대칭성이 깨졌다.
    /*public boolean equals(Object o) {
        if(o instanceof CaseInsensitiveString) {
            return s.equalsIgnoreCase(((CaseInsensitiveString)o).s);
        }
        // CaseInsensitiveString은 String을 처리하지만
        // String은 CaseInsensitiveString을 처리하지 않는다.

        if(o instanceof String) {
            return s.equalsIgnoreCase((String)o);
        }
        return false;
    }*/

    // 수정 소스

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CaseInsensitiveString && ((CaseInsensitiveString)obj).s.equalsIgnoreCase(s);
    }
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /*
        ColorPoint의 equals 메소드는 Point 클래스의 equals 상속 받고 있으므로 색깔은 비교 대상이 아니다.
        색깔도 비교해줘야 한다.
     */
    /*
        1. 상속된 클래스도 비교해줘야한다. -> 아래의 경우 색상정보는 비교하지 않는다.
    */
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Point)) {
            return false;
        }
        Point p = (Point) o;
        return p.x == x && p.y == y ;
    }
}

class ColorPoint{

    private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        if(color == null)
            throw new NullPointerException();
        this.point = new Point(x, y);
        this.color = color;
    }

    /**
     * ColorPoint의 Point 뷰 변
     */
    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof ColorPoint)) {
            return false;
        }
        ColorPoint cp = (ColorPoint) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }
    /* 2. 대칭성이 꺠지는 eqauls
          왜냐하면 cp.equals(p)를 수행하면 color정보가 없어서 false가 되기 떄문이다.
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof ColorPoint))
            return false;
        return super.equals(o) && ((ColorPoint)o).color == color;
    }
    */

    // 추이상 위반!
    /*
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Point))
            return false;
        // o가 Point 객체이면 색상은 비교하지 않음
        if(!(o instanceof ColorPoint))
            return o.equals(this);

        // o가 ColorPoint이므로 모든 정보를 비교.
        return super.equals(o) && ((ColorPoint)o).color == color;
    }
    */

}
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by gunny on 2017. 7. 17..
 *
 */
public class Rule05_02 {
    public static void main(String[] args) {

        /*
         * 자바의 기본 자료형과 그 객체 표현형을 섞어 사용할 수 있도록 해준다.
         * 둘 간의 변환은 자동으로 이뤄진다. 자동 객체화 덕에 기본 자료형과 그객체 표현형 사이의 차이가
         * 희미해지긴 했지만, 아주 없어진 것은 아니다. 의미적인 차이는 미미하지만, 성능차이는 무시하기 어렵다.
         */

        /*
           Long sum에 더해질 때마다. 객체가 하나씩 생긴다.
           Long -> long으로 바꾸면 시간이 줄어든다.

           객체 표현형 대신 기본 자료형을 사용하고, 생각지도 못한 자동 객체화가 발생하지 않도록 유의하라는 것이다.
           
         */
        Long sum = 0L;

        for(long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }

        System.out.println(sum);
    }
}

class Person1 {
    private final Date birthDate = null;

    // 이렇게 하면 안 된다.
    public boolean isBabyBoomer() {
        // 생성 비용이 높은 객체를 쓸데없이 생성한다.
        Calendar gmtCal =
                Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0,  0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
    }
}

class Person2 {
    private final Date birthDate = null;

    /**
     * 베이비 붐 시대의 시작과 끝
     */

    private static final Date BOOM_START;
    private static final Date BOOM_END;

    /*
        static 구문은 클래스가 초기화 될 때 한번만 만든다.
        성능이 개선되었고 코드 또한 간결하고 명료해졌다.
        static final 필드로 바꾸자 이 두 날짜가 상수라는 사실이 분명하게 드러났다.

        만일 개선된 Person 클래스가 초기화 된 다음에 isBabyBoomer 메소드가 한번도 호출되지 않는다면,
        BOOM_START와 BOOM_END 필드는 쓸데없이 초기화 되었다고 봐야 할 것이다.

        -> 이런상황은 초기화 지연 기법을 사용하면 해결할 수 있다.
      */
    static {
        Calendar gmtCal =
                Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0,  0);
        BOOM_END = gmtCal.getTime();
    }

    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;
    }


}
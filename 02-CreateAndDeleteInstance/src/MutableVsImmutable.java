/**
 * Created by gunny on 2017. 7. 13..
 */
public class MutableVsImmutable {
    /* String vs StringBuffer
         * 1. String 클래스는 변경이 불가능한 immutable클래스이다.
         *    1) String 클래스에서 substring(), toLowerCase(), concat(), trim()등의 메소드를
         *      생각하면 String 클래스는 변경 가능한 클래스처럼 보인다.
         *      그러나, 실제로는 이러한 메소들은 원래 객체와 다른 새로운 String 객체를 만들어 반화한다.
         *      따라서 원래 String 객체는 가지고 있는 문자열이 변경되지 않으면 여전히 사용 가능 채 남아있다.
         *
         *    2) 즉, 기존의 String 객체에 substring()과 같은 문자열에 변경을 가하는 메소드를 실행하면
         *       또 하나의 String 객체가 새성되어 서로 다른 두개의 String 객체가 존재하게 된다.
         *       이런 이유로 String 클래스의 변경은 객체를 생성하기 위하여 시스템 자원을 낭비한다고 생각되는
         *       경향이 있다.
         */
    String hello = "Hello";
    String world = " World\n";

    // 객체를 만들어 할당해줘야한다.
    //String helloWorld = s.concat(world);



        /*
         * 왜 Immutable class 인가
         * 1. immutable class는 몇가지 조건과 특징을 가지고 있다.
         *    - 첫번째는 클래스가 가지고 있는 값(즉, String 클래스에서는 문자열)은 오직 생성자에서만 설정될 수 있으며,
         *      그 값을 변경할 수 있는 어떠한 메소드도 가지고 있지 않아야 한다. 만약 변경을 원한다면, 원하는 값을 가진
         *      새로운 객체를 생성한다.
         *
         *    - 이런 immutable 클래스의 가장 큰 장점은 안전하게 공유될 수 있다는 점이다. 즉, 변경은 적고, 읽기만 많은
         *      경우, 또는 여러 쓰레드나 객체에서 공유하는 경우, synchronization와 같은 특별한 안전장치 없이도 안전하게
         *      공유될 수 있다.
         *
         *    - 대부분의 문자열이 복잡한 문자열 처리과정보다는 한번 설정된 문자열들을 여러 곳에서 공유하는 경우가 많으므로,
         *      자바에서 기본 문자열을 처리하는 클래스로 String 클래스를 immutable 패턴으로 설정하였다.
         */

        /*
         * StringBuffer class는 변경이 가능한 mutable class이다.
         *   1. StringBuffer class는 가지고 있는 문자열의 내용을 변경 가능하도록 만든 클래스이다.
         *      - append(), insert(), delete() 등의 메소드를 통하여 StringBuffer 객체가 가지고 있는 문자열을 변경할 수 있으며,
         *        이때, String class처럼 새로운 객체를 생성하지 않고, 기존의 문자열을 변경한다.
         *      - 이 경우 객체를 생성하지 않으므로, String class보다 효율적이라고 생각하기 쉽지만, 동기화(synchronization)를 보장
         *        해야 하기 떄문에 단순한 참조에서는 상대적으로 String 보다 나쁜 성능을 보인다.
         *      - 단순 참조가 많은 경우 StringBuffer 클래스보다 String 클래스가 유리하다. 물론 StringBuffer 클래스는 동기화되어 있으므로,
         *        멀티 쓰레드에 대하여 안전하다.
         *
         *   2. StringBuffer 객체는 문자열을 다루는 다른 메소드에서 사용되기 위하여, toString() 메소드를 통하여 String 객체를 생성하게 된다.
         *      - 이 때, 일반적으로 String 객체의 생성 후에 문자열을 복사하지 않고, StringBuffer 객체와 문자열을 공유하여 참조하는
         *      프록시 패턴을 적용하고 있다.
         */

        /*
         * 성능 차이의 실제적인 비교
         * 1. StringBuffer 객체는 내용을 변경 할 때 String 객체보다 효율적인가?
         *
         * 2. String 객체는 가지고 있는 문자열을 변경할 때 어느 정도 StringBuffer 객체에 비해 성능 저하를 보이는가?
         *
         * 3. StringBuffer 및 String class는 모두 문자열 처리에서 가장 많이 쓰이는 substring 메소드에 대하여 String
         *    객체를 생성한다. 그렇다면 성능상의 차이는 있는가
         *
         * 4. StringBuffer 객체는 toString() 메소드를 통하여 String 객체를 생성하여야만 다른 객체에 문자열을 전달할 수 있다.
         *    toString 메소드를 통한 String객체 생성의 자원소모는 어느 정도인가?
         *
         * 5. String 객체 및 StringBuffer 객체의 생성은 어느 정도의 자원 소모를 필요로 하는가?
         */

        /* 비교를 위해 테스트에 사용된 8가지 메소드
         * 1. String.concat() : String 클래스에 문자열 추가
         * 2. StringBuffer.append() : StringBuffer 클래스에 문자열 추가
         * 3. String.substring() : String 클래스에서 문자열 일부 추출
         * 4. StringBuffer.substring() : StringBuffer 클래스에서 문자열 일부 추출
         * 5. String.toString() : String 클래스의 toString() 메소드 호출(실제로는 자기자신을 돌린다.)
         * 6. StringBuffer.toString : StringBuffer 클래스의 toString() 메소드 호출(즉, String 객체로 변환)
         * 7. new String() - String 객체 생성
         * 8. new StringBuffer() - StringBuffer 객체 생성.
         */

    private final static String HELLO = "Hello!";

    private int count = 1000;
    private int size = 640;
    private long[] timeStamp = new long[count + 1];
    private long[] freeMemory = new long[count + 1];

    String s = new String(HELLO);
    String s2 = new String(HELLO);

    StringBuffer sb = new StringBuffer(HELLO);
    StringBuffer sb2 = new StringBuffer(HELLO);

    public MutableVsImmutable() {

    }

    public void printResult() {
        for(int i = 1; i <= count; i++) {
            System.out.println(i + "\t" + (timeStamp[i] - timeStamp[0]) + "\t" + freeMemory[i]);
        }
        System.gc();
    }


    public void test1() {
        for(int i = 0; i <= count; i++) {
            for(int j = 0; j < size; j++) {
                s2 = s.concat(HELLO);
            }
            freeMemory[i] = Runtime.getRuntime().freeMemory();
            timeStamp[i] = System.currentTimeMillis();
        }
    }

    public void test2() {
        for(int i = 0; i <= count; i++) {
            for(int j = 0; j < size; j++) {
                sb = sb.append(HELLO);
                sb.setLength(6);
            }
            freeMemory[i] = Runtime.getRuntime().freeMemory();
            timeStamp[i] = System.currentTimeMillis();
        }
    }

    public void test3() {
        for(int i = 0; i <= count; i++) {
            for(int j = 0; j < size; j++) {
                s2 = s.substring(0, 2);
            }
            freeMemory[i] = Runtime.getRuntime().freeMemory();
            timeStamp[i] = System.currentTimeMillis();
        }
    }

    public void test4() {
        for(int i = 0; i <= count; i++) {
            for(int j = 0; j < size; j++) {
                s2 = sb.substring(0, 2);
            }
            freeMemory[i] = Runtime.getRuntime().freeMemory();
            timeStamp[i] = System.currentTimeMillis();
        }
    }

    public void noop(String st) {}

    public void test5() {
        for(int i = 0; i <= count; i++) {
            for(int j = 0; j < size; j++) {
                noop(s.toString());
            }
            freeMemory[i] = Runtime.getRuntime().freeMemory();
            timeStamp[i] = System.currentTimeMillis();
        }
    }

    public void test6() {
        for(int i = 0; i <= count; i++) {
            for(int j = 0; j < size; j++) {
                noop(sb.toString());
            }
            freeMemory[i] = Runtime.getRuntime().freeMemory();
            timeStamp[i] = System.currentTimeMillis();
        }
    }

    public void test7() {
        for(int i = 0; i <= count; i++) {
            for(int j = 0; j < size; j++) {
                s = new String(HELLO);
            }
            freeMemory[i] = Runtime.getRuntime().freeMemory();
            timeStamp[i] = System.currentTimeMillis();
        }
    }

    public void test8() {
        for(int i = 0; i <= count; i++) {
            for(int j = 0; j < size; j++) {
                sb = new StringBuffer(HELLO);
            }
            freeMemory[i] = Runtime.getRuntime().freeMemory();
            timeStamp[i] = System.currentTimeMillis();
        }
    }

    public static void main(String[] args) {
        MutableVsImmutable test = new MutableVsImmutable();
        System.gc();
        test.test1();   // String에 문자열 추가
        test.printResult();
        test.test2();   // StringBuffer에 문자열 추가
        test.printResult();
        //test.test3();   // String에서 substring() 호출
        //test.printResult();
        //test.test4();   // StringBuffer에서 substring() 호출
        //test.printResult();
        //test.test5();   // String에서 toString() 호출
        //test.printResult();
        //test.test6();   // StringBuffer에서 toString() 호출
        //test.printResult();
        //test.test7();   // String 객체 생성
        //test.printResult();
        //test.test8();   // StringBuffer 객체 생성
        //test.printResult();
    }

    /* 성능 향상에 대한 결론
     * 문자열을 추가하기 위하여 append()와 같은 메소드를 사용할 떄 StringBuffer 클래스는 String클래스와 비교하여 아주 뛰어난 성능을 보인다.
     *
     * 그러나 StringBuffer 객체의 생성 및 toString() 메소드를 통한 String 객체의 생성을 반드시 필요로 하므로 더많은 시간 및 메모리 자원의 낭비를 초래한다.
     *
     * 그에비하여 String 클래스는 StringBuffer 클래스와 비교하여 인스턴스화를 통하여 객체를 생성할 때 적은 자원을 소모하며, toString() 메소드를 통하여 String객체로 바꿀 필요가 없다.
     *
     * 따라서 StringBuffer 클래스는 하나의 문자열에 대하여 다른 문자나 문자열의 추가가 여러번 이루어지는 경우 유리하며
     * 단 한번의 문자열 추가에 대하여 StringBuffer 클래스를 사용하는 것은 오히려 시간 및 메모리 자원 낭비를 초래한다.
     */
}

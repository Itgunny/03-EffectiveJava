/**
 * Created by gunny on 2017. 7. 17..
 */
public class Rule02_02 {
    public static void main(String[] args) {
        /*
          이 패턴에는 점층적 생성자 패턴에 있던 문제는 없다. 작성해야하는 코드의 양이 조금 많아질 수는 있지만
          객체를 생성하기도 쉬우며, 읽기도 좋다.

          그러나 자바빈 패턴에는 심각한 단점이 있다.
          1회의 함수 호출로 객체 생성을 끝낼 수 없으므로, 객체 일관성이 일시적으로 꺠질 수 있다는 것이다.

          또 다른 문제는 자바빈 패턴으로는 변경 불가능한 클래스를 만들 수 없다는 것이다.

          스레드 안전성을 제공하기 위해 해야할 일도 더 많아진다.
          생성이 끝난 객체는 "얼리고(freezing)", 얼지 않은 객체는 사용할 수 없도록 하는 코드를 수작업으로
          추가해서 방지 할 수 도있다.

          다행인 것은, 점층적 생성자 패턴의 안전성에 자바빈 패턴의 가독성을 결합한 세 번째 대안이 있다는 것이다.
          바로 빌더(Builder) 패턴이다.
         */
        NutriotionFactsJavaBean cocaCola = new NutriotionFactsJavaBean();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
        cocaCola.setSodium(35);
        cocaCola.setCarbohydrate(27);
    }
}
// 자바빈 패턴 - 일관성 훼손이 가능하고, 항상 변경 가능하다.
class NutriotionFactsJavaBean {
    // 필드는 기본값으로 초기화(기본값이 있는 경우만)
    private int servingSize = -1;
    private int servings = -1;
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public NutriotionFactsJavaBean() {}

    public void setServingSize(int val) { servingSize = val; }
    public void setServings(int val) { servings = val; }
    public void setCalories(int val) { calories = val; }
    public void setFat(int val) { fat = val; }
    public void setSodium(int val) { sodium = val; }
    public void setCarbohydrate(int val) { carbohydrate = val;}
}
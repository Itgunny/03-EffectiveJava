/**
 * Created by gunny on 2017. 7. 17..
 *
 * 생성자 인자가 많을 때는 Builder 패턴을 적용을 고려하라.
 *
 * 보통 점층적 생성자 패턴(telescoping constructor pattern)을 적용한다.
 *
 */
public class Rule02_01 {
    public static void Main(String[] args) {
        /*
          객체를 생성할 때는 인자개수에 맞는 생성자를 골라 호출할 수 있다.
          하지만 설정할 필요가 없는 필드에도 인자를 전달해야 하는 경우가 생긴다.
          위의 코드에서 fat 필드 설정을 위한 전달한 0이 그런 사례이다.

          요약하자면 점층적 생성자 패턴은 잘 동작 하지만 인자수가 늘어나면 클라이언트 코드를 작성하기가 어려워지고
          무엇보다 읽기 어려운 코드가 되고만다.

          또한 자료형이 인자들이 많아지다보면
          */
        NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 3, 35, 27);
    }
}


class NutritionFacts {
    // 점층적 생성자 패턴 - 더많은 인자개수에 잘 적응 하지 못한다.
    private final int servingSize;  // (mL)             필수
    private final int servings;     // (per container)  필수
    private final int calories;     //                  선택
    private final int fat;          // (g)              선택
    private final int sodium;       //
    private final int carbohydrate;

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories,
                          int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }



}
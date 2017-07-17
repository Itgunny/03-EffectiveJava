/**
 * Created by gunny on 2017. 7. 17..
 *
 * 빌더 패턴
 * 1. 필요한 객체를 직접 생성하는 대신, 클라이언트는 먼저 필수 인자들을 생성자에 전부 전달하여
 *    빌더 객체를 만든다.
 * 2. 빌더 객체에 정의된 설정 메소드들을 호출하여 선택적 인자들을 추가해 나간다.
 *
 * 3. 마지막으로 아무런 인자 없이 builder 메소드를 호출하여 변경 불가능 객체를 만드는 것이다.
 *
 */
public class Rule02_03 {
    public static void main(String[] args) {

        /*
            NutritionFacts 객체가 변경 불가능하다는 사실, 그리고 모든 인자의 기본값(default value)이 한곳에
            모여 있다는 것에 유의하기 바란다.

            빌더에 정의된 설정 메소드는 빌더 객체 자신을 반환하므로, 설정 메소드를 호출하는 코드는 죽 이어서
            쓸수 있다.

            빌더패턴을 사용하면 인자에 불변식을 적용할 수 있다. build 메소드 안에서 해당 불변식이 위반되었는지
            검사할 수 있는 것이다.

            빌더 객체에서 실제 객체로 인자가 복사된 다음에 불변식들을 검사 할 수 있다는 것, 그리고 그 불변식을
            빌더 객체의 필드가 아니라 실제 객체의 필드를 두고 검사할 수 있다는 것은 중요하다.

            불변식을 위반한 경우 build 메소드는 IllegalStateException을 던져야 한다.

            장점
            1. build가 실제로 호출되기 전에 불변식을 깨뜨리는 인자가 전달 되었다는 사실을 알수 있다.

            2. 빌더 객체는 여러개의 varargs 인자를 받을 수 있다는 것이다. 생성자는 메서드와 마찬가지로
            하나의 varargs만 인자로 가질 수 있다. 하지만 빌더는 인자마다 별도의 설정 메소드를 사용하므로
            설정 메서드 마다 하나씩, 필요한 만큼 많은 varargs인자를 사용할수 있다.

            3. 빌더 패턴은 유연하다. 하나의 빌더 객체로 여러 객체를 만들 수 있다. 다른 객체를 생성해야 할 떄마다
               빌더 객체의 설정 메서드를 호출하면 다음에 생성될 객체를 바꿀 수 있다. 또한 빌더 객체는 어떤 필드의
               값은 자동으로 채울수도 있다.

            4. 인자가 설정된 빌더는 훌륭한 추상적 팩토리이다. 다시말해서, 클라이언트는 그런 빌더를 어떤 메소드에 넘겨서
               , 해당 메소드가 클라이언트에게 하나이상의 객체를 만들어 주도록 할 수 있다.

            단점
            1. 객체를 생성하려면 우선 빌더 객체를 생성해야한다. 겅능이 중요한 상황에서 점층적 생성자 패턴보다
               많은 코드를 요구 하기 떄문

            요약 : 빌더 패턴은 인자가 많은 생성자나 정적 팩터리가 필요한 클래스를 설계할 때,
                  특히 대부분의 인자가 선택적 인자인 상황에 유용하다.
         */
        NutritionFactsBuilder cocaCola = new NutritionFactsBuilder.Builder(240, 8).
                calories(100).sodium(35).carbohydrate(27).build();

    }
}
interface Builder<T> {
    public T build();
}
class NutritionFactsBuilder {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // 필수 인자
        private final int servingSize;
        private final int servings;

        // 선택적 인자 - 기본값으로 초기화
        private int calories = 0;
        private int fat = 0;
        private int carbohydrate = 0;
        private int sodium = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public NutritionFactsBuilder build() {
            return new NutritionFactsBuilder(this);
        }
    }

    private NutritionFactsBuilder(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
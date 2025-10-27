package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.service.CarService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarServiceTest {

    private CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService();
    }

    @Test
    @DisplayName("자동차 이름이 null이면 예외")
    void nameIsNull_throwsException(){
        assertThatThrownBy(()->carService.validateCarNames(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자동차 이름이 5글자를 초과하면 예외")
    void nameOverSize_throwsException() {
        String input = "pobiis";

        // IllegalArgumetException인지 확인
        assertThatThrownBy(() -> carService.validateCarNames(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자동차 이름 입력을 쉼표로 구분해서 List에 삽입")
    void createCarListFromInputNames() {
        String input = "pobi,woni";

        List<Car> cars = carService.setCarList(input);

        //List에 정상 생성 됐는지 확인
        assertThat(cars).hasSize(2);
        assertThat(cars.get(0).getCarName()).isEqualTo("pobi");
        assertThat(cars.get(1).getCarName()).isEqualTo("woni");
    }

    @Test
    @DisplayName("반복 횟수가 숫자가 아니면 예외")
    void repeatIsNotNum_throwsException() {
        String  input = "a";

        // IllegalArgumetException인지 확인
        assertThatThrownBy(() -> carService.validateRepeat(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("반복 횟수가 null이면 예외")
    void repeatIsNull_throwsException() {
        // IllegalArgumetException인지 확인
        assertThatThrownBy(() -> carService.validateRepeat(null))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("move position호출 시 자동차의 위치가 증가")
    void movePositionIsMoveCar() {
        Car car = new Car("pobi");
        assertThat(car.getPosition()).isZero();

        // 강제 move호출
        car.move();

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("최종 우승자가 최고 position인지 확인")
    void winnerIsHihgPosition() {
        Car car1 = new Car("pobi");
        Car car2 = new Car("woni");
        car1.move();
        car1.move(); // pobi: 2
        car2.move(); // woni: 1

        List<Car> carList = List.of(car1, car2);

        String winner = carService.getWinner(carList);

        assertThat(winner).isEqualTo("pobi");
    }
}

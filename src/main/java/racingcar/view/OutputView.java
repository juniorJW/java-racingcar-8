package racingcar.view;

import racingcar.model.Car;
import racingcar.controller.CarController;

import java.util.List;

public class OutputView {
    public void currentPosition(List<Car> carList){
        for(Car car : carList){
            System.out.print(car.getCarName() + " : ");
            System.out.println("-".repeat(car.getPosition()));
        }
        System.out.println();
    }
}

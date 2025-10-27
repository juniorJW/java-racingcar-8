package racingcar.controller;

import racingcar.model.Car;
import racingcar.service.CarService;
import racingcar.view.InputView;

import java.util.List;

public class CarController {
    CarService carService = new CarService();
    InputView inputView = new InputView();

    public void run(){
        String carNames = inputView.carNames();
        carService.validateCarNames(carNames);

        //자동차 목록 저장
        List<Car> carList = carService.setCarList(carNames);

        int repeat = carService.validateRepeat(inputView.repeat());
    }
}

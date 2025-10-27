package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.model.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarService {
    //자동차 이름 String 리스트로 반환
    public List<String> parseCarNames(String carNames) {
        List<String> carList = new ArrayList<String>(Arrays.asList(carNames.split(",")));
        return carList;
    }

    //자동차 이름 예외 처리
    public void validateCarNames(String carNames) {
        List<String> carList = parseCarNames(carNames);
        if (carNames == null || carList.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름이 없습니다");
        }
        for (String s : carList) {
            if (s.length() > 5) {
                throw new IllegalArgumentException("자동차 이름이 5를 초과합니다");
            }
        }
    }

    //모델 객체 반환(service에서 사용)
    public Car setCar(String carName) {
        return new Car(carName);
    }

    //자동차 이름 모델 객체로 반환 (controller에서 사용)
    public List<Car> setCarList(String carNames) {
        List<String> carList = parseCarNames(carNames);
        List<Car> setCarList = new ArrayList<>();

        for (String s : carList) {
            Car car = setCar(s);
            setCarList.add(car);
        }
        return setCarList;
    }

    // 반복 횟수 예외 처리
    public int validateRepeat(String inputRepeat) {
        int repeat;

        if (inputRepeat == null) {
            throw new IllegalArgumentException("반복 횟수가 없습니다");
        }

        try {
            repeat = Integer.parseInt(inputRepeat);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 아닙니다");
        }
        return repeat;
    }

    //자동차 움직입 체크
    public void movePosition(Car car) {
        if (Randoms.pickNumberInRange(0, 9) >= 4) {
            car.move();
        }
    }

    //우승자 포지션 반환
    public int winnerPosition(List<Car> carList) {
        int winnerPosition = 0;

        for (Car car : carList) {
            if (car.getPosition() > winnerPosition) {
                winnerPosition = car.getPosition();
            }
        }
        return winnerPosition;
    }

    //우승자 반환
    public String getWinner(List<Car> carList) {
        int winnerPosition = winnerPosition(carList);
        List<String> winnerList = new ArrayList<>();

        for (Car car : carList) {
            if (winnerPosition == car.getPosition()) {
                winnerList.add(car.getCarName());
            }
        }
        return String.join(", ", winnerList);
    }
}

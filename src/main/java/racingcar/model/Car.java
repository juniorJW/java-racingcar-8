package racingcar.model;

public class Car {
    private final String carName;
    private int position = 0;

    public Car(String carName){
        this.carName = carName;
    }

    public String getCarName(){
        return this.carName;
    }

    public int getPosition(){
        return this.position;
    }
}

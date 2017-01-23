package hu.meiit.repository;

import hu.meiit.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Czinke Csaba on 2017.01.15..
 */
public class CarRepository {
    private int counter;
    private List<Car> cars;

    public CarRepository(){
        cars = new ArrayList<Car>();
        Car car = new Car();
        car.setId(counter++);
        car.setType("Renault");
        car.setNumberOfWheels(4);
        cars.add(car);
    }

    public List<Car> getCars(){
        List<Car> actualCars = new ArrayList<Car>();
        for(Car car : cars){
            actualCars.add(car);
        }
        return actualCars;
    }

    public Car getCarById(int id){
        for(Car car : cars){
            if(car.getId() == id){
                Car result = new Car();
                result.setId(car.getId());
                result.setType(car.getType());
                result.setNumberOfWheels(car.getNumberOfWheels());
                return result;
            }
        }
        return null;
    }

    public void editCar(Car editedCar){
        for(Car car : cars){
            if(car.getId() == editedCar.getId()){
                car.setType(editedCar.getType());
                car.setNumberOfWheels(editedCar.getNumberOfWheels());
            }
        }
    }

    public void addCar(Car car){
        car.setId(counter++);
        cars.add(car);
    }
}

package edu.umb.cs681.hw20;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String make, model;
    private int mileage, year, price, dominationCount, HP;

    public Car(String make, String model, int mileage, int year, int price, int HP) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
        this.HP = HP;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public int getHP() { return this.HP; }

    public int getMileage() {
        return this.mileage;
    }

    public int getYear() {
        return this.year;
    }

    public int getPrice() {
        return this.price;
    }

    public void setDominationCount(List<Car> cars) {
        int count = 0;
        for (Car car : cars) {
            if (!car.equals(this)) {
                int price = car.getPrice();
                int year = car.getYear();
                int mileage = car.getMileage();

                if (this.getYear() >= year && this.getMileage() <= mileage && this.getPrice() <= price) {
                    if (this.getYear() > year || this.getMileage() < mileage || this.getPrice() < price) {
                        count++;
                    }
                }
            }
        }
        this.dominationCount = count;
    }

    public int getDominationCount() {
        return this.dominationCount;
    }

    public static void main(String args[]) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "SE", 190, 2018, 300, 1000));
        cars.add(new Car("BMW", "XM6", 10, 2019, 200, 2000));
        cars.add(new Car("Ford", "Runner", 200, 2010, 500, 200));
        cars.add(new Car("Mercedes", "SL550", 0, 2020, 100, 500));

        cars.forEach((Car car) -> car.setDominationCount(cars));

        Integer totalHP = cars.stream()
                .parallel()
                .map(Car::getHP)
                .reduce(0,
                        (result, hp) -> result+=hp,
                        (finalResult, intermediateResult) -> finalResult + intermediateResult);

        System.out.println("Total HP: " + totalHP);
    }
}
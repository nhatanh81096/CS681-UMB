package edu.umb.cs681.hw02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Car {
    private String make, model;
    private int mileage, year, price, dominationCount;

    public Car(String make, String model, int mileage, int year, int price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

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
        cars.add(new Car("Toyota", "SE", 190, 2018, 300));
        cars.add(new Car("BMW", "XM6", 10, 2019, 200));
        cars.add(new Car("Ford", "Runner", 200, 2010, 500));
        cars.add(new Car("Mercedes", "SL550", 0, 2020, 100));

        cars.forEach((Car car) -> car.setDominationCount(cars));

        List<Car> sortByPrice = cars.stream().sorted(Comparator.comparing(Car::getPrice)).collect(Collectors.toList());
        List<Car> sortByYear = cars.stream().sorted(Comparator.comparing(Car::getYear, Comparator.reverseOrder())).collect(Collectors.toList());
        List<Car> sortByMileage = cars.stream().sorted(Comparator.comparing(Car::getMileage)).collect(Collectors.toList());
        List<Car> sortByDominationCount = cars.stream().sorted(Comparator.comparing(Car::getDominationCount, Comparator.reverseOrder())).collect(Collectors.toList());

        System.out.println("--Sorted by Price--");
        sortByPrice.forEach((Car car) -> System.out.println(car.getMake() + " " + car.getPrice()));

        System.out.println("--Sorted by Year--");
        sortByYear.forEach((Car car) -> System.out.println(car.getMake() + " " + car.getYear()));

        System.out.println("--Sorted by Mileage--");
        sortByMileage.forEach((Car car) -> System.out.println(car.getMake() + " " + car.getMileage()));

        System.out.println("--Sorted by Domination Count--");
        sortByDominationCount.forEach((Car car) -> System.out.println(car.getMake() + " " + car.getDominationCount()));
    }
}
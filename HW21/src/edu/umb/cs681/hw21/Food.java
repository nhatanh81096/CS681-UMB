package edu.umb.cs681.hw21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Food {
    private String name;
    private int calories;
    private double carb;
    private double protein;
    private double fat;

    Food(String name, int calories, double carb, double protein, double fat) {
        this.name = name;
        this.calories = calories;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public double getCarb() {
        return carb;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public static void main(String args[]) {
        List<Food> breakfast = new ArrayList<>();

        breakfast.add(new Food("Egg", 50, 1, 7, 0.2));
        breakfast.add(new Food("Bacon", 400, 2, 10, 40));
        breakfast.add(new Food("Sandwich", 200, 40, 2, 1));

        long numberOfItems = breakfast.stream().count();
        System.out.println("---Number of food items in breakfast: "+ numberOfItems);

        Food highestCaloriesItem = breakfast.stream()
                .parallel()
                .max(Comparator.comparing(Food::getCalories))
                .get();
        System.out.println("---Food item that has highest calories intake: "+ highestCaloriesItem.getName());

        List<Food> lowFatItems = breakfast.stream()
                .parallel()
                .filter((Food food) -> food.getFat() < 10)
                .collect(Collectors.toList());
        System.out.println("---Low fat food items: ");
        lowFatItems.forEach((Food food) -> System.out.println(food.getName() + " " + food.getFat()));

        List<Food> sortedByProtein = breakfast.stream()
                .parallel()
                .sorted(Comparator.comparing(Food::getProtein, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        System.out.println("---Food item ordered by protein intake: ");
        sortedByProtein.forEach((Food food) -> System.out.println(food.getName() + " " + food.getProtein()));

        double totalCalories = breakfast.stream()
                .parallel()
                .map(Food::getCalories)
                .reduce(0, (result, calories) -> result+=calories);
        System.out.println("---Total calories intake: " + totalCalories);

        double totalCarb = breakfast.stream()
                .parallel()
                .map(Food::getCarb)
                .reduce(0.0,
                        (result, carb) -> result+=carb,
                        (finalResult, intermediateResult)->finalResult+intermediateResult);
        System.out.println("---Total carb intake: " + totalCarb);

        double totalProtein = breakfast.stream()
                .parallel()
                .map((Food food) -> food.getProtein())
                .reduce((result, protein) -> result+=protein)
                .get();
        System.out.println("---Total protein intake: " + totalProtein);

        double totalFat = breakfast.stream()
                .parallel()
                .map((Food food) -> food.getFat())
                .reduce((result, fat) -> result+=fat)
                .get();
        System.out.println("---Total fat intake: " + totalFat);
    }
}

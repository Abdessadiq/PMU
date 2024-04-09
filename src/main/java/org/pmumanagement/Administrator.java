package org.pmumanagement;

import java.util.LinkedList;
import java.util.List;
import java.io.*;


/**
 * @author ABDESSADIQ BABA HAMOU
 * @since 08/04/2024
 */
public class Administrator {
    private List<Food> foodCatalog;
    private static final String FILE_PATH = "foodCatalog.txt";

    public Administrator() {
        this.foodCatalog = new LinkedList<>();
        loadFoodCatalog();
    }

    public void addFood(String name, String origin) {
        Food newFood = new Food(name, origin);
        foodCatalog.add(newFood);
        System.out.println(name + " successfully added.");
    }

    public void deleteFood(String name) {
        foodCatalog.removeIf(food -> food.getName().equalsIgnoreCase(name));
        System.out.println(name + " successfully deleted.");
    }

    public Food findFood(String name) {
        for (Food food : foodCatalog) {
            if (food.getName().equalsIgnoreCase(name)) {
                return food;
            }
        }
        return null;
    }

    public void generateReport() {
        System.out.println("Food Report:");
        for (Food food : foodCatalog) {
            System.out.println(food);
        }
    }

    public void updateFoodOrigin(String name, String newOrigin) {
        Food food = findFood(name);
        if (food != null) {
            food.setOrigin(newOrigin);
            System.out.println("Le pays d'origine de " + name + " a été mis à jour.");
        } else {
            System.out.println("Aliment non trouvé.");
        }
    }

    public void handleRequest(String foodName) {
        addFood(foodName, "Demande en cours");
        System.out.println("La demande pour " + foodName + " a été ajoutée.");
    }

    public void handleComplaint(String foodName) {
        System.out.println("La plainte concernant la non-disponibilité de " + foodName + " a été enregistrée.");
    }
    public void saveFoodCatalog() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Food food : foodCatalog) {
                bufferedWriter.write(food.getName() + "," + food.getOrigin());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file '" + FILE_PATH + "'");
        }
    }

    public void loadFoodCatalog() {
        foodCatalog.clear();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    foodCatalog.add(new Food(parts[0], parts[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Food catalog file not found. A new one will be created.");
        } catch (IOException e) {
            System.out.println("Error reading file '" + FILE_PATH + "'");
        }
    }
}
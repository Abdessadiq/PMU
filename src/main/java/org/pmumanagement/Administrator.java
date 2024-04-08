package org.pmumanagement;

import java.util.LinkedList;
import java.util.List;
/**
 * @author ABDESSADIQ BABA HAMOU
 * @since 08/04/2024
 */
public class Administrator {
    private List<Food> foodCatalog;

    public Administrator() {
        this.foodCatalog = new LinkedList<>();
    }

    public void addFood(String name, String origin) {
        Food newFood = new Food(name, origin);
        foodCatalog.add(newFood);
        System.out.println(name + " ajouté avec succès.");
    }

    public void deleteFood(String name) {
        foodCatalog.removeIf(food -> food.getName().equalsIgnoreCase(name));
        System.out.println(name + " supprimé avec succès.");
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
        System.out.println("Rapport des aliments:");
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

}

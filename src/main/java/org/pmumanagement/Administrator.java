package org.pmumanagement;
import java.io.*;

public class Administrator {
    private FoodNode head;
    private static final String FILE_PATH = "foodCatalog.txt";

    public Administrator() {
        this.head = null;
        loadFoodCatalog();
    }

    public void addFood(String name, String origin) {
        Food newFood = new Food(name, origin);
        FoodNode newNode = new FoodNode(newFood);
        newNode.next = head;
        head = newNode;
        System.out.println(name + " successfully added.");
        saveFoodCatalog();
    }

    public void deleteFood(String name) {
        if (head == null) {
            System.out.println("The food catalog is empty.");
            return;
        }
        if (head.food.getName().equalsIgnoreCase(name)) {
            head = head.next;
            System.out.println(name + " successfully deleted.");
            saveFoodCatalog();
            return;
        }

        FoodNode current = head;
        while (current.next != null && !current.next.food.getName().equalsIgnoreCase(name)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            System.out.println(name + " successfully deleted.");
            saveFoodCatalog();
        } else {
            System.out.println("Food not found.");
        }
    }

    public Food findFood(String name) {
        FoodNode current = head;
        while (current != null) {
            if (current.food.getName().equalsIgnoreCase(name)) {
                return current.food;
            }
            current = current.next;
        }
        return null;
    }

    public void generateReport() {
        System.out.println("Food Report:");
        FoodNode current = head;
        while (current != null) {
            System.out.println(current.food);
            current = current.next;
        }
    }

    public void updateFoodOrigin(String name, String newOrigin) {
        Food food = findFood(name);
        if (food != null) {
            food.setOrigin(newOrigin);
            System.out.println("The origin of " + name + " has been updated.");
            saveFoodCatalog();
        } else {
            System.out.println("Food not found.");
        }
    }

    public void handleRequest(String foodName) {
        // Imaginons que chaque demande ajoute food avec une origine spéciale pour indiquer que c'est une demande.
        addFood(foodName, "Requested");
        System.out.println("The request for " + foodName + " has been added and will be reviewed.");
    }


    public void handleComplaint(String foodName) {
        // Imaginons que nous enregistrons les plaintes dans un fichier séparé pour un examen ultérieur.
        String complaintsFilePath = "foodComplaints.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(complaintsFilePath, true))) {
            bw.write("Complaint about the non-availability of " + foodName);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error recording complaint about " + foodName);
        }
        System.out.println("The complaint about the non-availability of " + foodName + " has been recorded.");
    }

    public void saveFoodCatalog() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            FoodNode current = this.head;
            while (current != null) {
                bufferedWriter.write(current.food.getName() + "," + current.food.getOrigin());
                bufferedWriter.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            System.out.println("Error writing to file '" + FILE_PATH + "'");
        }
    }

    public void loadFoodCatalog() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    addFood(parts[0], parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Food catalog file not found. A new one will be created.");
        } catch (IOException e) {
            System.out.println("Error reading file '" + FILE_PATH + "'");
        }
    }
}
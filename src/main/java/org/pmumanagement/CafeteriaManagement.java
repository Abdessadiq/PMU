package org.pmumanagement;
import java.util.Scanner;

public class CafeteriaManagement {
    private static Administrator admin = new Administrator();
    private static User user = new User(admin);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Cafeteria Management System!");
        while (true) {
            System.out.println("\nMain Menu\n1. Admin\n2. User\n3. Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    adminMenu();
                    break;
                case "2":
                    userMenu();
                    break;
                case "3":
                    System.out.println("Exiting... All changes saved.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void adminMenu() {
        String choice;
        do {
            System.out.println("\nAdmin Menu\n" +
                    "a. Add new food\n" +
                    "b. Delete food\n" +
                    "c. Update food origin\n" +
                    "d. Generate report\n" +
                    "e. Return to main menu");
            choice = scanner.nextLine();
            switch (choice) {
                case "a":
                    System.out.println("Enter food name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter food origin:");
                    String origin = scanner.nextLine();
                    admin.addFood(name, origin);
                    break;
                case "b":
                    System.out.println("Enter food name to delete:");
                    name = scanner.nextLine();
                    admin.deleteFood(name);
                    break;
                case "c":
                    System.out.println("Enter food name to update its origin:");
                    name = scanner.nextLine();
                    System.out.println("Enter new origin:");
                    origin = scanner.nextLine();
                    admin.updateFoodOrigin(name, origin);
                    break;
                case "d":
                    admin.generateReport();
                    break;
                case "e":
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!choice.equals("e"));
    }

    private static void userMenu() {
        String choice;
        do {
            System.out.println("\nUser Menu\n" +
                    "a. Request new food item\n" +
                    "b. Complain about food availability\n" +
                    "c. Search for food\n" +
                    "d. Return to main menu");
            choice = scanner.nextLine();
            switch (choice) {
                case "a":
                    System.out.println("Enter the name of the food item you want to request:");
                    String foodName = scanner.nextLine();
                    user.requestFood(foodName); // This will invoke handleRequest in Administrator
                    break;
                case "b":
                    System.out.println("Enter the name of the food item to complain about its availability:");
                    foodName = scanner.nextLine();
                    user.complainAboutFood(foodName); // This will invoke handleComplaint in Administrator
                    break;
                case "c":
                    System.out.println("Enter the name of the food item to search:");
                    foodName = scanner.nextLine();
                    user.searchFood(foodName);
                    break;
                case "d":
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!choice.equals("d"));
    }
}

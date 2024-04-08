package org.pmumanagement;
import java.util.Scanner;
/**
 * @author ABDESSADIQ BABA HAMOU
 * @since 08/04/2024
 */


public class CafeteriaManagement {
    private static Administrator admin = new Administrator();
    private static User user = new User(admin);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Menu principal\n1. Admin\n2. Utilisateur\n3. Quitter");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    adminMenu();
                    break;
                case "2":
                    userMenu();
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private static void adminMenu() {
        String choice;
        do {
            System.out.println("\nMenu Administrateur\n" +
                    "a. Ajouter un aliment\n" +
                    "b. Supprimer un aliment\n" +
                    "c. Mettre à jour le pays d'origine d'un aliment\n" +
                    "d. Générer un rapport\n" +
                    "e. Retour au menu principal");
            choice = scanner.nextLine();
            switch (choice) {
                case "a":
                    System.out.println("Nom de l'aliment :");
                    String name = scanner.nextLine();
                    System.out.println("Pays d'origine :");
                    String origin = scanner.nextLine();
                    admin.addFood(name, origin);
                    break;
                case "b":
                    System.out.println("Nom de l'aliment à supprimer :");
                    name = scanner.nextLine();
                    admin.deleteFood(name);
                    break;
                case "c":
                    System.out.println("Nom de l'aliment à mettre à jour :");
                    name = scanner.nextLine();
                    System.out.println("Nouveau pays d'origine :");
                    String newOrigin = scanner.nextLine();
                    admin.updateFoodOrigin(name, newOrigin);
                    break;
                case "d":
                    admin.generateReport();
                    break;
                case "e":
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (!choice.equals("e"));
    }


    private static void userMenu() {
        String choice;
        do {
            System.out.println("\nMenu Utilisateur\n" +
                    "a. Demander un nouvel aliment\n" +
                    "b. Se plaindre de la non-disponibilité d'un aliment\n" +
                    "c. Rechercher un aliment\n" +
                    "d. Retour au menu principal");
            choice = scanner.nextLine();
            switch (choice) {
                case "a":
                    System.out.println("Nom de l'aliment demandé :");
                    String name = scanner.nextLine();
                    user.requestFood(name);
                    break;
                case "b":
                    System.out.println("Nom de l'aliment pour lequel se plaindre :");
                    name = scanner.nextLine();
                    user.complainAboutFood(name);
                    break;
                case "c":
                    System.out.println("Nom de l'aliment à rechercher :");
                    name = scanner.nextLine();
                    user.searchFood(name);
                    break;
                case "d":
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (!choice.equals("d"));
    }

}

package org.pmumanagement;

/**
 * @author ABDESSADIQ BABA HAMOU
 * @since 08/04/2024
 */
public class User {
    private Administrator admin;

    public User(Administrator admin) {
        this.admin = admin;
    }

    public void inquireFood(String name) {
        Food food = admin.findFood(name);
        if (food != null) {
            System.out.println("Information sur l'aliment: " + food);
        } else {
            System.out.println("Aliment non trouvé.");
        }
    }

    public void requestFood(String name) {
        admin.handleRequest(name);
    }

    public void complainAboutFood(String name) {
        admin.handleComplaint(name);
    }

    public void searchFood(String name) {
        inquireFood(name);
    }
}

package org.pmumanagement;

/**
 * @author ABDESSADIQ BABA HAMOU
 * @since 10/04/2024
 */

public class FoodNode {
    Food food;
    FoodNode next;

    public FoodNode(Food food) {
        this.food = food;
        this.next = null;
    }
}

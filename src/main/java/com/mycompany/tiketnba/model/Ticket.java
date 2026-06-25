package com.mycompany.tiketnba.model;

public class Ticket {
    private String customerName;
    private Game game;
    private int quantity;
    private double totalAmount;

    public Ticket(String customerName, Game game, int quantity) {
        this.customerName = customerName;
        this.game = game;
        this.quantity = quantity;
        this.totalAmount = game.getPrice() * quantity;
    }

    public void printReceipt() {
        System.out.println("\n===== TICKET RECEIPT =====");
        System.out.println("Customer: " + customerName);
        System.out.println("Game: " + game.getMatch());
        System.out.println("Date: " + game.getDate());
        System.out.println("Quantity: " + quantity);
        System.out.printf(java.util.Locale.US, "Total Amount: $%.2f\n", totalAmount);
        System.out.println("==========================\n");
    }

    public String getCustomerName() { return customerName; }
    public Game getGame() { return game; }
    public int getQuantity() { return quantity; }
    public double getTotalAmount() { return totalAmount; }
}

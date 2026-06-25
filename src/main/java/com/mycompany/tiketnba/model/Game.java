package com.mycompany.tiketnba.model;

public class Game {
    private int id;
    private String match;
    private String date;
    private double price;
    private int availableSeats;

    public Game(int id, String match, String date, double price, int availableSeats) {
        this.id = id;
        this.match = match;
        this.date = date;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public int getId() { return id; }
    public String getMatch() { return match; }
    public String getDate() { return date; }
    public double getPrice() { return price; }
    public int getAvailableSeats() { return availableSeats; }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return String.format(java.util.Locale.US, "[%d] %s - %s ($%.2f) - Available: %d", id, match, date, price, availableSeats);
    }
}

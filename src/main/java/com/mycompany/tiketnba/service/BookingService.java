package com.mycompany.tiketnba.service;

import com.mycompany.tiketnba.model.Game;
import com.mycompany.tiketnba.model.Ticket;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private List<Game> games;
    private List<Ticket> bookingHistory;

    public BookingService() {
        games = new ArrayList<>();
        bookingHistory = new ArrayList<>();
        // Seed more sample data
        games.add(new Game(1, "Lakers vs Warriors", "Oct 22, 2024", 150.0, 50));
        games.add(new Game(2, "Celtics vs Knicks", "Oct 23, 2024", 120.0, 30));
        games.add(new Game(3, "Bulls vs Bucks", "Oct 24, 2024", 100.0, 40));
        games.add(new Game(4, "Suns vs Mavericks", "Oct 25, 2024", 140.0, 25));
        games.add(new Game(5, "Nuggets vs Clippers", "Oct 26, 2024", 130.0, 35));
        games.add(new Game(6, "Heat vs 76ers", "Oct 27, 2024", 110.0, 45));
        games.add(new Game(7, "Grizzlies vs Pelicans", "Oct 28, 2024", 95.0, 60));
        games.add(new Game(8, "Kings vs Timberwolves", "Oct 29, 2024", 105.0, 20));
        games.add(new Game(9, "Hawks vs Nets", "Oct 30, 2024", 85.0, 55));
        games.add(new Game(10, "Pacers vs Pistons", "Oct 31, 2024", 75.0, 70));
    }

    public List<Game> getAvailableGames() {
        return games;
    }

    public List<Game> searchByTeam(String teamName) {
        return games.stream()
                .filter(g -> g.getMatch().toLowerCase().contains(teamName.toLowerCase()))
                .toList();
    }

    public List<Game> sortByPrice(boolean ascending) {
        return games.stream()
                .sorted((g1, g2) -> ascending ? 
                        Double.compare(g1.getPrice(), g2.getPrice()) : 
                        Double.compare(g2.getPrice(), g1.getPrice()))
                .toList();
    }

    public Game findGameById(int id) {
        return games.stream()
                .filter(g -> g.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Ticket bookTicket(String customerName, int gameId, int quantity) {
        Game game = findGameById(gameId);
        if (game == null) {
            System.out.println("Error: Game not found.");
            return null;
        }

        if (game.getAvailableSeats() < quantity) {
            System.out.println("Error: Not enough seats available.");
            return null;
        }

        game.setAvailableSeats(game.getAvailableSeats() - quantity);
        Ticket ticket = new Ticket(customerName, game, quantity);
        bookingHistory.add(ticket);
        return ticket;
    }

    public List<Ticket> getBookingHistory() {
        return bookingHistory;
    }
}

package com.mycompany.tiketnba;

import com.mycompany.tiketnba.model.Game;
import com.mycompany.tiketnba.model.Ticket;
import com.mycompany.tiketnba.service.BookingService;
import java.util.Scanner;

public class TiketNBA {

    public static void main(String[] args) {
        BookingService bookingService = new BookingService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the NBA Ticket Booking System!");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View Available Games");
            System.out.println("2. Search Games by Team");
            System.out.println("3. Sort Games by Price");
            System.out.println("4. Book a Ticket");
            System.out.println("5. View Booking History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayGames(bookingService.getAvailableGames());
                    break;
                case "2":
                    System.out.print("Enter team name: ");
                    String team = scanner.nextLine();
                    displayGames(bookingService.searchByTeam(team));
                    break;
                case "3":
                    System.out.println("1. Lowest to Highest");
                    System.out.println("2. Highest to Lowest");
                    System.out.print("Choose sorting: ");
                    boolean asc = scanner.nextLine().equals("1");
                    displayGames(bookingService.sortByPrice(asc));
                    break;
                case "4":
                    performBooking(bookingService, scanner);
                    break;
                case "5":
                    displayHistory(bookingService);
                    break;
                case "6":
                    running = false;
                    System.out.println("Thank you for using our system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayHistory(BookingService service) {
        System.out.println("\n--- BOOKING HISTORY ---");
        java.util.List<Ticket> history = service.getBookingHistory();
        if (history.isEmpty()) {
            System.out.println("No tickets booked yet.");
        } else {
            for (Ticket ticket : history) {
                ticket.printReceipt();
            }
        }
    }

    private static void displayGames(java.util.List<Game> games) {
        System.out.println("\n--- GAMES LIST ---");
        if (games.isEmpty()) {
            System.out.println("No games found.");
        } else {
            for (Game game : games) {
                System.out.println(game);
            }
        }
    }

    private static void performBooking(BookingService service, Scanner scanner) {
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            displayGames(service.getAvailableGames());
            System.out.print("Enter Game ID: ");
            int gameId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            Game game = service.findGameById(gameId);
            if (game == null) {
                System.out.println("Error: Game not found.");
                return;
            }

            double total = game.getPrice() * quantity;
            System.out.printf(java.util.Locale.US, "Total Amount: $%.2f\n", total);

            System.out.println("Choose Payment Method:");
            System.out.println("1. Credit Card");
            System.out.println("2. E-Wallet (OVO/Dana)");
            System.out.println("3. Bank Transfer");
            System.out.print("Selection: ");
            String method = scanner.nextLine();

            System.out.println("Processing payment...");
            Thread.sleep(1000); // Simulate processing

            Ticket ticket = service.bookTicket(name, gameId, quantity);
            if (ticket != null) {
                System.out.println("Payment Successful via method " + method);
                ticket.printReceipt();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numbers for ID and Quantity.");
        } catch (InterruptedException e) {
            System.out.println("Payment interrupted.");
        }
    }
}

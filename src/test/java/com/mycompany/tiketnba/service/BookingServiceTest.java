package com.mycompany.tiketnba.service;

import com.mycompany.tiketnba.model.Game;
import com.mycompany.tiketnba.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {

    private BookingService bookingService;

    @BeforeEach
    public void setUp() {
        bookingService = new BookingService();
    }

    @Test
    public void testGetAvailableGames() {
        List<Game> games = bookingService.getAvailableGames();
        assertNotNull(games);
        assertFalse(games.isEmpty());
        // Verify default seeded size
        assertEquals(10, games.size());
    }

    @Test
    public void testSearchByTeamSuccess() {
        List<Game> results = bookingService.searchByTeam("lakers");
        assertEquals(1, results.size());
        assertTrue(results.get(0).getMatch().contains("Lakers"));
    }

    @Test
    public void testSearchByTeamCaseInsensitiveAndSubstring() {
        List<Game> results = bookingService.searchByTeam("VS");
        // All default seeded matches contain "vs"
        assertEquals(10, results.size());
    }

    @Test
    public void testSearchByTeamNoMatch() {
        List<Game> results = bookingService.searchByTeam("NonExistentTeam");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSortByPriceAscending() {
        List<Game> sorted = bookingService.sortByPrice(true);
        assertEquals(10, sorted.size());
        for (int i = 0; i < sorted.size() - 1; i++) {
            assertTrue(sorted.get(i).getPrice() <= sorted.get(i + 1).getPrice());
        }
    }

    @Test
    public void testSortByPriceDescending() {
        List<Game> sorted = bookingService.sortByPrice(false);
        assertEquals(10, sorted.size());
        for (int i = 0; i < sorted.size() - 1; i++) {
            assertTrue(sorted.get(i).getPrice() >= sorted.get(i + 1).getPrice());
        }
    }

    @Test
    public void testFindGameByIdValid() {
        Game game = bookingService.findGameById(3);
        assertNotNull(game);
        assertEquals("Bulls vs Bucks", game.getMatch());
    }

    @Test
    public void testFindGameByIdInvalid() {
        Game game = bookingService.findGameById(99);
        assertNull(game);
    }

    @Test
    public void testBookTicketSuccess() {
        // Find game 1: Lakers vs Warriors, Oct 22, 2024, 150.0, 50 available seats
        Game game = bookingService.findGameById(1);
        int initialSeats = game.getAvailableSeats();

        Ticket ticket = bookingService.bookTicket("Alice", 1, 3);
        assertNotNull(ticket);
        assertEquals("Alice", ticket.getCustomerName());
        assertEquals(game, ticket.getGame());
        assertEquals(3, ticket.getQuantity());
        assertEquals(450.0, ticket.getTotalAmount());

        // Verify seats deduction
        assertEquals(initialSeats - 3, game.getAvailableSeats());

        // Verify history update
        List<Ticket> history = bookingService.getBookingHistory();
        assertEquals(1, history.size());
        assertEquals(ticket, history.get(0));
    }

    @Test
    public void testBookTicketGameNotFound() {
        Ticket ticket = bookingService.bookTicket("Bob", 99, 1);
        assertNull(ticket);
        assertTrue(bookingService.getBookingHistory().isEmpty());
    }

    @Test
    public void testBookTicketInsufficientSeats() {
        // Find game 8: Kings vs Timberwolves, 20 seats
        Game game = bookingService.findGameById(8);
        int initialSeats = game.getAvailableSeats();

        Ticket ticket = bookingService.bookTicket("Charlie", 8, 25);
        assertNull(ticket);
        
        // Seats should remain unchanged
        assertEquals(initialSeats, game.getAvailableSeats());
        assertTrue(bookingService.getBookingHistory().isEmpty());
    }
}

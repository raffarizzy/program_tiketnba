package com.mycompany.tiketnba.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    @Test
    public void testTicketCreationAndGetters() {
        Game game = new Game(1, "Lakers vs Warriors", "Oct 22, 2024", 150.0, 50);
        Ticket ticket = new Ticket("John Doe", game, 3);

        assertEquals("John Doe", ticket.getCustomerName());
        assertEquals(game, ticket.getGame());
        assertEquals(3, ticket.getQuantity());
        assertEquals(450.0, ticket.getTotalAmount(), 0.001);
    }

    @Test
    public void testPrintReceiptDoesNotThrow() {
        Game game = new Game(1, "Lakers vs Warriors", "Oct 22, 2024", 150.0, 50);
        Ticket ticket = new Ticket("John Doe", game, 3);
        
        assertDoesNotThrow(() -> ticket.printReceipt());
    }
}

package com.mycompany.tiketnba.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testGameConstructorAndGetters() {
        Game game = new Game(1, "Lakers vs Warriors", "Oct 22, 2024", 150.0, 50);

        assertEquals(1, game.getId());
        assertEquals("Lakers vs Warriors", game.getMatch());
        assertEquals("Oct 22, 2024", game.getDate());
        assertEquals(150.0, game.getPrice());
        assertEquals(50, game.getAvailableSeats());
    }

    @Test
    public void testSetAvailableSeats() {
        Game game = new Game(1, "Lakers vs Warriors", "Oct 22, 2024", 150.0, 50);
        game.setAvailableSeats(45);
        assertEquals(45, game.getAvailableSeats());
    }

    @Test
    public void testToString() {
        Game game = new Game(1, "Lakers vs Warriors", "Oct 22, 2024", 150.0, 50);
        String expected = "[1] Lakers vs Warriors - Oct 22, 2024 ($150.00) - Available: 50";
        assertEquals(expected, game.toString());
    }
}

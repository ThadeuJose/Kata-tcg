package com.tcg.observable;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tcg.Player;
import com.tcg.architecture.observer.Message;

public class MessageTest {
    @Test
    public void shouldReturnRightType() {
        Integer number = 0;
        Message message = new Message("Test", number);
        assertEquals(number, message.getPayload(Integer.class));
    }

    @Test
    public void shouldReturnRightObject() {
        Player player = Player.createPlayerWithEmptyDeck();
        Message message = new Message("Test", player);
        assertEquals(player, message.getPayload(Player.class));
    }
}

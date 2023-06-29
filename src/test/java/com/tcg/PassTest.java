package com.tcg;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.tcg.model.Match;
import com.tcg.model.move.MoveFactory;
import com.tcg.strategy.PassStrategy;
import com.tcg.strategy.Strategy;
import com.tcg.util.GameBuilder;
import com.tcg.util.MatchBuilder;
import com.tcg.util.PlayerBuilder;

public class PassTest {
    @Test
    public void shouldPass() {
        Player player1 = PlayerBuilder.anyPlayer1().withStrategy(new PassStrategy()).build();
        Player player2 = PlayerBuilder.anyPlayer2().build();
        Match match = MatchBuilder.aMatch().withActivePlayer(player1).withNonActivePlayer(player2).build();
        Game game = GameBuilder.aGame().withMatch(match).build();
        game.run();
        Player activePlayer = match.getActivePlayer();
        assertEquals("Player 2", activePlayer.getName());
    }

    @Test
    public void shouldPassBack() {
        Strategy strategy1 = mock(Strategy.class);
        when(strategy1.play(any(), any())).thenReturn(MoveFactory.createPassMove(),
                MoveFactory.createEndMove());
        Player player1 = PlayerBuilder.anyPlayer1().withStrategy(strategy1).build();

        Strategy strategy2 = mock(Strategy.class);
        when(strategy2.play(any(), any())).thenReturn(MoveFactory.createPassMove());
        Player player2 = PlayerBuilder.anyPlayer2().withStrategy(strategy2).build();

        Match match = MatchBuilder.aMatch().withActivePlayer(player1).withNonActivePlayer(player2).build();
        Game game = GameBuilder.aGame().withMatch(match).build();

        game.run();

        Player activePlayer = match.getActivePlayer();
        assertEquals("Player 1", activePlayer.getName());
    }
}

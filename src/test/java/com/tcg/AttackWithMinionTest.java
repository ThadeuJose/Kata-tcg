package com.tcg;

import static com.tcg.util.CreateUtils.createGame;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.tcg.model.ManaRefillService;

public class AttackWithMinionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldntAttackOpponentOnFirstRound() {
        thrown.expect(CantAttackWithMinion.class);
        thrown.expectMessage("Cant attack with a minion in the same turn is play");

        Player activePlayer = new Player.Builder()
                .setMana(1)
                .setCardsInHand(new Card.Builder(1).build())
                .build();
        Player nonActivePlayer = Player.createPlayerWithStandardDeck();
        ManaRefillService manaRefillService = mock(ManaRefillService.class);

        Game game = createGame(manaRefillService, activePlayer, nonActivePlayer);

        game.startTurn();

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_MINION)
                .build();

        game.play(move);

        game.attackPlayerWithMinion(0);
    }

    @Test
    public void shouldAttackOpponent() {
        Player activePlayer = new Player.Builder()
                .setMana(1)
                .setCardsInHand(new Card.Builder(1).build())
                .build();
        Player nonActivePlayer = Player.createPlayerWithStandardDeck();

        ManaRefillService manaRefillService = mock(ManaRefillService.class);

        Game game = createGame(manaRefillService, activePlayer, nonActivePlayer);

        game.startTurn();

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_MINION)
                .build();

        game.play(move);
        game.pass();

        game.startTurn();
        game.pass();
        game.attackPlayerWithMinion(0);

        assertEquals(29, nonActivePlayer.getCurrentHealth());
    }

    @Test
    public void shouldAttackOpponentWithMultipleMinions() {
        Player activePlayer = new Player.Builder()
                .setMana(6)
                .setCardsInHand(new Card.Builder(1).build(), new Card.Builder(5).build())
                .build();
        Player nonActivePlayer = Player.createPlayerWithStandardDeck();
        ManaRefillService manaRefillService = mock(ManaRefillService.class);

        Game game = createGame(manaRefillService, activePlayer, nonActivePlayer);

        game.startTurn();

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_MINION)
                .build();

        game.play(move);
        game.play(move);
        game.pass();

        game.startTurn();
        game.pass();

        game.attackPlayerWithMinion(0);
        game.attackPlayerWithMinion(1);

        assertEquals(24, nonActivePlayer.getCurrentHealth());
    }

    @Test
    public void shouldntAttackAMinionWithAMinionIfIsFirstRound() {
        thrown.expect(CantAttackWithMinion.class);
        thrown.expectMessage("Cant attack with a minion in the same turn is play");

        Player activePlayer = new Player.Builder()
                .setMana(1)
                .setCardsInHand(new Card.Builder(1).build())
                .build();
        Player nonActivePlayer = new Player.Builder()
                .setMana(2)
                .setCardsInHand(new Card.Builder(2).build())
                .build();
        ManaRefillService manaRefillService = mock(ManaRefillService.class);

        Game game = createGame(manaRefillService, activePlayer, nonActivePlayer);

        game.startTurn();

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_MINION)
                .build();

        game.pass();
        game.startTurn();
        game.play(move);
        game.pass();
        game.startTurn();
        game.play(move);
        game.attackMinionWithMinion(0, 0);
    }

    @Test
    public void shouldAttackAMinionWithAMinion() {
        Player activePlayer = new Player.Builder()
                .setMana(1)
                .setCardsInHand(new Card.Builder(1).build())
                .build();
        Player nonActivePlayer = new Player.Builder()
                .setMana(2)
                .setCardsInHand(new Card.Builder(2).build())
                .build();

        ManaRefillService manaRefillService = mock(ManaRefillService.class);

        Game game = createGame(manaRefillService, activePlayer, nonActivePlayer);

        game.startTurn();

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_MINION)
                .build();

        game.play(move);
        game.pass();
        game.startTurn();
        game.play(move);
        game.pass();
        game.attackMinionWithMinion(0, 0);

        assertEquals(1, nonActivePlayer.getMinion(0).getHealth());
    }

    @Test
    public void shouldAttackAMinionWithAMinionAndDie() {
        Player activePlayer = new Player.Builder()
                .setMana(1)
                .setCardsInHand(new Card.Builder(1).build())
                .build();
        Player nonActivePlayer = new Player.Builder()
                .setMana(2)
                .setCardsInHand(new Card.Builder(2).build())
                .build();
        ManaRefillService manaRefillService = mock(ManaRefillService.class);

        Game game = createGame(manaRefillService, activePlayer, nonActivePlayer);

        game.startTurn();

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_MINION)
                .build();

        game.play(move);
        game.pass();
        game.startTurn();
        game.play(move);
        game.pass();
        game.attackMinionWithMinion(0, 0);

        assertEquals(0, activePlayer.getBoardSize());
    }

    @Test
    public void shouldAttackOpponentOnlyOnce() {
        thrown.expect(CantAttackWithMinion.class);
        thrown.expectMessage("Cant attack with a minion more then once");
        Player activePlayer = new Player.Builder()
                .setMana(1)
                .setCardsInHand(new Card.Builder(1).build())
                .build();

        Player nonActivePlayer = Player.createPlayerWithStandardDeck();
        ManaRefillService manaRefillService = mock(ManaRefillService.class);

        Game game = createGame(manaRefillService, activePlayer, nonActivePlayer);

        game.startTurn();

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_MINION)
                .build();

        game.play(move);

        game.pass();
        game.startTurn();
        game.pass();
        game.attackPlayerWithMinion(0);
        game.attackPlayerWithMinion(0);
    }

    @Test
    public void shouldAttackAMinionWithAMinionOnlyOnce() {
        thrown.expect(CantAttackWithMinion.class);
        thrown.expectMessage("Cant attack with a minion more then once");
        Player activePlayer = new Player.Builder()
                .setMana(2)
                .setCardsInHand(new Card.Builder(2).build())
                .build();
        Player nonActivePlayer = new Player.Builder()
                .setMana(6)
                .setCardsInHand(new Card.Builder(1).build(), new Card.Builder(5).build())
                .build();

        ManaRefillService manaRefillService = mock(ManaRefillService.class);

        Game game = createGame(manaRefillService, activePlayer, nonActivePlayer);

        game.startTurn();

        Move move = new Move.Builder().setCardIndex(0).setType(Type.AS_MINION)
                .build();

        game.play(move);
        game.pass();

        game.startTurn();
        game.play(move);
        game.play(move);
        game.pass();

        game.attackMinionWithMinion(0, 0);
        game.attackMinionWithMinion(0, 0);
    }

}

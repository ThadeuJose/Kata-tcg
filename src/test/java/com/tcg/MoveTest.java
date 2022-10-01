package com.tcg;

import static com.tcg.Move.toMinion;
import static com.tcg.Move.toPlayer;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tcg.Move.Builder;
import com.tcg.target.TargetType;
import com.tcg.target.TargetMinion;
import com.tcg.target.TargetPlayer;

public class MoveTest {
    @Test
    public void shouldCreateDealDamageToPlayerMove() {
        Move actual = aMove().dealDamage(2, toPlayer());
        assertEquals(Type.AS_DAMAGE, actual.getType());
        assertEquals(2, actual.getCardIndex());

        TargetType expected = actual.getTargetType();
        assertEquals(TargetPlayer.class, expected.getClass());
    }

    @Test
    public void shouldCreateDealDamageToMinionMove() {
        Move actual = aMove().dealDamage(2, toMinion(1));
        assertEquals(Type.AS_DAMAGE, actual.getType());
        assertEquals(2, actual.getCardIndex());

        TargetType expected = actual.getTargetType();
        assertEquals(TargetMinion.class, expected.getClass());
        assertEquals(new TargetMinion(1), expected);
    }

    private Builder aMove() {
        return new Move.Builder();
    }
}

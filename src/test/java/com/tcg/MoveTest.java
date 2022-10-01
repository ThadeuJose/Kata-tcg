package com.tcg;

import static com.tcg.Move.toMinion;
import static com.tcg.Move.toPlayer;
import static com.tcg.util.CreateUtils.aMove;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tcg.target.TargetMinion;
import com.tcg.target.TargetPlayer;
import com.tcg.target.TargetType;

public class MoveTest {
    @Test
    public void shouldCreateDealDamageToPlayerMove() {
        Move actual = aMove().dealDamage(2, toPlayer());
        assertEquals(Type.AS_DAMAGE, actual.getType());
        assertEquals(2, actual.getCardIndex());

        TargetType actualClass = actual.getTargetType();
        assertEquals(TargetPlayer.class, actualClass.getClass());
    }

    @Test
    public void shouldCreateDealDamageToMinionMove() {
        Move actual = aMove().dealDamage(2, toMinion(1));
        assertEquals(Type.AS_DAMAGE, actual.getType());
        assertEquals(2, actual.getCardIndex());

        TargetType actualClass = actual.getTargetType();
        assertEquals(TargetMinion.class, actualClass.getClass());
        assertEquals(new TargetMinion(1), actualClass);
    }

}

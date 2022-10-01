package com.tcg.target;

import com.tcg.Target;

public class TargetPlayer implements TargetType {

    @Override
    public Target visit(TargetVisitor visitor) {
        return visitor.visit(this);
    }
}

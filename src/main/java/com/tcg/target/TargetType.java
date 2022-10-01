package com.tcg.target;

import com.tcg.Target;

public interface TargetType {

    public Target visit(TargetVisitor visitor);

}

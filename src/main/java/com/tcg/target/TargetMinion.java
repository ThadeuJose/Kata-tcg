package com.tcg.target;

import com.tcg.Target;

public class TargetMinion implements TargetType {
    private int index;

    public TargetMinion(int index) {
        this.index = index;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + index;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TargetMinion other = (TargetMinion) obj;
        if (index != other.index)
            return false;
        return true;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public Target visit(TargetVisitor visitor) {
        return visitor.visit(this);
    }

}

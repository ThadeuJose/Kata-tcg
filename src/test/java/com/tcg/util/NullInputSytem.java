package com.tcg.util;

import com.tcg.system.InputSystem;

public class NullInputSytem extends InputSystem {
    @Override
    public String getInput() {
        return "";
    }
}

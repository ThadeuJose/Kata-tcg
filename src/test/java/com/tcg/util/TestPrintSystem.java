package com.tcg.util;

import java.util.ArrayList;
import java.util.List;

import com.tcg.system.PrintSystem;

public class TestPrintSystem extends PrintSystem {

    private List<String> log;

    public TestPrintSystem() {
        log = new ArrayList<>();
    }

    @Override
    public void print(String str) {
        log.add(str);
    }

    public String get(int index) {
        return log.get(index);
    }

    public Object getLastRegister() {
        return log.get(log.size() - 1);
    }
}

package com.shekhar.parkinglot.service;

import java.util.List;

public interface CommandOps {
    void apply(List<String> input);
}

package io.github.davidsantana06.model;

import java.util.Set;

public record Gesture(
    String name,
    Set<String> winsAgainst,
    Set<String> losesAgainst
) {}

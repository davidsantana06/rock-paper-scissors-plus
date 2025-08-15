package io.github.davidsantana06.model;

import io.github.davidsantana06.facade.OutputFacade;

public enum Result {

    WINS("wins against", OutputFacade.GREEN),
    LOSES("loses against", OutputFacade.RED),
    DRAWS("draws with", OutputFacade.YELLOW);

    private final String relation;
    private final String color;

    Result(String relation, String color) {
        this.relation = relation;
        this.color = color;
    }

    public String getRelation() { return relation; }

    public String getColor() { return color; }
}

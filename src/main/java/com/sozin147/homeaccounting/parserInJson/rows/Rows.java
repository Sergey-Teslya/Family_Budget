package com.sozin147.homeaccounting.parserInJson.rows;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Rows {

    @JsonProperty("c")
    private List<Cells> cells;

    public Rows() {
    }

    public Rows(List<Cells> cells) {
        this.cells = cells;
    }

    public List<Cells> getCells() {
        return cells;
    }
}

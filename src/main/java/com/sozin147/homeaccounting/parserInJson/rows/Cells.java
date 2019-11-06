package com.sozin147.homeaccounting.parserInJson.rows;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Cells {

    @JsonProperty("v")
    private Object rowsValue;

    public Cells(Object rowsValue) {
        this.rowsValue = rowsValue;
    }

    public Object getRowsValue() {
        return rowsValue;
    }
}

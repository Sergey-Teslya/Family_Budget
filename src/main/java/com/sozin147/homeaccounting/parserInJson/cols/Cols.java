package com.sozin147.homeaccounting.parserInJson.cols;

public class Cols {
    private String label;
    private String type;

    public Cols() {
    }

    public Cols(String label, String type) {
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }
}

package com.sozin147.homeaccounting.model;

public enum QuantityDay {
    ONE_DAY(1), ONE_WEEK(7), ONE_MONTH(28);

    public int day;

    QuantityDay(int day) {
        this.day = day;
    }
}

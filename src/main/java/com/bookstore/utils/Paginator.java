package com.bookstore.utils;

import java.io.Serializable;
import java.util.List;

public class Paginator<T> implements Serializable {

    private List<T> list;

    private List<T> sheet;

    private int numberOfSheet = 0;

    private int range;

    private int quantitySheets;

    public Paginator(List<T> list, int range) {

        this.list = list;
        this.range = range;
        this.quantitySheets = (int) Math.ceil(this.list.size() / (double) this.range);
    }

    public Paginator(List<T> list) {
        this(list, 5);
    }

    public List<T> nextSheet() {

        numberOfSheet = (numberOfSheet + 1) % quantitySheets;
        sheet = goToSheet(numberOfSheet);

        return sheet;
    }

    public List<T> prevSheet() {

        numberOfSheet = (numberOfSheet + quantitySheets + 1) % (quantitySheets);
        sheet = goToSheet(numberOfSheet);

        return sheet;
    }

    public List<T> goToSheet(int number) {

        if (number != quantitySheets - 1) {
            sheet = list.subList(number * range, range * (number + 1));
        } else {
            sheet = list.subList(number * range, list.size());
        }

        return sheet;
    }

    public List<T> firstSheet() {
        return goToSheet(0);
    }

    public int numberOfCurrentSheet() {
        return numberOfSheet;
    }

    public int getRange() {
        return range;
    }

    public int getQuantitySheets() {
        return quantitySheets;
    }

}

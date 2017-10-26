package com.bookstore.utils;

import java.io.Serializable;
import java.util.List;

public class Paginator<T> implements Serializable {

    private List<T> list;

    private List<T> subList;

    private int currentSheet = 0;

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

        currentSheet = (currentSheet + 1) % quantitySheets;
        subList = goToSheet(currentSheet);

        return subList;
    }

    public List<T> prevSheet() {

        currentSheet = (currentSheet + quantitySheets + 1) % (quantitySheets);
        subList = goToSheet(currentSheet);

        return subList;
    }

    public List<T> goToSheet(int number) {

        if (number != quantitySheets - 1) {
            subList = list.subList(number * range, range * (number + 1));
        } else {
            subList = list.subList(number * range, list.size());
        }

        return subList;
    }

    public List<T> firstSheet() {
        return goToSheet(0);
    }

    public int numberOfCurrentSheet() {
        return currentSheet;
    }

    public int getRange() {
        return range;
    }

    public int getQuantitySheets() {
        return quantitySheets;
    }

}

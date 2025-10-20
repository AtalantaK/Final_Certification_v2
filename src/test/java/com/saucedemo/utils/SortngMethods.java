package com.saucedemo.utils;

public enum SortngMethods {
    az("Name (A to Z)"),
    za("Name (Z to A)"),
    lohi("Price (low to high)"),
    hilo("Price (high to low)");

    private String sortingName;

    SortngMethods(String sortingName) {
        this.sortingName = sortingName;
    }

    public String getSortingName() {
        return sortingName;
    }
}

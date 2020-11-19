package com.nhpva.shopping.model;

import java.util.List;

public class ProductResponse {
    private List<Product> results;
    private List<String> imgEvents;

    public List<String> getImgEvents() {
        return imgEvents;
    }

    public void setImgEvents(List<String> imgEvents) {
        this.imgEvents = imgEvents;
    }

    public List<Product> getResults() {
        return results;
    }

    public void setResults(List<Product> results) {
        this.results = results;
    }
}

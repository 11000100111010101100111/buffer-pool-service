package com.kit.weather.domain.param;

import java.util.List;

public class QueryCityLocationParam {
    String type;
    String lonMin;
    String latMin;
    String lonMax;
    String latMax;
    List<String> queryTypes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLonMin() {
        return lonMin;
    }

    public void setLonMin(String lonMin) {
        this.lonMin = lonMin;
    }

    public String getLatMin() {
        return latMin;
    }

    public void setLatMin(String latMin) {
        this.latMin = latMin;
    }

    public String getLonMax() {
        return lonMax;
    }

    public void setLonMax(String lonMax) {
        this.lonMax = lonMax;
    }

    public String getLatMax() {
        return latMax;
    }

    public void setLatMax(String latMax) {
        this.latMax = latMax;
    }

    public List<String> getQueryTypes() {
        return queryTypes;
    }

    public void setQueryTypes(List<String> queryTypes) {
        this.queryTypes = queryTypes;
    }
}

package com.kit.weather.domain.vo;

public class QueryCityLocationCountVo {
    int provinceCount;
    int cityCount;
    int countyCount;

    public int getProvinceCount() {
        return provinceCount;
    }

    public void setProvinceCount(int provinceCount) {
        this.provinceCount = provinceCount;
    }

    public int getCityCount() {
        return cityCount;
    }

    public void setCityCount(int cityCount) {
        this.cityCount = cityCount;
    }

    public int getCountyCount() {
        return countyCount;
    }

    public void setCountyCount(int countyCount) {
        this.countyCount = countyCount;
    }
}

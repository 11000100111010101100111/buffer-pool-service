package com.kit.system.domain.weather.baidu.entity;

import com.kit.common.core.domain.BaseEntity;

import java.math.BigDecimal;

public class CityInfo extends BaseEntity {
    int id;
    /**
     * 邮政编码
     */
    String adCode;
    String name;
    BigDecimal lon;
    BigDecimal lat;
    /**
     * @see com.kit.system.domain.weather.baidu.emun.CityType
     */
    String type;//省0（直辖市1），省会2，市3，县4（区5）

    public CityInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

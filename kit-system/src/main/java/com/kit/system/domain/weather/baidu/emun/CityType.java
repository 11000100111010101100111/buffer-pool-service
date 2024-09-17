package com.kit.system.domain.weather.baidu.emun;

/**
 * 省0（直辖市1），省会2，市3，县4（区5）
 */
public enum CityType {
    PROVINCE("0"),
    MUNICIPALITY("1"),//municipality directly under the Central Government
    PROVINCIAL_CAPITAL("2"), //provincial capital
    CITY("3"),
    COUNTRY("4"),
    DISTRICT("5"),//district
    ;

    String type;

    CityType(String t) {
        this.type = t;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

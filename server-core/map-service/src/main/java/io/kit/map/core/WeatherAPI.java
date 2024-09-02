package io.kit.map.core;

public interface WeatherAPI<T> {
    T query(String code) throws Exception;
}

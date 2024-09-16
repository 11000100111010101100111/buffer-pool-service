package com.kit.common.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 *
 * @author xjh
 * @since 2024/8/10
 * Time: 12:01
 **/
public class GlobalUtil {
    private GlobalUtil() {
    }

    public static <T> List<T> list(T... ent) {
        List<T> list = new ArrayList<>();
        Optional.ofNullable(ent).ifPresent(entry -> {
            for (T e : entry) {
                list.add(e);
            }
        });
        return list;
    }
}

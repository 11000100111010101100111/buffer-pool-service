package io.kit.hook.work;

import cn.hutool.core.collection.CollUtil;
import io.kit.hook.dto.WebHookInfoDto;
import io.kit.hook.entity.WebHookEvent;
import io.kit.hook.enums.HookType;
import io.kit.hook.work.sender.HttpSenderUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Converter<O> {
    O convert(Object o, WebHookInfoDto myOpenHookInfo, WebHookEvent event);

    default Map<String, Object> analyseHead(WebHookInfoDto myOpenHookInfo) {
        Map<String, Object> head = new HashMap<>();
        String customHttpHead = myOpenHookInfo.getCustomHttpHeaders();
        if (StringUtils.isNotBlank(customHttpHead)) {
            Header[] headers = HttpSenderUtil.toHeads(customHttpHead);
            if (headers.length > 0) {
                for (Header header : headers) {
                    head.put(header.getName(), header.getValue());
                }
            }
        }
        return head;
    }

    default void asValueWhenCustomKeyNotExists(String key ,Map<String, Object> map, Value value) {
        if (!value.contains) {
            map.put(key, null);
        }
        map.put(key, value.actualValue);
    }

    default Map<String, Object> eventData(Object o, WebHookInfoDto myOpenHookInfo) {
        String customTemplate = myOpenHookInfo.getCustomTemplate();
        Map<String, Object> template = customTemplate(customTemplate);
        Map<String, Object> result = getAllAlarmInfo(o);
        if (!template.isEmpty()) {
            new ArrayList<>(template.keySet()).forEach(key -> {
                Object value = template.get(key);
                Object replaceValue = replaceValue(key, value, result);
                if (replaceValue instanceof Value) {
                    asValueWhenCustomKeyNotExists(key, template, (Value)replaceValue);
                    return;
                }
                template.put(key, replaceValue);
            });
            return template;
        } else {
            return result;
        }
    }

    Map<String, Object> getAllAlarmInfo(Object o);

    default Object replaceValue(String key, Object value, Map<String, Object> map) {
        if (value instanceof String && isCustomKey((String) value)) {
            String normalKey = asNormalKey(value);
            if (containsKey(normalKey, map)) {
                return valueFromMap(normalKey, map);
            }
            String fullKey = (String) value;
            if (isManyCustomKeys(fullKey)) {
                return replaceManyCustomKey(fullKey, value, map);
            }
        } else if (value instanceof Map) {
           return replaceMap(key, (Map<String, Object>) value, map);
        } else if(value instanceof Collection) {
            return replaceCollection(key, (Collection<Object>) value, map);
        }
        return value;
    }

    default Object replaceMap(String key, Map<String, Object> valueMap, Map<String, Object> map) {
        Map<String,Object> afterMap = new HashMap<>();
        if (CollUtil.isEmpty(valueMap)) return afterMap;
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            String mapKey = entry.getKey();
            afterMap.put(mapKey, replaceValue(mapKey, entry.getValue(), map));
        }
        return afterMap;
    }
    default Object replaceCollection(String key, Collection<Object> valueCollection, Map<String, Object> map) {
        List<Object> afterCollection = new ArrayList<>();
        if (CollUtil.isEmpty(valueCollection)) return afterCollection;
        for (Object obj : valueCollection) {
            afterCollection.add(replaceValue(key, obj, map));
        }
        return afterCollection;
    }

    default Object replaceManyCustomKey(String normalKey, Object value, Map<String, Object> map) {
        List<String> keys = asContainsKey(normalKey);
        if (!keys.isEmpty()) {
            String valTemp = String.valueOf(value);
            for (String keyItem : keys) {
                Value valueFromMap = valueFromMap(keyItem, map);
                String v = valueFromMap.contains ? String.valueOf(valueFromMap.actualValue) : "";
                String keyMatch = "${" + keyItem + "}";
                while (valTemp.contains(keyMatch)) {
                    valTemp = valTemp.replace(keyMatch, v);
                }
            }
            return valTemp;
        }
        return value;
    }



    default boolean containsKeyInMap(String key, Map<String, Object> map) {
        return containsKey(key, map);
    }

    default boolean containsKeyInList(String key, Collection<Object> collection) {
        List<Object> list = new ArrayList<>(collection);
        boolean inMap = false;
        for (Object o : list) {
            if (o instanceof Map) {
                inMap = containsKey(key, (Map<String, Object>) o);
            }
            if (inMap) {
                return true;
            }
        }
        return false;
    }

    default boolean containsKey(String key, Map<String, Object> map) {
        int index = key.indexOf(".");
        if (index > 0 && index < key.length() - 1) {
            String k = key.substring(0, index);
            if (!map.containsKey(k)) {
                return false;
            }
            Object subMap = map.get(k);
            k = key.substring(index + 1);
            if (subMap instanceof Map) {
                return containsKeyInMap(k, (Map<String, Object>) subMap);
            } else if (subMap instanceof Collection) {
                return containsKeyInList(k, (Collection<Object>) subMap);
            } else {
                return false;
            }
        }
        return map.containsKey(key);
    }

    default String asNormalKey(Object key) {
        String k = String.valueOf(key);
        if (k.startsWith("${")) {
            k = k.replace("${", "");
        }
        if (k.endsWith("}")) {
            return k.substring(0, k.length() - 1);
        }
        return k;
    }

    default List<String> asContainsKey(Object key) {
        List<String> keys = new ArrayList<>();
        if (key instanceof String) {
            String k = (String) key;
            while (k.contains("${")) {
                int pif = k.lastIndexOf("${");
                if (pif < 0) {
                    break;
                }
                int suf = k.indexOf("}", pif + 1);
                if (pif > 0 && suf > 0 && suf > pif - 1) {
                    keys.add(k.substring(pif + 2, suf));
                }
                k = k.substring(0, pif);
            }
        }
        return keys;
    }

    default boolean isCustomKey(String key) {
        return StringUtils.isNotBlank(key) && (isOnlyOneCustomKeys(key) || isManyCustomKeys(key));
    }

    default boolean isOnlyOneCustomKeys(String key) {
        return key.startsWith("${") && key.endsWith("}");
    }
    default boolean isManyCustomKeys(String key) {
        int pif = key.indexOf("${");
        int suf = key.indexOf("}");
        return pif >= 0 && suf > pif;
    }

    default Value valueFromMap(String key, Map<String, Object> map) {
        if (StringUtils.isBlank(key) || null == map) return null;
        int index = key.indexOf(".");
        if (index > 0) {
            String fatherKey = key.substring(0, index);
            Object o = map.get(fatherKey);
            String sKey = key.substring(index + 1);
            if (o instanceof Map) {
                return valueFromMap(sKey, (Map<String, Object>) o);
            } else if (o instanceof Collection) {
                List<Object> list = new ArrayList<>((Collection<Object>) o);
                List<Object> l = new ArrayList<>();
                for (Object obj : list) {
                    if (obj instanceof Map) {
                        fixListElement(valueFromMap(sKey, (Map<String, Object>) obj), l);
                    }
                }
                return new Value(true, l);
            } else {
                return new Value(true, o);
            }
        }
        return new Value(map.containsKey(key), map.get(key));
    }

    default void fixListElement(Object value, Collection<Object> list) {
        if (!(value instanceof Value)) {
            list.add(value);
            return;
        }
        Value v = (Value) value;
        if (v == v.actualValue) {
            return;
        }
        if (v.contains) {
            if (v.actualValue instanceof Value) {
                fixListElement(v.actualValue, list);
            } else {
                list.add(v.actualValue);
            }
        }
    }

    default Map<String, Object> customTemplate(String customTemplate) {
        return new HashMap<>();
    }


    default Object fixObject(Object obj) {
        if (obj instanceof Map) {
            Map<String, Object> newMap = new HashMap<>();
            fixMap((Map<String, Object>)obj, newMap);
            return newMap;
        } else if (obj instanceof Collection) {
            List<Object> newCollection = new ArrayList<>();
            fixCollection((Collection<Object>) obj, newCollection);
            return newCollection;
        } else if (obj instanceof Value) {
            return fixValue((Value) obj);
        }
        return obj;
    }

    default void fixMap(Map<String, Object> map, Map<String, Object> newMap) {
        map.forEach((key, value) -> {
            newMap.put(key, fixObject(value));
        });
    }

    default void fixCollection(Collection<Object> collection, Collection<Object> newCollection) {
        for (Object item : collection) {
            Object o = fixObject(item);
            if (null != o) {
                newCollection.add(o);
            }
        }
    }

    default Object fixValue(Value value) {
        Object actualValue = value.actualValue;
        if (value == actualValue) {
            return null;
        }
        if (actualValue instanceof Value) {
            return fixValue(value);
        }
        return actualValue;
    }

    static class Value {
        boolean contains;
        Object actualValue;
        Value( boolean contains, Object value) {
            this.contains = contains;
            this.actualValue = value;
        }
    }

    public static HookType getHookBeanNameByType(String type) {
        if (StringUtils.isBlank(type)) {
            return null;
        }
        for (HookType value : HookType.values()) {
            if (value.getHookName().equals(type)) return value;
        }
        return null;
    }
}

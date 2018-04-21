package com.online.hospital.utils.constants;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.online.hospital.utils.ExceptionFactory;
import com.online.hospital.utils.FutureObject;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:14
 * Description:
 */
public class FutureMap <K, V> {


    private Map<K, V> innerData = new HashMap<>();

    public FutureMap<K, V> kv(K k, V v) {
        this.innerData.put(k, v);
        return this;
    }

    public static <K, V> FutureMap<K, V> of() {
        return new FutureMap<>();
    }

    public Stream<K> streamK() {
        return innerData.keySet().stream();
    }

    public Stream<Map.Entry<K, V>> streamEntry() {
        return innerData.entrySet().stream();
    }

    public List<K> listK() {
        return streamK().sorted().collect(Collectors.toList());
    }

    public List<Object> listValue() {
        return streamK()
                .sorted()
                .map((k) -> v(k) instanceof String ? String.format("'%s'", v(k)) : v(k))
                .collect(Collectors.toList());
    }

    public boolean k(K k) {
        return innerData.containsKey(k);
    }

    public Object vv(K k1, K k2) {

        FutureObject.of().notNull(k1, k2);

        V v = this.innerData.get(k1);

        if (Objects.nonNull(v) && v instanceof Map) {
            return ((Map) v).get(k2);
        }

        return null;
    }

    public Integer ivv(K k1, K k2) {
        Object o = vv(k1, k2);
        if (o instanceof Integer) {
            return Integer.valueOf(o.toString());
        }

        throw ExceptionFactory.createException("not int");
    }

    public String svv(K k1, K k2) {
        Object o = vv(k1, k2);
        return String.valueOf(o);
    }

    public String sv(K k) {
        return String.valueOf(v(k));
    }

    public int iv(K k) {
        return Integer.parseInt(sv(k));
    }

    public V v(K k) {
        return innerData.get(k);
    }

    public FutureMap<K, V> join(Map<K, V> data) {
        this.innerData.putAll(data);
        return this;
    }

    // public <T> T transToType(Class<T> clazz) {
    //
    // }

    public boolean isEmpty() {
        return innerData.isEmpty();
    }

    public boolean isNonEmpty() {
        return !innerData.isEmpty();
    }

    public Set<Map.Entry<K, V>> entrys() {
        return this.innerData.entrySet();
    }

    public Map<K, V> toMap() {
        return this.innerData;
    }

    public String toJson() {
        return JSON.toJSONString(this.innerData);
    }

    public int size() {
        return this.innerData.size();
    }

    public static boolean checkNull(FutureMap checkMap) {
        return checkMap == null;
    }

    public static boolean checkNotNull(FutureMap checkMap) {
        return checkMap != null;
    }

    public static boolean checkNull(Map checkMap) {
        return checkMap == null;
    }

    public static boolean checkNotNull(Map checkMap) {
        return checkMap != null;
    }

    public static boolean checkEmpty(FutureMap checkMap) {
        return checkMap == null || checkMap.isEmpty();
    }

    public static boolean checkNotEmpty(FutureMap checkMap) {
        return !checkEmpty(checkMap);
    }

    public static boolean checkEmpty(Map checkMap) {
        return checkMap == null || checkMap.isEmpty();
    }

    public static boolean checkNotEmpty(Map checkMap) {
        return !checkEmpty(checkMap);
    }

    public static Map<String, String> diff2Str(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, String> returnData = Maps.newHashMap();
        if (checkNull(map1)
                || checkNull(map2)
                || (checkEmpty(map1) && checkEmpty(map2))) {
            return returnData;
        }

        int map1Size = map1.size();
        int map2Size = map2.size();

        // 以map1为准
        if (map1Size >= map2Size) {
            for (Map.Entry<String, Object> entry : map1.entrySet()) {
                if (map2.containsKey(entry.getKey())
                        && String.valueOf(map2.get(entry.getKey()))
                        .equals(String.valueOf(entry.getValue()))) {
                    // key->str,value->str
                    returnData.put(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
        } else {
            // 以map2为准
            for (Map.Entry<String, Object> entry : map2.entrySet()) {
                if (map1.containsKey(entry.getKey())
                        && String.valueOf(map1.get(entry.getKey()))
                        .equals(String.valueOf(entry.getValue()))) {
                    // key->str,value->str
                    returnData.put(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
        }

        return returnData;
    }

    @Override
    public String toString() {
        return "FutureMap{" +
                "innerData=" + innerData +
                '}';
    }
}

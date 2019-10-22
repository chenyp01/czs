package com.beasts.czs.utils;

import java.util.HashMap;
import java.util.Map;

public class ResObject extends HashMap<String, Object> {

    public ResObject() {
        this.put((String)"code", Integer.valueOf(0));
    }

    public static ResObject error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static ResObject error(String message) {
        return error(500, message);
    }

    public static ResObject error(int code, String message) {
        ResObject r = new ResObject();
        r.put((String)"code", code);
        r.put((String)"message", message);
        return r;
    }

    public static ResObject ok(String message) {
        ResObject r = new ResObject();
        r.put((String)"message", message);
        return r;
    }

    public static ResObject ok(Map<String, Object> map) {
        ResObject r = new ResObject();
        r.putAll(map);
        return r;
    }

    public static ResObject ok() {
        return new ResObject();
    }

    public ResObject put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public ResObject data(Object value) {
        super.put("data", value);
        return this;
    }

    public static ResObject apiError(String message) {
        return error(1, message);
    }


}

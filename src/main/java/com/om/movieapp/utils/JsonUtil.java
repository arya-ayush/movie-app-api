package com.om.movieapp.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static final Gson mGson;


    static {
        mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    private void JsonUtil() {
        throw new UnsupportedOperationException("This class un-support create instance");
    }

    public static String translateToJson(Object object) {
        return mGson.toJson(object);
    }

    public static String toJson(Object o) {
        return translateToJson(o);
    }

    public static <T> T parserJson(String json, Class<T> cla) {
        if (StringUtils.isEmpty(json))
            return null;
        Gson gson = mGson;
        T t = null;
        try {

            t = gson.fromJson(json, cla);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> List<T> listJson(String json, Class<T> cla) {
        if (StringUtils.isEmpty(json))
            return null;
        List<T> list = new ArrayList<>();
        Gson gson = mGson;
        try {

            JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
            T t = null;
            for (JsonElement jsonElement : jsonArray) {
                t = gson.fromJson(jsonElement, cla);
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T> List<T> listParseAllJson(String json, Class<T> cla) {
        if (StringUtils.isEmpty(json))
            return null;
        List<T> list = null;
        try {
            list = JsonUtil.mGson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, Object> mapJson(String json) {
        if (StringUtils.isEmpty(json))
            return null;
        Map<String, Object> mMap = null;
        try {
            Gson mGson = JsonUtil.mGson;
            mMap = mGson.fromJson(json, new TypeToken<Map<String, Object>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mMap;
    }


    public static <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        if (StringUtils.isEmpty(json))
            return null;
        Gson mGson = JsonUtil.mGson;
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(mGson.fromJson(elem, cls));
        }
        return mList;
    }

    public static Gson getGson() {
        return JsonUtil.mGson;
    }
}
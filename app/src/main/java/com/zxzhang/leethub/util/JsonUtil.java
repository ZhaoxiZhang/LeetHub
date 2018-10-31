package com.zxzhang.leethub.util;


import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonUtil {
    public static String getJson(Context context, String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static <T> T generateObjectFromJson(String json, Class<T>type){
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
}

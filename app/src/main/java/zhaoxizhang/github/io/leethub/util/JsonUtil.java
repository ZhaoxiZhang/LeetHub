package zhaoxizhang.github.io.leethub.util;


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class JsonUtil {
    private static final String TAG = "JsonUtil";
    public static String getJson(Context context, String fileName){
        
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
                Log.d(TAG, "getJson: " + line);
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

    public static <T> List<T> generateObjectFromJsonArray(String json, Class<T[]> clazz)
    {
        Log.d(TAG, "generateObjectFromJsonArray: zyzhang");
        Gson gson = new Gson();
        T[] array = gson.fromJson(json, clazz);
        Log.d(TAG, "generateObjectFromJsonArray: after zyzhang");
        return Arrays.asList(array);
    }
}

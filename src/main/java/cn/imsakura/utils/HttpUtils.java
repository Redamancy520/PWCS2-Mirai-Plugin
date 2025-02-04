package cn.imsakura.utils;

import okhttp3.*;

import java.io.IOException;

public class HttpUtils {

    public static String post(Request request) throws IOException {
        String result = "";
        OkHttpClient client = new OkHttpClient();

        Call call = client.newCall(request);
        Response response = null;

        response=call.execute();
        result = response.body().string();
        return result;
    }

}

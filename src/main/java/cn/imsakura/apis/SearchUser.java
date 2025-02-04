package cn.imsakura.apis;

import cn.imsakura.Config;
import cn.imsakura.interfaces.PWApi;
import cn.imsakura.utils.HttpUtils;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SearchUser extends PWApi {
    public static String search(String name,String token) throws IOException {
        url="https://appengine.wmpvp.com/steamcn/app/search/user";

        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("keyword",name);
        map.put("page",1);
        String json = gson.toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("appversion", Config.INSTANCE.appversion)
                .addHeader("token",token)
                .build();
        return HttpUtils.post(request);
    }
}

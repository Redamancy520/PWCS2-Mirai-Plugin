package cn.imsakura.apis;

import cn.imsakura.interfaces.PWApi;
import cn.imsakura.utils.HttpUtils;
import com.google.gson.Gson;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Login extends PWApi {


    public static String login(String mobileNumber,String code) throws IOException {
        url="https://passport.pwesports.cn/account/login";
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("appId",2);
        map.put("mobilePhone",mobileNumber);
        map.put("securityCode",code);
        String json = gson.toJson(map);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return HttpUtils.post(request);
    }


}

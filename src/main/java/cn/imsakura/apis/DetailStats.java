package cn.imsakura.apis;

import cn.imsakura.Config;
import cn.imsakura.interfaces.PWApi;
import cn.imsakura.jsonData.detailStats.PlayerStats;
import cn.imsakura.utils.GraphUtils;
import cn.imsakura.utils.HttpUtils;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DetailStats extends PWApi {
    public static String get(String mySteamId,String toSteamId,String token) throws IOException {
        url="https://api.wmpvp.com/api/csgo/home/pvp/detailStats";

        Gson gson = new Gson();

        Map<String,Object> map = new HashMap<>();
        map.put("mySteamId",mySteamId);
        map.put("toSteamId",toSteamId);
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
    public static File generate(String mySteamId, String toSteamId, String token) throws IOException {
        String str = DetailStats.get(mySteamId,toSteamId,token);
        Gson gson = new Gson();
        PlayerStats playerStats = gson.fromJson(str, PlayerStats.class);
        Map<String, Object> map = new HashMap<>();
        System.out.println(playerStats.getData().getAvatar());
        map.put("RWS",playerStats.getData().getRws());
        map.put("Rating ",playerStats.getData().getCommonRating());
        map.put("KD",playerStats.getData().getKd());
        map.put("WE",playerStats.getData().getAvgWe());
        map.put("ADR",playerStats.getData().getAdr());
        map.put("胜率",playerStats.getData().getWinRate());
        map.put("赛季场次",playerStats.getData().getCnt());
        map.put("昵称",playerStats.getData().getName());
        return GraphUtils.draw(map,playerStats.getData().getAvatar());
    }


}

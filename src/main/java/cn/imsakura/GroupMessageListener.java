package cn.imsakura;

import cn.imsakura.apis.DetailStats;
import cn.imsakura.apis.SearchUser;
import cn.imsakura.jsonData.search.PwcsResponse;
import cn.imsakura.jsonData.search.PwcsUser;
import com.google.gson.Gson;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GroupMessageListener implements ListenerHost {
    @EventHandler
    public void onGroupMessage(GroupMessageEvent event) {
        MessageChain message = event.getMessage();
        String content = message.contentToString().trim();

        if (content.startsWith("/pwcs")) {
            // 解析指令
            String[] parts = content.split(" ", 2);
            if (parts.length != 2) {
                event.getGroup().sendMessage("喵？格式错了！请使用 /pwcs <name> 哦！");
                return;
            }

            String name = parts[1].trim();
            event.getGroup().sendMessage("喵喵！正在查询【" + name + "】的信息呢～");
            try {
                if (!PWCS.hasToken()) {
                    event.getGroup().sendMessage("[Error] - PWCS-Token为空!!!");
                } else {

                    if (!PWCS.hasMySteamId()) {
                        event.getGroup().sendMessage("[Error] - PWCS-MySteamId为空!!!");
                        return;
                    }

                    String js = SearchUser.search(name, Config.INSTANCE.token);
                    Gson gson = new Gson();
                    PwcsResponse pwcsResponse = gson.fromJson(js, PwcsResponse.class);
                    System.out.println(js);
                    if (pwcsResponse.getResult().size() < 1) {
                        event.getGroup().sendMessage("[Error] - 该玩家不存在1");
                    } else {
                        PwcsUser pwcsUser = pwcsResponse.getResult().get(0);
                        if (!Objects.equals(pwcsUser.getPvpNickName(), name)) {
                            event.getGroup().sendMessage("[Error] - 该玩家不存在2");
                        } else {
                            File file = DetailStats.generate(Config.INSTANCE.mySteamId, pwcsUser.getSteamId(), Config.INSTANCE.token);
                            ExternalResource resource = ExternalResource.create(file);
                            Image image = event.getGroup().uploadImage(resource);  // 上传图片
                            event.getGroup().sendMessage(image);  // 发送到群聊

                        }
                    }
                }

            } catch (IOException e) {
                event.getGroup().sendMessage("查询失败捏 作者真是杂鱼呢❤~~~");
                throw new RuntimeException(e);
            }

        }

    }
}

package cn.imsakura;

import cn.imsakura.apis.Login;
import cn.imsakura.jsonData.login.AccountInfo;
import cn.imsakura.jsonData.login.LoginResponse;
import com.google.gson.Gson;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.message.data.MessageChain;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivateMessageListener implements ListenerHost {

    @EventHandler
    public void onPrivateMessage(FriendMessageEvent event) {

        Friend sender = event.getSender();
        MessageChain message = event.getMessage();
        String content = message.contentToString();




        // **匹配 `/pwcs login <手机号> <验证码>` 命令**
        Pattern loginPattern = Pattern.compile("^/pwcs login (\\d{11}) (\\d{6})$");
        Matcher loginMatcher = loginPattern.matcher(content);

        if (loginMatcher.matches()&&event.getSender().getId()==Config.INSTANCE.adminQQ) {
            String phoneNumber = loginMatcher.group(1);
            String verificationCode = loginMatcher.group(2);

            try {
                Gson gson = new Gson();
                String json = Login.login(phoneNumber, verificationCode);
                LoginResponse response = gson.fromJson(json,LoginResponse.class);
                //登录成功
                if(response.code==0){
                   AccountInfo accountInfo = response.result.loginResult.accountInfo;
                    String msg = String.format("-->登录成功<--\r\n 用户ID:%d \r\n SteamId:%d \r\n Token: %s",accountInfo.userId,accountInfo.steamId,accountInfo.token);
                    event.getSender().sendMessage(msg);
                    Config.INSTANCE.token=accountInfo.token;
                    Config.INSTANCE.mySteamId=  String.valueOf(accountInfo.steamId);
                }
                //登录失败
                else{
                    event.getSender().sendMessage("[Error] - 登录失败,原因:{" + response.description+"}");
                }

            }catch (Exception ex){
                ex.printStackTrace();
                event.getSender().sendMessage("[Error] - 登录失败，发送POST请求错误");
                event.getSender().sendMessage(ex.getMessage());
            }


        }


        // 匹配 "/pwcs token <token>"
        Pattern tokenPattern = Pattern.compile("^/pwcs token (\\S+)$");
        Matcher tokenMatcher = tokenPattern.matcher(content);
        if (tokenMatcher.matches()&&event.getSender().getId()==Config.INSTANCE.adminQQ) {
            String token = tokenMatcher.group(1);
            Config.INSTANCE.token=token;
            sender.sendMessage("喵～你发送的 Token 是：" + token);
            return;
        }

        // 匹配 "/pwcs steamid <steamid>"
        Pattern steamIdPattern = Pattern.compile("^/pwcs steamid (\\S+)$");
        Matcher steamIdMatcher = steamIdPattern.matcher(content);
        if (steamIdMatcher.matches()&&event.getSender().getId()==Config.INSTANCE.adminQQ) {
            String steamId = steamIdMatcher.group(1);
            Config.INSTANCE.mySteamId=steamId;
            sender.sendMessage("喵～你发送的 Steam ID 是：" + steamId);
            return;
        }

    }
}

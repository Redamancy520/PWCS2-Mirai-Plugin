package cn.imsakura;

import cn.imsakura.apis.DetailStats;
import cn.imsakura.apis.SearchUser;
import cn.imsakura.jsonData.search.PwcsResponse;
import cn.imsakura.jsonData.search.PwcsUser;
import com.google.gson.Gson;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.MiraiConsole;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotOnlineEvent;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class PWCS extends JavaPlugin {
    public static final PWCS INSTANCE = new PWCS();



    public static boolean hasToken(){
        return Config.INSTANCE.token.length() > 6;
    }

    public static Bot bot;
    public static boolean hasMySteamId(){
        return Config.INSTANCE.mySteamId.length() > 6;
    }

    private PWCS() {
        super(new JvmPluginDescriptionBuilder("cn.imsakura.pwcs", "1.0.0")
                .name("PWCS")
                .author("1mSakura")
                .build());
    }

    @Override
    public void onEnable() {

        getLogger().info("喵喵！[PerfectWorld-CS2-Plugin] 开始加载了捏~~~");
        GlobalEventChannel.INSTANCE.subscribeAlways(BotOnlineEvent.class,botOnlineEvent -> {
            bot = botOnlineEvent.getBot();
            bot.getEventChannel().registerListenerHost(new PrivateMessageListener());
            bot.getEventChannel().registerListenerHost(new GroupMessageListener());
        });

        getLogger().info("喵喵！[PerfectWorld-CS2-Plugin] 加载完成啦~~~");

    }
}

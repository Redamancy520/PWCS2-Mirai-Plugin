package cn.imsakura;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotOnlineEvent;
import io.herry.Config;

public final class PWCS extends JavaPlugin {
    public static final PWCS INSTANCE = new PWCS();



    public static boolean hasToken(){
        return Config.INSTANCE.getToken().length() > 6;
    }

    public static Bot bot;
    public static boolean hasMySteamId(){
        return Config.INSTANCE.getMySteamId().length() > 6;
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

        // init config
        reloadPluginConfig(Config.INSTANCE);

        GlobalEventChannel.INSTANCE.subscribeAlways(BotOnlineEvent.class,botOnlineEvent -> {
            bot = botOnlineEvent.getBot();
            bot.getEventChannel().registerListenerHost(new PrivateMessageListener());
            bot.getEventChannel().registerListenerHost(new GroupMessageListener());
        });

        getLogger().info("喵喵！[PerfectWorld-CS2-Plugin] 加载完成啦~~~");

    }
}

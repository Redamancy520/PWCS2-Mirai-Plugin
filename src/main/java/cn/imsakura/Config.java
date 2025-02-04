package cn.imsakura;

import net.mamoe.mirai.console.data.AutoSavePluginConfig;
import net.mamoe.mirai.console.data.ValueName;
import org.jetbrains.annotations.NotNull;

public class Config extends AutoSavePluginConfig {

    public static final Config INSTANCE = new Config();

    public Config() {
        super("config");
    }

    public long adminQQ = 11451455;
    public String token = "";
    public String mySteamId = "";
    public String appversion = "3.5.4.172";

}

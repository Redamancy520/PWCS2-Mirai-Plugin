package io.herry

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("config") {

    val adminQQ: Long by value()
    val token: String by value()
    val mySteamId: String by value()
    val appVersion: String by value("3.5.4.172")

    /*
    调用的话
    Java:
        Config.INSTANCE.get[value]()

     对于Data数据，同样创建一个object继承AutoSavePluginData(saveName)
     在插件的onEnable下，初始化则写:
        reloadPluginData(Data.INSTANCE)
        即可
     */

}
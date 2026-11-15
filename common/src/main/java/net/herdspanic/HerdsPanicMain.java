package net.herdspanic;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.herdspanic.config.HerdsPanicConfig;

public class HerdsPanicMain {

    public static final String MOD_ID = "herdspanic";

    public static HerdsPanicConfig CONFIG = new HerdsPanicConfig();

    public static void init() {
        AutoConfig.register(HerdsPanicConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(HerdsPanicConfig.class).getConfig();
    }

}

package net.herdspanic;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.herdspanic.config.HerdsPanicConfig;

public class HerdsPanicMain implements ModInitializer {

    public static HerdsPanicConfig CONFIG = new HerdsPanicConfig();

    @Override
    public void onInitialize() {
        AutoConfig.register(HerdsPanicConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(HerdsPanicConfig.class).getConfig();
    }

}

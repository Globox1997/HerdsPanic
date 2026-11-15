package net.herdspanic.fabric;

import net.fabricmc.api.ModInitializer;
import net.herdspanic.HerdsPanicMain;

public class HerdsPanicFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        HerdsPanicMain.init();
    }

}

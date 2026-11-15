package net.herdspanic.neoforge.client;

import me.shedaniel.autoconfig.AutoConfigClient;
import net.herdspanic.HerdsPanicMain;
import net.herdspanic.config.HerdsPanicConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = HerdsPanicMain.MOD_ID, dist = Dist.CLIENT)
public class HerdsPanicNeoForgeClient {

    public HerdsPanicNeoForgeClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class,
                (modContainer, parent) -> AutoConfigClient.getConfigScreen(HerdsPanicConfig.class, parent).get());
    }

}

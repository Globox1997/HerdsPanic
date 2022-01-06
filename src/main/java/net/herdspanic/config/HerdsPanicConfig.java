package net.herdspanic.config;

import java.util.ArrayList;
import java.util.List;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "herdspanic")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class HerdsPanicConfig implements ConfigData {

    public boolean herd_panic = true;
    @Comment("Animals will seek shelter when it is raining")
    public boolean shelter_seeking = false;
    @Comment("List will exclude entities from panicing and sheltering - modid:mobname")
    public List<String> excluded_entities = new ArrayList<>();
    @Comment("in ticks")
    public int panic_time = 100;
    public float panic_distance = 16.0F;

}
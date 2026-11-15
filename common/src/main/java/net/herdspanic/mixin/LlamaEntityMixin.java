package net.herdspanic.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.equine.AbstractChestedHorse;
import net.minecraft.world.entity.animal.equine.Llama;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Llama.class)
public abstract class LlamaEntityMixin extends AbstractChestedHorse {

    public LlamaEntityMixin(EntityType<? extends AbstractChestedHorse> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "registerGoals", at = @At("HEAD"))
    protected void initGoalsMixin(CallbackInfo info) {
        super.registerGoals();
    }

}

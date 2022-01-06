package net.herdspanic.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.herdspanic.HerdsPanicMain;
import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @ModifyConstant(method = "baseTick", constant = @Constant(intValue = 100))
    private int baseTickMixin(int original) {
        return HerdsPanicMain.CONFIG.panic_time;
    }
}

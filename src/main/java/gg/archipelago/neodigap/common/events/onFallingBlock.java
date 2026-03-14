package gg.archipelago.neodigap.common.events;

import gg.archipelago.neodigap.APRandomizer;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@EventBusSubscriber(modid = APRandomizer.MODID)
public class onFallingBlock {

    // Bedrock platform boundaries
    private static final int PLATFORM_MIN_X = -5;
    private static final int PLATFORM_MAX_X = -1;
    private static final int PLATFORM_MIN_Z = -5;
    private static final int PLATFORM_MAX_Z = -1;
    private static final int PLATFORM_Y = 128;

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        // Only care about falling blocks
        if (!(event.getEntity() instanceof FallingBlockEntity fallingBlock)) {
            return;
        }

        // Only in overworld
        if (!event.getLevel().dimension().equals(Level.OVERWORLD)) {
            return;
        }

        // Check if the falling block is above or on the bedrock platform
        double x = fallingBlock.getX();
        double z = fallingBlock.getZ();

        if (x >= PLATFORM_MIN_X && x <= PLATFORM_MAX_X + 1 &&
            z >= PLATFORM_MIN_Z && z <= PLATFORM_MAX_Z + 1) {

            // Cancel the falling block entity - it will just disappear
            event.setCanceled(true);

            APRandomizer.LOGGER.debug("Prevented falling block from landing on bedrock platform at ({}, {}, {})",
                (int) x, (int) fallingBlock.getY(), (int) z);
        }
    }
}

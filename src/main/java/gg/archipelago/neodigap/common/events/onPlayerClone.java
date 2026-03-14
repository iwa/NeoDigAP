package gg.archipelago.neodigap.common.events;

import gg.archipelago.neodigap.APRandomizer;
import net.minecraft.core.BlockPos;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber(modid = APRandomizer.MODID)
public class onPlayerClone {

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.PlayerRespawnEvent event) {

        BlockPos pos = event.getEntity().getLevel().getSharedSpawnPos();
        event.getEntity().teleportTo(pos.getX(), pos.getY(), pos.getZ());

    }
}

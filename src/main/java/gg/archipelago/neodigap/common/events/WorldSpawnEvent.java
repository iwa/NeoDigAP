package gg.archipelago.neodigap.common.events;

import gg.archipelago.neodigap.APRandomizer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@EventBusSubscriber(modid = APRandomizer.MODID)
public class WorldSpawnEvent {

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent e) {
        Player player = e.getEntity();
        if (player.level.isClientSide) {
            return;
        }

        Level level = player.level;
        BlockPos spawn = level.getSharedSpawnPos();
        player.teleportTo(spawn.getX(),spawn.getY(),spawn.getZ());
    }
}

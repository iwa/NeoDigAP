package gg.archipelago.neodigap.managers.itemmanager.traps;

import gg.archipelago.neodigap.APRandomizer;
import gg.archipelago.neodigap.common.utils.Utils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.phys.Vec3;

public class FishFountainTrap implements Trap {

    @Override
    public void trigger(ServerPlayer player) {
        APRandomizer.getServer().execute(() -> {
            ServerLevel world = player.getLevel();
            Vec3 pos = player.position();
            for (int i = 0; i < 10; i++) {
                Silverfish fish = EntityType.SILVERFISH.create(world);
                if (fish == null)
                    continue;
                Vec3 offset = Utils.getRandomPosition(pos, 5);
                fish.moveTo(offset);
                world.addFreshEntity(fish);
            }
        });
    }
}

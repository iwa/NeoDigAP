package gg.archipelago.neodigap.managers.itemmanager.traps;

import gg.archipelago.neodigap.APRandomizer;
import gg.archipelago.neodigap.common.utils.Utils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.phys.Vec3;

public class BeeTrap implements Trap {

    final private int numberOfBees;

    public BeeTrap(int numberOfBees) {
        this.numberOfBees = numberOfBees;
    }

    public BeeTrap() {
        this(3);
    }

    @Override
    public void trigger(ServerPlayer player) {
        APRandomizer.getServer().execute(() -> {
            ServerLevel world = player.getLevel();
            Vec3 pos = player.position();
            for (int i = 0; i < numberOfBees; i++) {
                Bee bee = EntityType.BEE.create(world);
                Vec3 offset = Utils.getRandomPosition(pos, 5);
                bee.moveTo(offset);
                bee.setPersistentAngerTarget(player.getUUID());
                bee.setRemainingPersistentAngerTime(1200);
                world.addFreshEntity(bee);
            }

        });
    }
}

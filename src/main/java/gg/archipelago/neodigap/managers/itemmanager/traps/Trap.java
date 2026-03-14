package gg.archipelago.neodigap.managers.itemmanager.traps;

import net.minecraft.server.level.ServerPlayer;

public interface Trap {

    void trigger(ServerPlayer player);
}
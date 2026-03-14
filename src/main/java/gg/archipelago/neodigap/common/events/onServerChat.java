package gg.archipelago.neodigap.common.events;

import gg.archipelago.neodigap.APRandomizer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber(modid = APRandomizer.MODID)
public class onServerChat {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    static void onServerChatEvent(ServerChatEvent event) {
        if(!APRandomizer.getAP().isConnected())
            return;
        ServerPlayer player = event.getPlayer();

        String message = event.getMessage().getString();

        if (message.startsWith("!"))
            APRandomizer.getAP().sendChat(message);
        else
            APRandomizer.getAP().sendChat("(" + player.getDisplayName().getString() + ") " + message);
    }
}

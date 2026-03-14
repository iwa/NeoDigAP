package gg.archipelago.neodigap.common.utils;

import gg.archipelago.neodigap.APRandomizer;
import net.minecraft.server.MinecraftServer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.LinkedList;
import java.util.List;

@EventBusSubscriber(modid = APRandomizer.MODID)
public class TitleQueue {

    static List<QueuedTitle> titleQueue = new LinkedList<>();

    static int titleTime;

    final private static MinecraftServer server = APRandomizer.getServer();

    @SubscribeEvent
    static public void ServerTick(TickEvent.ServerTickEvent tick) {
        if (tick.phase == TickEvent.Phase.END) {
            if (!titleQueue.isEmpty()) {
                if (titleTime <= 0) {
                    QueuedTitle title = titleQueue.get(0);
                    titleQueue.remove(0);
                    titleTime = title.getTicks();
                    title.sendTitle();
                }
            }
            if (titleTime > 0) {
                titleTime -= 1;
            }
        }
    }

    public static void queueTitle(QueuedTitle queuedTitle) {
        titleQueue.add(queuedTitle);
    }
}

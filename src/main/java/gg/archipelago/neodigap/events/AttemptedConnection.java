package gg.archipelago.neodigap.events;

import gg.archipelago.neodigap.APRandomizer;
import gg.archipelago.neodigap.APStorage.APMCData;
import gg.archipelago.neodigap.SlotData;
import gg.archipelago.neodigap.common.Utils.Utils;
import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ConnectionAttemptEvent;

public class AttemptedConnection {

    @ArchipelagoEventListener
    static public void onAttemptConnect(ConnectionAttemptEvent event) {
        try {
            SlotData temp = event.getSlotData(SlotData.class);
            APMCData data = APRandomizer.getApmcData();
            if (!event.getSeedName().equals(data.seed_name)) {
                Utils.sendMessageToAll("Wrong .apmc file found. please stop the server, use the correct .apmc file, delete the world folder, then relaunch the server.");
                event.setCanceled(true);
            }
            if (!APRandomizer.getValidVersions().contains(temp.getClient_version())) {
                event.setCanceled(true);
                Utils.sendMessageToAll("Game was generated with an for an incompatible version of the Minecraft Randomizer.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

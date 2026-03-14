package gg.archipelago.neodigap.events;

import gg.archipelago.neodigap.APClient;
import gg.archipelago.neodigap.common.utils.Utils;
import io.github.archipelagomw.Print.APPrint;
import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.PrintJSONEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrintJson {

    private static final Logger LOGGER = LogManager.getLogger();
    APClient client;

    public PrintJson(APClient apClient) {
        client = apClient;
    }

    @ArchipelagoEventListener
    public void onPrintJson(PrintJSONEvent event) {
        APPrint apPrint = event.apPrint;
        if(apPrint.parts.length > 0) {
            if (!apPrint.parts[0].text.startsWith(client.getAlias() + ":")) {
                Utils.sendFancyMessageToAll(apPrint);
            }
        }
    }
}

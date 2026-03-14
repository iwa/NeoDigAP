package gg.archipelago.neodigap.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import gg.archipelago.neodigap.APRandomizer;
import gg.archipelago.neodigap.common.utils.Utils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber(modid = APRandomizer.MODID)
public class SyncCommand {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    //build our command structure and submit it
    public static void Register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("sync") //base slash command is "sync"
                        .executes(SyncCommand::sync)

        );

    }


    private static int sync(CommandContext<CommandSourceStack> source) {
        if (APRandomizer.getAP().isConnected()) {
            Utils.sendMessageToAll("Re-syncing progress with Archipelago server.");
            APRandomizer.getAP().sync();
            return 1;
        }
        return 0;
    }

    //wait for register commands event then register ourself as a command.
    @SubscribeEvent
    static void onRegisterCommandsEvent(RegisterCommandsEvent event) {
        SyncCommand.Register(event.getDispatcher());
    }
}
